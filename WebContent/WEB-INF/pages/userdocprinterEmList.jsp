<c:if test="${ not empty Userdocprinterlist}">
<display:table name="Userdocprinterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Userdocprinter" pagesize="25" class="table" export="true">
			<display:column property="docid" escapeXml="true" sortable="true" title="docid" url="/addupdateUserdocprinter.html" paramId="userdocprinterId" paramProperty="userid"/>
			<display:column property="printername" escapeXml="true" sortable="true" title="printername" url="/addupdateUserdocprinter.html" paramId="userdocprinterId" paramProperty="userid"/>
			<display:column property="userid" escapeXml="true" sortable="true" title="userid" url="/addupdateUserdocprinter.html" paramId="userdocprinterId" paramProperty="userid"/>

</display:table>		
</c:if>