<%@ include file="../layouts/taglibs.jsp"%>
<div id="container">
	<div id="header" class="info">
		<b>Stored Loan List</b>
	</div>
<form:form commandName="filterStoredLoanForm" method="post" action="${pageContext.request.contextPath}/storedLoan/list.html">
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
					<td><label> BarCode</label>
					</td>
					<td><form:input path="barCode" size="15" />
					</td>
					<td><label> Name Box</label>
					</td>
					<td><form:input path="nameBox" size="15" />
					</td>
					<td>Display <form:select path="pageSize">
						<form:option value="50"> 50 </form:option>
						<form:option value="100"> 100 </form:option>
						<form:option value="200"> 200 </form:option>
						<form:option value="0"> All </form:option>
						</form:select> 
					</td>					
					<td>
						<input type="submit" name="btnFilter" value="filter" id="actSearch" class="filter-button"> 
						<input type="submit" name="btnFilter" value="clear" class="clear-button">
					</td>
				</tr> 

			</tbody>
		
		</table>

	</div>
</div>
</form:form>
<br style="clear:both;">
    <form name="displ" method="post">
	<br>
	<display:table size="resultSize" export="true" cellpadding="1" cellspacing="1" id="data" name="recordList" class="tftable" requestURI="/storedLoan/list.html" pagesize="${pageSize}">
		<display:column property="block" title="Block"/>
		<display:column property="no" title="No" />		
		<display:column property="loanNo" title="Loan No" />	
		<display:column property="disbursalDate" format="{0,date,dd/MM/yyyy}" title="Disbursal date" />
		<display:column property="customerName" title="Customer Name"/>
		<display:column property="nameBox" title="Name Box"/>
		<display:column property="barCode" title="Barcode"/>
		<display:column property="dateSent" title="Date sent</br>Crown Store"/>
		<display:column property="destroyDate" title="Date of</br>Destroying"/>		
		<display:column property="remark" title="Remark"/>
		<display:column property="createBy" title="Upload By" />
		<display:column property="createDate" format="{0,date,dd/MM/yyyy}" title="Upload Date" />
		<display:column property="updateBy" title="Updated By" />
		<display:column property="updateDate" format="{0,date,dd/MM/yyyy}" title="Updated Date" />	
		<display:column style="width:2%;" title="Edit"  media="html">
			<img onclick="javascript:viewLoanDetail('${data.id}');return false;" alt="click to edit" src="../images/common/detail.png" />
		</display:column>
	</display:table>
</form>

</div>
<div style="visibility: hidden;" id="resultloading"></div>
<div id="dialog-loan-detail" title="Stored Loan Detail" class="hidden dialog">
	<table class="textleft" border="0" width="100%">
		<tr>
		<input id="dialog-loan-detail-restoredLoanId" type="hidden" name="dialog-loan-detail-restoredLoanId"/>
		</tr>
		<tr>
			<td style="width:15%">Loan No</td>
			<td style="width:40%">
			<input class="width90" readonly="readonly" type="text" name="dialog-loan-detail-loanNo" id="dialog-loan-detail-loanNo" />
			</td>
			<td style="width:45%"></td>
		</tr>
		<tr>
		<td>Customer Name </td>
		<td>
		<input class="width90" readonly="readonly" type="text" name="dialog-loan-detail-customerName" id="dialog-loan-detail-customerName" />
		</td>
		</tr>
		<tr>
		<td>Disbursal Date</td>
		<td>
		<input class="width90 start-term-date" readonly="readonly" type="text" name="dialog-loan-detail-disbursalDate" id="dialog-loan-detail-disbursalDate" />
		</td>
		</tr>
		<tr>
		<td>Block</td>
		<td>
		<input class="width90" type="text" name="dialog-loan-detail-block" id="dialog-loan-detail-block" />
		</td>
		</tr>
		<tr>
		<td>No</td>
		<td>
		<input class="width90" type="text" name="dialog-loan-detail-no" id="dialog-loan-detail-no" />
		</td>
		</tr>
		<tr>
		<td>Bar Code</td>
		<td>
		<input class="width90" type="text" name="dialog-loan-detail-barCode" id="dialog-loan-detail-barCode" />
		</td>
		</tr>
		<tr>
		<td>Name Box</td>
		<td>
		<input class="width90"  type="text" name="dialog-loan-detail-nameBox" id="dialog-loan-detail-nameBox" />
		</td>
		</tr>
		<tr>
		<td>Date sent</td>
		<td>
		<input class="width90"  type="text" name="dialog-loan-detail-dateSent" id="dialog-loan-detail-dateSent" />
		</td>
		</tr>
		<tr>
		<td>Date of Destroying</td>
		<td>
		<input class="width90" type="text" name="dialog-loan-detail-dateDestroy" id="dialog-loan-detail-dateDestroy" />
		</td>
		</tr>
		<tr>
			<td>Remark </td>
			<td colspan="5">
				<input type="text" id="dialog-loan-detail-remark" name ="dialog-loan-detail-remark" style="width:95%" maxlength="1000" />
			</td>
		</tr>
	</table>
</div>

