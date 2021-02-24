<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "GROUP_M"
 * 
 * @author Telosys Tools Generator
 *
-->

<script type="text/javascript">
	function goEditGroupM(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/groupmod.html?id=" + id;
	}
	
	function goDeleteGroupM(id, name) {
		if (confirm("Do you want to deactive group : " + name + " ?")) { 
			window.location.href = "<%=request.getContextPath() %>/admin/groupdel.html?id=" + id;
		}
	}

</script>
<br />

	<div id="header" class="info">
		<b>GroupM listing</b>
		<p class="right">
			<a href='<c:url value="/admin/groupmod.html" />'><b>Create groupM</b></a>
			
		</p>
	</div>
	
	<display:table size="resultSize" class="tftable" defaultsort="1" cellpadding="1" cellspacing="1" id="data" name="groupMList"  requestURI="/admin/grouplist.html" pagesize="25" >
		<display:column class="contactDept" property="groupcode" title="groupcode" sortable="true"   />
		<display:column class="contactDept" property="groupname" title="groupname" sortable="true"   />
		<display:column class="contactDept" property="groupdesc" title="groupdesc" sortable="true"   />
		<display:column class="contactDept" title="Active ?" >
				<c:choose>
					<c:when test="${data.isActived eq 1}">Active</c:when>
					<c:otherwise>Inactive</c:otherwise>
				</c:choose> 
		</display:column>
		<display:column class="contactDept" title="Edit" >
			<img onclick="javascript:goEditGroupM('${data.groupid}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" style="cursor:pointer;"/>
		</display:column>
		<display:column style="width:1%;" class="contactDept" title="Delete" >
			<img onclick="javascript:goDeleteGroupM('${data.groupid}','${data.groupname}');return false;" alt="click to delete record" src="<%=request.getContextPath() %>/images/common/delete.png" style="cursor:pointer;"/>
		</display:column>
	</display:table>
