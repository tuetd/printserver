<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.filetypemaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.filetypemaster.title"/></content>
	    <meta name="menu" content="Filetypemaster"/>
	</head>
<fieldset>
		<form:form commandName="filetypemaster" action="addupdateFiletypemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
<c:if test="${ empty requestScope['filetypemaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['filetypemaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>