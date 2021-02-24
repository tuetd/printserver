<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<c:if test="${not empty errorMessage}">
		<center class="errorMessage">${errorMessage}</center>
	</c:if>
	<div id="container" style="float: left;">

		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox" /> 
			<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
				<input type="submit" id="btnReceive" name="btnSubmit"
					value="<%=Constant.ACTIONS.RMT_RECEIVE_CS.name%>" class="btnSubmit" />
			</sec:authorize>
		</form>
		<%@ include file="../share/content.jsp"%>
	</div>
</div>
