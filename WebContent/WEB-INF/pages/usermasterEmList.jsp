<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
    function deleteObject(id,username, url){
        url = url +'?id=' + id;
        
        var answer = confirm ("Are you sure to want delete this " + username + "?");
        if (answer){
            window.location = url;
        }
    }
    
    </script>
<c:if test="${not empty message}">
	<center style="color: red">${message}</center>
</c:if>
<c:if test="${ not empty Usermasterlist}">
<display:table name="Usermasterlist" cellspacing="0" cellpadding="0" 
    id="Usermaster" pagesize="25" class="tftable"  export="true" requestURI="/listUsermaster.html">
    		<sec:authorize access="hasAnyRole('ADMINISTRATOR','SUPPORT')">
				<display:column property="username" escapeXml="true" sortable="true" title="username" url="/createUserForm.html" paramId="usermasterId" paramProperty="id"></display:column>
			</sec:authorize>
			<sec:authorize access="hasRole('HELPDESK')">
				<display:column property="username" escapeXml="true" sortable="true" title="username"></display:column>
			</sec:authorize>
			<display:column property="id" escapeXml="true"  title="id"  paramId="usermasterId" paramProperty="id"/>
			<display:column property="loggedin" escapeXml="true" title="loggedin"  />
			<display:column property="roleId" escapeXml="true"  title="roleId"  />
			<display:column property="sessionId" escapeXml="true"  title="sessionId"  />
			<display:column property="lastChangedPw" escapeXml="true"  title="lastChangedPw" />
			<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
			<display:column class="margin-left: 50px;display: inline;"  >
			 <a href="resetpassword.html?id=${Usermaster.id}"><img title="Reset password" src="<%=request.getContextPath() %>/images/arrow_refresh_small.png" /></a>
			 &nbsp;
			 <a href="unlockuser.html?id=${Usermaster.id}"><img title="Unlock" src="<%=request.getContextPath() %>/images/lock_unlock.png" /></a>
			 &nbsp;
			  <a href="#" onclick="deleteObject('${Usermaster.id}','${Usermaster.username}','deleteuser.html')"><img src="<%=request.getContextPath() %>/images/delete.png" title="Delete" width="16" height="16" /></a>
			</display:column>
			</sec:authorize>
			<display:column class="margin-left: 50px;display: inline;" title="status" >
				<c:if test="${Usermaster.status==0}">
					Inactive
				</c:if>
				<c:if test="${Usermaster.status==1}">
					Active
				</c:if>
				<c:if test="${Usermaster.status==2}">
					Locked
				</c:if>
				<c:if test="${Usermaster.status==3}">
					Deleted
				</c:if>
			</display:column>
			
</display:table>
</c:if>
<c:if test="${empty Usermasterlist}">
	<left>No records found !</left>
</c:if>