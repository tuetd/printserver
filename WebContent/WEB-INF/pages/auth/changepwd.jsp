<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<div id="container" style="width: 600px;margin: auto;">
	<form:form commandName="changePwdForm" action="changepwd.html" method="POST">
		<div id="header" class="info">
			<b>Change password for user  <font color="red"><c:out  value="${changePwdForm.username}"></c:out></font></b>
			<form:hidden path="username"/>
		</div>
		
		<p>
            <b>
            	&nbsp;&nbsp;&nbsp;&nbsp;Note: All fields are required !
            </b>
        </p>
        
        <font color="blue">
			&nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors path="*" /></b>
		</font> 
        		
		<div style="padding-top: 5px; padding-left: 10px; width: 80%">
			<ul style="float: left; padding: 0; width:50%">
				<li>Old password : &nbsp;&nbsp; </li>
				<li>New password : &nbsp;&nbsp; </li>
				<li>Re-enter password : &nbsp;&nbsp; </li>
				<li class="buttons">
					<input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit" />
					<input name="reset" type="reset" />
				</li>
			</ul>
			<ul style="float: right; padding: 0; width:50%">
				<li class="changepwd">
					<form:password path="oldPwd" cssClass="field text medium" />
					<font color="red">(*) </font>
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
