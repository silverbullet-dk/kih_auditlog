<%@ page import="dk.silverbullet.kih.auditlog.AuditLogEntry" %>



<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'action', 'error')} ">
	<label for="action">
		<g:message code="auditLogEntry.action.label" default="Action" />
		
	</label>
	<g:textField name="action" value="${auditLogEntryInstance?.action}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'controller', 'error')} ">
	<label for="controller">
		<g:message code="auditLogEntry.controller.label" default="Controller" />
		
	</label>
	<g:textField name="controller" value="${auditLogEntryInstance?.controller}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'correlationId', 'error')} ">
	<label for="correlationId">
		<g:message code="auditLogEntry.correlationId.label" default="Correlation Id" />
		
	</label>
	<g:textField name="correlationId" value="${auditLogEntryInstance?.correlationId}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'duration', 'error')} ">
	<label for="duration">
		<g:message code="auditLogEntry.duration.label" default="Duration" />
		
	</label>
	<g:field type="number" name="duration" value="${auditLogEntryInstance.duration}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'endTime', 'error')} ">
	<label for="endTime">
		<g:message code="auditLogEntry.endTime.label" default="End Time" />
		
	</label>
	<g:field type="number" name="endTime" value="${auditLogEntryInstance.endTime}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'exception', 'error')} ">
	<label for="exception">
		<g:message code="auditLogEntry.exception.label" default="Exception" />
		
	</label>
	<g:checkBox name="exception" value="${auditLogEntryInstance?.exception}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'exceptionMessage', 'error')} ">
	<label for="exceptionMessage">
		<g:message code="auditLogEntry.exceptionMessage.label" default="Exception Message" />
		
	</label>
	<g:textField name="exceptionMessage" value="${auditLogEntryInstance?.exceptionMessage}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'httpSessionId', 'error')} ">
	<label for="httpSessionId">
		<g:message code="auditLogEntry.httpSessionId.label" default="Http Session Id" />
		
	</label>
	<g:textField name="httpSessionId" value="${auditLogEntryInstance?.httpSessionId}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'messageId', 'error')} ">
	<label for="messageId">
		<g:message code="auditLogEntry.messageId.label" default="Message Id" />
		
	</label>
	<g:textField name="messageId" value="${auditLogEntryInstance?.messageId}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'patientCpr', 'error')} ">
	<label for="patientCpr">
		<g:message code="auditLogEntry.patientCpr.label" default="Patient Cpr" />
		
	</label>
	<g:textField name="patientCpr" value="${auditLogEntryInstance?.patientCpr}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'patientId', 'error')} ">
	<label for="patientId">
		<g:message code="auditLogEntry.patientId.label" default="Patient Id" />
		
	</label>
	<g:textField name="patientId" value="${auditLogEntryInstance?.patientId}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'request', 'error')} ">
	<label for="request">
		<g:message code="auditLogEntry.request.label" default="Request" />
		
	</label>
	<g:textField name="request" value="${auditLogEntryInstance?.request}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'response', 'error')} ">
	<label for="response">
		<g:message code="auditLogEntry.response.label" default="Response" />
		
	</label>
	<g:textField name="response" value="${auditLogEntryInstance?.response}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'result', 'error')} ">
	<label for="result">
		<g:message code="auditLogEntry.result.label" default="Result" />
		
	</label>
	<g:textField name="result" value="${auditLogEntryInstance?.result}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'service', 'error')} ">
	<label for="service">
		<g:message code="auditLogEntry.service.label" default="Service" />
		
	</label>
	<g:textField name="service" value="${auditLogEntryInstance?.service}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'startTime', 'error')} ">
	<label for="startTime">
		<g:message code="auditLogEntry.startTime.label" default="Start Time" />
		
	</label>
	<g:field type="number" name="startTime" value="${auditLogEntryInstance.startTime}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'success', 'error')} ">
	<label for="success">
		<g:message code="auditLogEntry.success.label" default="Success" />
		
	</label>
	<g:checkBox name="success" value="${auditLogEntryInstance?.success}" />
</div>

<div class="fieldcontain ${hasErrors(bean: auditLogEntryInstance, field: 'authority', 'error')} ">
	<label for="user">
		<g:message code="auditLogEntry.user.label" default="User" />
		
	</label>
	<g:textField name="user" value="${auditLogEntryInstance?.user}" />
</div>

