

<div id="ddtopmenubar" class="mattblackmenu">
<ul>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','OTHERS')">
<li><a href="<%=request.getContextPath()%>/listDocument.html">Document</a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
<li><a href="#" rel="usermanagement">User management</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ADMINISTRATOR')">
<li><a href="<%=request.getContextPath()%>/list.html">Reprint Document</a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','FCL REPORT')">
<li><a href="<%=request.getContextPath()%>/reportFcl.html">FCL Report</a></li>
</sec:authorize>		
</ul>
</div>

<script type="text/javascript">
ddlevelsmenu.setup("ddtopmenubar", "topbar");
</script>


<ul id="usermanagement" class="ddsubmenustyle">
<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
		<li><a href="<%=request.getContextPath()%>/listUsermaster.html">User list</a></li>
		
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ADMINISTRATOR','SUPPORT')">
			<li><a href="<%=request.getContextPath()%>/createUserForm.html">Create user</a></li>
		</sec:authorize>
</ul>



