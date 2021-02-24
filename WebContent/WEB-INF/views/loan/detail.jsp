<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<div id="container" style="float: left;">
		
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/> 
			<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH')">
				<input type="submit" value="<%=Constant.ACTIONS.SEND_TO_RMT.name%>" class="btnSubmit" name="btnSubmit"/>
				<input type="button" value="<%=Constant.ACTIONS.MARK_AS_SEND_TO_BRANCH.name%>" onclick="openPopup($('#recordIdPopup').val())" class="btnSubmit" />
			</sec:authorize>	
			<sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
				<input type="button" value="<%=Constant.ACTIONS.RMT_SUSPENSE.name%>" onclick="openPopup($('#recordIdPopup').val(),'popup-suspense-div','popupSuspenseForm')" class="btnSubmit"/>
			</sec:authorize>
		</form>
		
		
		<%@ include file="../share/content.jsp" %> 	
	</div>
</div>
