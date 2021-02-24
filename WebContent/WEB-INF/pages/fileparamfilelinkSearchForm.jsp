<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.fileparamfilelinkList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.fileparamfilelinkList.title"/></content>
	    <meta name="menu" content="Fileparamfilelink"/>
	</head>

<fieldset>
		<form:form commandName="fileparamfilelink" action="searchFileparamfilelink.html" method="post">
			<ul>
				<li><label class="desc" for="fileid">fileid</label><form:errors path="fileid" /><span><form:input cssClass="text medium" path="fileid" id="fileid"/></span></li>
				<li><label class="desc" for="fileparamid">fileparamid</label><form:errors path="fileparamid" /><span><form:input cssClass="text medium" path="fileparamid" id="fileparamid"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="fileparamfilelinkEmList.jsp" %>