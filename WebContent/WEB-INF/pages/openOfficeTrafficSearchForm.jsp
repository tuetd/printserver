<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.openOfficeTrafficList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.openOfficeTrafficList.title"/></content>
	    <meta name="menu" content="OpenOfficeTraffic"/>
	</head>

<fieldset>
		<form:form commandName="openOfficeTraffic" action="searchOpenOfficeTraffic.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="servername">servername</label><form:errors path="servername" /><span><form:input cssClass="text medium" path="servername" id="servername"/></span></li>
				<li><label class="desc" for="maxConn">maxConn</label><form:errors path="maxConn" /><span><form:input cssClass="text medium" path="maxConn" id="maxConn"/></span></li>
				<li><label class="desc" for="currConn">currConn</label><form:errors path="currConn" /><span><form:input cssClass="text medium" path="currConn" id="currConn"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="openOfficeTrafficEmList.jsp" %>