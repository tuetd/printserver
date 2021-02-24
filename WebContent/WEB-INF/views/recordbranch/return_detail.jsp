<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
<div id="container" style="float: left;">
	<form method="post">
		<input type="hidden" id="recordIdPopup" name="checkbox"/> 
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')"> 
			<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH_RECEIVE.name%>" class="btnSubmit"/>
		</sec:authorize>
	</form>
	<%@ include file="../share/content.jsp" %> 	
</div>
</div>