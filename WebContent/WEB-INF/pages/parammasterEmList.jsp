<c:if test="${ not empty Parammasterlist}">
<display:table name="Parammasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Parammaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateParammaster.html" paramId="parammasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateParammaster.html" paramId="parammasterId" paramProperty="id"/>
			<display:column property="value" escapeXml="true" sortable="true" title="value" url="/addupdateParammaster.html" paramId="parammasterId" paramProperty="id"/>
			<display:column property="description" escapeXml="true" sortable="true" title="description" url="/addupdateParammaster.html" paramId="parammasterId" paramProperty="id"/>

</display:table>		
</c:if>