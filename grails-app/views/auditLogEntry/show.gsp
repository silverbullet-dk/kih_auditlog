
<%@ page import="dk.silverbullet.kih.auditlog.AuditLogEntry" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'auditLogEntry.label', default: 'AuditLogEntry')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div id="show-auditLogEntry" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list auditLogEntry">

        <g:if test="${auditLogEntryInstance?.action}">
            <li class="fieldcontain">
                <span id="action-label" class="property-label"><g:message code="auditLogEntry.action.label" default="Action" /></span>

                <span class="property-value" aria-labelledby="controller-label">

                    <g:if test="${auditLogEntryInstance.action}">
                        <at:lookupDescription action="${auditLogEntryInstance.action}"/>
                    </g:if>
                    <g:else>
                        ${fieldValue(bean: auditLogEntryInstance, field: "service")} / ${fieldValue(bean: auditLogEntryInstance, field: "operation")}
                    </g:else>
            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.correlationId}">
            <li class="fieldcontain">
                <span id="correlationId-label" class="property-label"><g:message code="auditLogEntry.correlationId.label" default="Korrelations Id" /></span>

                <span class="property-value" aria-labelledby="correlationId-label"><g:fieldValue bean="${auditLogEntryInstance}" field="correlationId"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.duration}">
            <li class="fieldcontain">
                <span id="duration-label" class="property-label"><g:message code="auditLogEntry.duration.label" default="Tid" /></span>

                <span class="property-value" aria-labelledby="duration-label">

                    <g:formatNumber number="${auditLogEntryInstance.duration}" format="#####"/> ms </span>

            </li>
        </g:if>

    %{--<g:if test="${auditLogEntryInstance?.endTime}">--}%
    %{--<li class="fieldcontain">--}%
    %{--<span id="endTime-label" class="property-label"><g:message code="auditLogEntry.endTime.label" default="End Time" /></span>--}%
    %{----}%
    %{--<span class="property-parameterValue" aria-labelledby="endTime-label"><g:fieldValue bean="${auditLogEntryInstance}" field="endTime"/></span>--}%
    %{----}%
    %{--</li>--}%
    %{--</g:if>--}%

        <g:if test="${auditLogEntryInstance?.exception}">
            <li class="fieldcontain">
                <span id="exception-label" class="property-label"><g:message code="auditLogEntry.exception.label" default="Fejl" /></span>

                <span class="property-value" aria-labelledby="exception-label">
                    <g:formatBoolean boolean="${auditLogEntryInstance.exception}" true="${message(code: "default.yesno.yes", default: "Ja")}" false="${message(code: "default.yesno.no", default: 'Nej')}"/>
                </span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.exceptionMessage}">
            <li class="fieldcontain">
                <span id="exceptionMessage-label" class="property-label"><g:message code="auditLogEntry.exceptionMessage.label" default="Fejl besked" /></span>

                <span class="property-value" aria-labelledby="exceptionMessage-label"><g:fieldValue bean="${auditLogEntryInstance}" field="exceptionMessage"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.httpSessionId}">
            <li class="fieldcontain">
                <span id="httpSessionId-label" class="property-label"><g:message code="auditLogEntry.httpSessionId.label" default="HTTP Session Id" /></span>

                <span class="property-value" aria-labelledby="httpSessionId-label"><g:fieldValue bean="${auditLogEntryInstance}" field="httpSessionId"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.messageId}">
            <li class="fieldcontain">
                <span id="messageId-label" class="property-label"><g:message code="auditLogEntry.messageId.label" default="Besked Id" /></span>

                <span class="property-value" aria-labelledby="messageId-label"><g:fieldValue bean="${auditLogEntryInstance}" field="messageId"/></span>

            </li>
        </g:if>

        <li class="fieldcontain">
            <span id="patientCpr-label" class="property-label"><g:message code="auditLogEntry.patientCpr.label" default="CPR" /></span>

            <span class="property-value" aria-labelledby="patientCpr-label"><g:fieldValue bean="${auditLogEntryInstance}" field="patientCpr"/></span>

        </li>

        <g:if test="${auditLogEntryInstance?.request}">
            <li class="fieldcontain">
                <span id="request-label" class="property-label"><g:message code="auditLogEntry.request.label" default="Forespørgsel" /></span>

                <span class="property-value" aria-labelledby="request-label"><g:fieldValue bean="${auditLogEntryInstance}" field="request"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.response}">
            <li class="fieldcontain">
                <span id="response-label" class="property-label"><g:message code="auditLogEntry.response.label" default="Svar" /></span>

                <span class="property-value" aria-labelledby="response-label"><g:fieldValue bean="${auditLogEntryInstance}" field="response"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.result}">
            <li class="fieldcontain">
                <span id="result-label" class="property-label"><g:message code="auditLogEntry.result.label" default="Resultat" /></span>

                <span class="property-value" aria-labelledby="result-label"><g:fieldValue bean="${auditLogEntryInstance}" field="result"/></span>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.service}">
            <li class="fieldcontain">
                <span id="service-label" class="property-label"><g:message code="auditLogEntry.service.label" default="Service" /></span>

                <span class="property-value" aria-labelledby="service-label"><g:fieldValue bean="${auditLogEntryInstance}" field="service"/></span>

            </li>
        </g:if>

    %{--<g:if test="${auditLogEntryInstance?.startTime}">--}%
    %{--<li class="fieldcontain">--}%
    %{--<span id="startTime-label" class="property-label"><g:message code="auditLogEntry.startTime.label" default="Start Time" /></span>--}%
    %{----}%
    %{--<span class="property-parameterValue" aria-labelledby="startTime-label"><g:fieldValue bean="${auditLogEntryInstance}" field="startTime"/></span>--}%
    %{----}%
    %{--</li>--}%
    %{--</g:if>--}%

        <g:if test="${auditLogEntryInstance?.success}">
            <li class="fieldcontain">
                <span id="success-label" class="property-label"><g:message code="auditLogEntry.success.label" default="Success" /></span>

                <span class="property-value" aria-labelledby="success-label">

                    <g:formatBoolean boolean="${auditLogEntryInstance?.success}" true="${message(code: "default.yesno.yes", default: "Ja")}" false="${message(code: "default.yesno.no", default: 'Nej')}"/>

            </li>
        </g:if>

        <g:if test="${auditLogEntryInstance?.authority}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="auditLogEntry.user.label" default="Bruger" /></span>

                <span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${auditLogEntryInstance}" field="authority"/></span>

            </li>
        </g:if>
        <g:if test="${auditLogEntryInstance?.callingIp}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="auditLog.entry.callingIp.label" default="IP adresse" /></span>

                <span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${auditLogEntryInstance}" field="callingIp"/></span>

            </li>
        </g:if>
    </ol>
</div>
</body>
</html>
