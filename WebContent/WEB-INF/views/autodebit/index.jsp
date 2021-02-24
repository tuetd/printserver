<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
function getReport()
{
	var url = $("#urlHidden").val();
	$("#ADForm").attr("method", "get");
	$("#ADForm").attr("action", url+"/autodebit/report.html");
	$( "#ADForm" ).submit();
	$("#ADForm").attr("method", "post");
	$("#ADForm").attr("action", url+"/autodebit/index.html");
}
$( document ).ready(function() {
	if($("#bankCodeH").val()!=null && $("#bankCodeH").val()!="")
	{
		$("#bankCode").val($("#bankCodeH").val());
	}
	if($("#bankCodeH").val()!=null && $("#bankCodeH").val()!="")
	{
		$("#branchDesc").val($("#branchDescH").val());
	}	
	if($("#bankCodeH").val()==null || $("#bankCodeH").val()=="")
	{
		$("#branchDesc").val("Direct To Home");
	}
	if($("#branchSend").val()==1)
	{
		$("#branchDesc").val("-1");
		$("#branchDesc").hide();
		$("#lbBranch").hide();		
	}

});
</script>
<div id="container">
	<input type="hidden" id="urlHidden"	value="${pageContext.request.contextPath}" />
	<input type="hidden" id="bankCodeH" name="bankCodeH"	value="${bank}" />
	<input type="hidden" id="branchDescH" name="branchDescH"	value="${branch}" />
	<input type="hidden" id="branchSend" name="branchSend"	value="${branchSend}" />
	<b>Auto debit </b>
	<c:if test="${not empty errorMessage}">
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form id="ADForm" commandName="autoDebitForm"  
		method="post" action="${pageContext.request.contextPath}/autodebit/index.html">
		<%@ include file="basefilter.jsp"%>

	</form:form>


	<form name="displ" method="post">
		<sec:authorize access="hasAnyRole('ROLE_AD_SEND','ROLE_AD_ACCEPT')">	
			<input type="checkbox" id="checkboxAll"	value="Check All" />Check All &nbsp;&nbsp;
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_AD_SEND')">			
			<input type="submit" id="btnSubmit" name="btnSubmit" value="<%=Constant.AUTO_DEBIT.SEND_TO_OP.name%>" onclick="return sendSelected();" class="btnSubmit" />			
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_AD_ACCEPT')">
			<input type="button" value="<%=Constant.AUTO_DEBIT.RECEIVE_WITH_REASON.name%>" onclick="return openNotePopup('popup-receive-div','popupFormReceive')" class="btnSubmit"/>
			<input type="button" value="<%=Constant.AUTO_DEBIT.RETURN_WITH_REASON.name%>" onclick="return openNotePopup('popup-return-div','popupFormReturn')" class="btnSubmit"/>			
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_AD_SEND','ROLE_AD_ACCEPT')">	
		<input type="button" id="btnSubmit" name="btnSubmit" value="Report" onclick="getReport();" class="btnSubmit" />
		</sec:authorize>
		<div style="text-align: right;height:0px;padding-bottom: 10px"><span style="background-color:#99FF66;"> &nbsp;&nbsp;&nbsp;&nbsp; </span> &nbsp;  Send to OP</div>		
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator=""
			class="tftable" requestURI="/autodebit/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns_auto_debit.jsp"%> 
		</display:table>
	</form>
</div>
<%@ include file="../share/receive_reason_popup.jsp"%>
<%@ include file="../share/return_reason_popup.jsp"%>

	<script type="text/javascript">
	$('.exportlinks').hide();
</script>