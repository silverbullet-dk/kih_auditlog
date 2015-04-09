package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.AuditLogPermissionName
import dk.silverbullet.kih.api.auditlog.SkipAuditLog
import grails.plugin.springsecurity.annotation.Secured

@SkipAuditLog
@Secured([AuditLogPermissionName.AUDITLOG_NONE])
class AuditLogEntryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured([AuditLogPermissionName.AUDITLOG_READ])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured([AuditLogPermissionName.AUDITLOG_READ])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.order = "desc"
        params.sort = "id"


        def controllers = AuditLogControllerEntity.executeQuery("SELECT DISTINCT a.controllerName FROM AuditLogControllerEntity as a")
        def actions = AuditLogControllerEntity.executeQuery("SELECT DISTINCT a.actionName FROM AuditLogControllerEntity as a")

        [auditLogEntryInstanceList: AuditLogEntry.list(params), auditLogEntryInstanceTotal: AuditLogEntry.count(), controllers:controllers, actions: actions]
    }

    @Secured([AuditLogPermissionName.AUDITLOG_READ])
    def show(Long id) {
        def auditLogEntryInstance = AuditLogEntry.get(id)
        if (!auditLogEntryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditLogEntry.label', default: 'AuditLogEntry'), id])
            redirect(action: "list")
            return
        }

        [auditLogEntryInstance: auditLogEntryInstance]
    }


    @Secured([AuditLogPermissionName.AUDITLOG_READ])
    def ajaxGetActions() {
        log.debug "Params: " + params

        def actions = AuditLogControllerEntity.executeQuery("""SELECT DISTINCT a.actionName from AuditLogControllerEntity as a where a.controllerName='${params.c}' """)

        render(template: "auditLogActions", model: [actions:actions])
//        render actions as JSON
    }

    private def likeExpression(def value) {
        return """%${value}%"""
    }

    private def createDateFromParams(def year, def month, def day, def hour, def minute) {
        log.debug """Year: ${year} - ${month} - ${day} - ${hour} - ${minute} """

        def cal

        if ( year && month && day && hour && minute) {
            cal = new GregorianCalendar()
            cal.set(Calendar.YEAR,Integer.parseInt(year))
            cal.set(Calendar.MONTH,Integer.parseInt(month) - 1 )
            cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day) )
            cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour) )
            cal.set(Calendar.MINUTE,Integer.parseInt(minute) )
        }

        log.debug "Returning: " + cal?.getTime()
        return cal?.getTime()
    }


    @Secured([AuditLogPermissionName.AUDITLOG_READ])
    def search() {
        // Extract search criteria
        log.debug "Starting search: "+ params


        def ssn = params.ssn
        def patientId = params.patientId
        def action = params.usedAction // need to avoid grails variables
        def controller = params.usedController // need to avoid grails variables

        def offset = params.offset

        Date fromDate = createDateFromParams(params.fromDate_year,params.fromDate_month,params.fromDate_day,params.fromDate_hour,params.fromDate_minute)
        Date toDate = createDateFromParams(params.toDate_year,params.toDate_month,params.toDate_day,params.toDate_hour,params.toDate_minute)
        def exceptionOccurred = Boolean.FALSE

        if  (params.exceptionOccurred) {
            exceptionOccurred = Boolean.TRUE
        }

        log.debug """Searching for ssn: ${ssn} patient id: ${patientId} action: ${action} from: ${fromDate} to ${toDate} eception occured?: ${exceptionOccurred}  """

        def query = "from AuditLogEntry as a, AuditLogControllerEntity as ae where a.action = ae.id and "
        def criteria = [:]

        if (!fromDate) {
            def cal = new GregorianCalendar()
            cal.add(Calendar.WEEK_OF_YEAR,-1)
            fromDate = cal.getTime()
            log.debug "Setting from date " + fromDate
        }

        if (fromDate) {
            query += "a.startTime > :fromDate "
            criteria.put("fromDate",fromDate.time)
        }

        if (toDate) {
            query += "and a.endTime < :toDate "
            criteria.put("toDate",toDate.time)
        }

        if (ssn) {
            ssn.replaceAll("-","")
            ssn.replaceAll(" ","")

            if (((String)ssn).length() > 6) {
                def longerSsn = ssn[0..5] + "-" + ssn[6..((String)ssn).length()-1]
                query += "and (a.patientCpr like :ssn or a.patientCpr like :longerSsn) "
                criteria.put("ssn",likeExpression(ssn))
                criteria.put("longerSsn",likeExpression(longerSsn))
            } else {
                // Good case
                query += "and a.patientCpr like :ssn "
                criteria.put("ssn",likeExpression(ssn))
            }
        }

        if (patientId) {
            query += "and a.patientId like :patientId "
            criteria.put("patientId",likeExpression(patientId))
        }

        if (action) {
            query += "and ae.actionName like :action "
            criteria.put("action", likeExpression(action))
        }
        if (controller) {
            query += "and ae.controllerName like :controller "
            criteria.put("controller", likeExpression(controller))
        }

        if (action||controller) {
            query += "and a.action = ae.id"
        }

        if (exceptionOccurred) {
            query += "and exception = :exceptionOccurred"
            criteria.put("exceptionOccurred", exceptionOccurred)
        }

        def queryOptions = [max:10]
        if (offset) {
            queryOptions["offset"] = offset
        }

        log.debug "Executing: " + query + " with criteria: " + criteria


        def totalCount = AuditLogEntry.findAll(query,criteria).size()
        def list = AuditLogEntry.findAll(query,criteria,queryOptions)

        def entries = []

        // To keep views happy4
        for (item in list) {
            entries << item[0]
        }

        def controllers = AuditLogControllerEntity.executeQuery("SELECT DISTINCT a.controllerName FROM AuditLogControllerEntity as a")
        def actions = AuditLogControllerEntity.executeQuery("SELECT DISTINCT a.actionName FROM AuditLogControllerEntity as a")

        [auditLogEntryInstanceList: entries, auditLogEntryInstanceTotal: totalCount
                ,toDate:toDate,fromDate:fromDate,
                    ssn:ssn,patientId:patientId,
                exceptionOccurred:exceptionOccurred,
                choosenAction:action, controllers: controllers, choosenController:controller,actions:actions]
    }
}
