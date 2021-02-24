<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "return_detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','return_detail.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Return From Branch</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/recordbranch/return.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
			<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH_RECEIVE.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		</sec:authorize>
		<br>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/recordbranch/return.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="return_detail.jsp"%>