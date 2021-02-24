<div id="popup-return-div" class="hidden dialog" title="Return with reason">
	<form method="post" id="popupFormReturn">
		<input type="hidden" name="btnSubmit" value="Return with reason"/>
		
		<div style="height: 25px;">Note:</div>
		<select id="note_input" name="note" style="margin-left: 15px;">
              <c:forEach var="item" items="${valueReturnlist}">
                <option>
                  <c:out value="${item}" />
                </option>
              </c:forEach>
            </select>
	</form>
</div>