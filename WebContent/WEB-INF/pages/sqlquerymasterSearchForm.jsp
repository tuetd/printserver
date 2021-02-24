<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.sqlquerymasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.sqlquerymasterList.title"/></content>
	    <meta name="menu" content="Sqlquerymaster"/>
	</head>

<fieldset>
		<form:form commandName="sqlquerymaster" action="searchSqlquerymaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li><label class="desc" for="datasourceid">datasourceid</label><form:errors path="datasourceid" /><span><form:input cssClass="text medium" path="datasourceid" id="datasourceid"/></span></li>
				<li><label class="desc" for="type">type</label><form:errors path="type" /><span><form:input cssClass="text medium" path="type" id="type"/></span></li>
				<li><label class="desc" for="indexnum">indexnum</label><form:errors path="indexnum" /><span><form:input cssClass="text medium" path="indexnum" id="indexnum"/></span></li>
				<li><label class="desc" for="queryname">queryname</label><form:errors path="queryname" /><span><form:input cssClass="text medium" path="queryname" id="queryname"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="sqlquerymasterEmList.jsp" %>