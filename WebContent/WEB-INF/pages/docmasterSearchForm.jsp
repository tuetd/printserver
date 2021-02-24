<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.docmasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.docmasterList.title"/></content>
	    <meta name="menu" content="Docmaster"/>
	</head>

<fieldset>
		<form:form commandName="docmaster" action="searchDocmaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="startpage">startpage</label><form:errors path="startpage" /><span><form:input cssClass="text medium" path="startpage" id="startpage"/></span></li>
				<li><label class="desc" for="endpage">endpage</label><form:errors path="endpage" /><span><form:input cssClass="text medium" path="endpage" id="endpage"/></span></li>
				<li><label class="desc" for="templatefile">templatefile</label><form:errors path="templatefile" /><span><form:input cssClass="text medium" path="templatefile" id="templatefile"/></span></li>
				<li><label class="desc" for="serverfile">serverfile</label><form:errors path="serverfile" /><span><form:input cssClass="text medium" path="serverfile" id="serverfile"/></span></li>
				<li><label class="desc" for="localfile">localfile</label><form:errors path="localfile" /><span><form:input cssClass="text medium" path="localfile" id="localfile"/></span></li>
				<li><label class="desc" for="datasourceId">datasourceId</label><form:errors path="datasourceId" /><span><form:input cssClass="text medium" path="datasourceId" id="datasourceId"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="docmasterEmList.jsp" %>