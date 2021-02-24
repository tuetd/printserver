<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="userrecordsetprintedlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="userrecordsetprintedlinkList.title"/></content>
	    
<script language="javascript">

function removeUserrecordsetprintedlink(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUserrecordsetprintedlink.html?userrecordsetprintedlinkId='+id;
}

</script>
	</head>
<%@ include file="userrecordsetprintedlinkEmList.jsp" %>