<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<script type="text/javascript">
function setFocus()
{
	document.getElementById("applid").focus();
}

$(document).ready(function() {
	setFocus();
});


function goEditReprint(id) {
	window.location.href = "<%=request.getContextPath() %>/mod.html?id=" + id;
}
function goDeleteReprint(id) {
	window.location.href = "<%=request.getContextPath() %>/del.html?id=" + id;
}
</script>
<div id="wrapper">
<div id="twocenter-columns">
		<form:form class="box" commandName="prtReprintForm" method="post" action="list.html">
		<form:errors path="*" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label>Agreement Id</label>
						</div>
						<div class="inputtext">
						<form:input path="applid" type="applid" id="id_text" />
						</div>
						<div class="rowtest"></div>
					</div>
					</div>
				    <div id="column2test">
					<div class="rowtest">
						<div class="checkbutton">
						<input  class="btnLogin" type="submit" value="Search"  />
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
			</form:form>
		</div>
<div id="twocenter-columns">
<p class="right">
			<a href='<c:url value="/listlog.html" />'><b>Reprint logs</b></a> &nbsp;|&nbsp; 
			<a href='<c:url value="/mod.html" />'><b>Create new reprint setup</b></a>
			
		</p>
</div>		
<c:if test="${ not empty prtReprintForm.prtReprints}">		
<display:table class="tftable" size="resultSize" defaultsort="1" cellpadding="1" cellspacing="1"  id="data" name="${ prtReprintForm.prtReprints }"  requestURI="/prtreprint/list.html" pagesize="25" >
		<display:column style="width:5%;" class="contactDept" property="applid" title="Agreement Id" sortable="true"   />
<%-- 		<display:column style="width:5%;" class="contactDept" property="notes" title="Notes" sortable="true"   /> --%>
		<display:column style="width:5%;" class="contactDept" title="Notes" sortable="true"   >
			<str:truncateNicely lower="3" upper="25" >${ data.notes }</str:truncateNicely>
		</display:column>
		<display:column style="width:5%;" class="contactDept" property="fromDate" title="Allow from" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="stopInMinutes" title="Will close in (minute)" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="createdBy" title="Created By" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="createdDate" title="Created Date" sortable="true"   />
		
		<display:column style="width:3%;" class="contactDept" title="Modify" >				
			<img onclick="javascript:goEditReprint('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/edit.png" />
		</display:column>
		<display:column style="width:3%;" class="contactDept" title="Delete" >				
			<img onclick="javascript:goDeleteReprint('${data.id}');return false;" alt="click to modify record" src="<%=request.getContextPath() %>/images/delete.png" />
		</display:column>
	</display:table>
	</c:if>
	<c:if test="${empty prtReprintForm.prtReprints}">
	<left>No records found !</left>
</c:if>
</div>

