<%@ include file="/common/taglibs.jsp"%>
<head>
<title><fmt:message key="user.search.title" /></title>
<script type="text/javascript">
	jQuery().ready(
			function() {
				jQuery("#fromdate").datepicker(
						{
							defaultDate : "+1w",
							changeMonth : true,
							numberOfMonths : 3,
							onSelect : function(selectedDate) {
								jQuery("#todate").datepicker("option",
										"minDate", selectedDate);
							}
						});
				jQuery("#todate").datepicker(
						{
							defaultDate : "+1w",
							changeMonth : true,
							numberOfMonths : 3,
							onSelect : function(selectedDate) {
								jQuery("#fromdate").datepicker("option",
										"maxDate", selectedDate);
							}
						});

			});
</script>
</head>
<body>
	<div id="wrapper">

		<div id="twocenter-columns">
			<form:form class="box" commandName="ReportFclSearch"
				action="exportReportFcl.html" method="post">
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.search.fromdate" /></label>
						</div>
						<div class="inputtext">
							<form:input id="fromdate" cssClass="inp2" path="fromdate" />
						</div>
					</div>
					<br />
					<br />
					<div class="rowtest">
						<div class="viewtext">
							<label><fmt:message key="user.search.todate" /></label>
						</div>
						<div class="inputtext">
							<form:input id="todate" cssClass="inp2" path="todate" />
						</div>
					</div>
					<div class="rowtest">
						<div class="viewtext">
							<label></label>
						</div>
						<div class="inputtext">
							<input class="btnLogin" type="submit" value="Search"
								name="search" />
						</div>

					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>


