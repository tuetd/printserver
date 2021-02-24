<c:if test="${ not empty Userparamlinklist}">
<display:table name="Userparamlinklist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Userparamlink" pagesize="25" class="table" export="true">
			<display:column property="userid" escapeXml="true" sortable="true" title="userid" url="/addupdateUserparamlink.html" paramId="userparamlinkId" paramProperty="userid"/>
			<display:column property="paramid" escapeXml="true" sortable="true" title="paramid" url="/addupdateUserparamlink.html" paramId="userparamlinkId" paramProperty="userid"/>
			<display:column property="value" escapeXml="true" sortable="true" title="value" url="/addupdateUserparamlink.html" paramId="userparamlinkId" paramProperty="userid"/>

</display:table>		
</c:if>