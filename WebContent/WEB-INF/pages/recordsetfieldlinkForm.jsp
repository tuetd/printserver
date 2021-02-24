<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.recordsetfieldlink.title"/></title>
	    <content tag="heading"><fmt:message key="menu.recordsetfieldlink.title"/></content>
	    <meta name="menu" content="Recordsetfieldlink"/>
	</head>
<fieldset>
		<form:form commandName="recordsetfieldlink" action="addupdateRecordsetfieldlink.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="fieldid">fieldid</label><form:errors path="fieldid" /><span><form:input cssClass="text medium" path="fieldid" id="fieldid"/></span></li>
				<li><label class="desc" for="recordsetid">recordsetid</label><form:errors path="recordsetid" /><span><form:input cssClass="text medium" path="recordsetid" id="recordsetid"/></span></li>
				<li><label class="desc" for="data">data</label><form:errors path="data" /><span><form:input cssClass="text medium" path="data" id="data"/></span></li>
<c:if test="${ empty requestScope['recordsetfieldlink'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['recordsetfieldlink'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>