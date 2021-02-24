<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="filemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="filemasterList.title"/></content>
	    
<script language="javascript">

function removeFilemaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFilemaster.html?filemasterId='+id;
}

</script>
	</head>
<%@ include file="filemasterEmList.jsp" %>