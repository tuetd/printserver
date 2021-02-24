<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  	<center>
				<b>Hello, please login to continue...</b>
	  	<br />
	    <c:if test="${not empty param.login_error}">
	      <font color="red">
	        Your login attempt was not successful, try again.<br/>
	        Reason: 
	  		<c:if test="${not empty messages}">
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
             </c:if>
	      </font>
	    </c:if>
	 	<br />
	    <form name="f" action="<%=request.getContextPath() %>/j_spring_security_check" method="POST">
	      <table>
	        <tr><td align="left">User:</td><td><input style="width:150px" type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
	        <tr><td align="left">Password:</td><td><input style="width:150px" type='password' name='j_password'></td></tr>
	        <tr><td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr>
	 		<tr><td colspan=2><a href="<%=request.getContextPath() %>/resetpwd.html"><font color="blue">Forgot password ?</font></a></td></tr>
	        <tr><td colspan='2'><br /></td></tr>
	        <tr>
		        <td align="center" colspan='2'>
		        <input name="submit" type="submit" value="Login">
		        <input name="reset" type="reset"></td>
	        </tr>
	      </table>
	    </form>
	    </center>
