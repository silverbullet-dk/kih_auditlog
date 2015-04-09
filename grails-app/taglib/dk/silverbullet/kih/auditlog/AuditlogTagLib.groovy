package dk.silverbullet.kih.auditlog

class AuditlogTagLib {

    def auditLogLookupBean

    static namespace = "at"

    def lookup

    def lookupDescription = { attrs ->

        if (!lookup) {
            lookup = auditLogLookupBean?.retrieve()
        }

        AuditLogControllerEntity action = attrs?.action

        def description

        if (lookup) {
            if (lookup.containsKey(action.controllerName)) {
                if (lookup[action.controllerName].containsKey(action.actionName)) {
                    description = lookup[action.controllerName][action.actionName]
                    if (description instanceof Closure) {
                        description = description(g)
                    }
                }
            }
        }


        if (!description) {
            description = g.message(code: new String("auditlog.entry.default.${action.actionName}"), default:action.actionName) + " " + action.controllerName
        }

        out << description
    }
}
