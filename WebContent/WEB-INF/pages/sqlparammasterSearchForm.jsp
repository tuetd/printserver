<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.sqlparammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.sqlparammasterList.title"/></content>
	    <meta name="menu" content="Sqlparammaster"/>
	</head>

<fieldset>
		<form:form commandName="sqlparammaster" action="searchSqlparammaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="friendlyname">friendlyname</label><form:errors path="friendlyname" /><span><form:input cssClass="text medium" path="friendlyname" id="friendlyname"/></span></li>
				<li><label class="desc" for="typeid">typeid</label><form:errors path="typeid" /><span><form:input cssClass="text medium" path="typeid" id="typeid"/></span></li>
				<li><label class="desc" for="datasourceid">datasourceid</label><form:errors path="datasourceid" /><span><form:input cssClass="text medium" path="datasourceid" id="datasourceid"/></span></li>
				<li><label class="desc" for="fieldtype">fieldtype</label><form:errors path="fieldtype" /><span><form:input cssClass="text medium" path="fieldtype" id="fieldtype"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="sqlparammasterEmList.jsp" %>