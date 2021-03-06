<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "return_detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','return_detail.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Return From Post Office</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/po/return.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_RECIEVE_FROM_POST_RETURN.name%>" onclick="return sendSelected();" class="btnSubmit"/>	
		<br>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/po/return.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Upload date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
			<display:column property="poBillNo" title="Bill No" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="return_detail.jsp"%>