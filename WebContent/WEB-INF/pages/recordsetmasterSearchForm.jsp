<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.recordsetmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.recordsetmasterList.title"/></content>
	    <meta name="menu" content="Recordsetmaster"/>
	</head>

<fieldset>
		<form:form commandName="recordsetmaster" action="searchRecordsetmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="datetime">datetime</label><form:errors path="datetime" /><span><form:input cssClass="text medium" path="datetime" id="datetime"/></span></li>
				<li><label class="desc" for="statusid">statusid</label><form:errors path="statusid" /><span><form:input cssClass="text medium" path="statusid" id="statusid"/></span></li>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="recordsetmasterEmList.jsp" %>