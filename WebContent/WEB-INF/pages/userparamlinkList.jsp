<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="userparamlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="userparamlinkList.title"/></content>
	    
<script language="javascript">

function removeUserparamlink(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUserparamlink.html?userparamlinkId='+id;
}

</script>
	</head>
<%@ include file="userparamlinkEmList.jsp" %>