<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.parammasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.parammasterList.title"/></content>
	    <meta name="menu" content="Parammaster"/>
	</head>

<fieldset>
		<form:form commandName="parammaster" action="searchParammaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li><label class="desc" for="description">description</label><form:errors path="description" /><span><form:input cssClass="text medium" path="description" id="description"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="parammasterEmList.jsp" %>