<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="container" style="width: 600px;margin: auto;">

	<form:form commandName="changePwdForm" method="POST" action="${pageContext.request.contextPath}/auth/changepwd.html">
		<div id="header" class="info">
			<b>Change password for user <span class="red"><c:out value="${changePwdForm.username}"></c:out></span></b>
			<form:hidden path="username"/>
		</div>
		
		<ul style="float: left; padding: 0; width:100%">
			<li><b>You are prompted to change your password because one of following reasons:</b></li>
			<li>- Your account is firstly logged in.</li>
			<li>- Your account has been reset by you (using reset password function) or administrator.</li>
			<li>- Your account was expired (after 90 days).</li>
			<li>- You want to change your password manually by clicking "Change password" link.</li>
		</ul>
		
		<ul style="float: left; padding: 0; width:100%">
			<li><b><u>Password policy:</u></b></li>
			<li>- Must contains at least one digit from 0-9</li>
			<li>- Must contains at least one character</li>
			<li>- Must contains at least one of special symbols in the list "!@#$%"</li>
			<li>- Length at least 6 characters and maximum of 20</li>
		</ul>
		
		<ul style="float: left; padding: 0; width:100%">
			<li><b><u>Note:</u> All fields are required !</li>
		</ul>
        
        <font color="red" >
			<b ><form:errors path="*" /></b>
		</font> 
        		
		<div style="padding-top: 5px; padding-left: 10px; width: 80%">
			<ul style="float: left; padding: 0; width:50%">
				<li>Old password : &nbsp;&nbsp; </li>
				<li>New password : &nbsp;&nbsp; </li>
				<li>Re-enter password : &nbsp;&nbsp; </li>
				<li class="buttons">
					<input id="saveForm" name="saveForm" class="btnSubmit" type="submit" value="Submit" />
					<!-- <input name="reset" type="reset" class="btnSubmit"/> -->
				</li>
			</ul>
			<ul style="float: right; padding: 0; width:50%">
				<li class="changepwd">
					<form:password path="oldPwd" cssClass="field text medium" />
					<font color="red">(*)</font>
				</li>
				<li class="changepwd">
					<form:password path="newPwd" cssClass="field text medium" />
					<font color="red">(*)</font>
				</li>
				<li class="changepwd">
					<form:password path="reenterredNewPwd" cssClass="field text medium" />
					<font color="red">(*) </font>
				</li>
			</ul>
		</div>
	</form:form>
</div>
