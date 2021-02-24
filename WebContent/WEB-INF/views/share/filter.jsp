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

				$("#fromDate").datepicker(
						{
							//defaultDate : "-1w",
							changeMonth : true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								$("#toDate").datepicker("option", "minDate",
										selectedDate);
							}
						});
				$("#toDate").datepicker(
						{
							//defaultDate : "+1w",
							changeMonth : true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1,
							onSelect : function(selectedDate) {
								$("#fromDate").datepicker("option", "maxDate",
										selectedDate);
							}
						});
				$("#sendDate").datepicker(
						{
							changeMonth : true,
							dateFormat : 'dd/mm/yy',
							numberOfMonths : 1
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
					<td>Disb.Date <form:input path="fromDate"
							id="fromDate" size="8" /> To <form:input path="toDate"
							id="toDate" size="8" /></td>
					<td><label for="branch"> Branch</label>
					</td>
					<td><form:select path="branch">
							<form:option value="" label="--- Select branch ---" />
							<form:options items="${filterRecordForm.branchs}"
								itemLabel="code" itemValue="code" />
						</form:select>
					</td>		
					<td><label for="appFormNo"> App Form No</label>
					</td>
					<td><form:input path="appFormNo" size="7" />
					<td><label for="agreementNo"> Agreement No</label>
					</td>
					<td><form:input path="agreementNo" size="7" />
					</td>
					<td><label for="area"> Area</label>
					</td>
					<td><form:input path="area" size="5" />
					</td>
					<td>Date <form:input path="sendDate"
							id="sendDate" size="8" /> </td>
					<td>User <form:input path="sender"
							id="sender" size="7" /> </td>
					
					<td>Display <form:select path="pageSize">
						<form:option value="25"> 25 </form:option>
						<form:option value="50"> 50 </form:option>
						<form:option value="100"> 100 </form:option>
						<form:option value="100"> 200 </form:option>
						<form:option value="0"> All </form:option>
						</form:select> 
					</td>
					<td>
						<input type="submit" name="btnFilter" value="filter" class="filter-button"> 
						<input type="submit" name="btnFilter" value="clear" class="clear-button">
					</td>
				</tr>

			</tbody>
		
		</table>

	</div>
</div>