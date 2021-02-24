<c:if test="${ not empty Filetypemasterlist}">
<display:table name="Filetypemasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Filetypemaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateFiletypemaster.html" paramId="filetypemasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateFiletypemaster.html" paramId="filetypemasterId" paramProperty="id"/>

</display:table>		
</c:if>