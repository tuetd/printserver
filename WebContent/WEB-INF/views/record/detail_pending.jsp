<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<div id="container" style="float: left;">
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/> 
			<input type="button" value="<%=Constant.ACTIONS.RMT_CS_POST.name%>" onclick="openPopup($('#recordIdPopup').val(), 'popup-add-address-div', 'popupAddressForm')" class="btnSubmit"/>
			<input type="submit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH.name%>" name="btnSubmit" class="btnSubmit" />
		</form>
		<%@ include file="../share/content.jsp"%>
	</div>
</div>
