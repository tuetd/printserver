<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.userdocprinterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.userdocprinterList.title"/></content>
	    <meta name="menu" content="Userdocprinter"/>
	</head>

<fieldset>
		<form:form commandName="userdocprinter" action="searchUserdocprinter.html" method="post">
			<ul>
				<li><label class="desc" for="docid">docid</label><form:errors path="docid" /><span><form:input cssClass="text medium" path="docid" id="docid"/></span></li>
				<li><label class="desc" for="printername">printername</label><form:errors path="printername" /><span><form:input cssClass="text medium" path="printername" id="printername"/></span></li>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="userdocprinterEmList.jsp" %>