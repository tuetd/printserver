<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<head>
<title>Document Selection</title>
</head>
<script type="text/javascript">
var message="Function is disabled!"; // Message for the alert box
function md(e) 
{ 
  try { 
	  if (event.button==2||event.button==3 || event.button == 4) {
		  alert(message);
		  return false; 
	  }
  }  
  catch (e) { if (e.which == 3) return false; } 
}
document.oncontextmenu = function() { return false; }
document.ondragstart   = function() { return false; }
document.onmousedown   = md;

function setFocus()
{
	document.getElementById("applid").focus();
}

$(document).ready(function() {
	setFocus();
});

</script>
<div id="wrapper">
<div id="twocenter-columns">
		<form:form class="box" commandName="prtReprintForm" method="post" action="list.html">
		<form:errors path="*" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<form:label for="applid" path="applid">Agreement Id</form:label>
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
						<div id="btnGroup">
						<input class="btnLogin"  type="submit" value="Search" onclick="javascript:hideBtn();" />
						</div>
						<div id="inprogress" style="display:none;">
        				<input  class="btnLogin" type="button" value="In progress ..." onclick="javascript:alertInprogress();" />
        				</div>
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
			</form:form>
		</div>
	
<c:if test="${ not empty prtReprintForm.prtReprintLogs}">		
<display:table class="tftable" size="resultSize" defaultsort="1" cellpadding="1" cellspacing="1"  id="data" name="${ prtReprintForm.prtReprintLogs }"  requestURI="/prtreprintlog/list.html" pagesize="25" >
		<display:column style="width:5%;" class="contactDept" property="applid" title="Agreement Id" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="notes" title="Notes" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="fromDate" title="Allow from" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="stopInMinutes" title="Will close in (minute)" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="createdBy" title="Created By" sortable="true"   />
		<display:column style="width:5%;" class="contactDept" property="createdDate" title="Created Date" sortable="true"   />
	</display:table>
	</c:if>
	<c:if test="${empty prtReprintForm.prtReprintLogs}">
	<left>No records found !</left>
</c:if>
</div>

