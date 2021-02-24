<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail.html', id,'/utilities/logs.html','RECORD');
	}
	//$(document).ready(function() {
	//	$('#my_modal').popup();
	//});
</script>
<div id="container">
	<div id="header" class="info">
		<b>RMT Record Send By Branch</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/recordbranch/index.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH')">
			<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
			<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH_RECEIVE.name%>" onclick="return sendSelected();" class="btnSubmit"/>
			<input type="button" value="<%=Constant.ACTIONS.RMT_CS_BRANCH_RETURN.name%>" onclick="openNotePopup()" class="btnSubmit"/>

		</sec:authorize>
		<br>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/recordbranch/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="detail.jsp"%>
<!-- Add content to modal -->
<%@ include file="../share/reason_popup.jsp"%>