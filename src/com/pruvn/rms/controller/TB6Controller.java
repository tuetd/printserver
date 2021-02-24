package com.pruvn.rms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.rms.domain.TB6;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.TB6Service;
import com.pruvn.rms.service.response.UploadTB6Response;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.Constant.TB6_STAGE;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class TB6Controller extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(TB6Controller.class);

	private TB6Service tb6Service;
	
	public TB6Service getTb6Service() {
		return tb6Service;
	}
	@Autowired
	public void setTb6Service(TB6Service tb6Service) {
		this.tb6Service = tb6Service;
	}

	private FileExcelUploadValidator fileUploadValidator;
	

	public FileExcelUploadValidator getFileUploadValidator() {
		return fileUploadValidator;
	}
	@Autowired
	public void setFileUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}

	/****** TB6 ******/
	@RequestMapping(value = "/tb6/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_UPLOAD.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6List";
	}
	protected FilterMRCForm createFitlerForm(HttpServletRequest request) {
		FilterMRCForm filterMRCForm = new FilterMRCForm();
		if(!CommonUtils.isNullOrEmpty(request.getParameter("pageSize"))){
			filterMRCForm.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		} else {
			filterMRCForm.setPageSize(50);
		}
		
		if(!CommonUtils.isNullOrEmpty(request.getParameter("dateSend"))){
			filterMRCForm.setDateSend(request.getParameter("dateSend"));
		}
		return filterMRCForm;
	}

	@RequestMapping(value = "/tb6/index", method = RequestMethod.POST)
	public String indexAction(UploadItem uploadItem, 
			BindingResult result,@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "dateRemark", required = false) String dateRemark,
			@RequestParam(value = "dateSend", required = false) String dateSend,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String btmSubmit = request.getParameter("btnSubmit");
		String[] ids = request.getParameterValues("checkbox");	
		if (btmSubmit!= null && ids != null) {
			for (String id : ids) {
			    if(btmSubmit.equals("Remark")) {
					tb6Service.callAction(request, TB6_STAGE.TB6_UPLOAD.toString(), ACTIONS.TB6_REMARK.toString(), id, dateRemark, remark);			
				//} else if (btmSubmit.equals("Receive TB6")) {
				//	tb6Service.callAction(request, TB6_STAGE.TB6_UPLOAD.toString(), ACTIONS.TB6_RECEIVE.toString(), id, dateReceive, remark);
				} else if (btmSubmit.equals("Send To RMT")) {
					tb6Service.callAction(request, TB6_STAGE.TB6_UPLOAD.toString(), ACTIONS.TB6_SEND_TO_RMT.toString(), id, dateSend, remark);
				}	
			}
			return "redirect:/tb6/index.html";
		}
		if (request.getParameter("btnUpload") != null) {
				return uploadFileTB6(uploadItem, result, model, 
						request, response);
		}
		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_UPLOAD.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6List";
	}	
	
	public String uploadFileTB6(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return index(model, request, response);
		}

		// Some type of file processing...
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		List<String> errorList = new ArrayList<String>();
		int successRow =  0;
		String errorMessage = "";
		InputStream fis = null;
		try {
			fis = uploadItem.getFileData().getInputStream();

			// create a new org.apache.poi.poifs.filesystem.Filesystem
			POIFSFileSystem poifs = new POIFSFileSystem(fis);

			EventSAXReader reader = new EventSAXReader(poifs, -1);
			reader.process();

			List<String[]> allRows = reader.getData();
			poifs = null;
			reader = null; 
			fis.close();
			String loanNo = null, customerName = null, remark= null;
			Date receivedDate= null;			
			TB6 tb6 = null;
			List<TB6> listTB6 = new ArrayList<TB6>();
			if (request.getParameter("btnUpload").equals("Upload TB6")) {
				String formatDate = "dd/MM/yyyy";//31/12/2015
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 0) {
						
					}					
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}
					
					if (cols.length > 2) {
						customerName = CommonUtils.formatStringCompound(cols[2]);
					}
					
					if (cols.length > 3 && !CommonUtils.isNullOrEmpty(cols[3])) {	
						//try {
							receivedDate = DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]), formatDate);
						//} catch (Exception e) {
							
						//}
					}
					
					if (cols.length > 4) {
						remark = CommonUtils.formatStringCompound(cols[4]);
					}
					//Save into table RM_TB6
					tb6 = new TB6();
					tb6.setLoanNo(loanNo);
					tb6.setCustomerName(customerName);
					tb6.setReceiveFromCollectionDate(receivedDate);
					tb6.setRemark(remark);
					tb6.setCreateDate(Calendar.getInstance().getTime());					
					tb6.setCreateBy(username);
					tb6.setStage(TB6_STAGE.TB6_UPLOAD.toString());
					
					listTB6.add(tb6);					
				}
				
				UploadTB6Response resp = tb6Service.saveUploadTB6(listTB6);
				model.addAttribute("successList", resp.getSuccessList());
				model.addAttribute("existList", resp.getExistList());
				
				//Save user log in to table UserLog
				UserLog userlog= new UserLog();
		    	userlog.setLogDate(new Date());
		    	userlog.setUsername(username);    	
		    	userlog.setRemoteIP(request.getRemoteAddr());
		    	userlog.setSession(request.getSession().getId());    	
		    	userlog.setLogType(LOG_TYPE.TB6.toString());
		    	userlog.setActivity(ACTIONS.TB6_UPLOAD.toString());
				userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
		    	+ ";List Size: " + listTB6.size() + ";Action:" + ACTIONS.TB6_UPLOAD);
				userlog.setStatus(LOG_STATUS.SUCCESS.toString());
				/*userlog.setOutput(errorMsg);
		    	if(CommonUtils.isNullOrEmpty(errorMsg)){
		    		userlog.setStatus(LOG_STATUS.SUCCESS.toString());
		    	} else {
		    		userlog.setStatus(LOG_STATUS.FAILURE.toString());
		    	}
		    	userlog.setStatusNote(errorMsg);
		    	*/
				tb6Service.saveUserLog(userlog);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.composeExceptionMessage(e));
			result.reject("exception.fileTB6", e.toString());
			errorList.add("Fail to TB6 file. Reason: " + e.toString());
			//Save user log in to table UserLog
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.TB6.toString());
	    	userlog.setActivity(ACTIONS.TB6_UPLOAD.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";Action:" + ACTIONS.TB6_UPLOAD);
			userlog.setOutput("Fail to TB6 file. Reason: " + e.toString());
			userlog.setStatus(LOG_STATUS.FAILURE.toString());
			tb6Service.saveUserLog(userlog);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e.getStackTrace());
				}
			}
		}
		if (successRow > 0) {
			model.addAttribute("successMsg", 
					"Total " +  successRow + " rows import successfully.");
		}
		
		model.addAttribute("errorList", errorList);		
		return index(model, request, response);
	}
	
	
	/**** TB6 Send List(STAGE = TB6_SEND) ****/
	@RequestMapping(value = "/tb6/send", method = RequestMethod.GET)
	public String tb6ReceiveList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_SEND.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6SendList";
	}

	@RequestMapping(value = "/tb6/send", method = RequestMethod.POST)
	public String tb6ReceiveListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
			 @RequestParam(value = "remark", required = false) String remark,
			 @RequestParam(value = "dateReturn", required = false) String dateReturn,
			@RequestParam(value = "dateWaiting", required = false) String dateWaiting,
			@RequestParam(value = "dateComplete", required = false) String dateComplete,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String btmSubmit = request.getParameter("btnSubmit");
		if(btmSubmit!= null && ids != null) {
			for (String id : ids) {			
				if (btmSubmit.equals("Return")) 
					//tb6Service.markWatingTB6(id, remark, dateWaiting);
					tb6Service.callAction(request, TB6_STAGE.TB6_SEND.toString(), ACTIONS.TB6_RETURN.toString(), id, dateReturn, remark);
				else if (btmSubmit.equals("Mark As Waiting")) 
					tb6Service.callAction(request, TB6_STAGE.TB6_SEND.toString(), ACTIONS.TB6_WAITING.toString(), id, dateWaiting, remark);
				else if (btmSubmit.equals("Mark As Complete"))
					tb6Service.callAction(request, TB6_STAGE.TB6_SEND.toString(), ACTIONS.TB6_COMPLETE.toString(), id, dateComplete, remark);
			
			}
			return "redirect:/tb6/send.html";
		}

		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_SEND.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6SendList";
	
	}
	
	/**** TB6 Receive List(STAGE = TB6_WAITING) ****/
	@RequestMapping(value = "/tb6/waiting", method = RequestMethod.GET)
	public String tb6WaitingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_WAITING.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6WaitingList";
	}

	@RequestMapping(value = "/tb6/waiting", method = RequestMethod.POST)
	public String tb6WaitingListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
			@RequestParam(value = "dateComplete", required = false) String dateComplete,
			@RequestParam(value = "remark", required = false) String remark,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			String[] ids = request.getParameterValues("checkbox");
			String btmSubmit = request.getParameter("btnSubmit");
			if (btmSubmit!= null && ids != null) {
				for (String id : ids) {
					if(btmSubmit.equals("Mark As Complete"))
						tb6Service.callAction(request, TB6_STAGE.TB6_WAITING.toString(), ACTIONS.TB6_COMPLETE.toString(), id, dateComplete, remark);
				}
				return "redirect:/tb6/waiting.html";
			}
	
			List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_WAITING.toString(), filterMRCForm);		
			model.addAttribute("filterMRCForm", filterMRCForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
			return "TB6WaitingList";
	
	}
	
	
	/**** TB6 Receive List(STAGE = TB6_WAITING) ****/
	@RequestMapping(value = "/tb6/complete", method = RequestMethod.GET)
	public String tb6CompleteList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_COMPLETE.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "TB6CompleteList";
	}

	@RequestMapping(value = "/tb6/complete", method = RequestMethod.POST)
	public String tb6CompleteListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
			@RequestParam(value = "dateReturn", required = false) String dateReturn,
			@RequestParam(value = "remark", required = false) String remark,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			String[] ids = request.getParameterValues("checkbox");
			String btmSubmit = request.getParameter("btnSubmit");
			if (btmSubmit!= null && ids != null){
				for (String id : ids) {
					try {
						if (btmSubmit.equals("Return"))
							tb6Service.callAction(request, TB6_STAGE.TB6_COMPLETE.toString(), ACTIONS.TB6_RETURN.toString(), id, dateReturn, remark);
					} catch (Exception e) {
					}
				}
				return "redirect:/tb6/complete.html";
			}
	
			List<TB6> list =  tb6Service.getTB6List(TB6_STAGE.TB6_COMPLETE.toString(), filterMRCForm);		
			model.addAttribute("filterMRCForm", filterMRCForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
			return "TB6CompleteList";
	
	}
}
