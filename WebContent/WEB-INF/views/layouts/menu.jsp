<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="ddtopmenubar" class="mattblackmenu">
	<ul>
		<li><a href="<%=request.getContextPath()%>">Home page</a>
		</li>
		<sec:authorize access="hasAnyRole('ROLE_AD_ACCEPT','ROLE_AD_SEND')">
			<li><a href="<%=request.getContextPath()%>/autodebit/index.html">Auto debit</a>
			</li>
		</sec:authorize>
		<%--<sec:authorize access="hasAnyRole('ROLE_AD_ACCEPT','ROLE_AD_SEND')">
			<li><a href="<%=request.getContextPath()%>/document/index.html">Document Maintenance</a>
			</li>
		</sec:authorize>--%>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT', 'ROLE_RM_BRANCH','ROLE_VIEW_LOAN')">
			<li><a href="<%=request.getContextPath()%>/loan/index.html" rel="loan">Loan</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/record/index.html" rel="record">Loan Kit</a>
			</li>
		</sec:authorize>		
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<li><a href="<%=request.getContextPath()%>/po/index.html" rel="post">Post Office</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT', 'ROLE_RM_BRANCH')">
			<li><a href="<%=request.getContextPath()%>/recordbranch/index.html" rel="branch">Branch</a>
			</li>
		</sec:authorize>		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/cs/index.html" rel="creditShield">Credit Shield</a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
			<li><a href="<%=request.getContextPath()%>/ic/index.html" rel="insuranceCertificate">Insurance Certificate</a>
			</li>
		</sec:authorize>
	
		<li><a href="<%=request.getContextPath()%>/utilities/index.html" rel="utilities">Utilities</a>
		</li>

		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
			<li><a href="<%=request.getContextPath()%>/uploadData/mrc.html" rel="upload">Upload</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT', 'ROLE_ADMIN')">
			<li><a href="<%=request.getContextPath()%>/storedLoan/index.html" rel="storedLoan">StoredLoan</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
			<li><a href="<%=request.getContextPath()%>/tb6/index.html" rel="tb6">Tb6</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT', 'ROLE_XPU')">
			<li><a href="<%=request.getContextPath()%>/foreclosure/index.html" rel="foreclosure">Foreclosure</a>
			</li>
		</sec:authorize>
		
		<li><a href="<%=request.getContextPath()%>/auth/changepwd.html" rel="usermanagement">User management</a>
		</li>
	</ul>
</div>

<script type="text/javascript">
ddlevelsmenu.setup("ddtopmenubar", "topbar");
</script>

<!--HTML for the Drop Down Menus associated with Top Menu Bar-->
<!--They should be inserted OUTSIDE any element other than the BODY tag itself-->
<!--A good location would be the end of the page (right above "</BODY>")-->

<!--Top Drop Down Menu 1 HTML-->
<%-- <ul id="autodebit" class="ddsubmenustyle">
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT','ROLE_RM_BRANCH')">
		<li><a href="<%=request.getContextPath()%>/autodebit/index.html">Auto debit tree</a>
		</li>
	</sec:authorize>
</ul> --%>


<ul id="loan" class="ddsubmenustyle">
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH','ROLE_VIEW_LOAN')">
		<li><a href="<%=request.getContextPath()%>/loan/index.html">Disbursal Loan</a>
		</li>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
		<li><a href="<%=request.getContextPath()%>/loan/rmt.html">Disbursal Loan RMT</a>
		</li>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
		<li><a href="<%=request.getContextPath()%>/loan/rmtReceive.html">Loan Receive</a>
		</li>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_RM_BRANCH')">
		<li><a href="<%=request.getContextPath()%>/loan/wait.html">Loan Waiting</a>
		</li>
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
		<li><a href="<%=request.getContextPath()%>/loan/suspense.html">Suspense Loan</a>
		</li>
	</sec:authorize>
</ul>
<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
	<ul id="record" class="ddsubmenustyle">
		<li>
			<a href="<%=request.getContextPath()%>/record/verified.html">Loan Kit Verified</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/record/index.html">Loan Kit Prepare</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/record/rd.html">Loan Kit Ready List</a>
		</li>
			
		<li><a href="<%=request.getContextPath()%>/record/notsend.html">Loan Kit Not Send</a></li>
	
		<li><a href="<%=request.getContextPath()%>/record/pending.html">Loan Kit Pending</a></li>
		<%-- <li>
			<a href="<%=request.getContextPath()%>/record/waitingcs.html">Loan Kit Waiting CS</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/record/completedcs.html">Loan Kit Completed CS</a>
		</li> --%>
	</ul>
