package dk.silverbullet.kih.api.auditlog.model

import org.springframework.security.core.context.SecurityContextHolder

abstract class AbstractObject {

    Date createdDate
    Date modifiedDate
    String createdBy
    String modifiedBy


    static constraints = {
        createdDate nullable: true
        createdBy nullable:true
        modifiedBy nullable: true
        modifiedDate nullable: true
    }

    def beforeUpdate() {
        this.modifiedBy = SecurityContextHolder.context.authentication?.name ?: "Unknown"
        this.modifiedDate = new Date()
    }

    def beforeInsert() {
        Date now = new Date()
        this.createdDate = now
        this.createdBy = SecurityContextHolder.context.authentication?.name ?: "Unknown"
        this.modifiedBy = createdBy
        this.modifiedDate = now
    }

}
