<c:if test="${ not empty UserRoleMlist}">
<display:table name="UserRoleMlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="UserRoleM" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateUserRoleM.html" paramId="userRoleMId" paramProperty="id"/>
			<display:column property="roleName" escapeXml="true" sortable="true" title="roleName" url="/addupdateUserRoleM.html" paramId="userRoleMId" paramProperty="id"/>

</display:table>		
</c:if>