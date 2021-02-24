<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="statusmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="statusmasterList.title"/></content>
	    
<script language="javascript">

function removeStatusmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteStatusmaster.html?statusmasterId='+id;
}

</script>
	</head>
<%@ include file="statusmasterEmList.jsp" %>