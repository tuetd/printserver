<%@ include file="../layouts/taglibs.jsp"%>
<div id="dialog-detail-case" class="hidden dialog" title="Record detail">
	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<div id="container" style="float: left;">
		
<div style="margin-top: 5px;padding: 5px; background: #EBE5D9;">
	<fieldset style="margin-top:5px">
	<legend>Customer informations</legend>
	<table style="width: 800px;">
		<tr>
			<td class="width20">Customer name</td>
			<td class="width30"><b><span id="r_customername">${record.customername }</span></b></td>
			<td class="width20">Dob</td>
			<td class="width30"><span id="r_dob"><fmt:formatDate pattern="dd-MM-yyyy"
					value="${record.dob }" /></span>
			</td>
		</tr>
		<tr>
			<td>Address 1</td>
			<td><span id="r_address1">${record.address1 }</span></td>
			<td>Sex</td>
			<td>
				<span id="r_sex">${record.sex }</span>
			</td>
		</tr>

		<tr>
			<td>Address 2</td>
			<td><span id="r_address2">${record.address2 }</span></td>
			<td>National id</td>
			<td><span id="r_panNo">${record.panNo }</span></td>
		</tr>

		<tr>
			<td>Address 3</td>
			<td><span id="r_address3">${record.address3 }</span></td>
			<td>Mobile</td>
			<td><span id="r_mobile">${record.mobile}</span></td>
		</tr>
		<tr>
			<td>Address 4</td>
			<td><span id="r_address4">${record.address4 }</span></td>
			<td>Phone 1</td>
			<td><span id="r_phone1">${record.phone1 }</span></td>
		</tr>
		<tr>
			<td>District</td>
			<td><span id="r_city">${record.city }</span></td>
			<td>Phone 2</td>
			<td><span id="r_phone2">${record.phone2 }</span></td>
		</tr>
		<tr>
			<td>Province</td>
			<td><span id="r_statedesc">${record.statedesc }</span></td>
		</tr>
	</table>
	</fieldset>
	<fieldset style="margin-top:10px">
	<legend>Record informations</legend>
	<table style="width: 800px;">
		<tr>
			<td class="width20">Agreement no</td>
			<td class="width30"><b><span id="r_agreementno">${record.agreementno }</span></b></td>
			<td class="width20">Creation date</td>
			<td class="width30"><span id="r_ct"><fmt:formatDate pattern="dd-MM-yyyy"
					value="${record.ct}" /></span>
			</td>
		</tr>
		<tr>
			<td>Disbursal date</td>
			<td><span id="r_disbursaldate"><fmt:formatDate pattern="dd-MM-yyyy"
					value="${record.disbursaldate }" /></span></td>
			<td>Modified date</td>
			<td><span id="r_mt"><fmt:formatDate pattern="dd-MM-yyyy" value="${record.mt}" /></span>
			</td>
		</tr>
		<tr>
			<td>Loan amount</td>
			<td><span id="r_amtRequested"><fmt:formatNumber value="${record.amtRequested }"
					type="number" /></span> VND</td>
			<td>App form no</td>
			<td><span id="r_appFormno">${record.appFormno }</span></td>
		</tr>
		<tr>
			<td>Emi</td>
			<td><span id="r_emi">${record.emi }</span></td>
			<td>Credit shield</td>
			<td><span id="r_creditShield"><fmt:formatNumber value="${record.creditShield }"
					type="number" /></span> VND</td>
		</tr>
		<tr>
			<td>Dueday</td>
			<td><span id="r_dueday">${record.dueday }</span></td>
			<td>Product</td>
			<td><span id="r_productflag">${record.productflag }</span></td>
		</tr>
		<tr>
			<td>Tenure</td>
			<td><span id="r_tenure">${record.tenure }</span></td>
			<td>Relationship officer</td>
			<td><span id="r_lastRoName">${record.lastRoName }</span></td>
		</tr>
		<tr>
			<td>Maturity Date</td>
			<td><span id="r_maturitydate"><fmt:formatDate pattern="dd-MM-yyyy"
					value="${record.maturitydate }" /></span>
			</td>
			<td>Supplier</td>
			<td><span id="r_supplierName">${record.supplierName }</span></td>
		</tr>
		<tr>
			<td>Branch</td>
			<td><span id="r_branchdesc">${record.branchdesc }</span></td>
			<td>Source</td>
			<td><span id="r_sourceState">${record.sourceState }</span></td>
		</tr>
		<tr>
			<td>Area</td>
			<td><span id="r_area">${record.area }</span></td>
		</tr>
	</table>
	</fieldset>

	<fieldset style="margin-top:10px">
	<legend>Box informations</legend>
	<table style="width: 800px;">
		<tr>
			<td class="width20">Box label</td>
			<td class="width30"><span id="r_boxLabel">${record.boxLabel }</span></td>
			<td class="width20">Box date</td>
			<td class="width30"><span id="r_boxDate"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
					value="${record.boxDate }" /></span>
			</td>
		</tr>
	</table>
	</fieldset>

	<fieldset style="margin-top:10px">
	<legend>Loan: Transaction logs</legend>
		<table id="dialog-detail-case-dtl" class="dataTable">
		</table>
	</fieldset>
	
	<fieldset style="margin-top:10px">
	<legend>Record: Transaction logs</legend>
		<table id="dialog-detail-case-dtl2" class="dataTable">
		</table>
	</fieldset>
	</div>	
	</div>
</div>
