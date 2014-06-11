import dk.silverbullet.kih.api.auditlog.SkipAuditLog
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.context.ApplicationContext

import java.util.concurrent.atomic.AtomicLong
import dk.silverbullet.kih.api.auditlog.AuditLog
import dk.silverbullet.kih.api.auditlog.AuditLogFactory
import org.springframework.security.core.context.SecurityContextHolder

class AuditLogFilters {

    private static final AtomicLong REQUEST_NUMBER_COUNTER = new AtomicLong()
    private static final String START_TIME_ATTRIBUTE = 'Controller__START_TIME__'
    private static final String REQUEST_NUMBER_ATTRIBUTE = 'Controller__REQUEST_NUMBER__'

    private static final String AUDIT_ID = "AUDIT_TRAIL_ID__"

    private AuditLog auditLog

    // Inject spring security
    def springSecurityService

    def filters = {
        if (!auditLog) {
            auditLog = AuditLogFactory.createAuditLogging()
        }

        all(controller:'*', action:'*') {
            before = {
                def user = SecurityContextHolder.context.authentication?.name
                long start = System.currentTimeMillis()
                long currentRequestNumber = REQUEST_NUMBER_COUNTER.incrementAndGet()

                if (shouldAuditLog(grailsApplication, controllerName, actionName))
                {
                    def auditId = auditLog.startRequest(session, request, user, controllerName, actionName)
                    request[AUDIT_ID] = auditId
                }

                request[START_TIME_ATTRIBUTE] = start
                request[REQUEST_NUMBER_ATTRIBUTE] = currentRequestNumber
                return true
           }

            after = { Map model ->

            }

            afterView = { Exception e ->
                def user = SecurityContextHolder.context.authentication?.name
                REQUEST_NUMBER_COUNTER.incrementAndGet()

                long start = request[START_TIME_ATTRIBUTE]
                long end = System.currentTimeMillis()
                long requestNumber = request[REQUEST_NUMBER_ATTRIBUTE]

                def auditId = request[AUDIT_ID]

                def msg = "User: $user completed request #$requestNumber: " +
                        "start ${new Date(start)} end ${new Date()}, total time ${end - start}ms. " +
                        "'$request.servletPath'/'$request.forwardURI', " +
                        "from $request.remoteHost ($request.remoteAddr) " +
                        " at ${new Date(start)}, Ajax: $request.xhr, controller: $controllerName, " +
                        "action: $actionName"
                if (e) {
                    log.debug "$msg \n\texception: $e.message", e
                    if (shouldAuditLog(grailsApplication, controllerName, actionName)) {
                        auditLog.endRequest(session, response, auditId, false, params, e)
                    }
                } else {
                    if (shouldAuditLog(grailsApplication, controllerName, actionName)) {
                        auditLog.endRequest(session, response, auditId, true, params)
                    }
                    log.debug msg
                }
            }
        }
    }

    private boolean shouldAuditLog(GrailsApplication grailsApplication, String controllerName, String actionName) {
        //NOTE: could probably be optimized a bit, see http://burtbeckwith.com/blog/?p=80
        def controllerClass = grailsApplication.controllerClasses.find {it.logicalPropertyName == controllerName}
        def skipController = controllerClass.clazz.getAnnotation(SkipAuditLog) != null
        def calculatedActionName = actionName ?: controllerClass.defaultActionName
        Closure skipAction = {
            def isActionWithNameAndAnnotation = { action, name -> action.name == name && action.getAnnotation(SkipAuditLog) }
            def methods = controllerClass.clazz.declaredMethods
            methods.any { method -> isActionWithNameAndAnnotation.call(method, calculatedActionName) }
        }

        def skip = skipController || skipAction.call()
        !skip
    }
}
