<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "RM_BRANCH"
 * 
 * @author Telosys Tools Generator
 *
-->

<script type="text/javascript">
	function goEditBranch(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/branchmod.html?id=" + id;
	}
	
	function goDeleteBranch(id, name) {
		if (confirm("Do you want to deactive branch : " + name + " ?")) { 
			window.location.href = "<%=request.getContextPath() %>/admin/branchdel.html?id=" + id;
		}
	}

</script>
<br />

<div id="header" class="info">
	<b>RmBranch listing</b>
	<p class="right">
		<a href='<c:url value="/admin/branchmod.html" />'><b>Create rmBranch</b></a>
		
	</p>
</div>

<display:table size="resultSize" class="tftable" defaultsort="1" cellpadding="1" cellspacing="1" id="data" name="branchList"  requestURI="/admin/branchlist.html" pagesize="25" >
	<display:column class="contactDept" property="code" title="code" sortable="true"   />
	<display:column class="contactDept" property="name" title="name" sortable="true"   />
	<display:column class="contactDept" title="Active ?" >
				<c:choose>
					<c:when test="${data.isActived eq 1}">Active</c:when>
					<c:otherwise>Inactive</c:otherwise>
				</c:choose> 
		</display:column>
	<display:column class="contactDept" title="Edit" >
		<img onclick="javascript:goEditBranch('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" style="cursor:pointer;"/>
	</display:column>
	<display:column class="contactDept" title="Delete" >
		<img onclick="javascript:goDeleteBranch('${data.id}', '${data.name}');return false;" alt="click to delete record" src="<%=request.getContextPath() %>/images/common/delete.png" style="cursor:pointer;"/>
	</display:column>
</display:table>
