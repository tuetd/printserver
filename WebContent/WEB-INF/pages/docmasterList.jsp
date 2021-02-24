<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="docmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="docmasterList.title"/></content>
	    
<script language="javascript">

function removeDocmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteDocmaster.html?docmasterId='+id;
}

</script>
	</head>
<%@ include file="docmasterEmList.jsp" %>