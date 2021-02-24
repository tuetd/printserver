<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.statusmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.statusmasterList.title"/></content>
	    <meta name="menu" content="Statusmaster"/>
	</head>

<fieldset>
		<form:form commandName="statusmaster" action="searchStatusmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="statusmasterEmList.jsp" %>