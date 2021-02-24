<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
function synchronize() {
	$.ajax({
		type: "POST",
		url: ctx + '/utilities/index.html',
		})
		.error(function(jqXHR, textStatus, errorMessage){
			alert('error: '+ errorMessage);
			//alert('error textStatus: '+ textStatus);
		})
		.success(function (data) {
	            //$("#dialog-detail-follow-up-case").dialog("close");
	            //$("#dialog-follow-up-case-dtl").dataTable().fnDraw(); 
	            //alert( "Data Saved: " + data );
	            $("#message").html(data);
	     })
		.done(function( msg ) {
			//alert( "Data Saved: " + msg );
	});
}
</script>
<div id="container" style="height:300px">
	<div id="header" class="info">
		<b>Synchronize Disb Loan</b>
	</div>
	<br>
		<center id="message" class="errorMessage"></center>
		<button  name="btnSubmit" value="Synchronize" class="btnSubmit" onclick="synchronize()"> Synchronize </button>
		
</div>
