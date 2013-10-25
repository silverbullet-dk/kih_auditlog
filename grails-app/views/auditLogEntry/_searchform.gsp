<%
    def viewFromDate
    if (fromDate) {
        viewFromDate = fromDate
    } else {
        def cal = Calendar.getInstance()
        cal.add(Calendar.MONTH,-1)
        viewFromDate = cal.getTime()
    }
    %>

%{--<g:setProvider library="jquery"/>--}%
<g:javascript library="jquery" />


<div id="filter-box" class="filter-box">
    <table class="table table-hover">
        <tbody>
        <tr>
            <td><g:message code="auditlog.search.ssn"
                           default="CPR Nummer" /></td><td colspan="5"> <g:textField name="ssn" value="${ssn}"/></td>
        </tr>
        <tr>
            <td><g:message code="auditlog.search.controller"
                           default="Controller" /></td><td colspan="2"> <g:select name="usedController" from="${controllers}" onchange="${remoteFunction(
                                                                                  controller:'auditLogEntry',
                                                                                  action:'ajaxGetActions',
                                                                                  params:'\'c=\' + escape(this.value)',
                                                                                  update:'updateActions')}" value="${choosenController}"
                                                                                  noSelection="${['':g.message(code: "default.select.no.select", default: "Vælg en")]}"
        />
            <td><g:message code="auditlog.search.action"
                           default="Action" /></td><td colspan="2" id="updateActions">
            <g:render template="auditLogActions"/>
        </td>
        </tr>
        <tr>
            <td><g:message code="auditlog.search.from.date"
                           default="Fra dato" /> </td><td colspan="2"><g:datePicker value="${viewFromDate}" name="fromDate"/></td>
            <td><g:message code="auditlog.search.to.date"
                           default="Til dato"/></td><td colspan="2"><g:datePicker default="${toDate}" name="toDate"/></td>
        </tr>
        <tr>
            <td><g:message code="auditlog.search.exceptionOccurred"
                           default="Fejl?" /> </td><td colspan="5"><g:checkBox name="exceptionOccurred" value="${exceptionOccurred}"/></td>
        </tr>

        <tr>
            <td align="left" colspan="6"> <g:actionSubmit class="btn btn-primary" action="search" value="${message(code:"patient.overview.form.submit", default:"Søg") }"/></td>
        </tr>
        </tbody>


    </table>
</div>