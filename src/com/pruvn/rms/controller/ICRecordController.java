package com.pruvn.rms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordCompleted;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordSent;
import com.pruvn.rms.domain.CSRecordWaiting;
import com.pruvn.rms.domain.CSUploadData;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.InsuranceCertificateService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.CS_STAGE;
import com.pruvn.rms.utils.Constant.UPLOAD_TYPES;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class ICRecordController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(ICRecordController.class);

	private FileExcelUploadValidator fileUploadValidator;
	
	private InsuranceCertificateService icService;
	
	public InsuranceCertificateService getIcService() {
		return icService;
	}
	@Autowired
	public void setIcService(InsuranceCertificateService icService) {
		this.icService = icService;
	}
	
	public FileExcelUploadValidator getFileExcelUploadValidator() {
		return fileUploadValidator;
	}
	
	@Autowired
	public void setFileExcelUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}
	
	/**** Insurance Certificate  Waiting (STAGE = INSURANCE_CERTIFICATE/WAITING_LIST) ****/
	@RequestMapping(value = "/ic/index", method = RequestMethod.GET)
	public String waitingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordWaiting> list = icService.getAllCSRecordWaiting_ACL(
				username, CS_STAGE.INSURANCE_CERTIFICATE_WAITING_LIST.getName(), filters);
		model.addAttribute(form);
		model.addAttribute(new UploadItem());
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "InsuranceCertificateWaitingList";
	}

	@RequestMapping(value = "/ic/index", method = RequestMethod.POST)
	public String waitingListAction(@ModelAttribute("filterRecordForm") FilterRecordForm form,
			UploadItem uploadItem, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		//String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (request.getParameter("btnUpload") != null) {
			 boolean isSuccess = uploadFile(form, uploadItem, result, model, CS_STAGE.INSURANCE_CERTIFICATE_WAITING_LIST.getName(), request, response);
			 //if (isSuccess) {
			//	 
			//	 return waitingList(model, request, response);
			// } else {
			//	 
			//	 return "InsuranceCertificateWaitingList";
			// }	
			 return waitingList(model, request, response);
			 
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/ic/index.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordWaiting> list = icService.getAllCSRecordWaiting_ACL(
					username, CS_STAGE.INSURANCE_CERTIFICATE_WAITING_LIST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute(new UploadItem());
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "InsuranceCertificateWaitingList";
			
		}
		
		
	}

	@RequestMapping(value = "/ic/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model waitingDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordWaiting record = icService.getCSRecordWaitingById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Insurance Certificate Waiting ****/
	
	/**** Insurance Certificate  Pending (STAGE = INSURANCE_CERTIFICATE/PENDING_LIST) ****/
	@RequestMapping(value = "/ic/followup", method = RequestMethod.GET)
	public String pendingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordPending> list = icService.getAllCSRecordPending_ACL(
				username, CS_STAGE.INSURANCE_CERTIFICATE_PENDING_LIST.getName(), filters);
		model.addAttribute(form);
		model.addAttribute(new UploadItem());
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "InsuranceCertificatePendingList";
	}

	
	@RequestMapping(value = "/ic/followup", method = RequestMethod.POST)
	public String pendingListAction(@ModelAttribute("filterRecordForm") FilterRecordForm form,
			UploadItem uploadItem, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		if (request.getParameter("btnUpload") != null) {
			 boolean isSuccess = uploadFile(form, uploadItem, result, model, CS_STAGE.INSURANCE_CERTIFICATE_PENDING_LIST.getName(), request, response);
			 //if (isSuccess) {
				 return pendingList(model, request, response);
			 //} else {
			//	 return "InsuranceCertificatePendingList";
			 //}			 	
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/ic/followup.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordPending> list = icService.getAllCSRecordPending_ACL(
					username, CS_STAGE.INSURANCE_CERTIFICATE_PENDING_LIST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute(new UploadItem());
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "InsuranceCertificatePendingList";			
		}
	}

	@RequestMapping(value = "/ic/detail_followup", method = RequestMethod.GET)
	@ResponseBody
	public Model followupDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordPending record = icService.getCSRecordPendingById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Insurance Certificate Pending ****/
	
	
	/**** Insurance Certificate  Failed (STAGE = INSURANCE_CERTIFICATE/FAILED_LIST) ****/
	@RequestMapping(value = "/ic/failure", method = RequestMethod.GET)
	public String failedList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordFailed> list = icService.getAllCSRecordFailed_ACL(
				username, CS_STAGE.INSURANCE_CERTIFICATE_FAILED_LIST.getName(), filters);
		model.addAttribute(form);
		model.addAttribute(new UploadItem());
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "InsuranceCertificateFailedList";
	}

	@RequestMapping(value = "/ic/failure", method = RequestMethod.POST)
	public String failedAction(@ModelAttribute("filterRecordForm") FilterRecordForm form, 
			UploadItem uploadItem, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (request.getParameter("btnUpload") != null) {
			 boolean isSuccess = uploadFile(form, uploadItem, result, model, CS_STAGE.INSURANCE_CERTIFICATE_FAILED_LIST.getName(), request, response);
			 //if (isSuccess) {
				 return failedList(model, request, response);
			 //} else {
			//	 return "InsuranceCertificatePendingList";
			 //}			 	
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/ic/failure.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordFailed> list = icService.getAllCSRecordFailed_ACL(
					username, CS_STAGE.INSURANCE_CERTIFICATE_FAILED_LIST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute(new UploadItem());
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "InsuranceCertificateFailedList";
			
		}
		
		
	}

	@RequestMapping(value = "/ic/detail_failure", method = RequestMethod.GET)
	@ResponseBody
	public Model failureDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordFailed record = icService.getCSRecordFailedById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Insurance Certificate Failed ****/
	
	
	/**** Insurance Certificate Completed (STAGE = INSURANCE_CERTIFICATE/COMPLETED_LIST) ****/
	@RequestMapping(value = "/ic/completed", method = RequestMethod.GET)
	public String completedList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordCompleted> list = icService.getAllCSRecordCompleted_ACL(
				username, CS_STAGE.INSURANCE_CERTIFICATE_COMPLETED_LIST.getName(), filters);
		model.addAttribute(form);
		model.addAttribute(new UploadItem());
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "InsuranceCertificateCompletedList";
	}

	@RequestMapping(value = "/ic/completed", method = RequestMethod.POST)
	public String completeListAction(@ModelAttribute("filterRecordForm") FilterRecordForm form, 
			UploadItem uploadItem, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (request.getParameter("btnUpload") != null) {
			uploadFile(form, uploadItem, result, model, CS_STAGE.INSURANCE_CERTIFICATE_COMPLETED_LIST.getName(), request, response);
			return completedList(model, request, response);						 	
		}else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/ic/completed.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordCompleted> list = icService.getAllCSRecordCompleted_ACL(
					username, CS_STAGE.INSURANCE_CERTIFICATE_COMPLETED_LIST.getName(), filters);
 			model.addAttribute(new UploadItem());
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "InsuranceCertificateCompletedList";
			
		}		
	}

	@RequestMapping(value = "/ic/detail_completed", method = RequestMethod.GET)
	@ResponseBody
	public Model Detail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordCompleted record = icService.getCSRecordCompletedById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Insurance Certificate Completed ****/
	
	
	/**** Insurance Certificate  Sent (STAGE = INSURANCE_CERTIFICATE/SENT_LIST) ****/
	@RequestMapping(value = "/ic/sent", method = RequestMethod.GET)
	public String sentList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordSent> list = icService.getAllCSRecordSent_ACL(
				username, CS_STAGE.INSURANCE_CERTIFICATE_SENT_LIST.getName(), filters);
		model.addAttribute(form);
		model.addAttribute(new UploadItem());
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "InsuranceCertificateSentList";
	}

	@RequestMapping(value = "/ic/sent", method = RequestMethod.POST)
	public String sentListAction(@ModelAttribute("filterRecordForm") FilterRecordForm form,
			UploadItem uploadItem, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/ic/sent.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordSent> list = icService.getAllCSRecordSent_ACL(
					username, CS_STAGE.INSURANCE_CERTIFICATE_SENT_LIST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute(new UploadItem());
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "InsuranceCertificateSentList";
			
		}
		
		
	}

	@RequestMapping(value = "/ic/detail_sent", method = RequestMethod.GET)
	@ResponseBody
	public Model sentDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordSent record = icService.getCSRecordSentById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Insurance Certificate Sent ****/
	
	
	private UserMService userMService;
	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService the userMService to set
	 */
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}	
	
	public boolean uploadFile(FilterRecordForm form, UploadItem uploadItem,
			BindingResult result, Model model, String stage, HttpServletRequest request,
			HttpServletResponse response) {
		
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);

		if (result.hasErrors()) {
			return false;
		}

		// Some type of file processing...
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		List<String> errorList = new ArrayList<String>();
		int successRow =  0;
		String errorMessage = "";
		InputStream fis = null;
		//List<String> charList = Arrays.asList("/","-","+","\\",":","*", "~", "!", "@","#","$","%","^");
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
			CSRecord record ;
			String loanID = null, customerName = null;
			CSUploadData ud = null;
			if("UploadFollowUp".equals(uploadItem.getUploadType())) {
				String pendingReason = null, pendingDate = null; 
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}
					
					customerName= CommonUtils.formatStringCompound(cols[0]).trim();
					if (cols.length > 1) {
						loanID= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 3) {
						pendingReason = CommonUtils.formatStringCompound(cols[3]).trim();
					}
					//if (cols.length > 4) {
					//	pendingDate = CommonUtils.formatStringCompound(cols[4]).trim();
					//}
					
					//Save into table CS_UPLOAD_DATA
					ud = new CSUploadData();					
					ud.setLoanID(loanID);					
					ud.setCustomerName(customerName);
					ud.setPendingReason(pendingReason);				
					//ud.setPendingDate(pendingDate);
					
					ud.setUploadType(UPLOAD_TYPES.IC_PENDING.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					
					record = icService.getCSRecorByIdAndCusName(stage, loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = icService.callInvolve(request, ACTIONS.IC_PENDING.toString(),
								username, stage, record.getId(), pendingReason, pendingDate);
					} else {
						errorMessage = "Data not found.";
					}
					
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
						
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					icService.saveUploadData(ud);					
				}
			} else if("UploadFailed".equals(uploadItem.getUploadType())) {
				String failedReason = null, failedDate = null; 
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}					
					
					if (cols.length > 1) {
						customerName= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						loanID = CommonUtils.formatStringCompound(cols[2]).trim();
					}
					if (cols.length > 3) {
						failedDate = CommonUtils.formatStringCompound(cols[3]).trim();
					}
					if (cols.length > 4) {
						failedReason = CommonUtils.formatStringCompound(cols[4]).trim();
					}
					
					//Save into table CS_UPLOAD_DATA
					ud = new CSUploadData();					
					ud.setLoanID(loanID);					
					ud.setCustomerName(customerName);
					ud.setFailedReason(failedReason);				
					ud.setFailedDate(failedDate);
					
					ud.setUploadType(UPLOAD_TYPES.IC_FAILED.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					
					record = icService.getCSRecorByIdAndCusName(stage, loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = icService.callInvolve(request, ACTIONS.IC_FAILED.toString(),
								username, stage, record.getId(), failedReason, failedDate);
					} else {
						errorMessage = "Data not found.";
					}
					
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
						
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					icService.saveUploadData(ud);					
				}
				
				
			} if("UploadCompleted".equals(uploadItem.getUploadType())) {
				String deliverDate = null, receiveDate = null; 
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}					
					
					if (cols.length > 1) {
						loanID= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						customerName = CommonUtils.formatStringCompound(cols[2]).trim();
					}
					if (cols.length > 3) {
						deliverDate = CommonUtils.formatStringCompound(cols[3]).trim();
					}
					if (cols.length > 4) {
						receiveDate = CommonUtils.formatStringCompound(cols[4]).trim();
					}
					
					//Save into table CS_UPLOAD_DATA
					ud = new CSUploadData();					
					ud.setLoanID(loanID);					
					ud.setCustomerName(customerName);
					ud.setDeliverDate(deliverDate);				
					ud.setReceiveDate(receiveDate);
					
					ud.setUploadType(UPLOAD_TYPES.IC_COMPLETED.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					
					record = icService.getCSRecorByIdAndCusName(stage, loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = icService.callInvolve(request, ACTIONS.IC_COMPLETED.toString(),
								username, stage, record.getId(), deliverDate, receiveDate);
					} else {
						errorMessage = "Data not found.";
					}
					
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
						
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					icService.saveUploadData(ud);					
				}
			} else if("UploadFailedToCompleted".equals(uploadItem.getUploadType())) {
				String note = null; 
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}					
					
					if (cols.length > 1) {
						loanID= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						customerName = CommonUtils.formatStringCompound(cols[2]).trim();
					}
					if (cols.length > 3) {
						note = CommonUtils.formatStringCompound(cols[3]).trim();
					}
					
					
					//Save into table CS_UPLOAD_DATA
					ud = new CSUploadData();					
					ud.setLoanID(loanID);					
					ud.setCustomerName(customerName);
					ud.setFailToCompleteNote(note);
					
					ud.setUploadType(UPLOAD_TYPES.IC_FAILED_TO_COMPLETE.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					
					record = icService.getCSRecorByIdAndCusName(stage, loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = icService.callInvolve(request, ACTIONS.IC_FAILED_TO_COMPLETED.toString(),
								username, stage, record.getId(), note);
					} else {
						errorMessage = "Data not found.";
					}
					
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
						
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					icService.saveUploadData(ud);					
				}
				
			} else if("UploadCompletedToFailed".equals(uploadItem.getUploadType())) {
				String note = null; 
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}					
					
					if (cols.length > 1) {
						loanID= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						customerName = CommonUtils.formatStringCompound(cols[2]).trim();
					}
					if (cols.length > 3) {
						note = CommonUtils.formatStringCompound(cols[3]).trim();
					}					
					
					//Save into table CS_UPLOAD_DATA
					ud = new CSUploadData();					
					ud.setLoanID(loanID);					
					ud.setCustomerName(customerName);
					ud.setFailToCompleteNote(note);
					
					ud.setUploadType(UPLOAD_TYPES.IC_COMPLETED_TO_FALIED.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					
					record = icService.getCSRecorByIdAndCusName(stage, loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = icService.callInvolve(request, ACTIONS.IC_COMPLETED_TO_FAILED.toString(),
								username, stage, record.getId(), note);
					} else {
						errorMessage = "Data not found.";
					}
					
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
						
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					icService.saveUploadData(ud);					
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.composeExceptionMessage(e));
			result.reject("exception.fileUpload", e.toString());
			errorList.add("Fail to upload file. Reason: " + e.toString());
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
		return true;
		//return waitingList(model, request, response);
	}

}
