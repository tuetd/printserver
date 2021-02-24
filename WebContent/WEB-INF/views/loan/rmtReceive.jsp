<%@ include file="../layouts/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail_rmtReceive.html?id=" + id;
		return openDetailPopup('#dialog-detail-case', 'detail_rmtReceive.html', id,'/utilities/logs.html','LOAN');
	}
</script>
<div id="container">

	<div id="header" class="info">
		<b>Receive Loan</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>

	<form:form commandName="filterRecordForm" method="post" action="${pageContext.request.contextPath}/loan/rmtReceive.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
	
	<form name="displ" method="post">
		<input type="hidden" id="reason" name="reason" /> 
		<input type="hidden" id="reasonDesc" name="reasonDesc"/> 
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<input type="submit" id="btnWait" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_WAIT_LOAN.name%>" style="display: none" /> 
			<input type="checkbox" id="checkboxAll"name="checkboxAll" value="Check all" />Check all &nbsp;&nbsp;
			<input type="submit" id="btnSubmit" name="btnSubmit" value="<%=Constant.ACTIONS.PREPARE_TO_SCAN.name%>" onclick="return sendSelected();" class="btnSubmit"/> 
			<input type="button" value="<%=Constant.ACTIONS.RMT_WAIT_LOAN.name%>" onclick="openNotePopup()" class="btnSubmit"/>
			<input type="submit" id="btnSubmit" name="btnSubmit" value="Save" onclick="return sendSelected();" class="btnSubmit"/> 
		</sec:authorize>
		<br>
		<display:table size="resultSize" export="true"
				cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
				class="tftable" requestURI="/loan/rmtReceive.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<%-- <display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />	 --%>
			
			<display:column  class="checkboxclass" title="Category" media="html">
				<select name="category${data.id}">
					<option value=""></option>
					<option value="All documents are not verified">All documents are not verified</option>
					<option value="Not verified">Not verified</option>
					<option value="Need more information">Need more information</option>
					<option value="No photograph on Ap. Form">No photograph on Ap. Form</option>
					<option value="No signature on Ap. Form">No signature on Ap. Form</option>
					<option value="Other reason">Other reason</option>
				</select>
			</display:column>	
			
			<display:column class="checkboxclass" title="Note" media="html">
				<textarea rows="1" id="textnote${data.id}" name="textnote${data.id}" tabindex="0" class="textnote"
					style="width:80px"></textarea>
			</display:column>	
		</display:table>
	</form>
</div>

<%@ include file="detail_rmtReceive.jsp"%>
<%@ include file="../share/loan_wait_popup.jsp"%>