<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.fileparamfilelink.title"/></title>
	    <content tag="heading"><fmt:message key="menu.fileparamfilelink.title"/></content>
	    <meta name="menu" content="Fileparamfilelink"/>
	</head>
<fieldset>
		<form:form commandName="fileparamfilelink" action="addupdateFileparamfilelink.html" method="post">
			<ul>
				<li><label class="desc" for="fileid">fileid</label><form:errors path="fileid" /><span><form:input cssClass="text medium" path="fileid" id="fileid"/></span></li>
				<li><label class="desc" for="fileparamid">fileparamid</label><form:errors path="fileparamid" /><span><form:input cssClass="text medium" path="fileparamid" id="fileparamid"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
<c:if test="${ empty requestScope['fileparamfilelink'].fileparamid}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['fileparamfilelink'].fileparamid}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>