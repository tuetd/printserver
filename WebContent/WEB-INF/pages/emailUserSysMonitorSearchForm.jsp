<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.emailUserSysMonitorList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.emailUserSysMonitorList.title"/></content>
	    <meta name="menu" content="EmailUserSysMonitor"/>
	</head>

<fieldset>
		<form:form commandName="emailUserSysMonitor" action="searchEmailUserSysMonitor.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="userId">userId</label><form:errors path="userId" /><span><form:input cssClass="text medium" path="userId" id="userId"/></span></li>
				<li><label class="desc" for="sysId">sysId</label><form:errors path="sysId" /><span><form:input cssClass="text medium" path="sysId" id="sysId"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="emailUserSysMonitorEmList.jsp" %>