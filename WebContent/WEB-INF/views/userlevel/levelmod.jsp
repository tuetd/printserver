<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />
<div id="container" style="width: 400px;float:left;">
	<form:form commandName="userLevelMForm" method="POST" action="${pageContext.request.contextPath}/admin/levelmod.html">
		<div id="header" class="info">
			<b>Level modification</b>
			<form:hidden path="levelid"/>
		</div>
		
		<p>
            <b>
            	&nbsp;&nbsp;&nbsp;&nbsp;Note: Level code is required !
            </b>
        </p>
        
        <font color="blue">
			&nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors path="*" /></b>
		</font> 
        		
		<div style="padding-top: 5px; padding-left: 10px;">
			<ul style="float: left; padding: 0;">
				<li>Level code : &nbsp;&nbsp; </li>
				<li>Level name : &nbsp;&nbsp; </li>
				<li class="buttons">
					<input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit" />
					<input name="reset" type="reset" />
				</li>
			</ul>
			<ul>
				<li class="changepwd">
					<c:if test="${not empty userLevelMForm.levelid}">
						<form:input path="levelcode" cssClass="field text medium" readonly="true" />
					</c:if>
					<c:if test="${empty userLevelMForm.levelid}">
						<form:input path="levelcode" cssClass="field text medium" />
					</c:if>
					<font color="red">(*)<form:errors cssClass="red" path="levelcode" /> </font>
				</li>
				<li class="changepwd">
					<form:input path="levelname" cssClass="field text medium" />
				</li>
			</ul>
		</div>
	</form:form>
</div>
