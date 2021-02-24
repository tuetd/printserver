<c:if test="${ not empty EmailSysMonitorMlist}">
<display:table name="EmailSysMonitorMlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="EmailSysMonitorM" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateEmailSysMonitorM.html" paramId="emailSysMonitorMId" paramProperty="id"/>
			<display:column property="sysName" escapeXml="true" sortable="true" title="sysName" url="/addupdateEmailSysMonitorM.html" paramId="emailSysMonitorMId" paramProperty="id"/>
			<display:column property="sysCode" escapeXml="true" sortable="true" title="sysCode" url="/addupdateEmailSysMonitorM.html" paramId="emailSysMonitorMId" paramProperty="id"/>
			<display:column property="emailContent" escapeXml="true" sortable="true" title="emailContent" url="/addupdateEmailSysMonitorM.html" paramId="emailSysMonitorMId" paramProperty="id"/>

</display:table>		
</c:if>