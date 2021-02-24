<c:if test="${ not empty Systemtypemasterlist}">
<display:table name="Systemtypemasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Systemtypemaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateSystemtypemaster.html" paramId="systemtypemasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateSystemtypemaster.html" paramId="systemtypemasterId" paramProperty="id"/>

</display:table>		
</c:if>