<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.userparamlink.title"/></title>
	    <content tag="heading"><fmt:message key="menu.userparamlink.title"/></content>
	    <meta name="menu" content="Userparamlink"/>
	</head>
<fieldset>
		<form:form commandName="userparamlink" action="addupdateUserparamlink.html" method="post">
			<ul>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li><label class="desc" for="paramid">paramid</label><form:errors path="paramid" /><span><form:input cssClass="text medium" path="paramid" id="paramid"/></span></li>
				<li><label class="desc" for="value">value</label><form:errors path="value" /><span><form:input cssClass="text medium" path="value" id="value"/></span></li>
<c:if test="${ empty requestScope['userparamlink'].userid}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['userparamlink'].userid}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>