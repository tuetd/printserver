<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.filemasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.filemasterList.title"/></content>
	    <meta name="menu" content="Filemaster"/>
	</head>

<fieldset>
		<form:form commandName="filemaster" action="searchFilemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="filepath">filepath</label><form:errors path="filepath" /><span><form:input cssClass="text medium" path="filepath" id="filepath"/></span></li>
				<li><label class="desc" for="sheetnumber">sheetnumber</label><form:errors path="sheetnumber" /><span><form:input cssClass="text medium" path="sheetnumber" id="sheetnumber"/></span></li>
				<li><label class="desc" for="startindex">startindex</label><form:errors path="startindex" /><span><form:input cssClass="text medium" path="startindex" id="startindex"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="typeid">typeid</label><form:errors path="typeid" /><span><form:input cssClass="text medium" path="typeid" id="typeid"/></span></li>
				<li><label class="desc" for="projectid">projectid</label><form:errors path="projectid" /><span><form:input cssClass="text medium" path="projectid" id="projectid"/></span></li>
				<li id="buttons"><input class="button" type="submit" name="search" value="Search" /></li>
			</ul>	
		</form:form>
</fieldset>
<%@ include file="filemasterEmList.jsp" %>