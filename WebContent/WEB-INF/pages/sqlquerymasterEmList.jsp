<c:if test="${ not empty Sqlquerymasterlist}">
<display:table name="Sqlquerymasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Sqlquerymaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>
			<display:column property="value" escapeXml="true" sortable="true" title="value" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>
			<display:column property="datasourceid" escapeXml="true" sortable="true" title="datasourceid" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>
			<display:column property="type" escapeXml="true" sortable="true" title="type" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>
			<display:column property="indexnum" escapeXml="true" sortable="true" title="indexnum" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>
			<display:column property="queryname" escapeXml="true" sortable="true" title="queryname" url="/addupdateSqlquerymaster.html" paramId="sqlquerymasterId" paramProperty="id"/>

</display:table>		
</c:if>