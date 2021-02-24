<c:if test="${ not empty Projectmasterlist}">
<display:table name="Projectmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Projectmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateProjectmaster.html" paramId="projectmasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateProjectmaster.html" paramId="projectmasterId" paramProperty="id"/>

</display:table>		
</c:if>