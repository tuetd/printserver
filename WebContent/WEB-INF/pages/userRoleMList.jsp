<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="userRoleMList.title"/></title>
	    <content tag="heading"><fmt:message key="userRoleMList.title"/></content>
	    
<script language="javascript">

function removeUserRoleM(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUserRoleM.html?userRoleMId='+id;
}

</script>
	</head>
<%@ include file="userRoleMEmList.jsp" %>