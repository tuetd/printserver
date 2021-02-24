<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%> 

<html>
  <head>
    <title>Login</title>
  </head>
  
  
  <body>
  	<br /><br /><br /><br /><br /><br /><br />
  	<c:if test="${not empty message}">
		<center class="errorMessage" >${message}</center>
	</c:if>
  	<center>
	    <div id="header" class="info">
				<b>Hello, please login to continue...</b>
			</div>
	 
	  	<br />
	    <c:if test="${not empty param.login_error}">
	      <font color="red">
	        Your login attempt was not successful, try again.<br/>
	        Reason: 
	         <c:if test="${empty messages}">
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
             </c:if>
	  		<c:if test="${not empty messages}">
                <c:out value="${messages}"/>
             </c:if>
	      </font>
	    </c:if>
	 	<br />
	    <form name="f" action="<%=request.getContextPath()%>/j_spring_security_check" method="POST">
			<table>
				<tr>
					<td align="left">User:</td>
					<td><input style="width: 150px" type='text' name='j_username' />
					</td>
				</tr>
				<tr>
					<td align="left">Password:</td>
					<td><input style="width: 150px" type='password'
						name='j_password'>
					</td>
				</tr>
				<!-- <tr>
					<td><input type="checkbox" name="_spring_security_remember_me">
					</td>
					<td>Don't ask for my password for two weeks</td>
				</tr> -->
				<tr>
					<td colspan=2><a
						href="<%=request.getContextPath()%>/auth/resetpwd.html"><font
							color="blue">Forgot password ?</font>
					</a>
					</td>
				</tr>
				<tr>
					<td colspan='2'><br />
					</td>
				</tr>
				<tr>
					<td align="center" colspan='2'><input name="submit"
						type="submit" value="Login" class="btnSubmit"> <!--  <input name="reset" type="reset" class="btnSubmit"></td> -->
				</tr>
			</table>
		</form>
	    </center>
  </body>
  
  
  
  
  
 
<!--   <body> -->
<!-- 	<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%" height="100%"> -->
<!-- 	  	<br /><br /><br /><br /><br /><br /><br /> -->
<%-- 	  	<center> --%>
<!-- 		    <div id="header" class="info"> -->
<!-- 					<b>Hello, please login to continue...</b> -->
<!-- 				</div> -->
		 
<!-- 		  	<br /> -->
<%-- 		    <c:if test="${not empty param.login_error}"> --%>
<!-- 		      <font color="red"> -->
<!-- 		        Your login attempt was not successful, try again.<br/> -->
<%-- 		        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. --%>
<!-- 		  		<br /> -->
<!-- 		      </font> -->
<%-- 		    </c:if> --%>
<!-- 		 	<br /> -->
<%-- 		    <form name="f" action="<%=request.getContextPath() %>/j_spring_security_check" method="POST"> --%>
<!-- 		      <table> -->
<%-- 		        <tr><td align="left">User:</td><td><input style="width:150px" type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr> --%>
<!-- 		        <tr><td align="left">Password:</td><td><input style="width:150px" type='password' name='j_password'></td></tr> -->
<!-- 		        <tr><td><input type="hidden" name="_spring_security_remember_me"></td><td></td></tr> -->
<!-- 		        <tr><td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr> -->
<%-- 		 		<tr><td colspan=2><a href="<%=request.getContextPath() %>/auth/resetpwd.html"><font color="blue">Forgot password ?</font></a></td></tr> --%>
<!-- 		        <tr><td colspan='2'><br /></td></tr> -->
<!-- 		        <tr> -->
<!-- 			        <td align="center" colspan='2'> -->
<!-- 			        <input name="submit" type="submit" value="Login"> -->
<!-- 			        <input name="reset" type="reset"></td> -->
<!-- 		        </tr> -->
<!-- 		      </table> -->
<!-- 		    </form> -->
<%-- 		    </center> --%>
<!-- 		</table> -->
<!--   </body> -->
</html>