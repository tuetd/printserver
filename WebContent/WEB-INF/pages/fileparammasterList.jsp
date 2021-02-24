<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="fileparammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="fileparammasterList.title"/></content>
	    
<script language="javascript">

function removeFileparammaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFileparammaster.html?fileparammasterId='+id;
}

</script>
	</head>
<%@ include file="fileparammasterEmList.jsp" %>