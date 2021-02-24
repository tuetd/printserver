<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="recordsetfieldlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="recordsetfieldlinkList.title"/></content>
	    
<script language="javascript">

function removeRecordsetfieldlink(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteRecordsetfieldlink.html?recordsetfieldlinkId='+id;
}

</script>
	</head>
<%@ include file="recordsetfieldlinkEmList.jsp" %>