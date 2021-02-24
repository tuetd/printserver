<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="filetypemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="filetypemasterList.title"/></content>
	    
<script language="javascript">

function removeFiletypemaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFiletypemaster.html?filetypemasterId='+id;
}

</script>
	</head>
<%@ include file="filetypemasterEmList.jsp" %>