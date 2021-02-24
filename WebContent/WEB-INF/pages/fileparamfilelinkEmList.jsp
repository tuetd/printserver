<c:if test="${ not empty Fileparamfilelinklist}">
<display:table name="Fileparamfilelinklist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Fileparamfilelink" pagesize="25" class="table" export="true">
			<display:column property="fileid" escapeXml="true" sortable="true" title="fileid" url="/addupdateFileparamfilelink.html" paramId="fileparamfilelinkId" paramProperty="fileparamid"/>
			<display:column property="fileparamid" escapeXml="true" sortable="true" title="fileparamid" url="/addupdateFileparamfilelink.html" paramId="fileparamfilelinkId" paramProperty="fileparamid"/>
			<display:column property="value" escapeXml="true" sortable="true" title="value" url="/addupdateFileparamfilelink.html" paramId="fileparamfilelinkId" paramProperty="fileparamid"/>

</display:table>		
</c:if>