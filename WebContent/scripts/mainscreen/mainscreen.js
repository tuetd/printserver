function goShowAddFollowUp(){
	
	$("#dialog-detail-follow-up-case").dialog({
		resizable : false,
		height : 300,
		width : 300,
		modal : true,
		buttons : {
			Save : function(){
				goSaveFollowup();
			},
			
			Close : function() {
				$(this).dialog("close");	
			}
		}
	});
}
function viewLoanDetail(id) {
	var aoData = new Array();
	document.getElementById('resultloading').style.visibility="visible";
	aoData.push({ "name": "id", "value": id });
	$.ajax({
    	"type" : "GET",
    	url: ctx + '/storedLoan/viewStoredDetail.html',
    	"data" : aoData,
    	cache : false,
    	"success" : function (data) {
    		document.getElementById('resultloading').style.visibility="hidden";
    		if(data === null) {
    			alertMsg("Do something error!");
    		} else {
    			$("#dialog-loan-detail-restoredLoanId").val(data.id);
    			$("#dialog-loan-detail-loanNo").val(data.loanNo);
    			$("#dialog-loan-detail-customerName").val(data.customerName);
    			$("#dialog-loan-detail-disbursalDate").val(data.disbursalDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.disbursalDate)));
    			$("#dialog-loan-detail-block").val(data.block);
    			$("#dialog-loan-detail-no").val(data.no);
    			$("#dialog-loan-detail-barCode").val(data.barCode);
    			$("#dialog-loan-detail-nameBox").val(data.nameBox);
    			$("#dialog-loan-detail-dateSent").val(data.dateSent);
    			$("#dialog-loan-detail-dateDestroy").val(data.destroyDate);
    			$("#dialog-loan-detail-remark").val(data.remark);
    		}
        }
	});
	$("#dialog-loan-detail").dialog({
		resizable : false,
		height : 400,
		width : 800,
		modal : true,
		buttons : {
			Update : function() {
				saveStoredLoan();
			},Close : function() {
				$(this).dialog("close");
			}
		}
	});
};
function saveStoredLoan() {
		var aoData = new Array();
		document.getElementById('resultloading').style.visibility="visible";
		aoData.push({ "name": "id", "value": $("#dialog-loan-detail-restoredLoanId").val()});
		aoData.push({ "name": "block", "value": $("#dialog-loan-detail-block").val() });
		aoData.push({ "name": "no", "value": $("#dialog-loan-detail-no").val() });
		aoData.push({ "name": "barCode", "value": $("#dialog-loan-detail-barCode").val() });
		aoData.push({ "name": "nameBox", "value": $("#dialog-loan-detail-nameBox").val() });
		aoData.push({ "name": "dateSent", "value": $("#dialog-loan-detail-dateSent").val() });
		aoData.push({ "name": "dateDestroy", "value": $("#dialog-loan-detail-dateDestroy").val() });
		aoData.push({ "name": "remark", "value": $("#dialog-loan-detail-remark").val() });
		$.ajax({
        	"type" : "POST",
        	"data" : aoData,
        	url: ctx + '/storedLoan/updateStoredDetail.html',
        	cache : false,
        	"success" : function (data) {
        		if(data.resultCode === "00000") {
					$("#dialog-loan-detail").dialog("close");
					$("#actSearch").click();
                } else {
                	alertMsg(data.resultMsg);
                }
        		document.getElementById('resultloading').style.visibility="hidden";
            }
    	});
}
function goSaveFollowup() {
	if ($("#description").val() == '') {
		alert("Please input Description before save.");
		return false;
	}
	
	var followUpForm = $("#followUpForm");
	$.ajax({
		type: "POST",
		url: ctx + followUpForm.attr('action') + '.html',
		data: followUpForm.serialize()
		})
		.error(function(jqXHR, textStatus, errorMessage){
			//alert('error: '+ errorMessage);
			//alert('error textStatus: '+ textStatus);
		})
		.success(function (data) {
                $("#dialog-detail-follow-up-case").dialog("close");
                $("#dialog-follow-up-case-dtl").dataTable().fnDraw(); 
         })
		.done(function( msg ) {
			//alert( "Data Saved: " + msg );
	});
}
var preStatus;
function followUpStatusOnClick(id){
	preStatus = $('#followUpStatus' + id).val();
	
}
function updateStatus(id){
	if (!confirm("Do you want to change status ?")) { 
		$('#followUpStatus' + id).val(preStatus);
		return;
	}
	var value  = $('#followUpStatus' + id + ' :selected').text();
	changeStatusFollowUp(id, value);
}

