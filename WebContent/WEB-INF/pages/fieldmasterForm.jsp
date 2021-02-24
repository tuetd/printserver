<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.fieldmaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.fieldmaster.title"/></content>
	    <meta name="menu" content="Fieldmaster"/>
	</head>
<fieldset>
		<form:form commandName="fieldmaster" action="addupdateFieldmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="typeid">typeid</label><form:errors path="typeid" /><span><form:input cssClass="text medium" path="typeid" id="typeid"/></span></li>
				<li><label class="desc" for="datasourceid">datasourceid</label><form:errors path="datasourceid" /><span><form:input cssClass="text medium" path="datasourceid" id="datasourceid"/></span></li>
				<li><label class="desc" for="sqlqueryid">sqlqueryid</label><form:errors path="sqlqueryid" /><span><form:input cssClass="text medium" path="sqlqueryid" id="sqlqueryid"/></span></li>
<c:if test="${ empty requestScope['fieldmaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['fieldmaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>