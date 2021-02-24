<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#callDateFrom").datepicker(
				{
					changeMonth : true,
					dateFormat : 'dd/mm/yy',
					numberOfMonths : 1,
					onSelect : function(selectedDate) {
						$("#callDateFrom").datepicker("option", "minDate",
								selectedDate);
					}
				});
		$("#callDateTo").datepicker(
				{
					changeMonth : true,
					dateFormat : 'dd/mm/yy',
					numberOfMonths : 1,
					onSelect : function(selectedDate) {
						$("#callDateTo").datepicker("option", "minDate",
								selectedDate);
					}
				});
	});
</script>
<div id="container" style="width: 1000px;float:left;">
	<form:form id="creditShieldForm" commandName="creditShieldForm" method="post" action="${pageContext.request.contextPath}/utilities/icmdocument.html" >
    <fieldset>
        <legend>Export Credit Shield Document</legend>
       
       <div>
		<c:if test="${filename != null}">
		<p>
		<font color="blue" size="4">
				<b>Download IBMCM Document: </b><a href="<%=request.getContextPath() %>/reports/${filename}">${filename}</a>
		</font>		
		</p>		
			</c:if>
		</div>
		
        <table>
            <tbody>
            <form:errors cssClass="red" path="*" />
            <br> 
            <tr>
                <td>
                    Disbursal Date: 
                </td>
                <td>
                    <form:input  size="10" path="callDateFrom" ></form:input>
                </td>
            </tr>
            <br> 
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr>
                <td>
                    <input type="submit" name="btnSubmit" value="Export File" class="btnSubmit"/>
                </td>
            </tr>
        </tbody></table>
    </fieldset>

    </form:form>
</div>
<!-- end of cntool -->