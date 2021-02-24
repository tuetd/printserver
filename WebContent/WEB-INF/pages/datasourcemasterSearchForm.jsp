<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.datasourcemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.datasourcemasterList.title"/></content>
	    <meta name="menu" content="Datasourcemaster"/>
	</head>

<fieldset>
		<form:form commandName="datasourcemaster" action="searchDatasourcemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="fileid">fileid</label><form:errors path="fileid" /><span><form:input cssClass="text medium" path="fileid" id="fileid"/></span></li>
				<li><label class="desc" for="serverconfigid">serverconfigid</label><form:errors path="serverconfigid" /><span><form:input cssClass="text medium" path="serverconfigid" id="serverconfigid"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="datasourcemasterEmList.jsp" %>