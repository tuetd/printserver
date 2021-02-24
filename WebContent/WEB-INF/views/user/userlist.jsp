	<%@ include file="../layouts/taglibs.jsp"%>
	<script type="text/javascript">
	function goEditUser(id) {
		window.location.href = "<%=request.getContextPath() %>/user/usermod.html?id=" + id;
	}
	
	function goEditPermissionUser(id) {
		window.location.href = "<%=request.getContextPath() %>/user/permissionmod.html?id=" + id;
	}
	
	function goResetPassword(id, username) {
		if (confirm("Do you want to reset password for user : " + username + " ?")) { 
			window.location.href = "<%=request.getContextPath() %>/user/resetpwd.html?id=" + id;
		}
		
	}
	
	function changeStatusUser(id, username, isActived) {
		var msg ='';
		if(isActived == 1) {
			msg = "Do you want to deactive user : " + username + " ?";
		} else {
			msg = "Do you want to active user : " + username + " ?";
		}
		
		if (confirm(msg)) { 
				window.location.href = "<%=request.getContextPath() %>/user/userdel.html?id=" + id + "&isActived=" + isActived;
		}
		
	}
	
	function setFocus()
	{
		document.getElementById("username").focus();
	}

	$(document).ready(function() {
		setFocus();
	});
	
	function reportExcelUser(){
		var username=jQuery("#username").val();
		window.location.href = "<%=request.getContextPath() %>/user/prudentialreport.html?username=" + username+ "&type=listuser";
	}
	
</script>
	<div id="wrapper">
		<div id="twocenter-columns">
			<form:form class="box" commandName="searchUserForm" action="search.html" method="post">
		<form:errors path="*" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label>	Search username :  </label>
						</div>
						<div class="inputtext">
						<form:input id="username" path="username" />
						</div>
						<div class="rowtest"></div>
					</div>
					<br/>
					
					
						
					</div>
				    <div id="column2test">
				   
					<div class="rowtest">
						<div class="checkbutton">
							<input class="btnLogin" type="submit" name="search" value="Search" />
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
			</form:form>
	</div>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HELPDESK_MANAGER')">
			<p class="right">
			<a href='<c:url value="/user/usermod.html" />'><b>Create user</b></a>
			</p>
		</sec:authorize>
		<display:table name="userList" cellspacing="0" cellpadding="0" 
    id="data" pagesize="25" class="tftable" defaultsort="8" defaultorder="descending"  requestURI="userlist.html" >
			<display:column property="username" title="User name" sortable="true"  media="html excel pdf"></display:column>
			<display:column property="fullname" title="Full name" sortable="true" media="html excel pdf" />
			<display:column property="emailCode" title="Email" media="html excel pdf"   />
			<display:column property="userPlace" title="Place" media="html excel pdf"   />
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HELPDESK_MANAGER')">
				<display:column  title="Edit" media="html">
					<img onclick="javascript:goEditUser('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" style="cursor:pointer;"/>
				</display:column>
				<display:column  title="Permission"  media="html">
					<img onclick="javascript:goEditPermissionUser('${data.id}');return false;" alt="click to set permission" src="<%=request.getContextPath() %>/images/common/permission.png" style="cursor:pointer;"/>
				</display:column>
			</sec:authorize>
			<display:column  title="Reset pwd"  media="html">
				<img onclick="javascript:goResetPassword('${data.id}', '${data.username}');return false;" alt="click to reset password" src="<%=request.getContextPath() %>/images/common/reset_password.png" style="cursor:pointer;"/>
			</display:column>
			<display:column title="Status" >
				<c:choose>
					<c:when test="${data.isActived eq 1}"> <a onclick="javascript:changeStatusUser('${data.id}', '${data.username}', '${data.isActived}');return false;" style="cursor:pointer;"> Active  </a> </c:when>
					<c:otherwise><a onclick="javascript:changeStatusUser('${data.id}', '${data.username}', '${data.isActived}');return false;" style="cursor:pointer;">Inactive</a></c:otherwise>
				</c:choose>
			</display:column>
</display:table>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<form  action="reportuser.html" method="post">
		<input class="btnSubmit" type="submit" name="search" value="Export User List" />
	</form>
</sec:authorize>
