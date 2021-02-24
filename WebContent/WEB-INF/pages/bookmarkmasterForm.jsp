<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.bookmarkmaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.bookmarkmaster.title"/></content>
	    <meta name="menu" content="Bookmarkmaster"/>
	</head>
<fieldset>
		<form:form commandName="bookmarkmaster" action="addupdateBookmarkmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="docid">docid</label><form:errors path="docid" /><span><form:input cssClass="text medium" path="docid" id="docid"/></span></li>
				<li><label class="desc" for="fieldid">fieldid</label><form:errors path="fieldid" /><span><form:input cssClass="text medium" path="fieldid" id="fieldid"/></span></li>
				<li><label class="desc" for="function">function</label><form:errors path="function" /><span><form:input cssClass="text medium" path="function" id="function"/></span></li>
				<li><label class="desc" for="format">format</label><form:errors path="format" /><span><form:input cssClass="text medium" path="format" id="format"/></span></li>
<c:if test="${ empty requestScope['bookmarkmaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['bookmarkmaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>