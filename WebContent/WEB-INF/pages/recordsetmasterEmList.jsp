<c:if test="${ not empty Recordsetmasterlist}">
<display:table name="Recordsetmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Recordsetmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateRecordsetmaster.html" paramId="recordsetmasterId" paramProperty="id"/>
			<display:column property="datetime" escapeXml="true" sortable="true" title="datetime" url="/addupdateRecordsetmaster.html" paramId="recordsetmasterId" paramProperty="id"/>
			<display:column property="statusid" escapeXml="true" sortable="true" title="statusid" url="/addupdateRecordsetmaster.html" paramId="recordsetmasterId" paramProperty="id"/>
			<display:column property="userid" escapeXml="true" sortable="true" title="userid" url="/addupdateRecordsetmaster.html" paramId="recordsetmasterId" paramProperty="id"/>

</display:table>		
</c:if>