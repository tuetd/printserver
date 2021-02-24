<c:if test="${ not empty Userparammasterlist}">
<display:table name="Userparammasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Userparammaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateUserparammaster.html" paramId="userparammasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateUserparammaster.html" paramId="userparammasterId" paramProperty="id"/>
			<display:column property="description" escapeXml="true" sortable="true" title="description" url="/addupdateUserparammaster.html" paramId="userparammasterId" paramProperty="id"/>

</display:table>		
</c:if>