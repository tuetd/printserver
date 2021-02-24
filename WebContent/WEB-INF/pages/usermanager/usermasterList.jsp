<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
	<head>
	    <title><fmt:message key="usermasterList.title"/></title>
	    <content tag="heading"><fmt:message key="usermasterList.title"/></content>
	    
<script language="javascript">

function removeUsermaster(id)
{
/* todo : ajax request with prototype*/
document.location = 'deleteUsermaster.html?usermasterId='+id;
}

</script>
	</head>
	


<div id="twocenter-columns">
		<form:form class="box" commandName="usermaster" action="searchUsermaster.html" method="post">
		<form:errors path="username" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label>	<fmt:message key="user.search.username" /></label>
						</div>
						<div class="inputtext">
						<form:input path="username" id="username"/>
						</div>
						<div class="rowtest"></div>
					</div>
					</div>
				    <div id="column2test">
					<div class="rowtest">
						<div class="checkbutton">
							<input class="btnLogin" type="submit" name="search" value="Search" />
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
			</form:form>
		</div>

		
<%@ include file="usermasterEmList.jsp" %>

	
		