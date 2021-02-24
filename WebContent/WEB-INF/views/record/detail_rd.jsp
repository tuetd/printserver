<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<div id="container" style="float: left;">
		<form method="post">
			<input type="hidden" id="recordIdPopup" name="checkbox"/> 
			<input type="submit" value="Send(Auto)" name="btnSubmit"  class="btnSubmit" /> 
			<input type="button" value="<%=Constant.ACTIONS.RMT_CS_POST.name%>" onclick="openPopup($('#recordIdPopup').val(), 'popup-add-address-div', 'popupAddressForm')" class="btnSubmit"/>
			<input type="submit" value="<%=Constant.ACTIONS.RMT_CS_BRANCH.name%>" name="btnSubmit" class="btnSubmit" />
			<input type="button" value="<%=Constant.ACTIONS.MARK_AS_SEND_TO_BRANCH.name%>" onclick="openPopup($('#recordIdPopup').val())" class="btnSubmit"/>
			<input type="submit" value="<%=Constant.ACTIONS.RMT_CS_NOT_SEND.name%>" name="btnSubmit" class="btnSubmit"/>
			<input type="submit" value="<%=Constant.ACTIONS.RMT_CS_PENDING.name%>" name="btnSubmit" class="btnSubmit"/>
		</form>
		<%@ include file="../share/content.jsp"%>
	</div>
</div>