<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		window.location.href = "detail_rmt.html?id=" + id;
	}
	$(document).ready(function() {
		$('#my_modal').popup();
		
		$("#fromdate").datepicker(
				{
					changeMonth : true,
					dateFormat : 'dd/mm/yy',
					numberOfMonths : 1,
					onSelect : function(selectedDate) {
						$("#toDate").datepicker("option", "minDate",
								selectedDate);
					}
				});
		$("#todate").datepicker(
				{
					changeMonth : true,
					dateFormat : 'dd/mm/yy',
					numberOfMonths : 1,
					onSelect : function(selectedDate) {
						$("#toDate").datepicker("option", "minDate",
								selectedDate);
					}
				});
	});
	
	function reportExcel(){
		var fromdate=$("#fromdate").val();
		var todate=$("#todate").val();
		window.location.href = "<%=request.getContextPath()%>/report/prudentialreport.html?fromdate="
				+ fromdate + "&todate=" + todate;
	}
</script>

<div id="wrapper">
	<div id="twocenter-columns">
		<form:form class="box" commandName="searchReportForm"
			action="search.html" method="post">
			<form:errors path="*" />
			<div id="column1test">
				<div class="rowtest">
					<div class="viewtext">
						<label> From date</label>
					</div>
					<div class="inputtext">
						<form:input path="fromDate" id="fromdate" />
					</div>
					<div class="rowtest"></div>
				</div>
				<br />

			</div>
			<div id="column2test">
				<div class="rowtest">
					<div class="viewtext">
						<label> To date</label>
					</div>
					<div class="inputtext">
						<form:input path="toDate" id="todate" />
					</div>
					<div class="rowtest"></div>
				</div>
				<br />
				<div class="rowtest">
					<div class="checkbutton">
						<input class="btnLogin" type="submit" name="search" value="Search" />
					</div>
				</div>
				<div class="rowtest"></div>
			</div>


		</form:form>

	</div>

	<display:table name="lstdeviationReport" cellspacing="0"
		cellpadding="0" id="deviationReport" pagesize="25" class="tftable"
		defaultsort="8" defaultorder="descending" requestURIcontext="true"
		requestURI="search.html">
		<display:column property="deviation.subject" media="html excel pdf"
			sortable="true" title="Deviation Subject"></display:column>
		<display:column property="deviation.casnumber" media="html excel pdf"
			sortable="true" title="Cas number" />
		<display:column property="deviation.appnumber" media="html excel pdf"
			title="App number" />
		<display:column property="deviation.nationalnumber"
			media="html excel pdf" title="National number" />
		<display:column property="deviation.fullname" media="html excel pdf"
			title="FullName" />
		<display:column property="deviation.status" media="html excel pdf"
			sortable="true" title="Status" />
		<display:column property="deviation.createDate" media="html excel pdf"
			title="Create Date" />
		<display:column property="deviation.user.fullname" sortable="true"
			media="html excel pdf" title="Requester" />
		<display:column property="deviation.userreviewer.fullname"
			sortable="true" media="html excel pdf" title="Reviewer" />
		<display:column property="userapproval.fullname" sortable="true"
			media="html excel pdf" title="Approver" />
		<display:column property="deviation.userfinish.fullname"
			sortable="true" media="html excel pdf" title="Finisher" />
	</display:table>
	<div align="center">
		<a onclick="reportExcel();">Export Excel</a>
	</div>
</div>

<!-- end of cntool -->