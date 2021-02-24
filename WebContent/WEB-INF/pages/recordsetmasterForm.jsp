<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.recordsetmaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.recordsetmaster.title"/></content>
	    <meta name="menu" content="Recordsetmaster"/>
	</head>
<fieldset>
		<form:form commandName="recordsetmaster" action="addupdateRecordsetmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="datetime">datetime</label><form:errors path="datetime" /><span><form:input cssClass="text medium" path="datetime" id="datetime"/></span></li>
				<li><label class="desc" for="statusid">statusid</label><form:errors path="statusid" /><span><form:input cssClass="text medium" path="statusid" id="statusid"/></span></li>
				<li><label class="desc" for="userid">userid</label><form:errors path="userid" /><span><form:input cssClass="text medium" path="userid" id="userid"/></span></li>
<c:if test="${ empty requestScope['recordsetmaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['recordsetmaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>