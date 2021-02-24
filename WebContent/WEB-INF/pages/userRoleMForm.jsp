<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.userRoleM.title"/></title>
	    <content tag="heading"><fmt:message key="menu.userRoleM.title"/></content>
	    <meta name="menu" content="UserRoleM"/>
	</head>
<fieldset>
		<form:form commandName="userRoleM" action="addupdateUserRoleM.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="roleName">roleName</label><form:errors path="roleName" /><span><form:input cssClass="text medium" path="roleName" id="roleName"/></span></li>
<c:if test="${ empty requestScope['userRoleM'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['userRoleM'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>