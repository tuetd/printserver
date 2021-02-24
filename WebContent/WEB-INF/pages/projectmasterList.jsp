<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="projectmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="projectmasterList.title"/></content>
	    
<script language="javascript">

function removeProjectmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteProjectmaster.html?projectmasterId='+id;
}

</script>
	</head>
<%@ include file="projectmasterEmList.jsp" %>