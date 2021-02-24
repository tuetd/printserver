<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.userrecordsetprintedlink.title"/></title>
	    <content tag="heading"><fmt:message key="menu.userrecordsetprintedlink.title"/></content>
	    <meta name="menu" content="Userrecordsetprintedlink"/>
	</head>
<fieldset>
		<form:form commandName="userrecordsetprintedlink" action="addupdateUserrecordsetprintedlink.html" method="post">
			<ul>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li><label class="desc" for="recordsetid">recordsetid</label><form:errors path="recordsetid" /><span><form:input cssClass="text medium" path="recordsetid" id="recordsetid"/></span></li>
				<li><label class="desc" for="datetimeprinted">datetimeprinted</label><form:errors path="datetimeprinted" /><span><form:input cssClass="text medium" path="datetimeprinted" id="datetimeprinted"/></span></li>
<c:if test="${ empty requestScope['userrecordsetprintedlink'].userid}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['userrecordsetprintedlink'].userid}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>