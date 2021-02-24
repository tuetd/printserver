<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div>
<b style="text-align: left;">Welcome, please select the tool to continue :</b>
</div>


<div style="margin-top: 5px;padding: 5px;float:left;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Loan</legend>
	<ul class="app_list">
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH','ROLE_RM_RMT','ROLE_VIEW_LOAN')">
			<li><a href="<%=request.getContextPath()%>/loan/index.html">Disbursal Loan</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH','ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/loan/rmt.html">Disbursal Loan RMT</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH','ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/loan/rmtReceive.html">Loan Receive</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_BRANCH','ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/loan/wait.html">Loan Waiting</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li>
				<a href="<%=request.getContextPath()%>/cm/index.html">Loan Documents Scan</a>
			</li>
			
			<li>
				<a href="<%=request.getContextPath()%>/cm/check.html">Loan Documents Scan Check</a>
			</li>
			
			<li>
				<a href="<%=request.getContextPath()%>/cm/inBox.html">Loan Documents In Box</a>
			</li>
		</sec:authorize>				
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
			<li><a href="<%=request.getContextPath()%>/loan/suspense.html">Suspense Loan</a></li>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
		<li><a href="<%=request.getContextPath()%>/record/verified.html">Loan Kit Verified</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/index.html">Loan Kit Prepare</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/rd.html">Loan Kit Ready List</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/notsend.html">Loan Kit Not Send</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/pending.html">Loan Kit Pending</a></li>
		</sec:authorize>
		
		<%-- <sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/waitingcs.html">Loan Kit Waiting CS</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/completedcs.html">Loan Kit Completed CS</a></li>
		</sec:authorize> --%>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/po/index.html">Loan Kit Send To Post Office</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/po/return.html">Loan Kit Return From Post Office</a></li>
		</sec:authorize>
	
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
			<li><a href="<%=request.getContextPath()%>/recordbranch/index.html">Loan Kit Send To Branch</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
			<li><a href="<%=request.getContextPath()%>/recordbranch/receive.html">Loan Kit Receive At Branch</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
			<li><a href="<%=request.getContextPath()%>/recordbranch/return.html">Loan Kit Return From Branch</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_AD_ACCEPT','ROLE_AD_SEND')">
			<li><a href="<%=request.getContextPath()%>/autodebit/index.html">Auto Debit</a>
			</li>
		</sec:authorize>
</ul>
</fieldset>
</div>

<div style="margin-top: 5px;padding: 5px;float:left;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Credit Shield</legend>
		<ul class="app_list">
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/index.html">Insurance Certificate Waiting List</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/cs/index.html">Credit Shield List</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/cs/rmt.html">Credit Shield at RMT</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/cs/rmtReceive.html">Credit Shield Receive</a>
			</li>
		</sec:authorize>
	
		<%--
		
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/followup.html">Follow-Up List</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/failure.html">Insurance Certificate Failure List</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/completed.html">Insurance Certificate Completed List</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/sent.html">Insurance Certificate Sent List</a>
			</li>
		</sec:authorize>
		--%>
	</ul>
	</fieldset>
</div>

<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Management</legend>
	
	<ul class="app_list" id="managementUL">
		<sec:authorize access="isAuthenticated()">
			<li><a href="<%=request.getContextPath()%>/auth/changepwd.html">Change Password</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/admin/deptlist.html">Department Management</a></li>
		
			<li><a href="<%=request.getContextPath()%>/admin/grouplist.html">Group
					Management</a>
			</li>
		
			<li><a href="<%=request.getContextPath()%>/admin/branchlist.html">Branch
					Management</a>
			</li>
		
			<li><a href="<%=request.getContextPath()%>/admin/productlist.html">Product
					Management</a>
			</li>
			
			<li><a href="<%=request.getContextPath()%>/admin/levellist.html">User
				Level Management</a>
			</li>
			
			<li><a href="<%=request.getContextPath()%>/admin/screenlist.html">Screen	Management</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HELPDESK','ROLE_HELPDESK_MANAGER')">
			<li><a href="<%=request.getContextPath()%>/user/userlist.html">User Management</a></li>
		</sec:authorize>
	

		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HELPDESK_MANAGER')">
			<li><a href="<%=request.getContextPath()%>/user/usermod.html">Create user</a>
			</li>	
		</sec:authorize>
	</ul>
