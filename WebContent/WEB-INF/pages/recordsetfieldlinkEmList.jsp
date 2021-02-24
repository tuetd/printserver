<c:if test="${ not empty Recordsetfieldlinklist}">
<display:table name="Recordsetfieldlinklist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Recordsetfieldlink" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateRecordsetfieldlink.html" paramId="recordsetfieldlinkId" paramProperty="id"/>
			<display:column property="fieldid" escapeXml="true" sortable="true" title="fieldid" url="/addupdateRecordsetfieldlink.html" paramId="recordsetfieldlinkId" paramProperty="id"/>
			<display:column property="recordsetid" escapeXml="true" sortable="true" title="recordsetid" url="/addupdateRecordsetfieldlink.html" paramId="recordsetfieldlinkId" paramProperty="id"/>
			<display:column property="data" escapeXml="true" sortable="true" title="data" url="/addupdateRecordsetfieldlink.html" paramId="recordsetfieldlinkId" paramProperty="id"/>

</display:table>		
</c:if>