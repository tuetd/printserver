<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<div id="container" style="float: left;">
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/> 
			<input type="submit" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_MARK_PREPARED.name %>" onclick="return sendSelected();" class="btnSubmit"/>
		</form>
		<%@ include file="../share/content.jsp"%>
	</div>
</div>
