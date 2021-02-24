<%@ include file="/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="bookmarkmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="bookmarkmasterList.title"/></content>
	    
<script language="javascript">

function removeBookmarkmaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteBookmarkmaster.html?bookmarkmasterId='+id;
}

</script>
	</head>
<%@ include file="bookmarkmasterEmList.jsp" %>