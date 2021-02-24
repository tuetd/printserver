<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "deliver_detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','deliver_detail.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Delivered</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm" method="post"
	action="${pageContext.request.contextPath}/recordbranch/deliver.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
<%-- 		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
			<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
		</sec:authorize> --%>
		<br>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/recordbranch/deliver.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="deliver_detail.jsp"%>