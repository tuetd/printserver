<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.sqlquerymaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.sqlquerymaster.title"/></content>
	    <meta name="menu" content="Sqlquerymaster"/>
	</head>
<fieldset>
		<form:form commandName="sqlquerymaster" action="addupdateSqlquerymaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li><label class="desc" for="datasourceid">datasourceid</label><form:errors path="datasourceid" /><span><form:input cssClass="text medium" path="datasourceid" id="datasourceid"/></span></li>
				<li><label class="desc" for="type">type</label><form:errors path="type" /><span><form:input cssClass="text medium" path="type" id="type"/></span></li>
				<li><label class="desc" for="indexnum">indexnum</label><form:errors path="indexnum" /><span><form:input cssClass="text medium" path="indexnum" id="indexnum"/></span></li>
				<li><label class="desc" for="queryname">queryname</label><form:errors path="queryname" /><span><form:input cssClass="text medium" path="queryname" id="queryname"/></span></li>
<c:if test="${ empty requestScope['sqlquerymaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['sqlquerymaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>