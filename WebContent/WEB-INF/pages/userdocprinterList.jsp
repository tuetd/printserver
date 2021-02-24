<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="userdocprinterList.title"/></title>
	    <content tag="heading"><fmt:message key="userdocprinterList.title"/></content>
	    
<script language="javascript">

function removeUserdocprinter(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUserdocprinter.html?userdocprinterId='+id;
}

</script>
	</head>
<%@ include file="userdocprinterEmList.jsp" %>