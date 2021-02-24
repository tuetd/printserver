<c:if test="${ not empty Sqlparammasterlist}">
<display:table name="Sqlparammasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Sqlparammaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>
			<display:column property="friendlyname" escapeXml="true" sortable="true" title="friendlyname" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>
			<display:column property="typeid" escapeXml="true" sortable="true" title="typeid" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>
			<display:column property="datasourceid" escapeXml="true" sortable="true" title="datasourceid" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>
			<display:column property="fieldtype" escapeXml="true" sortable="true" title="fieldtype" url="/addupdateSqlparammaster.html" paramId="sqlparammasterId" paramProperty="id"/>

</display:table>		
</c:if>