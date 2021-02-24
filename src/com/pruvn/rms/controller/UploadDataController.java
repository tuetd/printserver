package com.pruvn.rms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.pruvn.rms.domain.FollowUp;
import com.pruvn.rms.domain.HomeLoan;
import com.pruvn.rms.domain.Insurance;
import com.pruvn.rms.domain.MRC;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.domain.RecordFL2;
import com.pruvn.rms.domain.RecordPending;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.domain.RecordWait;
import com.pruvn.rms.model.FilterHomeLoanForm;
import com.pruvn.rms.model.FilterInsuranceForm;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.LoanService;
import com.pruvn.rms.service.RecordService;
import com.pruvn.rms.service.UploadService;
import com.pruvn.rms.service.response.UploadInsuranceResponse;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.FollowUpStatus;
import com.pruvn.rms.utils.Constant.INSURANCE_STAGE;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.Constant.STAGE;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class UploadDataController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(UploadDataController.class);

	private UploadService uploadService;

	private LoanService loanService;

	/**
	 * @return the LoanService
	 */
	public LoanService getLoanService() {
		return loanService;
	}

	/**
	 * @param LoanService
	 *            the LoanService to set
	 */
	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}
	
	private RecordService recordService;

	/**
	 * @return the LoanService
	 */
	public RecordService getRecordService() {
		return recordService;
	}

	/**
	 * @param LoanService
	 *            the LoanService to set
	 */
	@Autowired
	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	/**
	 * @return the UploadService
	 */
	public UploadService getUploadService() {
		return uploadService;
	}

	/**
	 * @param UploadService
	 *            the UploadService to set
	 */
	@Autowired
	public void setUploadService(UploadService UploadService) {
		this.uploadService = UploadService;
	}
	
	private FileExcelUploadValidator fileUploadValidator;
	public FileExcelUploadValidator getFileExcelUploadValidator() {
		return fileUploadValidator;
	}
	
	@Autowired
	public void setFileExcelUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}
	/****** MRC ******/
	@RequestMapping(value = "/uploadData/mrc", method = RequestMethod.GET)
	public String mrc(Model model, HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		
		FilterMRCForm filterMRCForm = createFitlerMRCForm(request);
		List<MRC> list =  uploadService.getMRCList(filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "MRCList";
	}
	
	protected FilterMRCForm createFitlerMRCForm(HttpServletRequest request) {
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
	@RequestMapping(value = "/uploadData/mrc", method = RequestMethod.POST)
	public String mrcAction(UploadItem uploadItem, 
			BindingResult result,@ModelAttribute("filterMRCForm") FilterMRCForm filterMRCForm,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "dateReturn", required = false) String dateReturn,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		if (request.getParameter("btnSubmit") != null && request.getParameter("btnSubmit").equals("MRC Remark")) {
				//xu ly MRC Remark	
			String[] ids = request.getParameterValues("checkbox");
			
			for (String id : ids) {
				try {
					uploadService.remarkMRC(id, remark, dateReturn);	
				} catch (Exception e) {
				}
			}
			return "redirect:/uploadData/mrc.html";
		} else if (request.getParameter("btnUpload") != null) {
				return uploadFileMRC(uploadItem, result, model, 
						request, response);
		}  else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/uploadData/mrc.html";
		}
		List<MRC> list =  uploadService.getMRCList(filterMRCForm);		
		model.addAttribute("filterMRCForm", filterMRCForm);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", filterMRCForm.getPageSize() != 0 ? filterMRCForm.getPageSize() : list.size());
		return "MRCList";
	}

	/****** revertLoan ******/
	@RequestMapping(value = "/uploadData/home_loan", method = RequestMethod.GET)
	public String homeLoan(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		FilterHomeLoanForm filterHomeLoanForm = new FilterHomeLoanForm();
		List<HomeLoan> list =  uploadService.getHomeLoanList(filterHomeLoanForm);		
		model.addAttribute("filterHomeLoanForm", filterHomeLoanForm);
		model.addAttribute("recordList", list);	
		return "HomeLoanList";
	}
	@RequestMapping(value = "/uploadData/loankit_autopost", method = RequestMethod.GET)
	public String loan_kit(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		/*FilterHomeLoanForm filterHomeLoanForm = new FilterHomeLoanForm();
		List<HomeLoan> list =  uploadService.getHomeLoanList(filterHomeLoanForm);		
		model.addAttribute("filterHomeLoanForm", filterHomeLoanForm);
		model.addAttribute("recordList", list);	*/
		return "LoanKitPostList";
	}
	@RequestMapping(value = "/uploadData/loankit_autopost", method = RequestMethod.POST)
	public String loankitupdate(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return uploadLoankitByPost(uploadItem, result, model, 
							request, response);
			
	}
	
	
	@RequestMapping(value = "/uploadData/loanReceive_WaitAuto", method = RequestMethod.GET)
	public String loanReceive_WaitAuto(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		return "LoanReceiveWaitList";
	}
	@RequestMapping(value = "/uploadData/loanReceive_WaitAuto", method = RequestMethod.POST)
	public String loanReceive_WaitAuto(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return uploadLoanReceiveWaitAuto(uploadItem, result, model, 
							request, response);
			
	}
	@RequestMapping(value = "/uploadData/loanWaiting_ScanAuto", method = RequestMethod.GET)
	public String loanReceive_ScanAuto(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		return "LoanReceiveScanList";
	}
	@RequestMapping(value = "/uploadData/loanWaiting_ScanAuto", method = RequestMethod.POST)
	public String loanReceive_ScanAuto(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return uploadLoanWaitingToDocScan(uploadItem, result, model, 
							request, response);
			
	}
	
	@RequestMapping(value = "/uploadData/loankit_pending", method = RequestMethod.GET)
	public String loanRD_pending(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		return "LoanKitReadyPendingList";
	}
	@RequestMapping(value = "/uploadData/loankit_pending", method = RequestMethod.POST)
	public String loanRD_pending(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return uploadLoanRDPendingByAuto(uploadItem, result, model, 
							request, response);
			
	}
	@RequestMapping(value = "/uploadData/loanRMT_autopost", method = RequestMethod.GET)
	public String loanRMT_autopost(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		return "LoanRMTList";
	}
	@RequestMapping(value = "/uploadData/loanRMT_autopost", method = RequestMethod.POST)
	public String loanRMT_autopost(UploadItem uploadItem, 
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return uploadLoanRMTByAuto(uploadItem, result, model, 
							request, response);
			
	}

	@RequestMapping(value = "/uploadData/home_loan", method = RequestMethod.POST)
	public String homeLoanAction(UploadItem uploadItem, 
			BindingResult result,@ModelAttribute("filterHomeLoanForm") FilterHomeLoanForm filterHomeLoanForm,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "dateReturn", required = false) String dateReturn,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			if (request.getParameter("btnSubmit") != null && request.getParameter("btnSubmit").equals("Remark")) {
				//xu ly MRC Remark	
				String[] ids = request.getParameterValues("checkbox");
				
				for (String id : ids) {
					try {
						uploadService.remarkHomeLoan(id, remark, dateReturn);	
					} catch (Exception e) {
					}
				}
				return "redirect:/uploadData/home_loan.html";
			} else if (request.getParameter("btnUpload") != null) {
					return uploadFileHomeLoan(uploadItem, result, model, 
							request, response);
			}
			else if (request.getParameter("btnFilter") != null
					&& request.getParameter("btnFilter").equals("clear")) {
				return "redirect:/uploadData/home_loan.html";
			}
			List<HomeLoan> list =  uploadService.getHomeLoanList(filterHomeLoanForm);		
			model.addAttribute("filterHomeLoanForm", filterHomeLoanForm);
			model.addAttribute("recordList", list);
		return "HomeLoanList";
	}
	

	@RequestMapping(value = "/uploadData/insurance", method = RequestMethod.GET)
	public String insurance(			
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(new UploadItem());
		FilterInsuranceForm filterInsuranceForm = createFitlerForm(request);
		List<Insurance> list =  uploadService.getInsuranceList(filterInsuranceForm);		
		model.addAttribute("filterInsuranceForm", filterInsuranceForm);
		model.addAttribute("recordList", list);	
		model.addAttribute("pageSize", filterInsuranceForm.getPageSize() != 0 ? filterInsuranceForm.getPageSize() : list.size());
		return "InsuranceList";
	}
	protected FilterInsuranceForm createFitlerForm(HttpServletRequest request) {
		FilterInsuranceForm filterInsuranceForm = new FilterInsuranceForm();
		if(!CommonUtils.isNullOrEmpty(request.getParameter("pageSize"))){
			filterInsuranceForm.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		} else {
			filterInsuranceForm.setPageSize(50);
		}
		return filterInsuranceForm;
	}
	@RequestMapping(value = "/uploadData/insurance", method = RequestMethod.POST)
	public String insuranceAction(UploadItem uploadItem, 
			BindingResult result,@ModelAttribute("filterInsuranceForm") FilterInsuranceForm filterInsuranceForm,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "dateReceive", required = false) String dateReceive,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
			if (request.getParameter("btnSubmit") != null && request.getParameter("btnSubmit").equals("Remark")) {
				//xu ly  Remark	
				String[] ids = request.getParameterValues("checkbox");
				
				for (String id : ids) {
					try {
						uploadService.callAction(request, INSURANCE_STAGE.INSURANCE_UPLOAD.toString(), INSURANCE_STAGE.INSURANCE_REMARK.toString(), id, dateReceive, remark);
					} catch (Exception e) {
					}
				}
				return "redirect:/uploadData/insurance.html";
			} else if (request.getParameter("btnUpload") != null) {
					return uploadFileInsurance(uploadItem, result, model, 
							request, response);
			} else if (request.getParameter("btnFilter") != null
					&& request.getParameter("btnFilter").equals("clear")) {
				return "redirect:/uploadData/insurance.html";
			}
			List<Insurance> list =  uploadService.getInsuranceList(filterInsuranceForm);		
			model.addAttribute("filterInsuranceForm", filterInsuranceForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", filterInsuranceForm.getPageSize() != 0 ? filterInsuranceForm.getPageSize() : list.size());
		return "InsuranceList";
	}
	
	
	public String uploadFileMRC(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return mrc(model, request, response);
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
			String block = null, loanNo = null, customerName = null, dealer= null, 
					 certNo = null, orgReturn= null, isBill= null, status= null, remark= null;
			Date disbDate =null, rcvdDate = null,copy1st= null,  maturityDate= null;
			Integer no = null, tenure = null;
			MRC mrc = null;
			List<MRC> listMRC = new ArrayList<MRC>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				String formatDate = "dd-MMM-yyyy";
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 2 && StringUtils.isEmpty(cols[2])) {
						continue;
					}
					if (cols.length > 0) {
						block = CommonUtils.formatStringCompound(cols[0]);
					}
					if (cols.length > 1) {
						no = Integer.parseInt(cols[1].trim());						
					}
					if (cols.length > 2) {
						loanNo = CommonUtils.formatStringCompound(cols[2]);
					}
					if (cols.length > 3) {	
						try {
							disbDate = DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]), formatDate);
						} catch (Exception e) {
							
						}
					}
					if (cols.length > 4) {
						customerName= CommonUtils.formatStringCompound(cols[4]);
					}
					if (cols.length > 5) {
						dealer= CommonUtils.formatStringCompound(cols[5]);
					}	
					if (cols.length > 6) {
						certNo= CommonUtils.formatStringCompound(cols[6]);
					}	
					if (cols.length > 7) {
						try {
							rcvdDate= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[7]), formatDate);	
						} catch (Exception e) {
							
						}
					}	
					if (cols.length > 8) {
						try {
							copy1st= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[8]), formatDate);		
						} catch (Exception e) {
							
						}						
					}	
					if (cols.length > 9) {
						tenure= Integer.parseInt(cols[9]);
					}	
					if (cols.length > 10) {
						try {
							maturityDate= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[10]), formatDate);		
						} catch (Exception e) {
							
						}
					}
					if (cols.length > 11) {
						orgReturn= CommonUtils.formatStringCompound(cols[11]);
					}	
					if (cols.length > 12) {
						isBill= CommonUtils.formatStringCompound(cols[12]);
					}	
					if (cols.length > 13) {
						status= CommonUtils.formatStringCompound(cols[13]);
					}	
					if (cols.length > 14) {
						remark= CommonUtils.formatStringCompound(cols[14]);
					}
					//Save into table RM_MRC
					mrc = new MRC();
					mrc.setBlock(block);
					mrc.setNo(no);					
					mrc.setLoanNo(loanNo);
					mrc.setDisbursalDate(disbDate);					
					mrc.setCustomerName(customerName);
					mrc.setDealer(dealer);
					mrc.setCertNo(certNo);
					mrc.setRcvdDate(rcvdDate);
					mrc.setCopyDate(copy1st);					
					mrc.setTenure(tenure);
					mrc.setMaturityDate(maturityDate);
					mrc.setOrgReturn(orgReturn);
					mrc.setIsBill(isBill);
					mrc.setStatus(status);
					mrc.setRemark(remark);
					mrc.setUploadDate(Calendar.getInstance().getTime());
					mrc.setUploadUser(username);
					
					listMRC.add(mrc);					
				}
				
				uploadService.saveUploadMRC(listMRC);
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
		return mrc(model, request, response);
	}
	@SuppressWarnings("rawtypes")
	public String uploadLoankitByPost(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return loan_kit(model, request, response);
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
			String loanNo = null;
			RecordReady record ;
			RecordPending recordPe ;
			HashMap<Integer, String> listRecordId = new HashMap<Integer, String>();
			//List<Integer,String> listRecordId = new ArrayList<Integer,String>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					record = recordService.getRecordRDByLoanId(loanNo);
					if (record != null ) {
						if(!listRecordId.containsKey(record.getId()) ){
							listRecordId.put(record.getId(), record.getStage());
							//listRecordId.add(record.getId());	
						}
					}else{
						recordPe=recordService.getRecordPendingByLoanId(loanNo);
						if (recordPe != null ) {
							if(!listRecordId.containsKey(recordPe.getId()) ){
								listRecordId.put(recordPe.getId(), recordPe.getStage());
								//listRecordId.add(record.getId());	
							}
						}
					}
				}
				successRow = listRecordId.size();
				Set set = listRecordId.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         String stage = mentry.getValue().toString();
			         if(stage.equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName())){
			        	 recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), (Integer)mentry.getKey(), 
									LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
			         }else if(stage.equalsIgnoreCase(STAGE.RMT_RECORD_PENDING.getName())){
			        	 recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_PENDING.getName(),(Integer)mentry.getKey(),
									LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString()); 
			         }
			    }
				/*for (Integer recordId : listRecordId) {
					
					recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
							username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), recordId, LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
					
					recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
							username, STAGE.RMT_RECORD_PENDING.getName(), recordId, LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
				}*/
				
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
		}else{
			model.addAttribute("successMsg", 
					"Not Found Loan Ready List For Upload To Send By Post.");
		}
		
		return loan_kit(model, request, response);
	}
	
	public void updateFollowUp(Integer recordId){
		List<FollowUp> list = new ArrayList<FollowUp>();
		list = loanService.getFollowUpsByRecordId(recordId);
		for (FollowUp fu : list) {
			if(fu != null) {
				fu.setStatus(Constant.CONFIRM);
				fu.setUpdateDate(Calendar.getInstance().getTime());
				fu.setUpdater(SecurityContextHolder.getContext().getAuthentication().getName());
				loanService.saveFollowUp(fu);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public String uploadLoanWaitingToDocScan(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return loanReceive_ScanAuto(model, request, response);
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
			String loanNo = null;
			RecordWait record ;
			//RecordPending recordPe ;
			HashMap<Integer, String> listRecordId = new HashMap<Integer, String>();
			//List<Integer,String> listRecordId = new ArrayList<Integer,String>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					record = loanService.getRecordWaitByLoanId(loanNo);
					if (record != null ) {
						if(!listRecordId.containsKey(record.getId()) ){
							listRecordId.put(record.getId(), record.getStage());
							//listRecordId.add(record.getId());	
						}
					}
				}
				successRow = listRecordId.size();
				Set set = listRecordId.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         //String stage = mentry.getValue().toString();
			         updateFollowUp((Integer)mentry.getKey());
			         loanService.callInvolve(request, 
								ACTIONS.PREPARE_TO_SCAN.toString(),
								username, STAGE.FRESH_LOAN_RMT_WAIT_LOAN.getName(),
								(Integer)mentry.getKey(),LOG_TYPE.UPLOAD_LOANWAITING_DOCUMENTS_SCAN.toString());
			         
			         /*if(stage.equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName())){
			        	 recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), (Integer)mentry.getKey(), 
									LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
			         }*/
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
		}else{
			model.addAttribute("successMsg", 
					"Not Found Loan Receive For Upload To Documents Scan.");
		}
		
		return loanReceive_ScanAuto(model, request, response);
	}
	
	@SuppressWarnings("rawtypes")
	public String uploadLoanReceiveWaitAuto(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return loanReceive_WaitAuto(model, request, response);
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
			String loanNo = null;
			RecordFL2 record ;
			//RecordPending recordPe ;
			HashMap<Integer, String> listRecordId = new HashMap<Integer, String>();
			//List<Integer,String> listRecordId = new ArrayList<Integer,String>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					record = loanService.getRecordFL2ByLoanId(loanNo);
					if (record != null ) {
						if(!listRecordId.containsKey(record.getId()) ){
							listRecordId.put(record.getId(), CommonUtils.formatStringCompound(cols[3]));
							//listRecordId.add(record.getId());	
						}
					}
				}
				successRow = listRecordId.size();
				Set set = listRecordId.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         //String stage = mentry.getValue().toString();
			        
			         loanService.callInvolve(request, ACTIONS.RMT_WAIT_LOAN.toString(),
							 username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(),(Integer)mentry.getKey()
							 , Constant.OTHER_REASON, (String)mentry.getValue(),
							 LOG_TYPE.UPLOAD_LOANRECEIVE_WAITING.toString());
					 saveFollowUpSubmit((Integer)mentry.getKey(), Constant.OTHER_REASON,(String)mentry.getValue());
							 
			         /*if(stage.equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName())){
			        	 recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), (Integer)mentry.getKey(), 
									LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
			         }*/
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
		}else{
			model.addAttribute("successMsg", 
					"Not Found Loan Receive For Upload To Waiting.");
		}
		
		return loanReceive_WaitAuto(model, request, response);
	}
	
	public void saveFollowUpSubmit(Integer recordId,String category,String description)  throws Exception
	{
		
		FollowUp followUp = new FollowUp();
		followUp.setRecordId(recordId);
		followUp.setCategory(category);
		followUp.setDescription(description);
		followUp.setCreateDate(Calendar.getInstance().getTime());
		followUp.setCreater(SecurityContextHolder.getContext().getAuthentication().getName());
		followUp.setStatus(FollowUpStatus.INITIAL.getName());
		loanService.saveFollowUp(followUp);
	}
	
	@SuppressWarnings("rawtypes")
	public String uploadLoanRMTByAuto(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return loanRMT_autopost(model, request, response);
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
			String loanNo = null;
			RecordFL1 record ;
			//RecordPending recordPe ;
			HashMap<Integer, String> listRecordId = new HashMap<Integer, String>();
			//List<Integer,String> listRecordId = new ArrayList<Integer,String>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					record = loanService.getRecordFL1ByLoanId(loanNo);
					if (record != null ) {
						if(!listRecordId.containsKey(record.getId()) ){
							listRecordId.put(record.getId(), record.getStage());
							//listRecordId.add(record.getId());	
						}
					}
				}
				successRow = listRecordId.size();
				Set set = listRecordId.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         //String stage = mentry.getValue().toString();
			         loanService.callInvolve(request, 
								ACTIONS.RMT_RECEIVE_ACK.toString(),
								username, STAGE.FRESH_LOAN_SEND_TO_RMT.getName(),
								(Integer)mentry.getKey(),LOG_TYPE.UPLOAD_LOANRMT_RECEIVE.toString());
			         /*if(stage.equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName())){
			        	 recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), (Integer)mentry.getKey(), 
									LOG_TYPE.UPLOAD_LOANKIT_SEND_BY_POST.toString());
			         }*/
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
		}else{
			model.addAttribute("successMsg", 
					"No Loan RMT For Upload To Receive.");
		}
		
		return loanRMT_autopost(model, request, response);
	}
	
	@SuppressWarnings("rawtypes")
	public String uploadLoanRDPendingByAuto(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return loanRD_pending(model, request, response);
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
			String loanNo = null;
			RecordReady record ;
			HashMap<Integer, String> listRecordId = new HashMap<Integer, String>();
			//List<Integer,String> listRecordId = new ArrayList<Integer,String>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					record = recordService.getRecordRDByLoanId(loanNo);
					if (record != null ) {
						if(!listRecordId.containsKey(record.getId()) ){
							listRecordId.put(record.getId(), record.getStage());
							//listRecordId.add(record.getId());	
						}
					}
				}
				successRow = listRecordId.size();
				Set set = listRecordId.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         recordService.callInvolve(request, ACTIONS.RMT_CS_PENDING.toString(), username,
								STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), (Integer)mentry.getKey(),
								LOG_TYPE.UPLOAD_LOANRD_PENDING.toString());
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
		}else{
			model.addAttribute("successMsg", 
					"No Loan Ready For Upload To Pending.");
		}
		
		return loanRD_pending(model, request, response);
	}
	public String uploadFileHomeLoan(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return homeLoan(model, request, response);
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
			String loanNo = null, customerName = null, fileFolder= null, 
					seal1 = null, seal2= null, seal3= null, returnOriginalDoc = null;
			Date orderDate =null, executionDate = null;	
			HomeLoan homeLoan = null;
			List<HomeLoan> listHomeLoan = new ArrayList<HomeLoan>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				String formatDate = "dd/MM/yyyy";
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}					
					if (cols.length > 2) {
						customerName= CommonUtils.formatStringCompound(cols[2]);
					}
					
					if (cols.length > 3) {
						try {
							orderDate= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]), formatDate);	
						} catch (Exception e) {
							
						}
					}
					if (cols.length > 4) {
						try {
							executionDate= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[4]), formatDate);		
						} catch (Exception e) {
							
						}						
					}
					
					if (cols.length > 5) {
						fileFolder= CommonUtils.formatStringCompound(cols[5]);
					}	
					if (cols.length > 6) {
						seal1= CommonUtils.formatStringCompound(cols[6]);
					}	
					if (cols.length > 7) {
						seal2= CommonUtils.formatStringCompound(cols[7]);
					}	
					if (cols.length > 8) {
						seal3= CommonUtils.formatStringCompound(cols[8]);
					}
					if (cols.length > 9) {
						returnOriginalDoc= CommonUtils.formatStringCompound(cols[9]);
					}
					//Save into table RM_HomeLoan
					homeLoan = new HomeLoan();			
					homeLoan.setLoanNo(loanNo);				
					homeLoan.setCustomerName(customerName);
					homeLoan.setOrderDate(orderDate);
					homeLoan.setExecutionDate(executionDate);
					homeLoan.setFileFolder(fileFolder);
					homeLoan.setSeal1(seal1);					
					homeLoan.setSeal2(seal2);
					homeLoan.setSeal3(seal3);
					homeLoan.setReturnOriginalDocument(returnOriginalDoc);
					homeLoan.setUploadDate(Calendar.getInstance().getTime());
					homeLoan.setUploadUser(username);
					
					listHomeLoan.add(homeLoan);					
				}
				
				uploadService.saveUploadHomeLoan(listHomeLoan);
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
		return homeLoan(model, request, response);
	}
	
	public String uploadFileInsurance(UploadItem uploadItem,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);
		if (result.hasErrors()) {
			return insurance(model, request, response);
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
			String loanNo = null, customerName = null,  remark= null;
			Date receiveDate =null;
			Insurance insu = null;
			List<Insurance> listInsurance = new ArrayList<Insurance>();
			if (request.getParameter("btnUpload").equals("Upload")) {
				String formatDate = "dd/MM/yyyy";
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					
					if (cols.length > 1) {
						loanNo = CommonUtils.formatStringCompound(cols[1]);
					}
					
					if (cols.length > 2) {
						customerName= CommonUtils.formatStringCompound(cols[2]);
					}					
					
					if (cols.length > 3) {						
						receiveDate= DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]), formatDate);		
					
					}
					
					if (cols.length > 4) {
						remark= CommonUtils.formatStringCompound(cols[4]);
					}
					//Save into table RM_INSURANCE
					insu = new Insurance();							
					insu.setLoanNo(loanNo);							
					insu.setCustomerName(customerName);
					insu.setReceiveDate(receiveDate);
					insu.setRemark(remark);
					insu.setCreateDate(Calendar.getInstance().getTime());
					insu.setCreateBy(username);
					insu.setReceiveBy(username);
					listInsurance.add(insu);					
				}
				
			
				UploadInsuranceResponse resp = uploadService.saveUploadInsurance(listInsurance);
				model.addAttribute("successList", resp.getSuccessList());
				model.addAttribute("existList", resp.getExistList());
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
		return insurance(model, request, response);
	}
}
