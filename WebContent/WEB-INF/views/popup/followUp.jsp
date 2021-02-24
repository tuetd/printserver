<%@ include file="../layouts/taglibs.jsp"%>

<div id="dialog-follow-up-case" class="hidden dialog" title="Follow Up List">
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
		<div id="dialog-detail-follow-up-case" class="hidden dialog" title="Follow Up">
			<form id="followUpForm" action="/loan/followUpMod" enctype="application/x-www-form-urlencoded">
				<input type="hidden" name="recordId" id="recordIdPopupFU" />
					<table class="textleft">
						<tr>
							<td>Category :</td>
							<td><select name="category">
									<option value="All documents are not verified">All documents are not verified</option>
									<option value="Not verified">Not verified</option>
									<option value="Need more information">Need more information</option>
									<option value="No photograph on Ap. Form">No photograph on Ap. Form</option>
									<option value="No signature on Ap. Form">No signature on Ap. Form</option>
									<option value="Other reason">Other reason</option>
							</select></td>
						</tr>
						<tr>
							<td>Description :</td>
							<td><textarea name="description" id="description" cols="23"
									rows="5"></textarea>
							</td>
						</tr>
					</table>
				</form> 
		</div>
		<button onclick="goShowAddFollowUp()" class="btnSubmit"> Add new Follow Up </button>
	</sec:authorize>
	<br>
	<table id="dialog-follow-up-case-dtl" class="dataTable">
	</table>
</div>
