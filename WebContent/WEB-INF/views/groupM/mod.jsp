<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--
 * View implementation for table "GROUP_M"
 * 
 * @author Telosys Tools Generator
 *
-->
 


<br />
	<form:form commandName="groupMForm" method="POST" action="${pageContext.request.contextPath}/admin/groupmod.html">
		<div id="header" class="info">
			<b>GroupM modification</b>
					</div>
        	<p>
            <b>
            	&nbsp;&nbsp;&nbsp;&nbsp;Note: Group code is required !
            </b>
        </p>
		<div id="input">	        
	        <font color="blue">
				&nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors path="*" /></b>
			</font> 
			<table class="width30">
				<form:hidden path="groupid" />
								<tr>
					<td class="width40">groupcode :</td>
					<td class="width60">
						<form:input path="groupcode" cssClass="field text width90" />
					</td>
				</tr>
								<tr>
					<td class="width40">groupname :</td>
					<td class="width60">
						<form:input path="groupname" cssClass="field text width90" />
					</td>
				</tr>
								<tr>
					<td class="width40">groupdesc :</td>
					<td class="width60">
						<form:input path="groupdesc" cssClass="field text width90" />
					</td>
				</tr>
				<tr>
					<td>Active :</td>
					<td><form:checkbox path="isActived" /></td>
				</tr>
								<tr colspan="2" class="center">
					<td>
						<input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit" />
					<input name="reset" type="reset" />
					</td>
				</tr>
			</table>
		</div>
	</form:form>
