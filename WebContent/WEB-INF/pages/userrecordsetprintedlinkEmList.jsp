<c:if test="${ not empty Userrecordsetprintedlinklist}">
<display:table name="Userrecordsetprintedlinklist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Userrecordsetprintedlink" pagesize="25" class="table" export="true">
			<display:column property="userid" escapeXml="true" sortable="true" title="userid" url="/addupdateUserrecordsetprintedlink.html" paramId="userrecordsetprintedlinkId" paramProperty="userid"/>
			<display:column property="recordsetid" escapeXml="true" sortable="true" title="recordsetid" url="/addupdateUserrecordsetprintedlink.html" paramId="userrecordsetprintedlinkId" paramProperty="userid"/>
			<display:column property="datetimeprinted" escapeXml="true" sortable="true" title="datetimeprinted" url="/addupdateUserrecordsetprintedlink.html" paramId="userrecordsetprintedlinkId" paramProperty="userid"/>

</display:table>		
</c:if>