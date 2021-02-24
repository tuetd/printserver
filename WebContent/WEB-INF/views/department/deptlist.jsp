<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function goEditDept(id) {
		window.location.href = "<%=request.getContextPath() %>/admin/deptmod.html?id=" + id;
	}
	
	function goDeleteDept(id,name) {
		if (confirm("Do you want to deactive department : " + name + " ?")) { 
			window.location.href = "<%=request.getContextPath() %>/admin/deptdel.html?id=" + id;
		}
	}

</script>
<br />
<div id="container" style="width: auto;float:left;">
	<div id="header" class="info">
		<b>Department listing</b>
	</div>
	<p class="right">
		<a href='<c:url value="/admin/deptmod.html" />'><b>Create department</b></a>
		
	</p>
	
	<display:table size="resultSize" defaultsort="1" cellpadding="1" cellspacing="1" id="data" name="deptList"  requestURI="/admin/deptlist.html" pagesize="25" >
		<display:column style="width:5%;" class="contactDept" property="deptcode" title="Dept Code" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="deptname" title="Dept Name" sortable="true"   />
		<display:column style="width:1%;" class="contactDept" title="Edit" >
			<img onclick="javascript:goEditDept('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/common/modify.jpg" style="cursor:pointer;"/>
		</display:column>
		<display:column style="width:1%;" class="contactDept" title="Delete" >
			<img onclick="javascript:goDeleteDept('${data.id}','${data.deptname}');return false;" alt="click to delete record" src="<%=request.getContextPath() %>/images/common/delete.png" style="cursor:pointer;"/>
		</display:column>
	</display:table>
</div>
