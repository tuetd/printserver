<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.parammaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.parammaster.title"/></content>
	    <meta name="menu" content="Parammaster"/>
	</head>
<fieldset>
		<form:form commandName="parammaster" action="addupdateParammaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li><label class="desc" for="description">description</label><form:errors path="description" /><span><form:input cssClass="text medium" path="description" id="description"/></span></li>
<c:if test="${ empty requestScope['parammaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['parammaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>