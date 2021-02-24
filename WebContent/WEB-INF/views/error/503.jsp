<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" />
</title>
<link href="<c:url value="/css/styles_m.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/general.css"/>' rel="stylesheet" type="text/css"/>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" align="center"
		width="100%" height="100%">
		<tr><td height="30" /></tr>
		<tr><td height="30" /></tr>
		<tr><td height="30" /></tr>
		<tr><td height="30" /></tr>
		<tr><td height="30" /></tr>
		
		<tr>
			<td height="30">
				<img alt="Oops!!" src="<%=request.getContextPath()%>/images/oops.jpg">
			</td>
		</tr>
		<tr>
			<td height="30">
			</td>
		</tr>
		<tr>
			<td>
				<font color="red">Error 503 - Service unavailable, please check back later or contact your helpdesk for assistance.</font>
			</td>
		</tr>
		<tr>
			<td height="30">Click <a href="<%=request.getContextPath() %>"><font color="blue">here</font></a>
			to return to Application list</td>
		</tr>
	</table>
</body>
</html>
