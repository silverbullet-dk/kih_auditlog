package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.model.AbstractObject

class AuditLogControllerEntity extends AbstractObject {

    String controllerName
    String actionName
    Integer numberOfCalls

    static constraints = {
        controllerName(nullable: false)
        actionName(nullable: false)
        numberOfCalls(nullable: false)
    }
}
