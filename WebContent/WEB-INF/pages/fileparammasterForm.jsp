<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.fileparammaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.fileparammaster.title"/></content>
	    <meta name="menu" content="Fileparammaster"/>
	</head>
<fieldset>
		<form:form commandName="fileparammaster" action="addupdateFileparammaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="filetypeid">filetypeid</label><form:errors path="filetypeid" /><span><form:input cssClass="text medium" path="filetypeid" id="filetypeid"/></span></li>
<c:if test="${ empty requestScope['fileparammaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['fileparammaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>