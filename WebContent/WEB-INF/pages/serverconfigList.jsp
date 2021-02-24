<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="serverconfigList.title"/></title>
	    <content tag="heading"><fmt:message key="serverconfigList.title"/></content>
	    
<script language="javascript">

function removeServerconfig(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteServerconfig.html?serverconfigId='+id;
}

</script>
	</head>
<%@ include file="serverconfigEmList.jsp" %>