package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.model.AbstractObject 


class AuditLogParameter extends AbstractObject {
    
    AuditLogEntry entry
    
    String parameterKey
    String parameterValue
    
    static constraints = {
        entry(nullable: true) // Should be false
        parameterKey(nullable: true)
        parameterValue(nullable: true)
    }
}