function changeStatusFollowUp(followUpId, statusValue, recordId){
	$.ajax({
		type: "POST",
		url: ctx + '/loan/updateFollowUp.html',
		data: {followUpId: followUpId, status: statusValue, recordId: recordId}
		})
		.error(function(jqXHR, textStatus, errorMessage){
			alert('error: '+ errorMessage);
		})
		.success(function (data) {
			 $("#dialog-detail-follow-up-case").dialog("close");
             $("#dialog-follow-up-case-dtl").dataTable().fnDraw(); 
            if(followUpId == 0) {
            	$("#dialog-confirm" ).dialog({
					 resizable: false,
					 height:140,
					 modal: true,
					 buttons: {
					 "YES": function() {
						 $.ajax({
								type: "POST",
								url: ctx + '/loan/wait.html',
								data: {checkbox: recordId, btnSubmit: 'Prepare to scan'}
								})
								.error(function(jqXHR, textStatus, errorMessage){
									alert('error: '+ errorMessage);
									//alert('error textStatus: '+ textStatus);
								})
								.success(function (data) {
										//alert( "success Data Saved: " + data );
						                $("#dialog-confirm").dialog("close");
						                $("#dialog-follow-up-case").dialog("close");
						                location.href = location.href;
						         })
								.done(function( msg ) {
									//alert( "done Data Saved: " + msg );
							});
					 },
					 "NO": function() {
					 $( this ).dialog( "close" );
					 }
				}});
            }
               
                
                
         })
		.done(function( msg ) {
			//alert( "Data Saved: " + msg );
	});
}

function goShowFollowUpList(recordId, isfull) {
	$('#recordIdPopupFU').val(recordId);
	$("#dialog-follow-up-case-dtl").dataTable( {
        "bProcessing": true,
        "bServerSide" : true,
        "bLenthChange" : false,
        "bStateSave": false,
        "iDisplayLength" : -1,
        "sAjaxSource": ctx + "/loan/followUp.html",
        "sScrollY": "200px",
        "sScrollX": "100%",
        "sScrollXInner": "100%",
        "bScrollCollapse": true,
		"bPaginate": false,
		"bFilter": false,
		"bInfo": false,
		"bDestroy": true,
        "aaSorting": [],
		"aoColumns" : [
			{
			"sTitle" : "ID", "mData" : "id", "bSortable": false, "sClass" : "tdLeft", "sWidth": "20px"
			},
			{
				"sTitle" : "Category", "mData" : "category", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "Description", "mData" : "description", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "Create Date", "mData" : "createDate", "bSortable": false, "sClass" : "tdLeft", "sWidth": "50px",
				"fnRender": function ( o ) {
	                return o.aData.createDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(o.aData.createDate));
	            }
			},
			{
				"sTitle" : "Creater", "mData" : "creater", "bSortable": false, "sClass" : "tdLeft", "sWidth": "50px"
			},
			 {
				"sTitle" : "Update Date", "mData" : "updateDate", "bSortable": false, "sClass" : "tdLeft", "sWidth": "50px",
				"fnRender": function ( o ) {
	                return o.aData.updateDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(o.aData.updateDate));
	            }
			},
			{
				"sTitle" : "Updater", "mData" : "updater", "bSortable": false, "sClass" : "tdLeft", "sWidth": "50px"
			}, 

			{
				"sTitle" : "Status", "mData" : "status", "bSortable": false, "sClass" : "tdLeft", "sWidth": "80px"
			},
			{
				"sTitle" : "Actions", "mData" : null, "bSortable": false, "sClass" : "tdLeft", "sWidth": "170px",
				"fnRender": function ( o ) {
					if(isfull == 'true') {
						return '<button class="btnSubmit" onclick="changeStatusFollowUp('+ o.aData.id + ', \'Reopen\',' + recordId + ')"> Reopen </button>&nbsp;'
							+  '<button class="btnSubmit" onclick="changeStatusFollowUp('+ o.aData.id + ', \'Confirm\','+ recordId+ ')"> Confirm </button>';
					}else {						
						return '<button class="btnSubmit" onclick="changeStatusFollowUp('+ o.aData.id + ', \'In Progress\',' + recordId + ')"> In Progress </button>&nbsp;'
							+  '<button class="btnSubmit" onclick="changeStatusFollowUp('+ o.aData.id + ', \'Complete\',' + recordId + ')"> Complete </button>';
					}
					
				}
			}
			
			],	 
			
        "fnServerData" : function(sSource, aoData, fnCallback) {
        	aoData.push( { "name": "recordId", "value": recordId } );
        	$.ajax({
	        	"dataType" : 'json',
	        	"type" : "GET",
	        	"url" : sSource,
	        	"data" : aoData,
	        	"cache" : false,
	        	"success" : function (data) {
                    //alert('success' + data);
                    fnCallback(data);
                    
                }
        	});
        }
    } );
	var buttons = "";
	if(isfull == 'true') {
		buttons = {
				"Confirm All" : function() {
					changeStatusFollowUp('0', 'Confirm', recordId);			
				},
				"Close" : function() {
					$(this).dialog("close");
					$("#dialog-addresses-case-dtl").html('');
				}
			};
	} else {
		buttons = {
				Close : function() {
					$(this).dialog("close");
					$("#dialog-addresses-case-dtl").html('');
				}
			};
		
	}
	
	$("#dialog-follow-up-case").dialog({
		resizable : false,
		height : 400,
		width : 920,
		modal : true,
		buttons : buttons
	});
	
	
}

