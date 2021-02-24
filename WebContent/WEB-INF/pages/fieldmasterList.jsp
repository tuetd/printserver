<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="fieldmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="fieldmasterList.title"/></content>
	    
<script language="javascript">

function removeFieldmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFieldmaster.html?fieldmasterId='+id;
}

</script>
	</head>
<%@ include file="fieldmasterEmList.jsp" %>