<c:if test="${ not empty Statusmasterlist}">
<display:table name="Statusmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Statusmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateStatusmaster.html" paramId="statusmasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateStatusmaster.html" paramId="statusmasterId" paramProperty="id"/>

</display:table>		
</c:if>