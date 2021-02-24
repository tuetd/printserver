<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.datasourcemaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.datasourcemaster.title"/></content>
	    <meta name="menu" content="Datasourcemaster"/>
	</head>
<fieldset>
		<form:form commandName="datasourcemaster" action="addupdateDatasourcemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="fileid">fileid</label><form:errors path="fileid" /><span><form:input cssClass="text medium" path="fileid" id="fileid"/></span></li>
				<li><label class="desc" for="serverconfigid">serverconfigid</label><form:errors path="serverconfigid" /><span><form:input cssClass="text medium" path="serverconfigid" id="serverconfigid"/></span></li>
<c:if test="${ empty requestScope['datasourcemaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['datasourcemaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>