<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function revert(type) {		
		if(type == '1' && $('#stage').val() == null)  {
			alert("Please select screen to revert.");
			return;
		}
		
		if(type == '2' && $('#stage2').val() == null)  {
			alert("Please select screen to revert.");
			return;
		}
		
		if (confirm("Do you want to revert this loan?")) {
			var stage;var note;
			if(type == '1') {
				stage = $('#stage').val();
				note = $('#note').val();  
			} else {
				stage = $('#stage2').val();
				note = $('#note2').val();
			}
			$.ajax({
				type : "POST",
				url : ctx + '/utilities/doRevert.html',
				data : {
					agreementno : $('#agreementno').val(),
					stage : stage,
					type : type,
					note: note
				}
			}).error(function(jqXHR, textStatus, errorMessage) {
				alert('error: ' + errorMessage);
				//alert('error textStatus: '+ textStatus);
			}).success(function(data) {
				//alert( "Data Saved: " + data );
				window.location.href = "revert.html?message=" + data;
			}).done(function(msg) {
				//alert( "Data Saved: " + msg );
			});
		}
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Revert Loan</b>
	</div>
	<center id="message" class="errorMessage">${message}</center>
	<form method="post" id="filterForm">
		<table>
			<tbody>
				<tr>
					<td><label for="agreementNo">Agreement No</label>
					</td>
					<td><input type="text" name="agreementno" size="20" value="${agreementno}"/>
					</td>
					<td><input type="submit" name="btnFind" value="Find"
						class="btnSubmit">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="width: 760px">
		<c:if test="${ record.agreementno  ne null}">
			<fieldset style="width: 350px;float: left">
				<legend>Loan Kit</legend>
				<input type="hidden" id="agreementno" name="agreementno"
					value="${record.agreementno}" />
				<table>
					<tr>
						<td>Agreement No:</td>
						<td><b>${record.agreementno}</b>
						</td>
					</tr>
					<tr>
						<td>Customer Name:</td>
						<td><b>${record.customername}</b>
						</td>
					</tr>
					<tr>
						<td>Date Send:</td>
						<td>
						 <fmt:formatDate pattern="dd-MM-yyyy"
						value="${record.sendDate}" /> 
						</td>
					</tr>
					<tr>
						<td>Sender :</td>
						<td>
							 ${record.sender}
						</td>
					</tr>
					<tr>
						<td>Current Screen:</td>
						<td><b>${currentScreen.name}</b></td>
					</tr>
					<tr>
						<td>Revert to Screen :</td>
						<td><select id="stage" name="stage">
								<c:forEach items="${listScreen}" varStatus="item" var="screen">
									<option value="${screen.stage}">${screen.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
					<td>Note:</td>
							<td><textarea id="note" name="note" rows="3"> </textarea></td>
					</tr>
				</table>
				<button name="btnSubmit" value="<%=Constant.ACTIONS.REVERT_LOAN.name%>" class="btnSubmit"
					onclick="revert('1')"><%=Constant.ACTIONS.REVERT_LOAN.name%></button>
			</fieldset>
			<c:if test="${fn:length(listScreen2) gt 0}">
				<fieldset style="width: 350px;float: right">
					<legend>Scan Documents</legend>
					<table>
						<tr>
							<td>Agreement No:</td>
							<td><b>${record.agreementno}</b>
							</td>
						</tr>
						<tr>
							<td>Customer Name:</td>
							<td><b>${record.customername}</b>
							</td>
						</tr>
						<tr>
							<td>Date Send:</td>
							<td>
								 <fmt:formatDate pattern="dd-MM-yyyy"
							value="${record.sendDate}" /> 
							</td>
						</tr>
						<tr>
							<td>Sender :</td>
							<td>
								${record.sender}
							</td>
						</tr>
						<tr>
							<td>Current Screen:</td>
							<td><b>${currentScreen2.name}</b></td>
						</tr>
						<tr>
							<td>Revert to Screen :</td>
							<td><select id="stage2" name="stage2">
									<c:forEach items="${listScreen2}" varStatus="item" var="screen">
										<option value="${screen.stage}">${screen.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Note:</td>
							<td><textarea id="note2" name="note2" rows="3"> </textarea></td>
						</tr>
					</table>
					<button name="btnSubmit" value="<%=Constant.ACTIONS.REVERT_LOAN2.name%>" class="btnSubmit"
						onclick="revert('2')"><%=Constant.ACTIONS.REVERT_LOAN2.name%></button>
				</fieldset>
			</c:if>
		</c:if>
	</div>
</div>
