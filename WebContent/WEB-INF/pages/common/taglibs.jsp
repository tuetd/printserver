<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="scripts/table.css" rel="stylesheet" type="text/css" media="screen" /> 
<link href="scripts/style.css" rel="stylesheet" type="text/css" media="screen" /> 
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="<c:url value="scripts/ddlevelsfiles/ddlevelsmenu.js"/>" ></script>
<link rel="stylesheet" type="text/css" href="<c:url value="scripts/ddlevelsfiles/ddlevelsmenu-base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="scripts/ddlevelsfiles/ddlevelsmenu-topbar.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="scripts/ddlevelsfiles/ddlevelsmenu-sidebar.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='scripts/tablesorter/__jquery.tablesorter.css'/>" />
<script type="text/javascript" src="<c:url value='scripts/tablesorter/__jquery.tablesorter.min.js'/>"></script>


