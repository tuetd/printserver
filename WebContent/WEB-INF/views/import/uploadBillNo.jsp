<%@ include file="../layouts/taglibs.jsp"%>
<br />
<!-- <div id="main-feature" class="billboard"> -->
<c:forEach items="${errorList}" varStatus="item" var="error">
		<center class="errorMessage" >${error}</center>
	</c:forEach>
<form:form commandName="uploadItem" method="post" enctype="multipart/form-data">
		<div id="header" class="info">
			<b>Upload W/O List</b>
		</div>
        	
		<div id="input">
			<font color="red"><form:errors path="*" /> </font>
			<font color="blue">
				<c:if test="${successMsg != null}">
					<fmt:message key="${successMsg}" />
				</c:if>
			</font>
			
			<table class="width30">
				<tr>
					<td class="width40"><form:label for="fileData" path="fileData">Select file : </form:label></td>
					<td class="width60">
						<form:input path="fileData" type="file" />
					</td>
				</tr>
				<tr colspan="2" class="center">
					<td>
						<div id="btnGroup">
							<input type="submit" value="Upload" onclick="javascript:hideBtn();" />
						</div>
						<div id="inprogress" style="display: none;">
							<input type="button" value="In progress ..."
								onclick="javascript:alertInprogress();" />
						</div>
					</td>
				</tr>
			</table>
		</div>

</form:form>
<!-- </div> -->