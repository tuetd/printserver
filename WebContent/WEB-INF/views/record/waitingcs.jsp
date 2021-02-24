<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail_waitingcs.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Documents Waiting CS</b>
	</div>

	<c:if test="${not empty errorMessage}">
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	
	<c:forEach items="${errorList}" varStatus="item" var="error">
		<center class="errorMessage" >${error}</center>
	</c:forEach>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/record/waitingcs.html" >
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">	
		<br>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordCMDecorator"
			class="tftable" requestURI="/record/waitingcs.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="detail_waitingcs.jsp"%>