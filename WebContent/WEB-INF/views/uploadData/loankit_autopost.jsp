<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(
		function() {
			// Handler for .ready() called.
			$("td[class^='checkboxclass']").dblclick(function() {
				//alert('aaaaaa');
				return false;
			});

			$("#checkboxAll").live(
					'change',
					function() {
						$("[name='checkbox']").attr('checked',
								$(this).is(':checked') ? true : false);
			});
			$("#dateReturn_input").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
					//$("#dateReturn").datepicker("option", "minDate",
					//		selectedDate);
				}
			});
});


function sendSelected() {
	var size = $('input[name="checkbox"]:checked').length;
	if (size == 0) {
		alert("Please select record!");
		return false;
	}
	return true;
}

		
</script>

<div id="container">
	<div id="header" class="info">
		<b>Loan Kit send by Post</b>
	</div>

	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadData/loankit_autopost.html">
		<fieldset>
			<legend>Upload File</legend>

			<font color="red"><form:errors path="*" /> </font>
			<c:if test="${successMsg != null}">
				<font size="6" color="blue"> ${successMsg}</font>
			</c:if>
			<c:if test="${fn:length(errorList) gt 0}">
				<br/>
				<span  onclick="$('#expander-error').toggle();" style="cursor: pointer;">
				<font size="6" color="red">Total ${fn:length(errorList)} rows import fail. Click here to see more details. </font></span>
				<div id="expander-error" style="display:none;">
					<c:forEach items="${errorList}" varStatus="item" var="error">
						<span class="errorMessage"> ${error}</span>
						<br>
					</c:forEach>
				</div>
			</c:if>
		
			<table style="width: 650px">
				<tr>
					<td style="width: 90px"><form:label for="fileData" path="fileData">Select file : </form:label>
					</td>
					<td><form:input path="fileData" type="file"/>
					</td>
					<td style="width:450px">
						<div id="btnGroup" >
							<input type="submit" name="btnUpload" value="Upload"
								onclick="javascript:hideBtn();" class="btnSubmit" />
							<div id="inprogress" style="display: none;">
								<input type="button" value="In progress ..."
									onclick="javascript:alertInprogress();" class="btnSubmit" />
							</div>
						</div>
					</td>
						
				</tr>
			</table>
		</fieldset>
	</form:form>
	</sec:authorize>	
	<br style="clear:both;">










