<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404 - Not found</title>
<link href="<c:url value="/css/styles_m.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/structure.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/general.css"/>' rel="stylesheet" type="text/css"/>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" align="center"
		width="100%" height="100%">
		<tr>
			<td height="30">
			</td>
		</tr>
		<tr>
			<td width="350" style="padding-left: 500px;">
				<div style="float:left;padding-left:75px;">
					<font color="red">Page not found !</font>
				</div></td>
		</tr>
		<tr>
			<td height="30">Click <a href="<%=request.getContextPath() %>"><font color="blue">here</font></a>
			to return to Application list</td>
		</tr>
	</table>
</body>
</html>
