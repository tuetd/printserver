<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "RM_SCREEN"
 * 
 * @author Telosys Tools Generator
 *
-->



<br />
<form:form commandName="screenForm" method="POST" action="${pageContext.request.contextPath}/admin/screenmod.html">
	<div id="header" class="info">
		<b>Screen modification</b>
		<form:hidden path="id" />
	</div>

	<div id="input">
		<font color="blue"> &nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors
					path="*" />
		</b> </font>
		<table class="width30">
			<tr>
				<td class="width40">stage :</td>
				<td class="width60"><form:input path="stage"
						cssClass="field text width90" /></td>
			</tr>
			<tr>
				<td class="width40">name :</td>
				<td class="width60"><form:input path="name"
						cssClass="field text width90" /></td>
			</tr>

			<tr>
				<td class="width40">view name :</td>
				<td class="width60"><form:input path="viewName"
						cssClass="field text width90" /></td>
			</tr>

			<tr>
				<td class="width40">priority1 :</td>
				<td class="width60"><form:input path="priority1"
						cssClass="field text width90" /></td>
			</tr>

			<tr>
				<td class="width40">priority2 :</td>
				<td class="width60"><form:input path="priority2"
						cssClass="field text width90" /></td>
			</tr>
			<tr>
				<td>Active :</td>
				<td><form:checkbox path="isActived" />
				</td>
			</tr>
			<tr colspan="2" class="center">
				<td><input id="saveForm" name="saveForm" class="btTxt submit"
					type="submit" value="Submit" /> <input name="reset" type="reset" />
				</td>
			</tr>
		</table>
	</div>
</form:form>
