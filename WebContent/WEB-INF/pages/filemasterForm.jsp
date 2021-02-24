<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.filemaster.title"/></title>
	    <content tag="heading"><fmt:message key="menu.filemaster.title"/></content>
	    <meta name="menu" content="Filemaster"/>
	</head>
<fieldset>
		<form:form commandName="filemaster" action="addupdateFilemaster.html" method="post">
			<ul>
				<li><label class="desc" for="id">id</label><form:errors path="id" /><span><form:input cssClass="text medium" path="id" id="id"/></span></li>
				<li><label class="desc" for="filepath">filepath</label><form:errors path="filepath" /><span><form:input cssClass="text medium" path="filepath" id="filepath"/></span></li>
				<li><label class="desc" for="sheetnumber">sheetnumber</label><form:errors path="sheetnumber" /><span><form:input cssClass="text medium" path="sheetnumber" id="sheetnumber"/></span></li>
				<li><label class="desc" for="startindex">startindex</label><form:errors path="startindex" /><span><form:input cssClass="text medium" path="startindex" id="startindex"/></span></li>
				<li><label class="desc" for="name">name</label><form:errors path="name" /><span><form:input cssClass="text medium" path="name" id="name"/></span></li>
				<li><label class="desc" for="typeid">typeid</label><form:errors path="typeid" /><span><form:input cssClass="text medium" path="typeid" id="typeid"/></span></li>
				<li><label class="desc" for="projectid">projectid</label><form:errors path="projectid" /><span><form:input cssClass="text medium" path="projectid" id="projectid"/></span></li>
<c:if test="${ empty requestScope['filemaster'].id}">
				<li><input type="submit" class="button" name="add" value="Add" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
<c:if test="${ not empty requestScope['filemaster'].id}">
				<li><input type="submit" class="button" name="update" value="Update" />&nbsp;<input class="button" type="submit" name="cancel" value="Cancel" /></li>
</c:if>
			</ul>	
		</form:form>
</fieldset>