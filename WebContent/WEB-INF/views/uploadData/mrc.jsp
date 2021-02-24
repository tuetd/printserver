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
			$("#dateSend").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
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
		<b>MRC List</b>
	</div>

<form:form commandName="filterMRCForm" method="post" action="${pageContext.request.contextPath}/uploadData/mrc.html">
		<div id="expander" class="expander">
	<span class="expander-header" onclick="$('#expander-content').toggle();">Filter</span>
	<div class="expander-content" id="expander-content" style="display:none;">

		<form:errors path="*" />
		<table>
			<tbody>

				<tr>					
					<td><label> Loan No</label>
					</td>
					<td><form:input path="loanNo" size="7" />
					</td>	
					<td><label> Org Return Date</label>
					</td>
					<td><form:input path="dateSend" size="8" />
					</td>
					<td>Display <form:select path="pageSize">
						<form:option value="50"> 50 </form:option>
						<form:option value="100"> 100 </form:option>
						<form:option value="200"> 200 </form:option>
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
</form:form>


	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadData/mrc.html">
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


<form name="displ" method="post">
	<input type="checkbox" id="checkboxAll" value="Check all" />Check All &nbsp;&nbsp;	
	<input type="button" value="Remark" onclick="openNotePopup('popup-mrc-remark-div','popupMRCRemarkForm')" class="btnSubmit"/>
	<br>
	<display:table size="resultSize" export="true" cellpadding="1" cellspacing="1" id="data" 
	name="recordList" class="tftable" requestURI="/uploadData/mrc.html" pagesize="${pageSize}">
		<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
			<input type="checkbox" id="checkbox${data.id}" name="checkbox" value="${data.id}" tabindex="1"/>
		</display:column>
		<display:column property="block" title="Block" />
		<display:column property="no" title="No"/>
		<display:column property="loanNo" title="Loan No"/>
		<display:column property="disbursalDate" format="{0,date,dd/MM/yyyy}" title="Disbursal Date" style="width:80px;"/>		
		<display:column property="customerName" title="Customer Name" />		
		<display:column property="dealer" title="Dealer" media="excel pdf"/>
		<display:column property="certNo" title="Cert No" />
		<display:column property="rcvdDate" format="{0,date,dd/MM/yyyy}" title="Rcvd Date" style="width:80px;"/>
		<display:column property="copyDate" format="{0,date,dd/MM/yyyy}" title="Copy Date" style="width:80px;"/>		  
		<display:column property="tenure" title="Tenure" sortable="true" />
		<display:column property="maturityDate" format="{0,date,dd/MM/yyyy}" title="Maturity Date" />		
		<display:column property="orgReturn" title="Org Return" format="{0,date,dd/MM/yyyy}" style="width:80px;"/>
		<display:column property="isBill" title="Is Bill"/>
		<display:column property="status" title="Status"/>
		<display:column property="remark" title="Remark" style="width:150px;"/>
	</display:table>
</form>
 
</div>
<div id="popup-mrc-remark-div" class="hidden dialog" title="MRC Remark">
	<form method="post" id="popupMRCRemarkForm">
		<input type="hidden" name="btnSubmit" value="MRC Remark"/>
		Date return: <input id="dateReturn_input" type="text" name="dateReturn" size="12" value="" />
		<br>
		Remark: <textarea name="remark" rows="3"></textarea>
	</form>
</div>