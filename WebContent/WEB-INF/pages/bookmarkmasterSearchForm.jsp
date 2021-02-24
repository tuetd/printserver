<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.bookmarkmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.bookmarkmasterList.title"/></content>
	    <meta name="menu" content="Bookmarkmaster"/>
	</head>

<fieldset>
		<form:form commandName="bookmarkmaster" action="searchBookmarkmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="docid">docid</label><form:errors path="docid" /><span><form:input cssClass="text medium" path="docid" id="docid"/></span></li>
				<li><label class="desc" for="fieldid">fieldid</label><form:errors path="fieldid" /><span><form:input cssClass="text medium" path="fieldid" id="fieldid"/></span></li>
				<li><label class="desc" for="function">function</label><form:errors path="function" /><span><form:input cssClass="text medium" path="function" id="function"/></span></li>
				<li><label class="desc" for="format">format</label><form:errors path="format" /><span><form:input cssClass="text medium" path="format" id="format"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="bookmarkmasterEmList.jsp" %>