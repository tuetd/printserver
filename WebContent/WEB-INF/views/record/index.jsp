<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Prepare</b>
	</div>

	<c:if test="${not empty errorMessage}">
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	
	<c:if test="${fn:length(errorList) gt 0}">
		<br/>
		<span  onclick="$('#expander-error').toggle();" style="cursor: pointer;">
		<font size="6" color="red">Total ${fn:length(errorList)} rows fail. Click here to see more details. </font></span>
		<div id="expander-error" style="display:none;">
			<c:forEach items="${errorList}" varStatus="item" var="error">
				<span class="errorMessage"> ${error}</span>
				<br>
			</c:forEach>
		</div>
	</c:if>
	
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/record/index.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<input type="checkbox" id="checkboxAll"	value="Check all" />Check All &nbsp;&nbsp;	
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_PRINT_WELCOME_LETTER.name%>" onclick="alert('Notyet implement.');return false;return sendSelected();" class="btnSubmit"/>	
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_MARK_DONE.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		<div style="text-align: right;height:0px;padding-bottom: 10px"></div>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/record/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="csArrived"  title="CS Arrived" sortable="true" />
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send Date" sortable="true" />
			<display:column property="sender"  title="Sender" sortable="true" />
		</display:table>
	</form>
</div>

<%@ include file="detail.jsp"%>