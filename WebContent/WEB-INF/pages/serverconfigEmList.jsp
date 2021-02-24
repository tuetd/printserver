<c:if test="${ not empty Serverconfiglist}">
<display:table name="Serverconfiglist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Serverconfig" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="servername" escapeXml="true" sortable="true" title="servername" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="port" escapeXml="true" sortable="true" title="port" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="dbname" escapeXml="true" sortable="true" title="dbname" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="username" escapeXml="true" sortable="true" title="username" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="password" escapeXml="true" sortable="true" title="password" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="systemname" escapeXml="true" sortable="true" title="systemname" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>
			<display:column property="typeid" escapeXml="true" sortable="true" title="typeid" url="/addupdateServerconfig.html" paramId="serverconfigId" paramProperty="id"/>

</display:table>		
</c:if>