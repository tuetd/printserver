<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<html>
  <head>
    <title>Login</title>
  </head>
 
  <body>
  	<br />
  	<br />
  	<br />
  	<br />
  	<br />
  	<br />
  	<br />
    <form:form commandName="resetPwdForm" method="POST" action="resetpwd.html">
    	<fieldset>
		<legend>
			<b>FORGOT PASSWORD ? RESET IT !</b>
		</legend>
		<br />
    	<font color="red">
			&nbsp;&nbsp;&nbsp;&nbsp;<b>Note: Please provide your domain's email registered with system</b>
		</font> 
		<br /><br />
		<font color="blue">
			&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="*" />
		</font> 
		<br /><br />
      <table>
      <tr>
        	<td align="left"><fmt:message key="label.username" /></td>
        	<td><form:input style="width:150px" path="username" name="username" /></td>
        </tr>
        <tr>
        	<td align="left"><fmt:message key="user.create.useremail" /></td>
        	<td><form:input style="width:150px" name="email" path='email' /></td>
        </tr>
        <tr>
        	<td colspan='2'><br /></td>
        </tr>
        <tr>
	        <td align="center" colspan='2'>
	        <input name="submit" type="submit" value="Reset">
	        <input name="home" type="button" value="Login" onclick="window.location.href='<%=request.getContextPath() %>/auth/login.html'"></td>
        </tr>
      </table>
      </fieldset>
    </form:form>
  </body>
</html>