<%@ include file="../layouts/taglibs.jsp"%>

<div id="container">
	<div id="header" class="info">
		<b>Upload Stored Loan</b>
	</div>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RM_RMT')">
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/storedLoan/index.html">
		<fieldset>
			<legend>Upload File</legend>

			<font color="red"><form:errors path="*" /> </font>
			
			<c:if test="${fn:length(errorList) gt 0}">
				<br/>
				<span  onclick="$('#expander-error').toggle();" style="cursor: pointer;">
				<font size="6" color="red">Total ${fn:length(errorList)} rows import fail. Click here to see more details. </font></span>
				<div id="expander-error" style="display:none;">
					<c:forEach items="${errorList}" varStatus="item" var="error">
						<span class="errorMessage"> ${error}</span>
						<br>
					</c:forEach>
				</div>
			</c:if>
			
			<c:if test="${fn:length(successList) gt 0}">
				<br/>
				<span  onclick="$('#expander-success').toggle();" style="cursor: pointer;">
				<font size="5" color="blue">Total ${fn:length(successList)} rows is imported. Click here to see more details. </font></span>
				<div id="expander-success" style="display:none;">
					<table>
						<thead>
							<tr> 
								<th>Loan No</th>
								<th>Customer Name</th>								
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${successList}" varStatus="item" var="row">
							<tr>
								<td>${row.loanNo}</td>
								<td>${row.customerName} </td>								
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<br/>
		<c:if test="${fn:length(existList) gt 0}">
				<br/>
				<span  onclick="$('#expander2-error').toggle();" style="cursor: pointer;">
				<font size="5" color="red">Total ${fn:length(existList)} rows is exist. Click here to see more details. </font></span>
				<div id="expander2-error" style="display:none;">
					<table>
						<thead>
							<tr> 
								<th>Loan No</th>
								<th>Customer Name</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${existList}" varStatus="item" var="exist">
							<tr>
								<td>${exist.loanNo}</td>
								<td>${exist.customerName} </td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<br/>
		
			<table style="width: 650px">
				<tr>
					<td style="width: 90px"><form:label for="fileData" path="fileData">Select file : </form:label>
					</td>
					<td><form:input path="fileData" type="file"/>
					</td>
					<td style="width:100px">
						<div id="btnGroup" >
							<input type="submit" name="btnUpload" value="Upload storedLoan"
								onclick="javascript:hideBtn();" class="btnSubmit" />
							<div id="inprogress" style="display: none;">
								<input type="button" value="In progress ..."
									onclick="javascript:alertInprogress();" class="btnSubmit" />
							</div>
						</div>
					</td>
					<td>
					             <a target="_blank" style="margin-left:50px;color: blue; font-weight: bold;"
									href="<%=request.getContextPath()%>/templates/storedTemplate.xls">Download
									Template </a>
					</td>		
				</tr>
			</table>
		</fieldset>
	</form:form>
	</sec:authorize>	
	<br style="clear:both;">
 
</div>
