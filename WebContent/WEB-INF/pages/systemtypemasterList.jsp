<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="systemtypemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="systemtypemasterList.title"/></content>
	    
<script language="javascript">

function removeSystemtypemaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteSystemtypemaster.html?systemtypemasterId='+id;
}

</script>
	</head>
<%@ include file="systemtypemasterEmList.jsp" %>