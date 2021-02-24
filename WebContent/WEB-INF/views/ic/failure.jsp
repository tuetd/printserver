<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		return openDetailPopup('#dialog-detail-case', 'detail_failure.html', id,'/csrecord/logs.html');
		//window.location.href = "detail_rmt.html?id=" + id;
	}
</script>
<div id="container">

	<div id="header" class="info">
		<b>Insurance Certificate failure</b>
	</div>

	<c:if test="${not empty errorMessage}">
			<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	
  	<form:form commandName="filterRecordForm" method="post" action="${pageContext.request.contextPath}/ic/failure.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
  
  	
	<sec:authorize access="hasAnyRole('ROLE_CS_RMT')">
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/ic/failure.html">
		<fieldset>
			<legend>Upload File</legend>

			<font color="red"><form:errors path="*" /> </font>
			<c:if test="${successMsg != null}">
				<font size="6" color="blue"> ${successMsg}</font>
			</c:if>
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
		
			<table style="width: 850px">
				<tr>
				<td> Upload Type</td>
					<td> 
						<form:select path="uploadType">
							<form:option value="UploadFailedToCompleted" label="Upload Fail To Complete" />
						</form:select>
					</td>
					</tr>
					
					<tr>
					<td style="width: 90px"><form:label for="fileData" path="fileData">Select file : </form:label>
					</td>
					<td><form:input path="fileData" type="file"/>
					</td>
				
					<td style="width:450px">
						<div id="btnGroup" >
							<input type="submit" name="btnUpload" value="Upload"
								onclick="javascript:hideBtn();" class="btnSubmit" />
						</div>
						
						<div id="inprogress" style="display: none;">
							<input type="button" value="In progress ..."
								onclick="javascript:alertInprogress();" class="btnSubmit" />
						</div></td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	</sec:authorize>
	<display:table size="resultSize" export="true"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.CSRecordDecorator"
			class="tftable" requestURI="/ic/failure.html" pagesize="${pageSize}">
		<%@ include file="../share/columns.jsp"%>		
	</display:table>
	
</div>
<%@ include file="detail_failure.jsp"%>