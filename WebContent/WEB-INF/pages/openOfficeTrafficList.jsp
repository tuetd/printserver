<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="openOfficeTrafficList.title"/></title>
	    <content tag="heading"><fmt:message key="openOfficeTrafficList.title"/></content>
	    
<script language="javascript">

function removeOpenOfficeTraffic(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteOpenOfficeTraffic.html?openOfficeTrafficId='+id;
}

</script>
	</head>
<%@ include file="openOfficeTrafficEmList.jsp" %>