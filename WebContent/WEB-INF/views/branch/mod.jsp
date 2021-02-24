<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "RM_BRANCH"
 * 
 * @author Telosys Tools Generator
 *
-->



<br />
<form:form commandName="branchForm" method="POST" action="${pageContext.request.contextPath}/admin/branchmod.html">
	<div id="header" class="info">
		<b>RmBranch modification</b>
		<form:hidden path="id" />
	</div>

	<div id="input">
		<font color="blue"> &nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors
					path="*" />
		</b> </font>
		<table class="width30">
			<tr>
				<td class="width40">code :</td>
				<td class="width60"><form:input path="code"
						cssClass="field text width90" /></td>
			</tr>
			<tr>
				<td class="width40">name :</td>
				<td class="width60"><form:input path="name"
						cssClass="field text width90" /></td>
			</tr>
			<tr>
					<td>Active :</td>
					<td><form:checkbox path="isActived" /></td>
				</tr>
			<tr colspan="2" class="center">
				<td><input id="saveForm" name="saveForm" class="btTxt submit"
					type="submit" value="Submit" /> <input name="reset" type="reset" />
				</td>
			</tr>
		</table>
	</div>
</form:form>
