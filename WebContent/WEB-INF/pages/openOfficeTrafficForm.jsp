<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.openOfficeTraffic.title"/></title>
	    <content tag="heading"><fmt:message key="menu.openOfficeTraffic.title"/></content>
	    <meta name="menu" content="OpenOfficeTraffic"/>
	</head>
<fieldset>
		<form:form commandName="openOfficeTraffic" action="addupdateOpenOfficeTraffic.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="servername">servername</label><form:errors path="servername" /><span><form:input cssClass="text medium" path="servername" id="servername"/></span></li>
				<li><label class="desc" for="maxConn">maxConn</label><form:errors path="maxConn" /><span><form:input cssClass="text medium" path="maxConn" id="maxConn"/></span></li>
				<li><label class="desc" for="currConn">currConn</label><form:errors path="currConn" /><span><form:input cssClass="text medium" path="currConn" id="currConn"/></span></li>
<c:if test="${ empty requestScope['openOfficeTraffic'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['openOfficeTraffic'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>