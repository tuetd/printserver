<%@ include file="../layouts/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail_rmtReceive.html?id=" + id;
		return openDetailPopup('#dialog-detail-case', 'detail_sent.html', id,'/csrecord/logs.html');
	}
</script>
<div id="container">

	<div id="header" class="info">
		<b>Insurance Certificate Completed</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>

	<form:form commandName="filterRecordForm" method="post">
		<%@ include file="../share/filter.jsp"%>
	</form:form>

	<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.CSRecordDecorator"
			class="tftable" requestURI="/ic/sent.html" pagesize="${pageSize}">
		<%@ include file="../share/columns.jsp"%>
	</display:table>

</div>

<%@ include file="detail_sent.jsp"%>