package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.model.AbstractObject

/**
 * Simple domain class for handling audit log of the KIH systems.
 */
class AuditLogEntry extends AbstractObject {

    static hasMany = [parameters:AuditLogParameter]

    String messageId // Message id. which could be used for correlation if needed
    String correlationId // Correlation ID if ever needed.
    String httpSessionId
    String authority
    String patientCpr
    String service
    AuditLogControllerEntity action
    String result
    String callingIp
    boolean success
    boolean exception
    Long startTime
    Date startDate
    Long endTime
    Date endDate
    Long duration
    String request
    String response
    String exceptionMessage
    String idCard
    String operation
    String userAgent


    static constraints = {
        messageId(nullable: true)
        correlationId(nullable: true)
        httpSessionId(nullable: true)
        patientCpr(nullable: true)
        authority(nullable: true)
        service(nullable: true)
        action(nullable: true)
        result(nullable: true)
        success(nullable: true)
        exception(nullable: true)
        startTime(nullable: true)
        startDate(nullable: true)
        endTime(nullable: true)
        endDate(nullable: true)
        duration(nullable: true)
        request(nullable:  true)
        response(nullable:  true)
        exceptionMessage(nullable: true)
        parameters(nullable: true)
        callingIp(nullable: true)
        idCard(nullable: true)
        operation(nullable: true)
        userAgent(nullable: true)
    }

    static mapping = {
        patientCpr index: 'patient_cpr_idx'
        startTime index: 'start_time_idx'
        endTime index: 'end_time_idx'
    }

    @Override
    public String toString() {
        return """User: $authority - ($endTime - $startTime) -> : $duration"""
    }
}
