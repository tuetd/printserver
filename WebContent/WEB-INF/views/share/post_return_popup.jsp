<div id="popup-post-return-div" class="hidden dialog" title="Post return">
	<form method="post" id="popupPostReturnForm">
		<input type="hidden" name="btnSubmit" value="Post return"/>
		Date return: <input id="dateReturn_input" type="text" name="dateReturn" size="12" value="" />
		<br>
		Reason: <textarea name="reason" rows="3"></textarea>
	</form>
</div>
<script type="text/javascript">
$(document).ready(
	function() {
		$("#dateReturn_input").datepicker({
			changeMonth : true,
			dateFormat : 'dd/mm/yy',
			numberOfMonths : 1,
			onSelect : function(selectedDate) {
				//$("#dateReturn").datepicker("option", "minDate",
				//		selectedDate);
			}
		});
	});
</script>