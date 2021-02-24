<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="parammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="parammasterList.title"/></content>
	    
<script language="javascript">

function removeParammaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteParammaster.html?parammasterId='+id;
}

</script>
	</head>
<%@ include file="parammasterEmList.jsp" %>