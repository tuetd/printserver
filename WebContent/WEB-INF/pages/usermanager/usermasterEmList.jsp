<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.lang.String" %>
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
    id="Usermaster" pagesize="25" class="tftable" style="width: 960px;" export="true" requestURI="/listUsermaster.html">
    		<sec:authorize access="hasAnyRole('ADMINISTRATOR','SUPPORT')">
				<display:column property="username" escapeXml="true" sortable="true" title="Username" url="/createUserForm.html" paramId="usermasterId" paramProperty="id" ></display:column>
			</sec:authorize>
			<sec:authorize access="hasRole('HELPDESK')">
				<display:column property="username" escapeXml="true" sortable="true" title="Username" ></display:column>
			</sec:authorize>
			<display:column property="fullname" escapeXml="true"  title="Full Name">
			</display:column>
			<display:column property="usercodeid" escapeXml="true"  title="Employee ID"  />
			<display:column property="department" escapeXml="true" title="Department"  />
			<display:column escapeXml="true"  title="Role" media="csv excel">
				<c:if test="${Usermaster.roleId==1}">
					OTHERS
				</c:if>
				<c:if test="${Usermaster.roleId==2}">
					PAYMENT
				</c:if>
				<c:if test="${Usermaster.roleId==3}">
					CUSTOMER SERVICE
				</c:if>
				<c:if test="${Usermaster.roleId==4}">
					ICU
				</c:if>
				<c:if test="${Usermaster.roleId==5}">
					TB6
				</c:if>
				<c:if test="${Usermaster.roleId==6}">
					ADMINISTRATOR
				</c:if>
				<c:if test="${Usermaster.roleId==7}">
					HELPDESK
				</c:if>
			</display:column>
		
			<display:column property="last_changed_pw" escapeXml="true"  title="LastChangedPw" />
			<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
			<display:column class="margin-left: 50px;display: inline;"  >
			 <a href="resetpassword.html?id=${Usermaster.id}"><img title="Reset password" src="<%=request.getContextPath() %>/images/arrow_refresh_small.png" /></a>
			 &nbsp;
			 <a href="unlockuser.html?id=${Usermaster.id}"><img title="Unlock" src="<%=request.getContextPath() %>/images/lock_unlock.png" /></a>
			 &nbsp;
			  <a href="#" onclick="deleteObject('${Usermaster.id}','${Usermaster.username}','deleteuser.html')"><img src="<%=request.getContextPath() %>/images/delete.png" title="Delete" width="16" height="16" /></a>
			</display:column>
			</sec:authorize>
			<display:column class="margin-left: 50px;display: inline;" title="Status"  >
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
			  <display:setProperty name="export.excel.filename" value="userlist.xls"/>
			  <display:setProperty name="export.pdf" value="false"/>
			  <display:setProperty name="export.csv" value="false"/>
			  <display:setProperty name="export.xml" value="false"/>
</display:table>
</c:if>
<c:if test="${empty Usermasterlist}">
	<left>No records found !</left>
</c:if>