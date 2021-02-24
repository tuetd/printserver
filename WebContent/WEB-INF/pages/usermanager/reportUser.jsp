<%@ include file="/common/taglibs.jsp"%>
<head>
<title><fmt:message key="user.search.title" /></title>
<script type="text/javascript">
	jQuery().ready(
			function() {
				jQuery("#fromdate").datepicker(
						{
							defaultDate : "+1w",
							changeMonth : true,
							numberOfMonths : 3,
							onSelect : function(selectedDate) {
								jQuery("#todate").datepicker("option",
										"minDate", selectedDate);
							}
						});
				jQuery("#todate").datepicker(
						{
							defaultDate : "+1w",
							changeMonth : true,
							numberOfMonths : 3,
							onSelect : function(selectedDate) {
								jQuery("#fromdate").datepicker("option",
										"maxDate", selectedDate);
							}
						});

			});
</script>
<script type="text/javascript">
	function deleteObject(id, username, url) {
		url = url + '?id=' + id;

		var answer = confirm("Are you sure to want delete this " + username
				+ "?");
		if (answer) {
			window.location = url;
		}
	}
</script>
</head>
<body>
	<div id="wrapper">

		<div id="twocenter-columns">
		<form:form class="box" commandName="reportUserSearch" action="searchreportUser.html"
		method="post">
			<div id="column1test">
				<div class="rowtest">
					<div class="viewtext">
						<label><fmt:message key="user.search.fromdate" /></label>
					</div>
					<div class="inputtext">
						<form:input id="fromdate" cssClass="inp2" path="fromdateForm" />
					</div>
				</div>
				<br/><br/>
				<div class="rowtest">
					<div class="viewtext">
						<label ><fmt:message key="user.search.todate" /></label>
					</div>
					<div class="inputtext">
						<form:input id="todate" cssClass="inp2" path="todateForm" />
					</div>
				</div>
				<div class="rowtest">
				<div class="viewtext">
						<label ></label>
					</div>
					<div class="inputtext">
					<input class="btnLogin" type="submit" value="Search" name="search" />
					</div>
				
				</div>
			</div>
			<div id="column2test">
			
			<div class="rowtest">
				<div class="viewtext">
						<label ><fmt:message key="user.search.status" /></label>
					</div>
					<div class="inputtext">
					
					<form:select path="statusForm" cssClass="cjComboBox">
						<form:options items="${liststatus}" itemValue="status"
							itemLabel="name" />
					</form:select>
					
					</div>
				
				</div>
			
			</div>
			</form:form>
		</div>


				<c:if test="${ not empty Usermasterlist}">
				
		<div id="welcome">
				<h3><span>${message_result}</span></h3>
	<table id="tfhover" class="tftable">
		<thead>
			<tr>
				<th width="40px">ID
				</th>
				<th>Username
				</th>
				<th>Loggedin
				</th>
				<th>RoleId
				</th>
				<th>SessionId
				</th>
				<th>LastChangedPw
				</th>
<!-- 				<th width="60px"><a href="#">Action</a> -->
<!-- 				</th> -->

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Usermasterlist}" var="items">
				<tr>
					<td class="a-center">${items.id}</td>
					<td><a href="<%=request.getContextPath() %>/createUserForm.html?usermasterId=${items.id}">${items.username}</a></td>
					<td>${items.loggedin}</td>
					<td>${items.roleId}</td>
					<td>${items.sessionId}</td>
					<td>${items.lastChangedPw}</td>
<%-- 					<td align="center"><a href="resetpassword.html?id=${items.id}"><img --%>
<!-- 							title="Reset password" src="images/lock_unlock.png" /> -->
<!-- 					</a> &nbsp; <a href="#" -->
<%-- 						onclick="deleteObject('${items.id}','${items.username}','deleteuser.html')"><img --%>
<!-- 							src="images/delete.png" title="Delete" width="16" height="16" /> -->
<!-- 					</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/pages/pager.jsp" />
		</div>
	
</c:if>
				
				
	<c:if test="${ not empty Usermasterlist}">
	<form:form action="getMyReport.html" method="post">
		<input class="btnLogin"  type="submit" value="Report" name="report" />
	</form:form></c:if>			
	</div>
</body>


