<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br />

<script type="text/javascript">	
	function setFocus()
	{
		if ($("#id").val() != ""){
			$("#fullname").focus();
		} else {
			$("#username").focus();
		}
	}

	$(document).ready(function() {
		setFocus();
	});
	
	function loadProducts(){
		var branchId  = $('#BranchSelect :selected').val();
		$.ajax({
			type: "POST",
			url: ctx + '/user/loadProducts.html',
			data: {branchId: branchId}
			})
			.error(function(jqXHR, textStatus, errorMessage){
				alert('error: '+ errorMessage);
				//alert('error textStatus: '+ textStatus);
			})
			.success(function (data) {
				//alert( "Data Saved: " + data );
				var obj = $('#ProductSelect');
				var html = '<option value="0"> --- Select product --- </option>"';
				for(var i in data){
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>"';
				}		
				obj.html(html);
	         })
			.done(function( msg ) {
				//alert( "Data Saved: " + msg );
		});
		
	}
</script>
<div id="container" style="width: 400px;float:left;">
	<form:form commandName="userForm" method="POST" action="${pageContext.request.contextPath}/user/usermod.html">
		<div id="header" class="info">
			<b>User modification</b>
			<form:hidden path="id"/>
		</div>
		
		<p>
            <b>
            	&nbsp;&nbsp;&nbsp;&nbsp;Note: User name & Email are required !
            </b>
        </p>
        
        <font color="blue">
			&nbsp;&nbsp;&nbsp;&nbsp;<b><form:errors path="*" /></b>
		</font> 
        		
		<div style="padding-top: 5px; padding-left: 10px;">
			<table>
				<tr>
					<td class="width40">User name :</td>
					<td class="width60">
						<c:if test="${not empty userForm.id}">
							<form:input path="username" cssClass="field text width100" readonly="true" />
						</c:if>
						<c:if test="${empty userForm.id}">
							<form:input path="username" cssClass="field text width100" />
						</c:if>
						<font color="red">(*)<form:errors cssClass="red" path="username" /> </font>
					</td>
				</tr>
				<tr>
					<td>Full name :</td>
					<td><form:input path="fullname" cssClass="field text width100" /></td>
				</tr>
				<tr>
					<td>Department :</td>
					<td>
						<form:select path="department" cssClass="width100">
							<form:option value="0" label="--- Select department ---" />
	                        <form:options items="${userForm.departments}" itemLabel="deptname" itemValue="deptcode"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Level :</td>
					<td>
						<form:select  path="userLevel" cssClass="width100">
							<form:option value="0" label="--- Select user level ---" />
	                        <form:options itemValue="levelcode" items="${userForm.userLevels}" itemLabel="levelname"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Branch :</td>
					<td>
						<form:select path="branchId" cssClass="width100" onchange="loadProducts()" id="BranchSelect">
							<form:option value="0" label="--- Select branch ---" />
	                        <form:options items="${userForm.branchs}" itemLabel="name" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Product :</td>
					<td>
						<form:select path="productId" cssClass="width100" id="ProductSelect">
							<form:option value="0" label="--- Select product ---" />
	                        <form:options items="${userForm.products}" itemLabel="name" itemValue="id"/>
						</form:select>
					</td>
				</tr>				
				<tr>
					<td>Email :</td>
					<td>
						<form:input path="emailCode" cssClass="field text width100" />
						<font color="red">(*)<form:errors cssClass="red" path="emailCode" /></font>
					</td>
				</tr>
				<tr>
					<td>Place (Ex: Pico, STC...) :</td>
					<td><form:input path="userPlace" cssClass="field text width100" /></td>
				</tr>
				<tr>
					<td>Active :</td>
					<td><form:checkbox path="isActived" /></td>
				</tr>
				<tr class="center" >
					<td></td>
					<td>
						<input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit" />
						<input name="reset" type="reset" />
					</td>
				</tr>
			
			</table>
		</div>
	</form:form>
</div>
