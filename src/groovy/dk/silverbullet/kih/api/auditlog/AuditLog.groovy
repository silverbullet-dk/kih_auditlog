package dk.silverbullet.kih.api.auditlog

import dk.silverbullet.kih.api.auditlog.exception.AuditLogException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * Interface for handling audit log
 */
interface AuditLog {
    public Long startRequest(HttpSession session, HttpServletRequest request, String user, String controller, String action) throws AuditLogException
    public void endRequest(HttpSession session, HttpServletResponse response, Long auditLogId, boolean success, Map parameters) throws AuditLogException
    public void endRequest(HttpSession session, HttpServletResponse response, Long auditLogId, boolean success, Map parameters, Exception exception) throws AuditLogException
    public void logServiceRequest(Long startTime, String cpr, String issuer, String idcard, String callingIP, String url, String operation, String userAgent) throws AuditLogException

}
