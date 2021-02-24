
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu.js"/>" ></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-topbar.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-sidebar.css"/>" />


<div id="ddtopmenubar" class="mattblackmenu">
<ul>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','OTHERS')">
<li><a href="<%=request.getContextPath()%>/listDocument.html">Document</a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
<li><a href="#" rel="usermanagement">User management</a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMINISTRATOR', 'CUSTOMER SERVICE','FCL REPORT')">
<li><a href="<%=request.getContextPath()%>/prtreprint/list.html">Reprint Document</a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMINISTRATOR','FCL REPORT')">
<li><a href="<%=request.getContextPath()%>/reportFcl.html">FCL Report</a></li>
</sec:authorize>		
</ul>
</div>

<script type="text/javascript">
ddlevelsmenu.setup("ddtopmenubar", "topbar");
</script>


<ul id="usermanagement" class="ddsubmenustyle">
<sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT')">
		<li><a href="<%=request.getContextPath()%>/listUsermaster.html">User list</a></li>
		
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ADMINISTRATOR','SUPPORT')">
			<li><a href="<%=request.getContextPath()%>/createUserForm.html">Create user</a></li>
			<sec:authorize access="hasRole('ADMINISTRATOR')">
				<li><a href="<%=request.getContextPath()%>/reportUser.html">Report user</a></li>
			</sec:authorize>
		</sec:authorize>		
</ul>