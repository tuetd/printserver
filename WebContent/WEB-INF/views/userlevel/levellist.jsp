<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function goEditLevel(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/levelmod.html?id=" + id;
	}
	
	function goDeleteLevel(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/leveldel.html?id=" + id;
	}

</script>
<br />
<div id="container" style="width: auto;float:left;">
	<div id="header" class="info">
		<b>User Level listing</b>
	</div>
	<p class="right">
		<a href='<c:url value="/admin/levelmod.html" />'><b>Create level</b></a>
		
	</p>
	
	<display:table size="resultSize" defaultsort="1" cellpadding="1" cellspacing="1" id="data" name="levelList"  requestURI="/admin/levellist.html" pagesize="25" >
		<display:column style="width:5%;" class="contactLevel" property="levelcode" title="User name" sortable="true"   />
		<display:column style="width:5%;" class="contactLevel" property="levelname" title="Full name" sortable="true"   />
		<display:column style="width:1%;" class="contactLevel" title="Edit" >
			<img onclick="javascript:goEditLevel('${data.levelid}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" />
		</display:column>
		<display:column style="width:1%;" class="contactLevel" title="Delete" >
			<img onclick="javascript:goDeleteLevel('${data.levelid}');return false;" alt="click to delete record" src="<%=request.getContextPath() %>/images/common/delete.png" />
		</display:column>
	</display:table>
</div>
