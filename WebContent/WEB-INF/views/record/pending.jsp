<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail_rd.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail_pending.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Pending</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/record/pending.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
		<input type="button" value="<%=Constant.ACTIONS.RMT_CS_POST.name%>" onclick="openNotePopup('popup-add-address-div','popupAddressForm')" class="btnSubmit"/>
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		<br/>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/record/pending.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send Date" sortable="true" />
			<display:column property="sender"  title="Sender" sortable="true" />
		</display:table>
	</form>
</div>


<%@ include file="detail_pending.jsp"%>
<%@ include file="../share/add_address_popup.jsp"%>