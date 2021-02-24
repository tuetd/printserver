<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<head>
<title><fmt:message key="menu.usermaster.title" /></title>
</head>
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
</script>
	<div id="wrapper">
		<div id="twocenter-columns">
			<form:form class="box" commandName="prtReprintForm" method="POST">
			<form:hidden path="id"/>
				<form:errors cssStyle="color:red" path="*" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label>Agreement No <font style="color: red">(*)</font> </label>
						</div>
						<div class="inputtext">
							<c:if test="${not empty prtReprintForm.id}">
						<form:input path="applid"  readonly="true" />
					</c:if>
					<c:if test="${empty prtReprintForm.id}">
						<form:input path="applid"  />
					</c:if>
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label>Will close in (minutes)<font style="color: red">(*)</font></label>
						</div>
						<div class="inputtext">
							<form:input path="stopInMinutes" />
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label> Notes </label>
						</div>
						<div class="inputtext">
							<form:input path="notes" />
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<input class="btnLogin" id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit" />
						</div>
						<div class="inputtext">
							<input class="btnLogin" name="reset" type="reset" />
						</div>
					</div>
					
				</div>
				
			</form:form>
		</div>
	</div>


<!-- end of cntool -->

