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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pruvn.rms.domain.StoredLoan;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.dto.ApiDto;
import com.pruvn.rms.model.FilterStoredLoanForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.StoredLoanService;
import com.pruvn.rms.service.response.UploadStoredLoanResponse;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class StoredLoanController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(StoredLoanController.class);
	private StoredLoanService storedLoanService;
	public StoredLoanService getStoredLoanService() {
		return storedLoanService;
	}
	@Autowired
	public void setStoredLoanService(StoredLoanService storedLoanService) {
		this.storedLoanService = storedLoanService;
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
	@RequestMapping(value = "/storedLoan/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		//FilterMRCForm filterMRCForm = createFitlerForm(request);
		//List<Foreclosure> list =  foreclosureService.getForeclosureList(FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString(), filterMRCForm);		
		//model.addAttribute("filterMRCForm", filterMRCForm);
		//model.addAttribute("recordList", list);
		//model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "StoredLoanList";
	}

	@RequestMapping(value = "/storedLoan/index", method = RequestMethod.POST)
	public String indexAction(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("btnUpload") != null) {
				return uploadFileStoredLoan(uploadItem, result, model, 
						request, response);
		}
		return "StoredLoanList";
	}
	@RequestMapping(value = "/storedLoan/update", method = RequestMethod.GET)
	public String indexUpdate(Model model, HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		return "StoredLoanUpdateList";
	}
	
	@RequestMapping(value = "/storedLoan/viewStoredDetail", method = RequestMethod.GET)
	@ResponseBody
	public StoredLoan viewLoanDetail(
			@RequestParam(required = false) Integer id, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		StoredLoan obj = null;
		try {
			obj = storedLoanService.getStoredLoanById(id);
		} catch (Exception e) {
			String outputLog = ExceptionUtils.composeExceptionMessage(e);
			logger.error("View Loan Detail is error : " + outputLog);
		}
		return obj;
	}
	@RequestMapping(value = "/storedLoan/updateStoredDetail", method = RequestMethod.POST)
	@ResponseBody
	public ApiDto updateStoredDetail(Model model,
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String block,
			@RequestParam(required = false) String no,
			@RequestParam(required = false) String barCode,
			@RequestParam(required = false) String nameBox,
			@RequestParam(required = false) String dateSent,
			@RequestParam(required = false) String dateDestroy,
			@RequestParam(required = false) String remark,
			HttpServletRequest request, HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		ApiDto apiDto = new ApiDto();
		String resultCode = "";
		String resultMsg = "";
		String outputLog="";
		StoredLoan obj = new StoredLoan();
		try {
			if(StringUtils.isNotEmpty(id)){
				obj = storedLoanService.getStoredLoanById(Integer.valueOf(id));
				if(obj != null){
					obj.setBarCode(barCode);
					obj.setBlock(block);
					obj.setDateSent(dateSent);
					obj.setNameBox(nameBox);
					obj.setDestroyDate(dateDestroy);
					obj.setRemark(remark);
					obj.setNo(no);
					obj.setUpdateDate(new Date());
					obj.setUpdateBy(username);
					storedLoanService.updateStoredLoan(obj);
					
					UserLog userlog= new UserLog();
			    	userlog.setLogDate(new Date());
			    	userlog.setUsername(username);    	
			    	userlog.setRemoteIP(request.getRemoteAddr());
			    	userlog.setSession(request.getSession().getId());    	
			    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
			    	userlog.setActivity(ACTIONS.STORED_LOAN_UPDATE.toString());
					userlog.setInput("Update StoredLoan Id : "+  id);
					userlog.setStatus(LOG_STATUS.SUCCESS.toString());
					storedLoanService.saveUserLog(userlog);
					resultCode = "00000";
				}
			} 
		} catch (Exception e) {
			outputLog = ExceptionUtils.composeExceptionMessage(e);
			logger.error("Save new Waiver is error : " + outputLog);
			resultCode = "99999";
			resultMsg = "Update StoredLoan fail!";
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
	    	userlog.setActivity(ACTIONS.STORED_LOAN_UPDATE.toString());
			userlog.setInput("Update StoredLoan Id : "+  id);
			userlog.setStatus(LOG_STATUS.FAILURE.toString());
			storedLoanService.saveUserLog(userlog);
		}
		/*auditLogService.writeUserLog(Constant.LOGTYPE_ACT_ON_LOAN, request,
				Constant.ACT_SAVE_NEW_WAIVER, inputLog, outputLog, statusLog);*/
		apiDto.setResultCode(resultCode);
		apiDto.setResultMsg(resultMsg);
		return apiDto;
	}
	
	@RequestMapping(value = "/storedLoan/update", method = RequestMethod.POST)
	public String indexActionUpdate(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("btnUpload") != null) {
				return updateFileStoredLoan(uploadItem, result, model, 
						request, response);
		}
		return "StoredLoanUpdateList";
	}
	public String updateFileStoredLoan(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			model.addAttribute("errorList", "Format is invalid. Only File .xls accepted!");	
			return indexUpdate(model, request, response);
		}
		List<StoredLoan> listForeclosure = new ArrayList<StoredLoan>();
		//Some type of file processing...
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<String> errorList = new ArrayList<String>();
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
			String  
					barcode=null,nameBox=null,dateDestroy=null,remark=null,dateSend=null;
			StoredLoan foreclosure = null;
			for (int i = 1; i < allRows.size(); i++) {
				String[] cols = allRows.get(i);
				if (cols.length > 1 && StringUtils.isEmpty(cols[1]) &&  StringUtils.isEmpty(cols[0])) {
					continue;
				}
				if (cols.length > 0) {
					nameBox = CommonUtils.processString(cols[0]);
				}
				if (cols.length > 1) {
					barcode = CommonUtils.processString(cols[1]);
				}
				if (cols.length > 3) {
					dateDestroy = CommonUtils.processString(cols[3]);
				}
				if (cols.length > 2) {
					dateSend = CommonUtils.processString(cols[2]);
				}
				
				if (cols.length > 4) {
					remark = CommonUtils.processString(cols[4]);
				}
				//Save into table 
				foreclosure = new StoredLoan();
				foreclosure.setBarCode(barcode);					
				foreclosure.setNameBox(nameBox);
				foreclosure.setDestroyDate(dateDestroy);
				foreclosure.setDateSent(dateSend);
				foreclosure.setRemark(remark);
				listForeclosure.add(foreclosure);					
			}
			UploadStoredLoanResponse lst = storedLoanService.updateStoredLoan(listForeclosure,username);
			model.addAttribute("successList", lst.getSuccessList());
			model.addAttribute("existList", lst.getExistList());
			//Save user log in to table UserLog
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
	    	userlog.setActivity(ACTIONS.STORED_LOAN_UPDATE.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";List Size: " + listForeclosure.size() + ";Action:" + ACTIONS.STORED_LOAN_UPDATE);
			userlog.setStatus(LOG_STATUS.SUCCESS.toString());
			storedLoanService.saveUserLog(userlog);
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
	    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
	    	userlog.setActivity(ACTIONS.STORED_LOAN_UPDATE.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";Action:" + ACTIONS.STORED_LOAN_UPDATE);
			userlog.setOutput("Fail to Foreclosure file. Reason: " + e.toString());
			userlog.setStatus(LOG_STATUS.FAILURE.toString());
			storedLoanService.saveUserLog(userlog);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e.getStackTrace());
				}
			}
		}
		if (listForeclosure.size() > 0) {
			model.addAttribute("successMsg", 
					"Total " +  listForeclosure.size() + " rows import successfully.");
		}
		model.addAttribute("errorList", errorList);		
		return indexUpdate(model, request, response);
	}
	protected FilterStoredLoanForm createFitlerForm(HttpServletRequest request) {
		FilterStoredLoanForm filterMRCForm = new FilterStoredLoanForm();
		filterMRCForm.setBarCode(request.getParameter("barCode"));
		filterMRCForm.setLoanNo(request.getParameter("loanNo"));
		filterMRCForm.setNameBox(request.getParameter("nameBox"));
		
		if(!CommonUtils.isNullOrEmpty(request.getParameter("pageSize"))){
			filterMRCForm.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		} else {
			filterMRCForm.setPageSize(50);
		}
		return filterMRCForm;
	}
	
	public String uploadFileStoredLoan(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			model.addAttribute("errorList", "Format is invalid. Only File .xls accepted!");	
			return index(model, request, response);
		}
		List<StoredLoan> listForeclosure = new ArrayList<StoredLoan>();
		//Some type of file processing...
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<String> errorList = new ArrayList<String>();
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
			String loanNo = null, customerName = null, block=null,no=null,
					barcode=null,nameBox=null,dateSent=null;
			Date disbursalDate= null;			
			StoredLoan foreclosure = null;
			
			String formatDate = "dd/MM/yyyy";
			for (int i = 1; i < allRows.size(); i++) {
				String[] cols = allRows.get(i);
				if (cols.length > 2 && StringUtils.isEmpty(cols[2])) {
					continue;
				}
				if (cols.length > 1) {
					block = CommonUtils.processString(cols[0]);
				}
				if (cols.length > 1) {
					no = CommonUtils.processString(cols[1]);
				}
				if (cols.length > 2 && !CommonUtils.isNullOrEmpty(cols[2])) {	
					loanNo = CommonUtils.processString(cols[2]);
				}
				if (cols.length > 3) {
					disbursalDate = DateUtils.stringToDateFormat(CommonUtils.formatStringCompound(cols[3]), formatDate);
				}
				if (cols.length > 4) {
					customerName = CommonUtils.processString(cols[4]);
				}
				if (cols.length > 5) {
					nameBox = CommonUtils.processString(cols[5]);
				}
				if (cols.length > 6) {
					barcode = CommonUtils.processString(cols[6]);
				}
				if (cols.length > 7) {
					dateSent = CommonUtils.processString(cols[7]);
				}
				//Save into table 
				foreclosure = new StoredLoan(block, no, disbursalDate, loanNo, customerName, barcode, nameBox, dateSent);
				foreclosure.setCreateDate(Calendar.getInstance().getTime());					
				foreclosure.setCreateBy(username);
				listForeclosure.add(foreclosure);					
			}
			storedLoanService.saveUploadForeclosure(listForeclosure);
			model.addAttribute("successList", listForeclosure);
			//Save user log in to table UserLog
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(username);    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
	    	userlog.setActivity(ACTIONS.STORED_LOAN_UPLOAD.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";List Size: " + listForeclosure.size() + ";Action:" + ACTIONS.STORED_LOAN_UPLOAD);
			userlog.setStatus(LOG_STATUS.SUCCESS.toString());
			storedLoanService.saveUserLog(userlog);
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
	    	userlog.setLogType(LOG_TYPE.STORED_LOAN.toString());
	    	userlog.setActivity(ACTIONS.STORED_LOAN_UPLOAD.toString());
			userlog.setInput("File Name: "+  uploadItem.getFileData().getName() 
	    	+ ";Action:" + ACTIONS.STORED_LOAN_UPLOAD);
			userlog.setOutput("Fail to Foreclosure file. Reason: " + e.toString());
			userlog.setStatus(LOG_STATUS.FAILURE.toString());
			storedLoanService.saveUserLog(userlog);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e.getStackTrace());
				}
			}
		}
		if (listForeclosure.size() > 0) {
			model.addAttribute("successMsg", 
					"Total " +  listForeclosure.size() + " rows import successfully.");
		}
		model.addAttribute("errorList", errorList);		
		return index(model, request, response);
	}
	
	/**** Foreclosure Waiting List(STAGE = Foreclosure_WAITING) ****/
	@RequestMapping(value = "/storedLoan/list", method = RequestMethod.GET)
	public String foreclosureWaitingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		FilterStoredLoanForm filterMRCForm = createFitlerForm(request);
		List<StoredLoan> list =  storedLoanService.getForeclosureList(filterMRCForm);		
		model.addAttribute("filterStoredLoanForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "StoredList";
	}

	@RequestMapping(value = "/storedLoan/list", method = RequestMethod.POST)
	public String foreclosureWaitingListAction(@ModelAttribute("filterStoredLoanForm") FilterStoredLoanForm filterMRCForm, BindingResult result, 
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			List<StoredLoan> list =  storedLoanService.getForeclosureList(filterMRCForm);		
			model.addAttribute("filterStoredLoanForm", filterMRCForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
			return "StoredList";
	}
}
