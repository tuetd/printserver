<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//alert('goViewDetail id=' + id);
		return openDetailPopup('#dialog-detail-case','detail.html', id,'/utilities/logs.html','LOAN');
		//window.location.href = "detail.html?id="+ id;
	}
</script>
<div id="container">
	
	<b>Disbursal Loan</b>
	<c:if test="${not empty errorMessage}">
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"  
		method="post" action="${pageContext.request.contextPath}/loan/index.html">
		<%@ include file="../share/basefilter.jsp"%>
	</form:form>

	<form name="displ" method="post">
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH')">
			<input type="checkbox" id="checkboxAll"	value="Check All" />Check All &nbsp;&nbsp;
			<input type="submit" id="btnSubmit" name="btnSubmit" value="<%=Constant.ACTIONS.SEND_TO_RMT.name%>" onclick="return sendSelected();" class="btnSubmit" />
			<input type="button" value="<%=Constant.ACTIONS.MARK_AS_SEND_TO_BRANCH.name%>" onclick="openNotePopup()" class="btnSubmit"/>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
			<input type="button" value="<%=Constant.ACTIONS.RMT_SUSPENSE.name%>" onclick="openNotePopup('popup-suspense-div','popupSuspenseForm')" class="btnSubmit"/>
		</sec:authorize>
		<div style="text-align: right;height:0px;padding-bottom: 10px"><span style="background-color:#99FF66;"> &nbsp;&nbsp;&nbsp;&nbsp; </span> &nbsp;  Send to branch</div>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/loan/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
		</display:table>
	</form>
</div>
<%@ include file="detail.jsp"%>
<%@ include file="../share/note_popup.jsp"%>
<%@ include file="../share/suspense_popup.jsp"%>