<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" />
</title>
<link href="<c:url value="/scripts/style.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/styles_m.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/general.css"/>' rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-topbar.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu-sidebar.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/jquery/js/ui-lightness/jquery-ui-1.8.23.custom.css"/>" />
<script type="text/javascript" src="<c:url value="/scripts/ddlevelsfiles/ddlevelsmenu.js"/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery/js/jquery-ui-1.8.23.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/general.js"/>"></script>
<!-- scroller -->
<script type="text/javascript" src="<c:url value="/css/scroller/jquery.easing.1.3.js"/>"></script>
<script type="text/javascript" src="<c:url value="/css/scroller/jquery.mCustomScrollbar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/css/scroller/jquery.mousewheel.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/scroller/jquery.mCustomScrollbar.css"/>" />
<!-- Load upload script  aaaa-->
<script type="text/javascript" src="<c:url value="/tiny_mce/jquery.tinymce.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/upload/js/uploadfiles.js"/>"></script>
<script type="text/javascript" src="<c:url value="/upload/js/ajaxfileupload.js"/>"></script>

</head>
<body>

<div id="page">
       <div id="header" class="clearfix">
          <tiles:insertAttribute name="header" />
        </div>
      
	<!-- end #header -->
	<div class="clearfix" id="content">
	        <div >
	        	<tiles:insertAttribute name="body" />
            </div>
	</div>
	 <div id="footer" >
           <tiles:insertAttribute name="footer" />
     </div>
	</div>
</body>
</html>
