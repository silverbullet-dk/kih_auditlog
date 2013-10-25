<table class="table table-hover">
    <thead>
    <tr>
        <th>
        ${message(code: 'auditLog.entry.callingIp.label', default: 'IP adresse')}
        </th>
        <th>
        ${message(code: 'auditLogEntry.authority.label', default: 'Udført af')}
        </th>
        <th>

        ${message(code: 'auditLogEntry.startTime.label', default: 'Dato')}
        </th>
        <th>

        ${message(code: 'auditLogEntry.cpr.label', default: 'CPR')}
        </th>
        <th>

        ${message(code: 'auditLogEntry.action.label', default: 'Handling')}
        </th>
        <th>

        ${message(code: 'auditLogEntry.duration.label', default: 'Tid (ms)')}
        </th>
        <th>
        ${message(code: 'auditLogEntry.exception.label', default: 'Fejl')}
        </th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${auditLogEntryInstanceList}" status="i" var="auditLogEntryInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td>
            <g:if test="auditLogEntryInstance?.callingIp">
                ${fieldValue(bean: auditLogEntryInstance, field: "callingIp")}
            </g:if>
        </td>

            <td>${fieldValue(bean: auditLogEntryInstance, field: "authority")}</td>

            <td><g:link action="show" id="${auditLogEntryInstance.id}"><g:formatDate formatName="default.date.format.withseconds" date="${new java.util.Date(auditLogEntryInstance.startTime)}"/> </g:link></td>

            <td>${fieldValue(bean: auditLogEntryInstance, field: "patientCpr")}</td>


            <td>
                <g:if test="${auditLogEntryInstance.action}">

                <at:lookupDescription action="${auditLogEntryInstance.action}"/>

                </g:if>
                <g:else>
                    ${fieldValue(bean: auditLogEntryInstance, field: "service")} / ${fieldValue(bean: auditLogEntryInstance, field: "operation")}
                </g:else>

            </td>

            <td><g:formatNumber number="${auditLogEntryInstance.duration}" format="#####"/> ms </td>

            <td>
                <g:formatBoolean boolean="${auditLogEntryInstance.exception}" true="${message(code: "default.yesno.yes", default: "Ja")}" false="${message(code: "default.yesno.no", default: 'Nej')}"/></td>

        </tr>
    </g:each>
    </tbody>
</table>
