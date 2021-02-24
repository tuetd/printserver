<%@ include file="/common/taglibs.jsp"%>
<head>
<title>View Report</title>
<meta name="menu" content="Statusmaster" />
</head>
<SCRIPT LANGUAGE="JavaScript">
	function settitle() {
		var a = "Prudential CMS Administration - Message Success";
		var t = new Date();
		s = t.getSeconds();
		if (s != 00) {
			document.title = a;
		}
		setTimeout("settitle()", 1000);
	}
</script>
<!-- End Load Title -->
<script type="text/javascript">
	var section = '?section=' + '<c:out value="${section}"></c:out>';
	var type = '?type=' + '<c:out value="${type}"></c:out>';
	jQuery()
			.ready(
					function() {
						jQuery('#backbt')
								.click(
										function() {
											if (section != '?section=') {
												window.location = '<c:url value="${backUrl}'+section+'"/>';
											} else if (type != '?type=') {
												window.location = '<c:url value="${backUrl}'+type+'"/>';
											} else {
												window.location = '<c:url value="${backUrl}"/>';
											}
										});
						jQuery('#downloadfile')
								.click(
										function() {
											var type = jQuery('#state').val();
											window.location = '<c:url value="xuatFileDownload.html?type='
													+ type + '"/>';

										});

					});
</script>

	<div id="wrapper">

		<div id="twocenter-columns">
			<div id="column1test">
				<div class="rowtest">
					<div class="typefile">
						<label>Type file </label>
					</div>
					<div class="inputtext">
						<select class="cjComboBox" id="state" name="state">
							<option value="pdf">PDF</option>
							<option value="doc">Word</option>
							<option value="xls">Excel</option>
						</select>
					</div>
				</div>
			</div>
			<div id="column2test">
				<div class="rowtest">
				<div class="checkbutton" style="margin-top: 0px">
				<input class="btnLogin" id="downloadfile" type="button"
						value="Download File" />
				</div>
				<div class="messagecheck">
				<input class="btnLogin" id="backbt"
						type="button" value="Back" />
				</div>
				</div>
			</div>
		</div>
		
		<iframe
		src="<%=request.getContextPath() %>/reports/pdf/${filename}.pdf"
		width="100%" height="600px" />
	</div>


	


