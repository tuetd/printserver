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
			$("#dateRemark").datepicker({
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
					//$("#dateReturn").datepicker("option", "minDate",
					//		selectedDate);
				}
			});
});
</script>

<div id="container">
	<div id="header" class="info">
		<b>Foreclosure Upload List</b>
	</div>

<form:form commandName="filterMRCForm" method="post" action="${pageContext.request.contextPath}/foreclosure/index.html">
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


	<sec:authorize access="hasAnyRole('ROLE_XPU')">
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/foreclosure/index.html">
		<fieldset>
			<legend>Upload File</legend>

			<font color="red"><form:errors path="*" /> </font>
			
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
			
			<c:if test="${successMsg != null}">
				<font size="6" color="blue"> ${successMsg}</font>
			</c:if>
			<c:if test="${fn:length(successList) gt 0}">
				<br/>
				<span  onclick="$('#expander-success').toggle();" style="cursor: pointer;">
				<font size="5" color="blue">Total ${fn:length(successList)} rows is imported. Click here to see more details. </font></span>
				<div id="expander-success" style="display:none;">
					<table>
						<thead>
							<tr> 
								<th>Loan No</th>
								<th>Customer Name</th>								
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${successList}" varStatus="item" var="row">
							<tr>
								<td>${row.loanNo}</td>
								<td>${row.customerName} </td>								
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<br/>
		<c:if test="${fn:length(existList) gt 0}">
				<br/>
				<span  onclick="$('#expander2-error').toggle();" style="cursor: pointer;">
				<font size="5" color="red">Total ${fn:length(existList)} rows is exist. Click here to see more details. </font></span>
				<div id="expander2-error" style="display:none;">
					<table>
						<thead>
							<tr> 
								<th>Loan No</th>
								<th>Customer Name</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${existList}" varStatus="item" var="exist">
							<tr>
								<td>${exist.loanNo}</td>
								<td>${exist.customerName} </td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<br/>
			
		
			<table style="width: 650px">
				<tr>
					<td style="width: 90px"><form:label for="fileData" path="fileData">Select file : </form:label>
					</td>
					<td><form:input path="fileData" type="file"/>
					</td>
					<td style="width:450px">
						<div id="btnGroup" >
							<input type="submit" name="btnUpload" value="Upload foreclosure"
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
	
		<sec:authorize access="hasAnyRole('ROLE_XPU')">
			<input type="checkbox" id="checkboxAll"	value="Check All" />Check All &nbsp;&nbsp;
			<input type="button" value="Remark" onclick="openNotePopup('popup-remark-div','popupRemarkForm')" class="btnSubmit"/>
			<input type="button" value="Send" onclick="openNotePopup('popup-send-div','popupSendForm')" class="btnSubmit"/>
		</sec:authorize>
	
	<br>
	<display:table size="resultSize" export="true" cellpadding="1" cellspacing="1" id="data" name="recordList" class="tftable" requestURI="/foreclosure/index.html" pagesize="${pageSize}">
		<sec:authorize access="hasAnyRole('ROLE_XPU')">
		<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
			<input type="checkbox" id="checkbox${data.id}" name="checkbox" value="${data.id}" tabindex="1"/>
		</display:column>
		</sec:authorize>
		<display:column property="loanNo" title="Loan No"/>
		<display:column property="customerName" title="Customer Name" />		
		<display:column property="remark" title="Remark"/>
		<display:column property="remarkDate" format="{0,date,dd/MM/yyyy}" title="Remark Date" />			
		<display:column property="createBy" title="Upload By" />
		<display:column property="createDate" format="{0,date,dd/MM/yyyy}" title="Upload Date" />
		<display:column property="returnDate" format="{0,date,dd/MM/yyyy}" title="Return Date" />	
		<display:column property="returnBy" title="Return By" />
		<display:column property="returnNote" title="Return Note" />
	</display:table>
</form>
 
</div>
<div id="popup-remark-div" class="hidden dialog" title="Remark">
	<form method="post" id="popupRemarkForm">
		<input type="hidden" name="btnSubmit" value="Remark"/>
		Date Remark: <input id="dateRemark" type="text" name="dateRemark" size="12" value="" /><br/>
		Remark:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>
<div id="popup-send-div" class="hidden dialog" title="Send">
	<form method="post" id="popupSendForm">
		<input type="hidden" name="btnSubmit" value="Send"/>
		Date Send: <input id="dateSend" type="text" name="dateSendFromXPU" size="12" value="" /><br/>
		Note:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>