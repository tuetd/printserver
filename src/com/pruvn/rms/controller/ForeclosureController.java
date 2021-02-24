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

import com.pruvn.rms.domain.Foreclosure;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.ForeclosureService;
import com.pruvn.rms.service.response.UploadForeclosureResponse;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.FORECLOSURE_STAGE;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.Constant.TB6_STAGE;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class ForeclosureController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(ForeclosureController.class);

	private ForeclosureService foreclosureService;
	
	public ForeclosureService getForeclosureService() {
		return foreclosureService;
	}
	@Autowired
	public void setForeclosureService(ForeclosureService foreclosureService) {
		this.foreclosureService = foreclosureService;
	}

	private FileExcelUploadValidator fileUploadValidator;
	

	public FileExcelUploadValidator getFileUploadValidator() {
		return fileUploadValidator;
	}
	@Autowired
	public void setFileUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}

	/****** Foreclosure ******/
	@RequestMapping(value = "/foreclosure/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureList";
	}

	@RequestMapping(value = "/foreclosure/index", method = RequestMethod.POST)
	public String indexAction(UploadItem uploadItem, 
			BindingResult result,@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "dateRemark", required = false) String dateRemark,
			@RequestParam(value = "dateSendFromXPU", required = false) String dateSendFromXPU,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String btmSubmit = request.getParameter("btnSubmit");
		String[] ids = request.getParameterValues("checkbox");		
		if (btmSubmit!= null && ids != null) {
			for (String id : ids) {
			    if(btmSubmit.equals("Remark")) {
			    	foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString(), ACTIONS.FORECLOSURE_REMARK.toString(), id, dateRemark, remark);			
				} else if (btmSubmit.equals("Send")) {
					foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString(), ACTIONS.FORECLOSURE_SEND.toString(), id, dateSendFromXPU, remark);
				} 	
			}
			return "redirect:/foreclosure/index.html";
		}
		if (request.getParameter("btnUpload") != null) {
				return uploadFileForeclosure(uploadItem, result, model, 
						request, response);
		}
		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureList";
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
	public String uploadFileForeclosure(UploadItem uploadItem,
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
			Date remarkDate= null;			
			Foreclosure foreclosure = null;
			List<Foreclosure> listForeclosure = new ArrayList<Foreclosure>();
	
			String formatDate = "dd/MM/yyyy";
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
					remarkDate = DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]), formatDate);
				}
				
				if (cols.length > 4) {
					remark = CommonUtils.formatStringCompound(cols[4]);
				}
				//Save into table RM_Foreclosure
				foreclosure = new Foreclosure();
				foreclosure.setLoanNo(loanNo);
				foreclosure.setCustomerName(customerName);
				foreclosure.setRemarkDate(remarkDate);
				foreclosure.setRemark(remark);
				foreclosure.setCreateDate(Calendar.getInstance().getTime());					
				foreclosure.setCreateBy(username);
				foreclosure.setStage(FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString());
				
				listForeclosure.add(foreclosure);					
			}
			
			UploadForeclosureResponse resp = foreclosureService.saveUploadForeclosure(listForeclosure);
			model.addAttribute("successList", resp.getSuccessList());
			model.addAttribute("existList", resp.getExistList());
			
			//Save user log in to table UserLog
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.FORECLOSURE.toString());
	    	userlog.setActivity(ACTIONS.FORECLOSURE_UPLOAD.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";List Size: " + listForeclosure.size() + ";Action:" + ACTIONS.FORECLOSURE_UPLOAD);
			userlog.setStatus(LOG_STATUS.SUCCESS.toString());
			foreclosureService.saveUserLog(userlog);
			/*userlog.setOutput(errorMsg);
	    	if(CommonUtils.isNullOrEmpty(errorMsg)){
	    		userlog.setStatus(LOG_STATUS.SUCCESS.toString());
	    	} else {
	    		userlog.setStatus(LOG_STATUS.FAILURE.toString());
	    	}
	    	userlog.setStatusNote(errorMsg);
	    	*/
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.composeExceptionMessage(e));
			result.reject("exception.fileForeclosure", e.toString());
			errorList.add("Fail to Foreclosure file. Reason: " + e.toString());
			//Save user log in to table UserLog
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.FORECLOSURE.toString());
	    	userlog.setActivity(ACTIONS.FORECLOSURE_UPLOAD.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";Action:" + ACTIONS.FORECLOSURE_UPLOAD);
			userlog.setOutput("Fail to Foreclosure file. Reason: " + e.toString());
			userlog.setStatus(LOG_STATUS.FAILURE.toString());
			foreclosureService.saveUserLog(userlog);
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
	
	
	/**** Foreclosure Send List(STAGE = Foreclosure_SEND) ****/
	@RequestMapping(value = "/foreclosure/send", method = RequestMethod.GET)
	public String foreclosureSendList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_SEND.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureSendList";
	}

	@RequestMapping(value = "/foreclosure/send", method = RequestMethod.POST)
	public String foreclosureSendListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
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
					foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_SEND.toString(), ACTIONS.FORECLOSURE_RETURN.toString(), id, dateReturn, remark);
				else if (btmSubmit.equals("Mark As Waiting")) 
					foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_SEND.toString(), ACTIONS.FORECLOSURE_WAITING.toString(), id, dateWaiting, remark);
				else if (btmSubmit.equals("Mark As Complete"))
					foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_SEND.toString(), ACTIONS.FORECLOSURE_COMPLETE.toString(), id, dateComplete, remark);
			
			}
			return "redirect:/foreclosure/send.html";
		}

		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_SEND.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureSendList";
	
	}
	
	/**** Foreclosure Waiting List(STAGE = Foreclosure_WAITING) ****/
	@RequestMapping(value = "/foreclosure/waiting", method = RequestMethod.GET)
	public String foreclosureWaitingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_WAITING.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureWaitingList";
	}

	@RequestMapping(value = "/foreclosure/waiting", method = RequestMethod.POST)
	public String foreclosureWaitingListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
			@RequestParam(value = "dateComplete", required = false) String dateComplete,
			@RequestParam(value = "remark", required = false) String remark,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			String[] ids = request.getParameterValues("checkbox");
			String btmSubmit = request.getParameter("btnSubmit");
			if (btmSubmit!= null && ids != null) {
				for (String id : ids) {
					if(btmSubmit.equals("Mark As Complete"))
						foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_WAITING.toString(), ACTIONS.FORECLOSURE_COMPLETE.toString(), id, dateComplete, remark);
				}
				return "redirect:/foreclosure/waiting.html";
			}
	
			List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_WAITING.toString(), filterMRCForm);		
			model.addAttribute("filterMRCForm", filterMRCForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
			return "ForeclosureWaitingList";
	}
	
	
	/**** Foreclosure Complete List(STAGE = Foreclosure_COMPLETE) ****/
	@RequestMapping(value = "/foreclosure/complete", method = RequestMethod.GET)
	public String foreclosureCompleteList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterMRCForm filterMRCForm = createFitlerForm(request);
		List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_COMPLETE.toString(), filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "ForeclosureCompleteList";
	}

	@RequestMapping(value = "/foreclosure/complete", method = RequestMethod.POST)
	public String foreclosureCompleteListAction(@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm, BindingResult result, 
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
							foreclosureService.callAction(request, FORECLOSURE_STAGE.FORECLOSURE_COMPLETE.toString(), ACTIONS.FORECLOSURE_RETURN.toString(), id, dateReturn, remark);
					} catch (Exception e) {
					}
				}
				return "redirect:/foreclosure/complete.html";
			}
	
			List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_COMPLETE.toString(), filterMRCForm);		
			model.addAttribute("filterMRCForm", filterMRCForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
			return "ForeclosureCompleteList";
	
	}
}
