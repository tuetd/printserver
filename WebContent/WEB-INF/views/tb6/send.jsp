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
			
			$("#dateSend").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
				}
			});
			$("#dateReturn").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
				}
			});
			$("#dateWaiting").datepicker({
				changeMonth : true,
				dateFormat : 'dd/mm/yy',
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
				}
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
		<b>TB6 Send List</b>
	</div>

<form:form commandName="filterMRCForm" method="post" action="${pageContext.request.contextPath}/tb6/send.html">
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
					<td><label> Date Send(From CS)</label>
					</td>
					<td><form:input path="dateSend" size="8" />
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
		<input type="button" value="Return" onclick="openNotePopup('popup-return-div','popupFormReturn')" class="btnSubmit"/>
		<input type="button" value="Mark As Waiting" onclick="openNotePopup('popup-waiting-div','popupFormWaiting')" class="btnSubmit"/>
		<input type="button" value="Mark As Complete" onclick="openNotePopup('popup-complete-div','popupFormComplete')" class="btnSubmit"/>
	</sec:authorize>
		
	
	<br>
	<display:table size="resultSize" export="true" cellpadding="1" cellspacing="1" id="data" name="recordList" class="tftable" requestURI="/tb6/send.html" pagesize="${pageSize}">
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
		<display:column property="returnDate" format="{0,date,dd/MM/yyyy}" title="Return Date"/>
		<display:column property="returnBy" title="Return By"/>
		<display:column property="returnNote" title="Return Note"/>

	</display:table>
</form>
 
</div>
<div id="popup-return-div" class="hidden dialog" title="Return">
	<form method="post" id="popupFormReturn">
		<input type="hidden" name="btnSubmit" value="Return"/>
		Date Return: <input id="dateReturn" type="text" name="dateReturn" size="12" value="" /><br/>
		Note:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>

<div id="popup-waiting-div" class="hidden dialog" title="Waiting TB6">
	<form method="post" id="popupFormWaiting">
		<input type="hidden" name="btnSubmit" value="Mark As Waiting"/>
		Date Waiting: <input id="dateWaiting" type="text" name="dateWaiting" size="12" value="" /><br/>
		Remark:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>

<div id="popup-complete-div" class="hidden dialog" title="Complete TB6">
	<form method="post" id="popupFormComplete">
		<input type="hidden" name="btnSubmit" value="Mark As Complete"/>
		Date complete: <input id="dateComplete" type="text" name="dateComplete" size="12" value="" /><br/>
		Remark:<textarea id="remark" rows="5" name="remark" cols="30"></textarea>
	</form>
</div>