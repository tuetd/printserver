<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.userRoleMList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.userRoleMList.title"/></content>
	    <meta name="menu" content="UserRoleM"/>
	</head>

<fieldset>
		<form:form commandName="userRoleM" action="searchUserRoleM.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="roleName">roleName</label><form:errors path="roleName" /><span><form:input cssClass="text medium" path="roleName" id="roleName"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="userRoleMEmList.jsp" %>