<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<div id="container" style="float: left;">
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/>
			<input type="button" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_POST_RETURN.name%>" onclick="openPopup($('#recordIdPopup').val(), 'popup-post-return-div', 'popupPostReturnForm')" class="btnSubmit"/>
			<input type="button" value="<%=Constant.ACTIONS.RMT_UPDATE_PO_BILL_NO.name%>" onclick="openPopup($('#recordIdPopup').val())" class="btnSubmit"/>
		</form>
		<%@ include file="../share/content.jsp" %> 	
	</div>
</div>