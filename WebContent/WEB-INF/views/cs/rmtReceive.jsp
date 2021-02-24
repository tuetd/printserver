<%@ include file="../layouts/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail_rmtReceive.html?id=" + id;
		return openDetailPopup('#dialog-detail-case', 'detail_rmtReceive.html', id,'/csrecord/logs.html');
	}
</script>
<div id="container">

	<div id="header" class="info">
		<b>Credit Shield Receive List</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>

	<form:form commandName="filterRecordForm" method="post" action="${pageContext.request.contextPath}/cs/rmtReceive.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
	
	<form name="displ" method="post">
		<input type="hidden" id="reason" name="reason" /> 
		<input type="hidden" id="reasonDesc" name="reasonDesc"/> 
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<input type="checkbox" id="checkboxAll"name="checkboxAll" value="Check all" />Check all &nbsp;&nbsp;
			<input type="submit" id="btnSubmit" name="btnSubmit" value="<%=Constant.ACTIONS.SEND_TO_PVN.name%>" onclick="return sendSelected();" class="btnSubmit"/> 			 
		</sec:authorize>
		<br>
		<display:table size="resultSize" export="true"
				cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.CSRecordDecorator"
				class="tftable" requestURI="/cs/rmtReceive.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
			
		</display:table>
	</form>
</div>

<%@ include file="detail_rmtReceive.jsp"%>