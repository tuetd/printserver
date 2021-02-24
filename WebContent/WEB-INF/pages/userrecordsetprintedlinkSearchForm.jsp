<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.userrecordsetprintedlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.userrecordsetprintedlinkList.title"/></content>
	    <meta name="menu" content="Userrecordsetprintedlink"/>
	</head>

<fieldset>
		<form:form commandName="userrecordsetprintedlink" action="searchUserrecordsetprintedlink.html" method="post">
			<ul>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li><label class="desc" for="recordsetid">recordsetid</label><form:errors path="recordsetid" /><span><form:input cssClass="text medium" path="recordsetid" id="recordsetid"/></span></li>
				<li><label class="desc" for="datetimeprinted">datetimeprinted</label><form:errors path="datetimeprinted" /><span><form:input cssClass="text medium" path="datetimeprinted" id="datetimeprinted"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="userrecordsetprintedlinkEmList.jsp" %>