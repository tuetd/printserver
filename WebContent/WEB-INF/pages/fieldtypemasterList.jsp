<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="fieldtypemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="fieldtypemasterList.title"/></content>
	    
<script language="javascript">

function removeFieldtypemaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteFieldtypemaster.html?fieldtypemasterId='+id;
}

</script>
	</head>
<%@ include file="fieldtypemasterEmList.jsp" %>