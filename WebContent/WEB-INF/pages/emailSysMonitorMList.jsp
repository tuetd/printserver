<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="emailSysMonitorMList.title"/></title>
	    <content tag="heading"><fmt:message key="emailSysMonitorMList.title"/></content>
	    
<script language="javascript">

function removeEmailSysMonitorM(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteEmailSysMonitorM.html?emailSysMonitorMId='+id;
}

</script>
	</head>
<%@ include file="emailSysMonitorMEmList.jsp" %>