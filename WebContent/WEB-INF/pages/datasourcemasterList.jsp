<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="datasourcemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="datasourcemasterList.title"/></content>
	    
<script language="javascript">

function removeDatasourcemaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteDatasourcemaster.html?datasourcemasterId='+id;
}

</script>
	</head>
<%@ include file="datasourcemasterEmList.jsp" %>