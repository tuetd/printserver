<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(
		function() {
			// Handler for .ready() called.
			$("td[class^='checkboxclass']").dblclick(function() {
				//alert('aaaaaa');
				return false;
			});

			$("#checkboxAll").live(
					'change',
					function() {
						$("[name='checkbox']").attr('checked',
								$(this).is(':checked') ? true : false);
			});	
			
			$("#dateComplete").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
				}
			});
});
</script>

<div id="container">
	<div id="header" class="info">
		<b>Foreclosure Waiting List</b>
	</div>

<form:form commandName="filterMRCForm" method="post" action="${pageContext.request.contextPath}/foreclosure/waiting.html">
		<div id="expander" class="expander">
	<span class="expander-header" onclick="$('#expander-content').toggle();">Filter</span>
	<div class="expander-content" id="expander-content" style="display:none;">

		<form:errors path="*" />
		<table>
			<tbody>

				<tr>					
					<td><label> Loan No</label>
					</td>
					<td><form:input path="loanNo" size="7" />
					</td>
					<td>Display <form:select path="pageSize">
						<form:option value="50"> 50 </form:option>
						<form:option value="100"> 100 </form:option>
						<form:option value="200"> 200 </form:option>
						<form:option value="0"> All </form:option>
						</form:select> 
					</td>				
					<td>
						<input type="submit" name="btnFilter" value="filter" class="filter-button"> 
						<input type="submit" name="btnFilter" value="clear" class="clear-button">
					</td>
				</tr> 

			</tbody>
		
		</table>

	</div>
</div>
</form:form>

<form name="displ" method="post">
	<sec:authorize access="hasAnyRole('ROLE_RM_RMT')">
		<input type="checkbox" id="checkboxAll"	value="Check All" />Check All &nbsp;&nbsp;
		<input type="button" value="Mark As Complete" onclick="openNotePopup('popup-complete-div','popupFormComplete')" class="btnSubmit"/>
	</sec:authorize>
		
	
	<br>
	<display:table size="resultSize" export="true" cellpadding="1" cellspacing="1" id="data" name="recordList" class="tftable" requestURI="/foreclosure/waiting.html" pagesize="${pageSize}">
		<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
			<input type="checkbox" id="checkbox${data.id}" name="checkbox" value="${data.id}" tabindex="1"/>
		</display:column>
		<display:column property="loanNo" title="Loan No"/>
		<display:column property="customerName" title="Customer Name" />
		<display:column property="createDate" format="{0,date,dd/MM/yyyy}" title="Upload Date" />	
		<display:column property="createBy" title="Upload By" />
		<display:column property="sendDate" format="{0,date,dd/MM/yyyy}" title="Send Date"/>
		<display:column property="sendBy" title="Send By"/>
		<display:column property="sendNote" title="Send Note"/>
		<display:column property="waitingDate" format="{0,date,dd/MM/yyyy}" title="Waiting Date"/>
		<display:column property="waitingBy" title="Waiting By"/>		
		<display:column property="waitingNote" title="Waiting Note"/>

	</display:table>
</form>
 
</div>

<div id="popup-complete-div" class="hidden dialog" title="Complete">
	<form method="post" id="popupFormComplete">
		<input type="hidden" name="btnSubmit" value="Mark As Complete"/>
		Date complete: <input id="dateComplete" type="text" name="dateComplete" size="12" value="" /><br/>
		Remark:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>