function openDetailPopup(divPopup, pageDetail,  recordId, url, logType){
	$('#recordIdPopup').val(recordId);
	$("#dialog-detail-case-dtl").dataTable( {
        "bProcessing": true,
        "bServerSide" : true,
        "bLenthChange" : false,
        "bStateSave": false,
        "iDisplayLength" : -1,
        "sAjaxSource": ctx + url,
        //"sScrollY": "200px",
        //"sScrollX": "100%",
        //"sScrollXInner": "100%",
        "bScrollCollapse": true,
		"bPaginate": false,
		"bFilter": false,
		"bInfo": false,
		"bDestroy": true,
        "aaSorting": [],
        "bAutoWidth": false,
		"aoColumns" : [
			{
				"sTitle" : "#", "mData" : "id", "bSortable": false, "sClass" : "tdLeft", "sWidth": "20px"
			},
			{
				"sTitle" : "Created At", "mData" : "logDate", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px",
				"fnRender": function ( o ) {
						if(o.aData.logDate != null){
						    var date = new Date(o.aData.logDate);
						    var day = date.getDate();                
						    var month = date.getMonth() + 1;            
						    var year = date.getFullYear();
						    var hour = date.getHours();
						    var min = date.getMinutes();
							return day + '/' + month + '/' + year + ' ' + hour + ':' + min;
						}
						return "";
		                //return o.aData.logDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(o.aData.logDate));
		            }
			},
			{
				"sTitle" : "Created By", "mData" : "createBy", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "From Screen", "mData" : "screenName", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},			
			{
				"sTitle" : "Action", "mData" : "action", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "To Screen", "mData" : "toScreenName", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "Description", "mData" : "description", "bSortable": false, "sClass" : "tdLeft", "sWidth": "200px"
			}			
			],	 
			
        "fnServerData" : function(sSource, aoData, fnCallback) {
        	aoData.push( { "name": "recordId", "value": recordId },
        				 { "name": "logType", "value": logType });
        	$.ajax({
	        	"dataType" : 'json',
	        	"type" : "GET",
	        	"url" : sSource,
	        	"data" : aoData,
	        	"cache" : false,
	        	"success" : function (data) {
                    //alert('success' + data);
                    fnCallback(data);
                    
                }
        	});
        }
    } );
	
	
	$.get(pageDetail, {id : recordId},    function(data) {
		$("#r_customername").html(data.record.customername);
		$("#r_dob").html(data.record.dob == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.dob)));
		$("#r_address1")	.html(data.record.address1);
		$("#r_sex")			.html(data.record.sex == 'M' ? 'Male' : 'Female');
		$("#r_address2")	.html(data.record.address2);
		$("#r_panNo")		.html(data.record.panNo);
		$("#r_address3")	.html(data.record.address3);
		$("#r_mobile")		.html(data.record.mobile);
		$("#r_address4")	.html(data.record.address4);
		$("#r_phone1")		.html(data.record.phone1);
		$("#r_city")		.html(data.record.city);
		$("#r_phone2")		.html(data.record.phone2);
		$("#r_statedesc")	.html(data.record.statedesc);
		$("#r_agreementno")	.html(data.record.agreementno);
		$("#r_ct")			.html(data.record.ct == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.ct)));
		$("#r_disbursaldate").html(data.record.disbursaldate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.disbursaldate)));
		$("#r_mt")			.html(data.record.mt == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.mt)));
		$("#r_amtRequested").html(parseFloat(data.record.amtRequested, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString());
		$("#r_appFormno")	.html(data.record.appFormno);
		$("#r_emi")			.html(data.record.emi);
		$("#r_creditShield").html(parseFloat(data.record.creditShield, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString());
		$("#r_dueday")		.html(data.record.dueday);
		$("#r_productflag")	.html(data.record.productflag);
		$("#r_tenure")		.html(data.record.tenure);
		$("#r_lastRoName")	.html(data.record.lastRoName);
		$("#r_maturitydate").html(data.record.maturitydate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.maturitydate)));
		$("#r_supplierName").html(data.record.supplierName);
		$("#r_branchdesc")	.html(data.record.branchdesc);
		$("#r_area")	    .html(data.record.area);
		$("#r_sourceState")	.html(data.record.sourceState);
		$("#r_boxLabel")	.html(data.record.boxLabel);
		$("#r_boxDate").html(data.record.boxDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(data.record.boxDate)));
	}, 'json');
	var buttons = {
			Close : function() {
				$(this).dialog("close");
			}};
	$(divPopup).dialog({
		resizable : false,
		height : 600,
		width : 900,
		modal : true,
		buttons : buttons
		
	});
}

