<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="recordsetmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="recordsetmasterList.title"/></content>
	    
<script language="javascript">

function removeRecordsetmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteRecordsetmaster.html?recordsetmasterId='+id;
}

</script>
	</head>
<%@ include file="recordsetmasterEmList.jsp" %>