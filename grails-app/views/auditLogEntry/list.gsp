
<%@ page import="dk.silverbullet.kih.auditlog.AuditLogEntry" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auditLogEntry.label', default: 'AuditLogEntry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
        <div id="search-auditlig">
        <!--  Filter box  -->
            <g:form method="post" action="search" class="form-inline">
                <fieldset class="form">
                            <g:render template="searchform"/>

                </fieldset>
            </g:form>



        </div>
		<div id="list-auditLogEntry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:render template="list"/>
			<div class="pagination">
				<g:paginate total="${auditLogEntryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