function openDetailSearch(divPopup, pageDetail,  recordId, url, logType){
	openDetailPopup(divPopup, pageDetail,  recordId, url, 'RECORD');
	$("#dialog-detail-case-dtl2").dataTable( {
        "bProcessing": true,
        "bServerSide" : true,
        "bLenthChange" : false,
        "bStateSave": false,
        "iDisplayLength" : -1,
        "sAjaxSource": ctx + url,
        //"sScrollY": "200px",
        //"sScrollX": "100%",
        //"sScrollXInner": "100%",
        "bScrollCollapse": true,
		"bPaginate": false,
		"bFilter": false,
		"bInfo": false,
		"bDestroy": true,
        "aaSorting": [],
        "bAutoWidth": false,
		"aoColumns" : [
			{
				"sTitle" : "#", "mData" : "id", "bSortable": false, "sClass" : "tdLeft", "sWidth": "20px"
			},
			{
				"sTitle" : "Created At", "mData" : "logDate", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px",
				"fnRender": function ( o ) {
						if(o.aData.logDate != null){
						    var date = new Date(o.aData.logDate);
						    var day = date.getDate();                
						    var month = date.getMonth() + 1;            
						    var year = date.getFullYear();
						    var hour = date.getHours();
						    var min = date.getMinutes();
							return day + '/' + month + '/' + year + ' ' + hour + ':' + min;
						}
						return "";
		                //return o.aData.logDate == null ? '' : $.datepicker.formatDate( "dd/mm/yy", new Date(o.aData.logDate));
		            }
			},
			{
				"sTitle" : "Created By", "mData" : "createBy", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "From Screen", "mData" : "screenName", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},			
			{
				"sTitle" : "Action", "mData" : "action", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "To Screen", "mData" : "toScreenName", "bSortable": false, "sClass" : "tdLeft", "sWidth": "100px"
			},
			{
				"sTitle" : "Description", "mData" : "description", "bSortable": false, "sClass" : "tdLeft", "sWidth": "200px"
			}			
			],	 
			
        "fnServerData" : function(sSource, aoData, fnCallback) {
        	aoData.push( { "name": "recordId", "value": recordId },
        				 { "name": "logType", "value": 'LOAN' });
        	$.ajax({
	        	"dataType" : 'json',
	        	"type" : "GET",
	        	"url" : sSource,
	        	"data" : aoData,
	        	"cache" : false,
	        	"success" : function (data) {
                    //alert('success' + data);
                    fnCallback(data);
                    
                }
        	});
        }
    } );
}

function sendSelected() {
	var size = $('input[name="checkbox"]:checked').length;
	if (size == 0) {
		alert("Please select record!");
		return false;
	}
	return true;
}

function openNotePopup(popupId, popupForm){
	if (sendSelected()) {
		var list = $('input[name="checkbox"]:checked');
		
		var recordIds = "";
		jQuery.each(list, function(index, item) {
			recordIds+=item.value + ";";
		});
		openPopup(recordIds, popupId, popupForm);
	}
}

function openPopup(recordIds, popupId, popupForm){
	//alert('popupId=' + popupId);
	//alert('popupForm=' + popupForm);
	//alert('recordIds=' + recordIds);
	//default popup Id  is popup-note-div
	if(popupId === undefined || typeof popupId == 'undefined'){
		popupId = "popup-note-div";
	}
	
	if(popupForm === undefined || typeof popupForm == 'undefined'){
		popupForm = "popupForm";
	}
	var form = $('#' + popupForm);
	
	$('#'+popupId).dialog({
		resizable : false,
		//height : 220,
		//width : 180,
		modal : true,
		buttons : {
			Save : function() {
				//alert('Save');
				$("#" + popupForm + " input[name='checkbox']").remove();
				$.each( recordIds.split(';'), function( index, value ){
					//alert(index + ' = ' + value);
					
					if(value != '') {
					 	var input = $("<input>").attr("type", "hidden").attr("name", "checkbox").val(value);
					 	form.append($(input));
					}
				});			
				form.submit();
			},
			Close : function() {
				$(this).dialog("close");
			}
		}
	});
}