</fieldset>
</div>

<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Utilities</legend>
	<ul class="app_list floatleft">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/utilities/index.html">Synchronize Disb Loan</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
			<li><a href="<%=request.getContextPath()%>/utilities/revert.html">Revert Loan</a></li>
			<li><a href="<%=request.getContextPath()%>/utilities/revertCS.html">Revert Credit Shield</a></li>
		</sec:authorize>
		<li><a href="<%=request.getContextPath()%>/utilities/search.html">Search Loan</a></li>
		<sec:authorize access="hasAnyRole('ROLE_RMT_IBMCM')">
		<li><a href="<%=request.getContextPath()%>/utilities/icmdocument.html">Get Credit Shield Document</a></li>
		</sec:authorize>			
	</ul>
	</fieldset>
</div>
<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>StoredLoan</legend>
	<ul class="app_list floatleft">
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_ADMIN')">
			<li>
			   <a href="<%=request.getContextPath()%>/storedLoan/list.html">Stored Loan List</a>
		    </li>
		    <li>
			   <a href="<%=request.getContextPath()%>/storedLoan/index.html">Upload Stored Loan</a>
			</li>
			<li>
			   <a href="<%=request.getContextPath()%>/storedLoan/update.html">Update Status Storing</a>
			</li>
		</sec:authorize>			
	</ul>
	</fieldset>
</div>

<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT_MANAGER','ROLE_CS','ROLE_RM_RMT')">
<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Upload</legend>
	<ul class="app_list floatleft">
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
		<li>
			<a href="<%=request.getContextPath()%>/uploadData/mrc.html">MRC</a>
		</li>
		
		<li>
			<a href="<%=request.getContextPath()%>/uploadData/home_loan.html">Home Loan</a>
		</li>
		<li>
		<a href="<%=request.getContextPath()%>/uploadData/loankit_autopost.html">Loan Kit Upload</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/loankit_pending.html">Loan Kit Ready List - Pending</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/loanRMT_autopost.html">Loan RMT- Receive Upload</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/loanWaiting_ScanAuto.html">Loan Waiting- Documents Scan</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/loanReceive_WaitAuto.html">Loan Receive- Waiting</a>
	</li>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/insurance.html">Insurance Cancel</a>
	</li>
	</sec:authorize>
	</ul>
	</fieldset>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>TB6</legend>
	<ul class="app_list floatleft">
		<li>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
			<a href="<%=request.getContextPath()%>/tb6/index.html">Upload TB6 List</a>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<a href="<%=request.getContextPath()%>/tb6/send.html">Send List</a>
			<a href="<%=request.getContextPath()%>/tb6/waiting.html">Waiting List</a>
			<a href="<%=request.getContextPath()%>/tb6/complete.html">Completed List</a>
		</sec:authorize>
	</li>
	</ul>
	</fieldset>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_XPU')">
<div style="margin-top: 5px;padding: 5px;float:right;">
	<fieldset style="margin-top:5px;width:300px">
	<legend>Foreclosure</legend>
	<ul class="app_list floatleft">
		<li>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_XPU')">
			<a href="<%=request.getContextPath()%>/foreclosure/index.html">Upload List</a>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<a href="<%=request.getContextPath()%>/foreclosure/send.html">Send List</a>
			<a href="<%=request.getContextPath()%>/foreclosure/waiting.html">Waiting List</a>
			<a href="<%=request.getContextPath()%>/foreclosure/complete.html">Complete List</a>
		</sec:authorize>
		</li>
	</ul>
	</fieldset>
</div>
</sec:authorize>




