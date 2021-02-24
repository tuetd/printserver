<c:if test="${ not empty Fieldtypemasterlist}">
<display:table name="Fieldtypemasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Fieldtypemaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateFieldtypemaster.html" paramId="fieldtypemasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateFieldtypemaster.html" paramId="fieldtypemasterId" paramProperty="id"/>

</display:table>		
</c:if>