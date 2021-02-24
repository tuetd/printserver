<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "wait_detail.html?id=" + id;
		return openDetailPopup('#dialog-detail-case','wait_detail.html', id,'/utilities/logs.html','LOAN');
	}
	
	/* $("body").on({
	    ajaxStart: function() { 
	        $(this).addClass("loading"); 
	    },
	    ajaxStop: function() { 
	        $(this).removeClass("loading"); 
	    }    
	}); */
</script>
<div id="container">

	<div id="header" class="info">
		<b>Loan Waiting</b>
	</div>
	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	
  	<form:form commandName="filterRecordForm" method="post"
  	action="${pageContext.request.contextPath}/loan/wait.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
  
	<form name="displ" method="post">
		<%-- <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
			<input type="checkbox" id="checkboxAll"	value="Check all" />Check all &nbsp;&nbsp; 
		</sec:authorize> --%>
		<br>
		<display:table size="resultSize" export="true"
				cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
				class="tftable" requestURI="/loan/wait.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
			<display:column style="width:2%;" title="" media="html">
				<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
					<img onclick="javascript:goShowFollowUpList('${data.id}', 'true');return false;"
					alt="click to view follow up" style="cursor:pointer;"
					src="<%=request.getContextPath()%>/images/common/followup.png" />
				</sec:authorize>
					
				<sec:authorize access="!hasAnyRole('ROLE_RM_RMT')">
					<img onclick="javascript:goShowFollowUpList('${data.id}', 'false');return false;"
					alt="click to view follow up" style="cursor:pointer;"
					src="<%=request.getContextPath()%>/images/common/followup.png" />
				</sec:authorize>

			</display:column>
		</display:table>
	</form>
</div>
<%@ include file="wait_detail.jsp"%>
<%@ include file="../popup/followUp.jsp"%>
<div id="dialog-confirm" title="Complete and prepare to scan the loan?" class="hidden dialog">
<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
Do you want to complete and prepare to scan this loan?</p>
</div>