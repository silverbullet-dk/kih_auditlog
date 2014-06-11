package dk.silverbullet.kih.api.auditlog.impl

import dk.silverbullet.kih.api.auditlog.AuditLog
import dk.silverbullet.kih.api.auditlog.exception.AuditLogException
import dk.silverbullet.kih.api.auditlog.util.AuditLogUtil
import dk.silverbullet.kih.auditlog.AuditLogControllerEntity
import dk.silverbullet.kih.auditlog.AuditLogEntry
import dk.silverbullet.kih.auditlog.AuditLogParameter
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

class AuditLogImpl implements AuditLog {
    def log = LoggerFactory.getLogger(AuditLog.class)

    @Override
    Long startRequest(HttpSession session, HttpServletRequest request,String user, String controller, String action) {
        String retVal // Extract somekind of message id.
        def id
        def loggedController

        loggedController = AuditLogControllerEntity.findByControllerNameAndActionName(controller,action)
        AuditLogControllerEntity.withTransaction {
            if (!loggedController) {
                loggedController = new AuditLogControllerEntity(controllerName: (controller ? controller : "unknownController" ) , actionName: (action?action:"index"), numberOfCalls: 1)
                loggedController.actionName = (action ? action : "index")
                if (!loggedController.save()) {
                    log.error "Could not store entity " + loggedController.errors
                    throw new AuditLogException("Could not create audit trail")
                }
            }
        }

        AuditLogEntry.withTransaction {
            AuditLogEntry entry = new AuditLogEntry(authority: (user? user: "unknown"), startTime: System.currentTimeMillis())
            entry.startDate = new Date(entry.startTime)
            entry.action = loggedController
            entry.callingIp = AuditLogUtil.extractIpFromRequest(request)
            entry.userAgent = request.getHeader('User-Agent')

            if (!entry.save()) {
                log.error "Errors: " + entry.errors
                throw new AuditLogException("Could not create audit trail")
            }

            id = entry.id
        }


        log.debug "Returning: " + id
        return id
    }


    void endRequest(HttpSession session, HttpServletResponse response, Long id, boolean success, Map parameters, Exception exception) throws AuditLogException {
        AuditLogEntry.withNewTransaction {

            AuditLogEntry entry = AuditLogEntry.get(id)

            if (entry) {

                def cpr = session.cpr

                if (cpr) {
                    cpr.replaceAll("-","")
                    cpr.replaceAll(" ","")

                    entry.patientCpr = cpr
                }
                if (parameters) {

                    parameters.each() { k,v ->
                        String value = v.toString()
                        if (value.length() > 256 ) {
                            value = value.substring(0,255)
                        }

                        AuditLogParameter p = new AuditLogParameter(key: k.toString(), value: value)
                        if (!p.save()) {
                            log.error "Errors: " + p.errors
                            throw new AuditLogException("Could not store parameters key: " + k.toString() + " value: " + v.toString())
                        }
                        entry.addToParameters(p)
                    }
                }

                Long endTime = System.currentTimeMillis()
                entry.endDate = new Date(endTime)
                entry.endTime = endTime
                entry.duration = entry.endTime - entry.startTime

                if (exception) {
                    entry.exceptionMessage = exception.message
                    entry.exception = true
                    entry.success = false
                } else {
                    entry.success = true
                    entry.exception = false
                }

                if (!entry.save(flush: true)) {
                    log.error "Errors: " + entry.errors
                    throw new AuditLogException("Could not finalize audit log")
                }
            } else {
                log.error "Problem in audit log. No start request recieved"
            }
        }
    }

    void endRequest(HttpSession session, HttpServletResponse response, Long id, boolean success, Map parameters) throws AuditLogException {
        endRequest(session, response,id,success,parameters, null)
    }


    @Override
    void logServiceRequest(Long startTime, String cpr, String issuer, String idcard, String callingIP, String url, String operation, String userAgent) throws AuditLogException {
        long endTime = System.currentTimeMillis()
        AuditLogEntry.withNewTransaction {

            AuditLogEntry a = new AuditLogEntry()
            a.startTime = startTime.longValue()
            a.endTime = endTime
            a.duration = endTime - a.startTime
            a.service = url
            a.patientCpr = cpr
            a.authority = issuer
            a.idCard = idcard
            a.operation = operation
            a.callingIp = callingIP
            a.userAgent = userAgent

            if (!a.save()) {
                throw new AuditLogException("Could not store audit log")
            }
        }
    }
}
