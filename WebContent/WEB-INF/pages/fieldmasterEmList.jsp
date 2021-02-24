<c:if test="${ not empty Fieldmasterlist}">
<display:table name="Fieldmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Fieldmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateFieldmaster.html" paramId="fieldmasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateFieldmaster.html" paramId="fieldmasterId" paramProperty="id"/>
			<display:column property="typeid" escapeXml="true" sortable="true" title="typeid" url="/addupdateFieldmaster.html" paramId="fieldmasterId" paramProperty="id"/>
			<display:column property="datasourceid" escapeXml="true" sortable="true" title="datasourceid" url="/addupdateFieldmaster.html" paramId="fieldmasterId" paramProperty="id"/>
			<display:column property="sqlqueryid" escapeXml="true" sortable="true" title="sqlqueryid" url="/addupdateFieldmaster.html" paramId="fieldmasterId" paramProperty="id"/>

</display:table>		
</c:if>