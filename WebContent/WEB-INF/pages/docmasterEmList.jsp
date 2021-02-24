<c:if test="${ not empty Docmasterlist}">
<display:table name="Docmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Docmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="startpage" escapeXml="true" sortable="true" title="startpage" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="endpage" escapeXml="true" sortable="true" title="endpage" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="templatefile" escapeXml="true" sortable="true" title="templatefile" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="serverfile" escapeXml="true" sortable="true" title="serverfile" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="localfile" escapeXml="true" sortable="true" title="localfile" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>
			<display:column property="datasourceId" escapeXml="true" sortable="true" title="datasourceId" url="/addupdateDocmaster.html" paramId="docmasterId" paramProperty="id"/>

</display:table>		
</c:if>