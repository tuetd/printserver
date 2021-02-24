<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				// Handler for .ready() called.
				$("tr[id^='recordRow']").dblclick(function() {
					var id = $(this).attr('id').replace('recordRow', '');
					goViewDetail(id);
				});

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

				$("#dateAuthorized").datepicker(
						{
							//defaultDate : "-1w",
							changeMonth : true,
							changeYear: true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								
							}
						});
				$("#dateDisbursal").datepicker(
						{
							//defaultDate : "+1w",
							changeMonth : true,
							changeYear: true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								
							}
						});	
				$("#firstDueDate").datepicker(
						{
							//defaultDate : "+1w",
							changeMonth : true,
							changeYear: true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								
							}
						});	
				$("#sendDate").datepicker(
						{
							//defaultDate : "+1w",
							changeMonth : true,
							changeYear: true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								
							}
						});	
			});

	function sendAll() {
		return confirm("Do you want to send all contract?");
	}

	function sendSelected() {
		var size = $('input[name="checkbox"]:checked').length;
		if (size == 0) {
			alert("Please select record!");
			return false;
		}
		return true;

	}

	function checkReturn() {
		if (sendSelected()) {
			$('#openPopup').click();
		}
	}
</script>
<div id="expander" class="expander">
	<span class="expander-header" onclick="$('#expander-content').toggle();">Filter</span>
	<div class="expander-content" id="expander-content" style="display:none;">

		<form:errors path="*" />
		<table>
			<tbody>

				<tr>
					
					<td><label for="loanNo"> Loan no</label></td>
					<td><form:input path="loanNo"
							id="loanNo" size="10" /> </td>
					<td><label for="customerName"> Customer name</label></td>
					<td><form:input path="customerName" size="15" /></td>
					<td><label for="sendername"> Sender</label></td>
					<td><form:input id="sendername" name="sendername" path="sendername" size="15" /></td>									
					<td><label for="firstDueDate"> FirstDue date</label></td>
					<td><form:input id="firstDueDate" name="firstDueDate" path="firstDueDate" size="10" /></td>							
					<td><label for="authorizedDate"> Authorized date</label></td>
					<td><form:input id="dateAuthorized" name="dateAuthorized" path="authorizedDate" size="10" /></td>					
					<td>Send Date</td>
					<td>
					 <form:input path="sendDate"
							id="sendDate" size="10" /> 
					</td>									
					<td>Display</td>
					<td> <form:select path="pageSize">
						<form:option value="25"> 25 </form:option>
						<form:option value="50"> 50 </form:option>
						<form:option value="100"> 100 </form:option>
						<form:option value="100"> 200 </form:option>
						<form:option value="0"> All </form:option>
						</form:select> 
					</td>	
				</tr>
				<tr>
					<td><label for="bankCode"> Bank</label></td>
					<td colspan="3">
						<form:select path="bankCode" style="width: 300px">							
							<form:option value="-1">-- Select One --</form:option>
							<form:options items="${banklist}"/>			
						</form:select>
							</td>
					<td><label for="roName"> RO name</label></td>
					<td><form:input path="roName" size="15" /></td>							
					<td><label for="disbursalDate"> Disbursal date</label></td>
					<td><form:input id="dateDisbursal" name="dateDisbursal" path="disbursalDate" size="10" /></td>						
					<td><label for="statusAD"> Status</label></td>
					<td>
						<form:select path="status"  style="width: 100px">
							<form:option value="None" />
							<form:option value="Send" />
							<form:option value="Receive" />
							<form:option value="Return" />
							<form:option value="Delete" />
							<form:option value="Done" />				
						</form:select>
					</td>
					<td><label id="lbBranch" for="branchDescAD"> Branch</label></td>
					<td>
						<form:select path="branchDesc"  style="width: 100px">
							<form:option value="-1">-- Select One --</form:option>
							<form:options items="${branchlist}"/>				
						</form:select>
					</td>
					<td><input type="submit" name="btnFilter" value="filter" class="filter-button"> </td>					
					<td>
						<input type="submit" name="btnFilter" value="clear" class="clear-button">
					</td>
				</tr>

			</tbody>
		
		</table>

	</div>
</div>