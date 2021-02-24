<div id="popup-receive-div" class="hidden dialog" title="Receive with reason">
	<form method="post" id="popupFormReceive">
		<input type="hidden" name="btnSubmit" value="Receive with reason"/>
		
		<div style="height: 25px;">Note:</div>
			<select id="note_input" name="note" style="margin-left: 15px;">
              <c:forEach var="item" items="${valueReceivelist}">
                <option>
                  <c:out value="${item}" />
                </option>
              </c:forEach>
            </select>
	</form>
</div>