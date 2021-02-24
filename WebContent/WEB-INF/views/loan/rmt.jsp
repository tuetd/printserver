<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		return openDetailPopup('#dialog-detail-case', 'detail_rmt.html', id,'/utilities/logs.html','LOAN');
		//window.location.href = "detail_rmt.html?id=" + id;
	}
</script>
<div id="container">

	<div id="header" class="info">
		<b>Disbursal Loan RMT</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	
  	<form:form commandName="filterRecordForm" method="post" action="${pageContext.request.contextPath}/loan/rmt.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
  
	<form name="displ" method="post">
		<input type="hidden" id="reason" name="reason"/> 
		<input type="hidden" id="reasonDesc" name="reasonDesc"/> 
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<input type="checkbox" id="checkboxAll" value="Check all" />Check all &nbsp;&nbsp;		
			<input type="submit" id="btnReceive" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_RECEIVE_ACK.name%>" onclick="return sendSelected();" class="btnSubmit"/> 
		</sec:authorize>
		<br>
		<display:table size="resultSize" export="true"
				cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
				class="tftable" requestURI="/loan/rmt.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />			
		</display:table>
	</form>
</div>
<%@ include file="detail_rmt.jsp"%>