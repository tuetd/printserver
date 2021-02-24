<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.userparamlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.userparamlinkList.title"/></content>
	    <meta name="menu" content="Userparamlink"/>
	</head>

<fieldset>
		<form:form commandName="userparamlink" action="searchUserparamlink.html" method="post">
			<ul>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li><label class="desc" for="paramid">paramid</label><form:errors path="paramid" /><span><form:input cssClass="text medium" path="paramid" id="paramid"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="userparamlinkEmList.jsp" %>