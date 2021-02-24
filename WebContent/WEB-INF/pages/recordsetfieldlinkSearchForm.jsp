<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.recordsetfieldlinkList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.recordsetfieldlinkList.title"/></content>
	    <meta name="menu" content="Recordsetfieldlink"/>
	</head>

<fieldset>
		<form:form commandName="recordsetfieldlink" action="searchRecordsetfieldlink.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="fieldid">fieldid</label><form:errors path="fieldid" /><span><form:input cssClass="text medium" path="fieldid" id="fieldid"/></span></li>
				<li><label class="desc" for="recordsetid">recordsetid</label><form:errors path="recordsetid" /><span><form:input cssClass="text medium" path="recordsetid" id="recordsetid"/></span></li>
				<li><label class="desc" for="data">data</label><form:errors path="data" /><span><form:input cssClass="text medium" path="data" id="data"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="recordsetfieldlinkEmList.jsp" %>