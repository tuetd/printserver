<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="sqlquerymasterList.title"/></title>
	    <content tag="heading"><fmt:message key="sqlquerymasterList.title"/></content>
	    
<script language="javascript">

function removeSqlquerymaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteSqlquerymaster.html?sqlquerymasterId='+id;
}

</script>
	</head>
<%@ include file="sqlquerymasterEmList.jsp" %>