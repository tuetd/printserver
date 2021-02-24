<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<div id="container" style="float: left;">
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/> 
			<input class="btnSubmit" type="submit" name="btnSubmit"
				value="<%=Constant.ACTIONS.RMT_PRINT_WELCOME_LETTER.name%>"
				onclick="alert('Notyet implement.');return false;" />	

			<input type="submit" id="btnSubmit" name="btnSubmit"
				value="<%=Constant.ACTIONS.RMT_MARK_DONE.name%>" class="btnSubmit" />


		</form>
		<%@ include file="../share/content.jsp"%>
	</div>
</div>
