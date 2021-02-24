<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<div id="container" style="width: 400px;float:left;">
	<form:form commandName="userPermissionForm" method="POST" action="${pageContext.request.contextPath}/user/permissionmod.html">
		<div id="header" class="info">
			<b>User's permission modification</b>
			<form:hidden path="id"/>
		</div>
		
		<p>
            <b>
            	&nbsp;&nbsp;&nbsp;&nbsp;Note: Please choose permission and click Save to update
            	<font color="blue">
					&nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors path="*" /></b>
				</font> 
            </b>
        </p>

		<div style="padding-top: 5px; padding-left: 10px;">
			<ul style="float: left; padding: 0; width: 100%;">
				<li>User name: &nbsp;&nbsp; <c:out value="${userPermissionForm.username}"></c:out></li>
				<li>Email : &nbsp;&nbsp; <c:out value="${userPermissionForm.emailCode}"></c:out></li>
				<li>User place : &nbsp;&nbsp; <c:out value="${userPermissionForm.userPlace}"></c:out></li>
				<li>Permissions : &nbsp;&nbsp; </li>
			</ul>
			
		</div>
		
		<div style="padding-top: 5px; padding-left: 10px; position: relative;">
			<form:checkboxes items="${userPermissionForm.allPermission}" path="grantedPermission" delimiter="<br>"/>
		</div>
		
		<div style="padding-top: 25px; padding-left: 10px;">
			<input type="submit" value="Save" />
		</div>
	</form:form>
</div>
