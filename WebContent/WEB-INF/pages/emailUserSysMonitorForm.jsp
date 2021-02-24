<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.emailUserSysMonitor.title"/></title>
	    <content tag="heading"><fmt:message key="menu.emailUserSysMonitor.title"/></content>
	    <meta name="menu" content="EmailUserSysMonitor"/>
	</head>
<fieldset>
		<form:form commandName="emailUserSysMonitor" action="addupdateEmailUserSysMonitor.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="userId">userId</label><form:errors path="userId" /><span><form:input cssClass="text medium" path="userId" id="userId"/></span></li>
				<li><label class="desc" for="sysId">sysId</label><form:errors path="sysId" /><span><form:input cssClass="text medium" path="sysId" id="sysId"/></span></li>
<c:if test="${ empty requestScope['emailUserSysMonitor'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['emailUserSysMonitor'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>