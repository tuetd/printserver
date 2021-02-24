<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/styles/style.css'/>" />
        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/andreas01/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/andreas01/print.css'/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/styles/screen.css'/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/scripts/jquery/css/ui-lightness/jquery-ui-1.8.23.custom.css'/>" />
<%--         <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script> --%>
<%--         <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script> --%>
<%--         <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script> --%>
        
		<script type="text/javascript" src="<c:url value="/scripts/jquery/js/jquery-1.8.0.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/scripts/jquery/js/jquery-ui-1.8.23.custom.min.js"/>"></script>
	    <link rel="stylesheet" href="<c:url value='/scripts/jquery/css/pagination.css'/>"  />
<%--         <script type="text/javascript" src="<c:url value="/scripts/jquery.js"/>"></script> --%>
<%--         <script type="text/javascript" src="<c:url value="/scripts/jquery/pagination.js"/>" ></script> --%>
<%--         <script type="text/javascript" src="<c:url value="/scripts/members.js"/>" ></script> --%>
<%--         <script type="text/javascript" src="<c:url value="/scripts/jquery.displaytag-ajax-1.2.js"/>" ></script> --%>
		<script>
			jQuery.noConflict();
		</script>
        <decorator:head/>
    </head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>

    <div id="page">
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>
         <% if (request.getUserPrincipal() != null) { %>
         <sec:authorize access="hasAnyRole('ADMINISTRATOR','HELPDESK','SUPPORT', 'FCL REPORT')">
         <div id="stylefour">
             <jsp:include page="/common/menu.jsp"/>
           </div>
         </sec:authorize>
        <% } %>
        <hr />

        <div id="content" class="clearfix">
	        <% if (request.getUserPrincipal() != null) { %>
	        	<div style="float:right;padding-right:20px;">
	        	Welcome <%=request.getUserPrincipal().getName() %>
	        	| <b><a href="<%=request.getContextPath() %>/changepwd.html">Change password </a></b>| <b><a href="<%=request.getContextPath() %>/j_spring_security_logout">Logout</a></b></div>
	        <% } %> 
	        
            <div id="main">
                <%@ include file="/common/messages.jsp" %>
                <decorator:body/>
            </div>

            <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>

        </div>

        <div id="footer" >
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
