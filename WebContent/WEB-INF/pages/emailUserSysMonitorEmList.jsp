<c:if test="${ not empty EmailUserSysMonitorlist}">
<display:table name="EmailUserSysMonitorlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="EmailUserSysMonitor" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateEmailUserSysMonitor.html" paramId="emailUserSysMonitorId" paramProperty="id"/>
			<display:column property="userId" escapeXml="true" sortable="true" title="userId" url="/addupdateEmailUserSysMonitor.html" paramId="emailUserSysMonitorId" paramProperty="id"/>
			<display:column property="sysId" escapeXml="true" sortable="true" title="sysId" url="/addupdateEmailUserSysMonitor.html" paramId="emailUserSysMonitorId" paramProperty="id"/>

</display:table>		
</c:if>