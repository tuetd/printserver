<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//window.location.href = "detail.html?id="+ id;
		return openDetailPopup('#dialog-detail-case','detail.html', id,'/utilities/logs.html','RECORD');
	}
</script>
<div id="container">
	<div id="header" class="info">
		<b>Loan Kit Send To Post Office</b>
	</div>

	<form:form commandName="filterRecordForm"
		method="post" action="${pageContext.request.contextPath}/po/index.html">
		<%@ include file="../share/filter.jsp"%>
	</form:form>
	
	<form:form commandName="uploadItem" method="post"
		enctype="multipart/form-data" action="${pageContext.request.contextPath}/po/index.html">
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
		
			<table style="width: 750px">
				<tr>
					<td style="width: 90px"><form:label for="fileData" path="fileData">Select file : </form:label>
					</td>
					<td><form:input path="fileData" type="file"/>
					</td>
					<td style="width:250px">
						<div id="btnGroup" >
							<input type="submit" name="btnUpload" value="Upload Post Return"
								onclick="javascript:hideBtn();" class="btnSubmit" />
								
							<input type="submit" name="btnUpload" value="Upload Bill No"
								onclick="javascript:hideBtn();" class="btnSubmit" />
						</div>
						
						<div id="inprogress" style="display: none;">
							<input type="button" value="In progress ..."
								onclick="javascript:alertInprogress();" class="btnSubmit" />
						</div>
					</td>
					<td>
					             <a target="_blank" style="color: blue; font-weight: bold;"
									href="<%=request.getContextPath()%>/templates/Template_Post_Return.xls">Post Return
									Template </a>
					 </td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	<br style="clear:both;">
	<form name="displ" method="post">
		<input type="checkbox" id="checkboxAll"	value="Check all" />Check All &nbsp;&nbsp;	
		<input type="button" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_CS_POST_RETURN.name%>" onclick="openNotePopup('popup-post-return-div','popupPostReturnForm')" class="btnSubmit"/>	
		<input type="button" name="btnSubmit" value="<%=Constant.ACTIONS.RMT_UPDATE_PO_BILL_NO.name%>" onclick="openNotePopup()" class="btnSubmit"/>
		<br>
		<display:table size="resultSize" export="true" sort="external"
			cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
			class="tftable" requestURI="/po/index.html" pagesize="${pageSize}">
			<%@ include file="../share/columns.jsp"%>
			<display:column property="sendDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" title="Send date" sortable="true" />
			<display:column property="sender" title="Sender" sortable="true" />
			<display:column property="poBillNo" title="Bill No" sortable="true" />
		</display:table>
	</form>
</div>
<%@ include file="detail.jsp"%>
<!-- Add content to modal -->
<%@ include file="../share/billno_popup.jsp"%>
<%@ include file="../share/post_return_popup.jsp"%>