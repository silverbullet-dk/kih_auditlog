package dk.silverbullet.kih.api.auditlog

import dk.silverbullet.kih.api.auditlog.impl.AuditLogImpl
import org.slf4j.LoggerFactory
import org.slf4j.Logger

class AuditLogFactory {
    private static Logger log = LoggerFactory.getLogger(AuditLogFactory.class)
    private static Map<Long,AuditLog> listOfAuditLogs

    static AuditLog createAuditLogging() {
        // Make something smart, which holds an instance per thread.

        def threadId = Thread.currentThread().id
        def auditLog

        if (!listOfAuditLogs) {
            log.debug  "Instantaiting Map"
            listOfAuditLogs = new TreeMap<String, AuditLog>()
        }

        if (listOfAuditLogs.containsKey(threadId)) {
            log.debug "Map contains " + listOfAuditLogs.keySet().size() + " number of entries"
            auditLog = listOfAuditLogs.get(threadId)
        } else {
            log.debug "Creating new audit log"
            auditLog = new AuditLogImpl()
            log.debug "Adding audit to stack key: " + threadId + " logger: " + auditLog
            listOfAuditLogs.put(threadId,auditLog)
        }

        log.debug "Returning auditlog " + auditLog
        return auditLog
    }
}
