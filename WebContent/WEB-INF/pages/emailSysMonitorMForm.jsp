<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.emailSysMonitorM.title"/></title>
	    <content tag="heading"><fmt:message key="menu.emailSysMonitorM.title"/></content>
	    <meta name="menu" content="EmailSysMonitorM"/>
	</head>
<fieldset>
		<form:form commandName="emailSysMonitorM" action="addupdateEmailSysMonitorM.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="sysName">sysName</label><form:errors path="sysName" /><span><form:input cssClass="text medium" path="sysName" id="sysName"/></span></li>
				<li><label class="desc" for="sysCode">sysCode</label><form:errors path="sysCode" /><span><form:input cssClass="text medium" path="sysCode" id="sysCode"/></span></li>
				<li><label class="desc" for="emailContent">emailContent</label><form:errors path="emailContent" /><span><form:input cssClass="text medium" path="emailContent" id="emailContent"/></span></li>
<c:if test="${ empty requestScope['emailSysMonitorM'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['emailSysMonitorM'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>