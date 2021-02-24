<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//alert('goViewDetail id=' + id);
		return openDetailPopup('#dialog-detail-case','detail.html', id ,'/csrecord/logs.html');
		//window.location.href = "detail.html?id="+ id;
	}
</script>
<div id="container">
	
	<b>Credit Shield List</b>
	<c:if test="${not empty errorMessage}">
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"  
		method="post" action="${pageContext.request.contextPath}/cs/index.html">
		<%@ include file="../share/basefilter.jsp"%>
	</form:form>

	<form name="displ" method="post">
		<sec:authorize access="hasAnyRole('ROLE_CS_BRANCH')">
			<input type="checkbox" id="checkboxAll"	value="Check All" />Check All &nbsp;&nbsp;
			<input type="submit" id="btnSubmit" name="btnSubmit" value="<%=Constant.ACTIONS.SEND_CS_TO_RMT.name%>" onclick="return sendSelected();" class="btnSubmit" />
		</sec:authorize>
		<div style="text-align: right;height:0px;padding-bottom: 10px"> </div>
		<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.CSRecordDecorator"
			class="tftable" requestURI="/cs/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
		</display:table>
	</form>
</div>
<%@ include file="detail.jsp"%>