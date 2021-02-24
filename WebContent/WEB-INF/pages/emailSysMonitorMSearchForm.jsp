<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.emailSysMonitorMList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.emailSysMonitorMList.title"/></content>
	    <meta name="menu" content="EmailSysMonitorM"/>
	</head>

<fieldset>
		<form:form commandName="emailSysMonitorM" action="searchEmailSysMonitorM.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="sysName">sysName</label><form:errors path="sysName" /><span><form:input cssClass="text medium" path="sysName" id="sysName"/></span></li>
				<li><label class="desc" for="sysCode">sysCode</label><form:errors path="sysCode" /><span><form:input cssClass="text medium" path="sysCode" id="sysCode"/></span></li>
				<li><label class="desc" for="emailContent">emailContent</label><form:errors path="emailContent" /><span><form:input cssClass="text medium" path="emailContent" id="emailContent"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="emailSysMonitorMEmList.jsp" %>