</sec:authorize>


<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
<ul id="post" class="ddsubmenustyle">
	<li>
		<a href="<%=request.getContextPath()%>/po/index.html">Loan Kit Send To Post Office</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/po/return.html">Loan Kit Return From Post Office</a>
	</li>
</ul>
</sec:authorize>


<ul id="branch" class="ddsubmenustyle">
	<li>
		<a href="<%=request.getContextPath()%>/recordbranch/index.html">Loan Kit Send To Branch</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/recordbranch/receive.html">Loan Kit Receive At Branch</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/recordbranch/return.html">Loan Kit Return From Branch</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/recordbranch/deliver.html">Loan Kit Delivered</a>
	</li>
</ul>

 <ul id="creditShield" class="ddsubmenustyle">
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

</ul>

 <ul id="insuranceCertificate" class="ddsubmenustyle">
	<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
		<li><a href="<%=request.getContextPath()%>/ic/index.html">Insurance Certificate Waiting List</a>
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

<ul id="utilities" class="ddsubmenustyle">
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<li>
		<a href="<%=request.getContextPath()%>/utilities/index.html">Synchronize Disb Loan</a>
	</li>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
	<li>
		<a href="<%=request.getContextPath()%>/utilities/revert.html">Revert Loan</a>
	</li>
	</sec:authorize>
	
	<%-- <sec:authorize access="hasAnyRole('ROLE_RM_RMT_MANAGER')">
	<li>
		<a href="<%=request.getContextPath()%>/utilities/revertCS.html">Revert Credit Shield</a>
	</li>
	</sec:authorize>
	 --%>
	<li>
		<a href="<%=request.getContextPath()%>/utilities/search.html">Search Loan</a>
	</li>
    <sec:authorize access="hasAnyRole('ROLE_RMT_IBMCM')">
    <li><a href="<%=request.getContextPath()%>/utilities/icmdocument.html">Get Credit Shield Document</a></li>
	</sec:authorize>
</ul>

<ul id="upload" class="ddsubmenustyle">
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/mrc.html">MRC</a>
	</li>
	
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/home_loan.html">Home Loan</a>
	</li>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
	<li>
		<a href="<%=request.getContextPath()%>/uploadData/insurance.html">Insurance Cancel</a>
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
</ul>
 
<ul id="tb6" class="ddsubmenustyle">
	
	<li>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_CS')">
			<a href="<%=request.getContextPath()%>/tb6/index.html">Upload TB6 List</a>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
			<a href="<%=request.getContextPath()%>/tb6/send.html">Send List</a>
			<a href="<%=request.getContextPath()%>/tb6/waiting.html">Waiting List</a>
			<a href="<%=request.getContextPath()%>/tb6/complete.html">Complete List</a>
		</sec:authorize>
	</li>	
	
</ul>
<ul id="foreclosure" class="ddsubmenustyle">
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
<ul id="storedLoan" class="ddsubmenustyle">
	<li>
		<sec:authorize access="hasAnyRole('ROLE_RM_RMT','ROLE_ADMIN')">
			<a href="<%=request.getContextPath()%>/storedLoan/list.html">Stored Loan List</a>
			<a href="<%=request.getContextPath()%>/storedLoan/index.html">Upload Stored Loan</a>
			 <a href="<%=request.getContextPath()%>/storedLoan/update.html">Update Status Storing</a>
		</sec:authorize>
	</li>	
</ul>
<ul id="usermanagement" class="ddsubmenustyle">
	<sec:authorize access="isAuthenticated()">
		<li><a href="<%=request.getContextPath()%>/auth/changepwd.html">Change
				Password</a>
		</li>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href="<%=request.getContextPath()%>/admin/deptlist.html">Department
				Management</a>
		</li>
	
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
		
		<li><a href="<%=request.getContextPath()%>/admin/screenlist.html">Screen
				Management</a>
		</li>
	</sec:authorize>

	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HELPDESK','ROLE_HELPDESK_MANAGER')">
		<li><a href="<%=request.getContextPath()%>/user/userlist.html">User
				Management</a>
		</li>
	</sec:authorize>
</ul>