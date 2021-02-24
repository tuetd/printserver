<%@page import="com.pruvn.rms.utils.Constant"%>
<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail_rd.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail_rd.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Ready List</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/record/rd.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>


	<form name="displ" method="post">
		<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
		<input type="submit" name="btnSubmit" value="Send(Auto)" class="btnSubmit"/>
		<input type="button" value="<%=Constant.ACTIONS.RMT_CS_POST.name%>" onclick="openNotePopup('popup-add-address-div','popupAddressForm')" class="btnSubmit"/>
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		<input type="button" value="<%=Constant.ACTIONS.MARK_AS_SEND_TO_BRANCH.name%>" onclick="openNotePopup()" class="btnSubmit"/>
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_NOT_SEND.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_PENDING.name%>" onclick="return sendSelected();" class="btnSubmit"/>
		<div style="text-align: right;height:0px;padding-bottom: 10px"><span style="background-color:#99FF66;"> &nbsp;&nbsp;&nbsp; </span> &nbsp;&nbsp;  <%=Constant.ACTIONS.RMT_CS_BRANCH.name%></div>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/record/rd.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="csArrived" title="CS Arrived" sortable="true" />
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send Date" sortable="true" />
			<display:column property="sender"  title="Sender" sortable="true" />
		</display:table>
	</form>
</div>


<%@ include file="detail_rd.jsp"%>
<!-- Add content to modal -->
<%@ include file="../share/add_address_popup.jsp"%>
<%@ include file="../share/note_popup.jsp"%>