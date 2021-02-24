<div id="dialog-detail-case" class="hidden dialog" title="Record detail">

	<c:if test="${not empty errorMessage}">
		<center class="errorMessage">${errorMessage}</center>
	</c:if>

	<div id="container" style="float: left;">

		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox" />
			<%-- <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
				<input type="submit" id="btnComplete" name="btnSubmit"
					value="Mark as verified" class="btnSubmit" />
			</sec:authorize> --%>

			<%--  <sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_ADMIN')">
				<img
					onclick="javascript:goShowFollowUpList('${record.id}','true');return false;"
					alt="click to view follow up" style="cursor: pointer;"
					src="<%=request.getContextPath()%>/images/common/followup.png" />
			</sec:authorize>

			<sec:authorize access="!hasAnyRole('ROLE_RM_RMT','ROLE_ADMIN')">
				<img
					onclick="javascript:goShowFollowUpList('${record.id}','false');return false;"
					alt="click to view follow up" style="cursor: pointer;"
					src="<%=request.getContextPath()%>/images/common/followup.png" />
			</sec:authorize> --%>

		</form>

		<%@ include file="../share/content.jsp"%>
	</div>
</div>

