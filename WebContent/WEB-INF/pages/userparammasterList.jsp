<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="userparammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="userparammasterList.title"/></content>
	    
<script language="javascript">

function removeUserparammaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUserparammaster.html?userparammasterId='+id;
}

</script>
	</head>
<%@ include file="userparammasterEmList.jsp" %>