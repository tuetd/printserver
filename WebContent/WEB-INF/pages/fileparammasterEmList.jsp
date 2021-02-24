<c:if test="${ not empty Fileparammasterlist}">
<display:table name="Fileparammasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Fileparammaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateFileparammaster.html" paramId="fileparammasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateFileparammaster.html" paramId="fileparammasterId" paramProperty="id"/>
			<display:column property="filetypeid" escapeXml="true" sortable="true" title="filetypeid" url="/addupdateFileparammaster.html" paramId="fileparammasterId" paramProperty="id"/>

</display:table>		
</c:if>