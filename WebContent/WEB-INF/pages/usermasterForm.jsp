<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.usermaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.usermaster.title"/></content>
	    <meta name="menu" content="Usermaster"/>
	</head>
<fieldset>
		<form:form commandName="usermaster" action="addupdateUsermaster.html" method="post">
			<ul>
				<li><label class="desc" for="username">username</label><form:errors path="username" /><span><form:input cssClass="text medium" path="username" id="username"/></span></li>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="loggedin">loggedin</label><form:errors path="loggedin" /><span><form:input cssClass="text medium" path="loggedin" id="loggedin"/></span></li>
				<li><label class="desc" for="roleId">roleId</label><form:errors path="roleId" /><span><form:input cssClass="text medium" path="roleId" id="roleId"/></span></li>
				<li><label class="desc" for="sessionId">sessionId</label><form:errors path="sessionId" /><span><form:input cssClass="text medium" path="sessionId" id="sessionId"/></span></li>
				<li><label class="desc" for="finnoneSecurityCode">finnoneSecurityCode</label><form:errors path="finnoneSecurityCode" /><span><form:input cssClass="text medium" path="finnoneSecurityCode" id="finnoneSecurityCode"/></span></li>
				<li><label class="desc" for="userPlace">userPlace</label><form:errors path="userPlace" /><span><form:input cssClass="text medium" path="userPlace" id="userPlace"/></span></li>
<c:if test="${ empty requestScope['usermaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['usermaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>