<%@ include file="/common/taglibs.jsp"%>
<head>
<title><fmt:message key="menu.usermaster.title" /></title>
<content tag="heading"> <fmt:message
	key="menu.usermaster.title" /></content>
<meta name="menu" content="Usermaster" />

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/scripts/jquery/tablesorter/__jquery.tablesorter.css'/>" />
<script type="text/javascript"
	src="<c:url value='/scripts/jquery/tablesorter/__jquery.tablesorter.min.js'/>"></script>
</head>
<script type="text/javascript">
	jQuery(function() {
		jQuery(".tablesorter")
	        .droppable({
	            drop: function (event, ui){
	            	jQuery(this)
	                    .find('tr:last')
	                    .after(jQuery(ui.draggable));
	            }
	        })
	        .tablesorter()
	        .find('tr')
	        .draggable({ helper: "clone" });
		
		jQuery("input[name='perSave']").click(function(){
			var lstper = [];
			jQuery('#assignedDox tr').each(function() {
			    var perId = jQuery(this).find("td").eq(0).html();    
			    lstper.push(perId);
			});
			jQuery('input[name="lsthidden"]').val(lstper);
			var lstChannelPer = [];
			jQuery('#assignedChannel tr').each(function() {
			    var perId = jQuery(this).find("td").eq(0).html();    
			    lstChannelPer.push(perId);
			});
			jQuery('input[name="lstChannelHidden"]').val(lstChannelPer);
	      });
		jQuery("input[name='perCancel']").click(function(){
			window.location =  "<%=request.getContextPath()%>/listUsermaster.html";
		});    
		
		
// 		jQuery('#checkExisted').click(function() {
// 			jQuery.getJSON('${pageContext.request.contextPath}/user/validateuser.html', function(data) {
// 				jQuery('#checkUserResult').text(data);
// 		        });
// 		      });
		
		
	}); 
	
	
// 	function checkExistedUser(username) {
// 		jQuery.ajax({
// 			type : "POST",
<%-- 			url : "<%=request.getContextPath()%>/user/validateuser.html" , --%>
// 			data : {
// 				username : username
// 			}
// 		}).success(function(data) {
			alert( "Data Saved: " + data );
			window.location.href = "revert.html?message=" + data;
// 			jQuery('#checkUserResult').html(data);
// 		}).done(function(msg) {
			alert( "Data Saved: " + msg );
// 		});
		
		
// 	}
	
	
	
// 	function checkExistedUser(username){
// 		jQuery.ajax({
// 			type: "POST",
<%-- 			url: "<%=request.getContextPath()%>/user/validateuser.html", --%>
// 			cache: true,				
// 			data:username,
// 			success: function(data){
// 				jQuery('#checkUserResult').html(data);
// 			},
// 			error: function(){						
// 				alert('Error while request..');
// 			}
// 		});
// 	}

	
</script>

	<div id="wrapper">

		<div id="twocenter-columns">
			<form:form class="box" commandName="userform" action="createUserForm.html"
				method="post">
				<form:errors path="*" cssClass="error"></form:errors>
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.username" /> </label>
						</div>
						<div class="inputtext">
							<c:if test="${requestScope['userform'].id==0}">
								<form:input  path="username" />
							</c:if>
							<c:if test="${requestScope['userform'].id!=0}">
								<form:input  path="username" disabled="true" />
							</c:if>
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label> <fmt:message key="user.create.usercode" /> </label>
						</div>
						<div class="inputtext">
							<form:input  path="usersecid" />
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label> <fmt:message key="user.create.userfullname" /> </label>
						</div>
						<div class="inputtext">
							<form:input  path="userfullname" />
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label> <fmt:message key="user.create.userfinnonecode" />
							</label>
						</div>
						<div class="inputtext">
							<form:input  path="usercode" />
						</div>
					</div>
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.useremail" />
							</label>
						</div>
						<div class="inputtext">
							<form:input  path="email" />
						</div>
					</div>
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userpermisions" />
							</label>
						</div>
					</div>
			
				<div class="rowtable">
						<table>
						<thead>
							<tr>
								<th>Channel Id</th>
								<th>Channel Name</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td colspan=2>
									<div style="width: 325px; height: 300px; overflow: auto;">
										<table id="availableChannel" class="tablesorter">
											<tbody>
												<c:forEach items="${mapchannel}" var="items">
													<tr>
														<td class="left30">${items.key}</td>
														<td class="right70">${items.value}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userunassigned" />
							</label>
						</div>
					</div>

					<div class="rowtable">
						<table>
						<thead>
							<tr>
								<th>Document Id</th>
								<th>Document Name</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan=2>
									<div style="width: 325px; height: 300px; overflow: auto;">
										<table id="availableDox" class="tablesorter">
											<tbody>
												<c:forEach items="${mapdocmaster}" var="items">
													<tr>
														<td class="left30">${items.key}</td>
														<td class="right70">${items.value}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div id="column2test">
