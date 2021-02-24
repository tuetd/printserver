<c:if test="${ not empty OpenOfficeTrafficlist}">
<display:table name="OpenOfficeTrafficlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="OpenOfficeTraffic" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateOpenOfficeTraffic.html" paramId="openOfficeTrafficId" paramProperty="id"/>
			<display:column property="servername" escapeXml="true" sortable="true" title="servername" url="/addupdateOpenOfficeTraffic.html" paramId="openOfficeTrafficId" paramProperty="id"/>
			<display:column property="maxConn" escapeXml="true" sortable="true" title="maxConn" url="/addupdateOpenOfficeTraffic.html" paramId="openOfficeTrafficId" paramProperty="id"/>
			<display:column property="currConn" escapeXml="true" sortable="true" title="currConn" url="/addupdateOpenOfficeTraffic.html" paramId="openOfficeTrafficId" paramProperty="id"/>

</display:table>		
</c:if>