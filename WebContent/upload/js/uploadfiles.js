function ajaxFileUpload(contextPath, callBackfunc, type, agreementno, id)
{
	//starting setting some animation when the ajax starts and completes
	$.ajaxFileUpload
	(
		{
			
			url: contextPath +'fileUpload?agreementno=' + agreementno + '&id=' + id,
			secureuri:false,
			fileElementId: "file_Upload",
			dataType: 'json',
			success: function (data, status)
			{
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '') {
						alert(data.error);
					} else {
						//update component here.
						callBackfunc(data.url, data.fileName);
						$("#upload_dialog").dialog('close');
					}
				}
			},
			error: function (data, status, e)
			{
				try {
					var text = $(data.responseText).text();
					var text = eval("data=" + text);
				} catch(ex){
					alert(e);
				}
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '') {
						alert(data.error);
					} else {
						//update component here.
						callBackfunc(data.url, data.fileName);
						$("#upload_dialog").dialog('close');
					}
				}
			}
		}
	);

	return false;

}

function showUploadFile(contextPath, callBackfunc){
	$('body').append(
			$("<div id='upload_dialog' title='File Upload'></div>")
			.load(contextPath + "upload/uploadFiles.jsp #upload_dialog",
					function(response, status, xhr){
						$.getScript(contextPath + "upload/js/ajaxfileupload.js");
						$("#upload_dialog").append(response);
						if(typeof(type) != 'undefined' && type != null ){
							$("#file_Upload").attr("name", "file_Upload_" + type);
						}
						$('#uploadButton').click(function() {
							ajaxFileUpload(contextPath , callBackfunc);
						});
				  })
		);
	var dialog = $("#upload_dialog").dialog({
		 	bgiframe: true,
		 	resizable: false,
		 	modal: true,
		 	close: function(){
				$("#upload_dialog").remove();
			}
		});
		dialog.dialog( "option", "width", 350 );
		dialog.dialog( "option", "height", 150 );
	$("#upload_dialog").dialog('open');

}


