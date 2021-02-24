<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<script type="text/javascript">
	function GoPrint() {
		document.forms[0].submit();
	}
	jQuery()
	.ready(
			function() {
				jQuery('#backbt')
						.click(
								function() {
										window.location = '<c:url value="listDocument.html"/>';
								});
			
			});
	
	
	
</script>
<div id="wrapper">
	<div id="twocenter-columns">
		<form:form class="box" id="formID" commandName="paradocumentForm"
			action="printDocument.html" method="post">
			<div id="column1test">
			<table>
						
						<tr>
								<td colspan=2>Document name: <c:out value="${paradocumentForm.documentname}" /> </td>
									
						</tr>
					
						<c:forEach items="${paradocumentForm.list}" var="parad"
							varStatus="pStatus">
							<c:if test="${parad.fieldType!='DB'}">
							<tr>
								<td width="150px">
									<div class="viewtext">
										<label> <c:out value="${parad.paramFriendlyName}" />
										</label>
									</div></td>
								<td>
									<c:if test="${parad.fieldType eq null or parad.fieldType eq '' or parad.fieldType eq 'textfield'}">
									<div class="inputtext">
										<form:input path="list[${pStatus.index}].paramType" />
									</div>
									</c:if>
									
									<c:if test="${parad.fieldType eq 'combobox'}">
										<form:select path="list[${pStatus.index}].paramType" cssClass="cjComboBox">
										    <form:options items="${parad.fields}" />
										</form:select>									
									</c:if>
									
									<c:if test="${parad.fieldType eq 'radiobutton'}">
									<form:checkbox  path="list[${pStatus.index}].paramType" value="Signature" checked="checked"  theme="simple"/>
									</c:if>
									
								</td>
							</tr>
							</c:if>
							
							<c:if test="${parad.fieldType=='DB'}">
								<form:hidden path="list[${pStatus.index}].paramType" />
							</c:if>
						
						
						
							
						</c:forEach>
					
					</table>
			</div>
			    <div id="column2test">
					<div class="rowtest">
						<div class="checkbutton" >
							<input type="hidden" value="${paradocumentForm.paramnamelist}" name="paramnamelist" /> 
							<input type="hidden" value="${paradocumentForm.paramtypelist}" name="paramtypelist" />
							<input type="hidden" value="${paradocumentForm.documentname}" name="documentname" /> 
							<input id="print" class="btnLogin"  type="button" value="Print" name="search" onclick="GoPrint();Invisible()"/>	
						</div>
						<div class="messagecheck" style="margin-top: 8px">
							<input class="btnLogin" id="backbt" type="button" value="Back" />
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
		</form:form>
	</div>
</div>






