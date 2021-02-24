<c:if test="${ not empty Datasourcemasterlist}">
<display:table name="Datasourcemasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Datasourcemaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateDatasourcemaster.html" paramId="datasourcemasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateDatasourcemaster.html" paramId="datasourcemasterId" paramProperty="id"/>
			<display:column property="fileid" escapeXml="true" sortable="true" title="fileid" url="/addupdateDatasourcemaster.html" paramId="datasourcemasterId" paramProperty="id"/>
			<display:column property="serverconfigid" escapeXml="true" sortable="true" title="serverconfigid" url="/addupdateDatasourcemaster.html" paramId="datasourcemasterId" paramProperty="id"/>

</display:table>		
</c:if>