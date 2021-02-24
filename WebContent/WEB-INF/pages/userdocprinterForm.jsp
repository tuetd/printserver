<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.userdocprinter.title"/></title>
	    <content tag="heading"><fmt:message key="menu.userdocprinter.title"/></content>
	    <meta name="menu" content="Userdocprinter"/>
	</head>
<fieldset>
		<form:form commandName="userdocprinter" action="addupdateUserdocprinter.html" method="post">
			<ul>
				<li><label class="desc" for="docid">docid</label><form:errors path="docid" /><span><form:input cssClass="text medium" path="docid" id="docid"/></span></li>
				<li><label class="desc" for="printername">printername</label><form:errors path="printername" /><span><form:input cssClass="text medium" path="printername" id="printername"/></span></li>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
<c:if test="${ empty requestScope['userdocprinter'].userid}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['userdocprinter'].userid}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>