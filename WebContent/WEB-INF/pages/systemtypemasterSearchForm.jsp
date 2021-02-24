<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.systemtypemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.systemtypemasterList.title"/></content>
	    <meta name="menu" content="Systemtypemaster"/>
	</head>

<fieldset>
		<form:form commandName="systemtypemaster" action="searchSystemtypemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="systemtypemasterEmList.jsp" %>