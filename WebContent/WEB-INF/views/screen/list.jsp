<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "RM_SCREEN"
 * 
 * @author Telosys Tools Generator
 *
-->

<script type="text/javascript">
	function goEditScreen(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/screenmod.html?id=" + id;
	}
	
	function goDeleteScreen(id, name) {
		if (confirm("Do you want to deactive screen : " + name + " ?")) { 
			window.location.href = "<%=request.getContextPath() %>/admin/screendel.html?id=" + id;
		}
	}

</script>
<br />

	<div id="header" class="info">
		<b>Screen listing</b>
		<p class="right">
			<a href='<c:url value="/admin/screenmod.html" />'><b>Create Screen</b></a>
			
		</p>
	</div>
	
	<display:table size="resultSize" class="tftable" defaultsort="1" cellpadding="1" cellspacing="1" id="data" name="screenList"  requestURI="/admin/screenlist.html" pagesize="25" >
		<display:column class="contactDept" property="stage" title="stage" sortable="true"   />
		<display:column class="contactDept" property="name" title="name" sortable="true"   />
		<display:column class="contactDept" property="viewName" title="view name" sortable="true"   />
		<display:column class="contactDept" property="priority1" title="priority1" sortable="true"   />
		<display:column class="contactDept" property="priority2" title="priority2" sortable="true"   />
		<display:column class="contactDept" title="Active ?" >
				<c:choose>
					<c:when test="${data.isActived eq 1}">Active</c:when>
					<c:otherwise>Inactive</c:otherwise>
				</c:choose> 
		</display:column>
		
		<display:column class="contactDept" title="Edit" >
			<img onclick="javascript:goEditScreen('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" style="cursor:pointer;"/>
		</display:column>
		<display:column style="width:1%;" class="contactDept" title="Delete" >
			<img onclick="javascript:goDeleteScreen('${data.id}', '${data.name}');return false;" alt="click to delete record" src="<%=request.getContextPath() %>/images/common/delete.png" style="cursor:pointer;"/>
		</display:column>
			</display:table>
