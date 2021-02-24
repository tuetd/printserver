<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="emailUserSysMonitorList.title"/></title>
	    <content tag="heading"><fmt:message key="emailUserSysMonitorList.title"/></content>
	    
<script language="javascript">

function removeEmailUserSysMonitor(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteEmailUserSysMonitor.html?emailUserSysMonitorId='+id;
}

</script>
	</head>
<%@ include file="emailUserSysMonitorEmList.jsp" %>