<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<c:if test="${not empty errorMessage}">
		<center class="errorMessage">${errorMessage}</center>
	</c:if>
	<div id="container" style="float: left;">

		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox" />
			<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
				<input name="btnSubmit" type="submit" value="<%=Constant.ACTIONS.PREPARE_TO_SCAN.name%>" class="btnSubmit" />
				<input type="button" class="btnSubmit" value="<%=Constant.ACTIONS.RMT_WAIT_LOAN.name%>"	onclick="openPopup($('#recordIdPopup').val())" />
			</sec:authorize>
		</form>

		<%@ include file="../share/content.jsp"%>
	</div>
</div>