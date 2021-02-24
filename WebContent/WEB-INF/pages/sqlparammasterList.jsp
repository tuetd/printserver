<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="sqlparammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="sqlparammasterList.title"/></content>
	    
<script language="javascript">

function removeSqlparammaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteSqlparammaster.html?sqlparammasterId='+id;
}

</script>
	</head>
<%@ include file="sqlparammasterEmList.jsp" %>