<!-- 					<div class="rowtest"> -->
<!-- 						<div class="checkbutton"> -->
<%-- 							<c:if test="${requestScope['userform'].id==0}"> --%>
<!-- 								<input class="btnLogin" type="button" id="checkExisted"  -->
<!-- 									onclick="checkExistedUser(jQuery('#username').val());" -->
<!-- 										value="Verify exited" /> -->
									
<%-- 							</c:if> --%>
<!-- 						</div> -->
<!-- 						<div class="messagecheck"> -->
<!-- 							<label id="checkUserResult" style="color: red"></label> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userrole" /> </label>

						</div>
						<div class="inputtext">
							<form:select path="userrole" cssClass="cjComboBox">
								<form:options items="${lstrole}" itemValue="id"
									itemLabel="roleName" />
							</form:select>
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userdepartment" />
							</label>

						</div>
						<div class="inputtext">
							<form:select path="department" cssClass="cjComboBox">
								<form:options items="${lstder}" itemValue="name"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userplace" /> </label>

						</div>
						<div class="inputtext">
							<form:select path="userplace" cssClass="cjComboBox">
								<form:options items="${lstplace}" itemValue="name"
									itemLabel="name" />
							</form:select>
						</div>
					</div>

			
							<div class="rowtest">
						<div class="viewtext">
							<label >&nbsp;&nbsp;
							</label>
						</div>
					</div>
					<div class="rowtest">
						<div class="viewtext">
							<label >&nbsp;&nbsp;
							</label>
						</div>
					</div>
					<div class="rowtest">
						<div class="viewtext">
							<label >&nbsp;
							</label>
						</div>
					</div>
					<div class="rowtable">
						<table>
							<thead>
								<tr>
									<th>Channel Id</th>
									<th>Channel Name</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan=2>
										<div style="width: 325px; height: 300px; overflow: auto;">
											<table id="assignedChannel" class="tablesorter">
												<tbody>
													<tr>
														<td class="left30">--</td>
														<td class="right70">--</td>
													</tr>
													<c:forEach items="${mapperchannel}" var="itemper">
														<tr>
															<td class="left30">${itemper.key}</td>
															<td class="right70">${itemper.value}</td>
														</tr>
													</c:forEach>
													
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>	
					
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.create.userassigned" />
							</label>
						</div>
					</div>	
					<div class="rowtable">
						<table>
							<thead>
								<tr>
									<th>Document Id</th>
									<th>Document Name</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan=2>
										<div style="width: 325px; height: 300px; overflow: auto;">
											<table id="assignedDox" class="tablesorter">
												<tbody>
													<tr>
														<td class="left30">--</td>
														<td class="right70">--</td>
													</tr>
													<c:forEach items="${mapperdocmaster}" var="itemper">
														<tr>
															<td class="left30">${itemper.key}</td>
															<td class="right70">${itemper.value}</td>
														</tr>
													</c:forEach>
													
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="rowtest">
					<input type="hidden" name="lsthidden"/>
					<input type="hidden" name="lstChannelHidden"/>
						<div class="viewtext">
							 <c:if test="${requestScope['userform'].id==0}">
					<input class="btnLogin" type="submit" value="Save" name="perSave" />
					</c:if>
					<c:if test="${requestScope['userform'].id !=0 }">
					<input class="btnLogin" type="submit" value="Edit" name="perSave" /></c:if>
					</div>
					<div class="inputtext">
					<input class="btnLogin" type="button" value="Cancel" name="perCancel"/>
					</div>
					</div>
				</div>
				<div class="rowtest">
						<div class="viewtext">
							<label >&nbsp;
							</label>
						</div>
					</div>
			</form:form>
		</div>
	</div>


<!-- end of cntool -->

