<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="fileparamfilelinkList.title"/></title>
	    <content tag="heading"><fmt:message key="fileparamfilelinkList.title"/></content>
	    
<script language="javascript">

function removeFileparamfilelink(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFileparamfilelink.html?fileparamfilelinkId='+id;
}

</script>
	</head>
<%@ include file="fileparamfilelinkEmList.jsp" %>