<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.serverconfig.title"/></title>
	    <content tag="heading"><fmt:message key="menu.serverconfig.title"/></content>
	    <meta name="menu" content="Serverconfig"/>
	</head>
<fieldset>
		<form:form commandName="serverconfig" action="addupdateServerconfig.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="servername">servername</label><form:errors path="servername" /><span><form:input cssClass="text medium" path="servername" id="servername"/></span></li>
				<li><label class="desc" for="port">port</label><form:errors path="port" /><span><form:input cssClass="text medium" path="port" id="port"/></span></li>
				<li><label class="desc" for="dbname">dbname</label><form:errors path="dbname" /><span><form:input cssClass="text medium" path="dbname" id="dbname"/></span></li>
				<li><label class="desc" for="username">username</label><form:errors path="username" /><span><form:input cssClass="text medium" path="username" id="username"/></span></li>
				<li><label class="desc" for="password">password</label><form:errors path="password" /><span><form:input cssClass="text medium" path="password" id="password"/></span></li>
				<li><label class="desc" for="systemname">systemname</label><form:errors path="systemname" /><span><form:input cssClass="text medium" path="systemname" id="systemname"/></span></li>
				<li><label class="desc" for="typeid">typeid</label><form:errors path="typeid" /><span><form:input cssClass="text medium" path="typeid" id="typeid"/></span></li>
<c:if test="${ empty requestScope['serverconfig'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['serverconfig'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>