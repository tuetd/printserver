package com.pruvn.rms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.pruvn.rms.domain.LogTransaction;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordCSCompleted;
import com.pruvn.rms.domain.RecordCSWaiting;
import com.pruvn.rms.domain.RecordNotSend;
import com.pruvn.rms.domain.RecordPO;
import com.pruvn.rms.domain.RecordPOReturn;
import com.pruvn.rms.domain.RecordPending;
import com.pruvn.rms.domain.RecordPrepared;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.domain.RecordVerified;
import com.pruvn.rms.domain.UploadData;
import com.pruvn.rms.dto.DataTablesDto;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.model.UploadItem;
import com.pruvn.rms.service.RecordCSService;
import com.pruvn.rms.service.RecordService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.STAGE;
import com.pruvn.rms.utils.Constant.UPLOAD_TYPES;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;
import com.pruvn.rms.utils.poi.EventSAXReader;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class RecordController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(RecordController.class);

	private FileExcelUploadValidator fileUploadValidator;
	
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
	
	private RecordCSService recordCSService;

	/**
	 * @return the recordCSService
	 */
	public RecordCSService getRecordCMService() {
		return recordCSService;
	}

	/**
	 * @param RecordrecordCSService
	 *            the recordCSService to set
	 */
	@Autowired
	public void setSecordCSService(RecordCSService recordCSService) {
		this.recordCSService = recordCSService;
	}
	
	public FileExcelUploadValidator getFileExcelUploadValidator() {
		return fileUploadValidator;
	}
	
	@Autowired
	public void setFileExcelUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}
	
	/****** Loan Kit Verified (STAGE = RMT_RECORD/VERIFIED) ******/
	@RequestMapping(value = "/record/verified", method = RequestMethod.GET)
	public String verified(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordVerified> list = recordService.getAllRecordVerifieds_ACL(username,
				STAGE.RMT_RECORD_VERIFIED.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordVerifiedList";
	}

	@RequestMapping(value = "/record/verified", method = RequestMethod.POST)
	public String verifiedAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if (ACTIONS.RMT_MARK_PREPARED.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_MARK_PREPARED.toString(),
									username, STAGE.RMT_RECORD_VERIFIED.getName(),	Integer.parseInt(id));
						}
					}
				}
			} catch (Exception e) {
				logger.error("verifiedAction has error message=" + e.getMessage());
			}
			return "redirect:/record/verified.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/verified.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordVerified> list = recordService.getAllRecordVerifieds_ACL(username,
					STAGE.RMT_RECORD_VERIFIED.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordVerifiedList";
		}
	}

	@RequestMapping(value = "/record/detail_verified", method = RequestMethod.GET)
	@ResponseBody
	public Model viewVerifiedDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		RecordVerified record = recordService.getRecordVerifiedById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Verified ******/

	/****** Loan Kit Prepare (STAGE = RMT_RECORD) ******/
	@RequestMapping(value = "/record/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordPrepared> list = recordService.getAllRecordPrepareds_ACL(username,
				STAGE.RMT_RECORD.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RMTRecordList";
	}

	@RequestMapping(value = "/record/index", method = RequestMethod.POST)
	public String indexAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errorList = new ArrayList<String>();
		String errorMessage = "";
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						RecordPrepared record = recordService.getRecordPreparedById(Integer.parseInt(id));
						if (ACTIONS.RMT_PRINT_WELCOME_LETTER.name.equalsIgnoreCase(btnSubmit)) {
							// TODO connect to Print server to print document
							// welcome letter.
						} else if (ACTIONS.RMT_MARK_DONE.name.equalsIgnoreCase(btnSubmit)) {
							errorMessage = recordService.callInvolve(request, 
									ACTIONS.RMT_MARK_DONE.toString(),
									username, STAGE.RMT_RECORD.getName(),
									Integer.parseInt(id));
						}
						
						if(!CommonUtils.isNullOrEmpty(errorMessage) && record != null){
							errorList.add("Agreement No:  " + record.getAgreementno() + " has error: " + errorMessage);
						}
					}

				}
				if (errorList.size() > 0) {
					model.addAttribute("errorList", errorList);
					return index(model, request, response);
				}
			} catch (Exception e) {
				logger.error("indexAction has error message=" + e.getMessage());
			}
			return "redirect:/record/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/index.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordPrepared> list = recordService.getAllRecordPrepareds_ACL(username,
					STAGE.RMT_RECORD.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RMTRecordList";
		}
	}

	@RequestMapping(value = "/record/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		RecordPrepared record = recordService.getRecordPreparedById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Prepare ******/
	
	/****** Loan Kit Ready List (STAGE = RMT_RECORD/RMT_MARK_DONE) ******/
	@RequestMapping(value = "/record/rd", method = RequestMethod.GET)
	public String rd(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordReady> list = recordService.getAllRecordReadys_ACL(username,
				STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RMTRecordRDList";
	}

	@RequestMapping(value = "/record/rd", method = RequestMethod.POST)
	public String rdAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "note", required = false) String note,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String strId : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						int id = Integer.parseInt(strId);
						RecordReady record = recordService.getRecordReadyById(id);
						String sendToBranch = record.getSendToBranch();
						if ("Send(Auto)".equalsIgnoreCase(btnSubmit) && !CommonUtils.isNullOrEmpty(sendToBranch)
								|| ACTIONS.RMT_CS_BRANCH.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_CS_BRANCH.toString(),
									username,STAGE.RMT_RECORD_RMT_MARK_DONE.getName() , id, address);
						}else if ("Send(Auto)".equalsIgnoreCase(btnSubmit) && CommonUtils.isNullOrEmpty(sendToBranch)
								|| ACTIONS.RMT_CS_POST.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), id, address);
						}else if (ACTIONS.MARK_AS_SEND_TO_BRANCH.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.MARK_AS_SEND_TO_BRANCH.toString(), username,
									STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), id, note + " ");
						}else if (ACTIONS.RMT_CS_NOT_SEND.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_CS_NOT_SEND.toString(), username,
									STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), id);
						}else if (ACTIONS.RMT_CS_PENDING.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_CS_PENDING.toString(), username,
									STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), id);
						}
						
					}
				}
			} catch (Exception e) {
				logger.error("rdAction has error message=" + e.getMessage());
			}
			return "redirect:/record/rd.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/rd.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordReady> list = recordService.getAllRecordReadys_ACL(username,
					STAGE.RMT_RECORD_RMT_MARK_DONE.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RMTRecordRDList";
		}
	}

	@RequestMapping(value = "/record/detail_rd", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRd(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		RecordReady record = recordService.getRecordReadyById(id);
		model.addAttribute("record", record);
		return model;
	}
	
	/****** END Loan Kit Ready List (STAGE = RMT_RECORD/RMT_MARK_DONE) ******/
	
	/****** Loan Kit Not Send (STAGE = RMT_RECORD/NOT_SEND) ******/
	@RequestMapping(value = "/record/notsend", method = RequestMethod.GET)
	public String notSend(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordNotSend> list = recordService.getAllRecordNotSends_ACL(username,
				STAGE.RMT_RECORD_NOT_SEND.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordNotSendList";
	}

	@RequestMapping(value = "/record/notsend", method = RequestMethod.POST)
	public String notSendAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if (ACTIONS.RMT_MARK_RE_READY.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_MARK_RE_READY.toString(),
									username, STAGE.RMT_RECORD_NOT_SEND.getName(),	Integer.parseInt(id));
						}
					}
				}
			} catch (Exception e) {
				logger.error("notSendAction has error message=" + e.getMessage());
			}
			return "redirect:/record/notsend.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/notsend.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordNotSend> list = recordService.getAllRecordNotSends_ACL(username,
					STAGE.RMT_RECORD_NOT_SEND.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordNotSendList";
		}
	}

	@RequestMapping(value = "/record/detail_notsend", method = RequestMethod.GET)
	@ResponseBody
	public Model viewNotSendDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordNotSend record = recordService.getRecordNotSendById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Not Send ******/
	
	/****** Loan Kit Pending (STAGE = RMT_RECORD/PENDING) ******/
	@RequestMapping(value = "/record/pending", method = RequestMethod.GET)
	public String pending(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordPending> list = recordService.getAllRecordPendings_ACL(username,
				STAGE.RMT_RECORD_PENDING.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordPendingList";
	}

	@RequestMapping(value = "/record/pending", method = RequestMethod.POST)
	public String PendingAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			@RequestParam(value = "address", required = false) String address,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		String btnSubmit = request.getParameter("btnSubmit");
		if (btnSubmit != null) {
			try {
				for (String id : ids) {
					if (ACTIONS.RMT_CS_POST.getName().equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_CS_POST.toString(),
									username, STAGE.RMT_RECORD_PENDING.getName(), Integer.parseInt(id), address);
					} else if (ACTIONS.RMT_CS_BRANCH.getName().equalsIgnoreCase(btnSubmit)) {
						recordService.callInvolve(request, ACTIONS.RMT_CS_BRANCH.toString(),
								username, STAGE.RMT_RECORD_PENDING.getName(), Integer.parseInt(id), address);
					}
				}
			} catch (Exception e) {
				logger.error("PendingAction has error message=" + e.getMessage());
			}
			return "redirect:/record/pending.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/pending.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordPending> list = recordService.getAllRecordPendings_ACL(username,
					STAGE.RMT_RECORD_PENDING.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordPendingList";
		}
	}

	@RequestMapping(value = "/record/detail_pending", method = RequestMethod.GET)
	@ResponseBody
	public Model viewPendingDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordPending record = recordService.getRecordPendingById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Pending ******/
	
	/****** RMT Record By Post Office List (STAGE = RMT_RECORD/RMT_CS_POST) ******/
	@RequestMapping(value = "/po/index", method = RequestMethod.GET)
	public String postOfficeList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		/*int page =1;
		String pageStr = request.getParameter("d-49216-p");
		if(pageStr != null)
		{
			page= Integer.parseInt(pageStr);
		}
		int startRow = 1;
		*/
		
		
		
		List<RecordPO> list = recordService.getAllRecordPOs_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_POST.getName(), filters);
		model.addAttribute(new UploadItem());
		model.addAttribute(form);
		model.addAttribute("recordList", list); 
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordPOList";
	}

	@RequestMapping(value = "/po/index", method = RequestMethod.POST)
	public String postOfficeListAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			UploadItem uploadItem, 
			BindingResult result,
			@RequestParam(value = "billNo", required = false) String billNo,
			@RequestParam(value = "dateReturn", required = false) String dateReturn,
			@RequestParam(value = "reason", required = false) String reason,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				UploadData ud = null;
				for (String strId : ids) {
					//Save into table RM_UPLOAD_DATA
					Integer id = Integer.parseInt(strId);
					RecordPO record = recordService.getRecordPOById(id);
					ud = new UploadData();
					ud.setRecordID(id);
					ud.setLoanID(record.getAgreementno());									
					ud.setCustomerName(record.getCustomername());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					String btnSubmit = request.getParameter("btnSubmit");
					if ("Post return".equalsIgnoreCase(btnSubmit)) {
						 recordService.callInvolve(request, ACTIONS.RMT_CS_POST_RETURN.toString(), username, STAGE.RMT_RECORD_RMT_CS_POST.getName(), id, dateReturn, reason);
						 try{
							 ud.setReturnDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(dateReturn));
						 } catch (Exception e) {
						 }
						 ud.setReasonPost(reason);
						 ud.setUploadType(Constant.UPLOAD_TYPES.POST_RETURN.toString());
					} else if (ACTIONS.RMT_UPDATE_PO_BILL_NO.name.equalsIgnoreCase(btnSubmit)) {
							recordService.callInvolve(request, ACTIONS.RMT_UPDATE_PO_BILL_NO.toString(), username, STAGE.RMT_RECORD_RMT_CS_POST.getName(), id, billNo);
							ud.setBillNo(billNo);
							ud.setUploadType(Constant.UPLOAD_TYPES.BILL_NO.toString());
					}
					ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					recordService.saveUploadData(ud);
				}
			} catch (Exception e) {
				logger.error("postOfficeListAction has error message=" + e.getMessage());
			}
			return "redirect:/po/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/po/index.html";
		} else if (request.getParameter("btnUpload") != null) {
			return uploadFile(form, uploadItem, result, model, STAGE.RMT_RECORD_RMT_CS_POST.getName(), 
					request, response);
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordPO> list = recordService.getAllRecordPOs_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_POST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute(new UploadItem());
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordPOList";
		}
	}

	@RequestMapping(value = "/po/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model postOfficeDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		RecordPO record = recordService.getRecordPOById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END RMT Record By Post List (STAGE = RMT_RECORD/RMT_CS_POST) ******/
	
	/****** Loan Kit Return From Post Office (STAGE = RMT_RECORD/RMT_CS_POST_RETURN) ******/
	@RequestMapping(value = "/po/return", method = RequestMethod.GET)
	public String postOfficeReturn(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordPOReturn> list = recordService.getAllRecordPOReturns_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_POST_RETURN.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordPOReturnList";
	}

	@RequestMapping(value = "/po/return", method = RequestMethod.POST)
	public String postOfficeReturnAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					recordService.callInvolve(request, ACTIONS.RMT_CS_RECIEVE_FROM_POST_RETURN.toString(),
							username, STAGE.RMT_RECORD_RMT_CS_POST_RETURN.getName(), Integer.parseInt(id));
				}
			} catch (Exception e) {
				logger.error("postOfficeReturnAction has error message=" + e.getMessage());
			}
			return "redirect:/po/return.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/po/return.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordPOReturn> list = recordService.getAllRecordPOReturns_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_POST_RETURN.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordPOReturnList";
		}
	}

	@RequestMapping(value = "/po/return_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model postOfficeReturnDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordPOReturn record = recordService.getRecordPOReturnById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Return From Post Office (STAGE = RMT_RECORD/RMT_CS_POST_RETURN) ******/
	
	public String uploadFile(FilterRecordForm form, UploadItem uploadItem,
			BindingResult result, Model model, String stage, HttpServletRequest request,
			HttpServletResponse response) {
		// Validate
		this.fileUploadValidator.validate(uploadItem, result);

		if (result.hasErrors()) {
			Map<String, Object> filters = buildFilter(form);
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			List<RecordPO> list = recordService.getAllRecordPOs_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_POST.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute(uploadItem);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordPOList";
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
			RecordPO record ;
			String loanID = null, returnDate = null, customerName = null, reason = null, billNo = null
					, receiver = null,sentDate=null,numUpload=null;
			Date deliveredDate =null;
			UploadData ud = null;
			if (request.getParameter("btnUpload").equals("Upload Bill No")) {
				// Loop over first 15 column and lines
				String formatDate = "MM/dd/yy hh:mm";
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 0 && StringUtils.isEmpty(cols[0])) {
						continue;
					}
					if (cols.length > 0) {
						billNo= CommonUtils.formatStringCompound(cols[0]).trim();
					}
					if (cols.length > 1) {
						loanID= CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						customerName= CommonUtils.formatStringCompound(cols[2]).trim();
					}
					if (cols.length > 3) {						
						deliveredDate = DateUtils.stringToDate(CommonUtils.formatStringCompound(cols[3]).trim(), formatDate);			
					}
					if (cols.length > 4) {
						receiver= CommonUtils.formatStringCompound(cols[4]).trim();
					}					
					
					//Save into table RM_UPLOAD_DATA
					ud = new UploadData();					
					ud.setLoanID(loanID);
					ud.setBillNo(billNo);					
					ud.setCustomerName(customerName);
					ud.setDeliveredDate(deliveredDate);
					ud.setReceiver(receiver);					
					ud.setUploadType(UPLOAD_TYPES.BILL_NO.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					
					record = recordService.getRecordPOByLoanIdAndName(
							loanID, customerName);
					if (record != null) {
						ud.setRecordID(record.getId());
						errorMessage = recordService.callInvolve(request, ACTIONS.RMT_UPDATE_PO_BILL_NO.toString(),
								username, stage, record.getId(), billNo, DateUtils.dateToString(deliveredDate, "dd/MM/yy hh:mm"), receiver);
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
					recordService.saveUploadData(ud);
				}
			} else if (request.getParameter("btnUpload").equals("Upload Post Return")){
				
				for (int i = 1; i < allRows.size(); i++) {
					errorMessage = "";
					String[] cols = allRows.get(i);
					if (cols.length > 5) {
						numUpload = CommonUtils.formatStringCompound(cols[5]).trim();
						if(StringUtils.isEmpty(numUpload)) continue;
					}
					if (cols.length > 1 && StringUtils.isEmpty(cols[1])) {
						continue;
					}
					if (cols.length > 0) {
						returnDate = CommonUtils.formatStringCompound(cols[0]).trim();
					}
					if (cols.length > 1) {
						loanID =CommonUtils.formatStringCompound(cols[1]).trim();
					}
					if (cols.length > 2) {
						customerName = CommonUtils.formatStringCompound(cols[2].trim());
					}
					if (cols.length > 3) {
						reason = CommonUtils.formatStringCompound(cols[3]).trim();
					}
					if (cols.length > 4) {
						sentDate = CommonUtils.formatStringCompound(cols[4]).trim();
					}
					// Save into table RM_UPLOAD_DATA
					ud = new UploadData();
					ud.setLoanID(loanID);
					ud.setCustomerName(customerName);
					try {
						ud.setReturnDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(returnDate));
					}catch (Exception e) {
						
					}
					try {
						ud.setDeliveredDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(sentDate));
					} catch (Exception e) {
						
					}
					ud.setReasonPost(reason);	
					ud.setUploadType(UPLOAD_TYPES.POST_RETURN.toString());
					ud.setUploadDate(Calendar.getInstance().getTime());
					ud.setUploadUser(username);
					Integer recordId = null;
					if(StringUtils.isNotEmpty(numUpload.trim()) && Integer.valueOf(numUpload.trim()) > 1){
						Record rmRecord = recordService.getRecordByLoanId(loanID);
						if(rmRecord != null) recordId = rmRecord.getId();
					}else{ // == 1 check po return
						record = recordService.getRecordPOByLoanIdAndName(
								loanID, customerName);
						if(record != null)recordId = record.getId();
					}
					if (recordId != null) {
						if(ud.getReturnDate() != null) {
							ud.setRecordID(recordId);
							errorMessage = recordService.callInvolve(request, ACTIONS.RMT_CS_POST_RETURN.toString(),username, stage,recordId, returnDate, reason);
						} else {
							errorMessage = "Return Date is invalids.";
						}
					} else {
						errorMessage = "Data not found.";
					}
					if (!CommonUtils.isNullOrEmpty(errorMessage)) {
						errorList.add("Fail to upload Loan ID " + cols[1]
								+ "\t" + cols[2] + ". Reason: " + errorMessage);
						ud.setErrorMessage(errorMessage);
						ud.setUploadStatus(Constant.UPLOAD_STATUS.FAIL.toString());
					} else {
						successRow++;
						ud.setUploadStatus(Constant.UPLOAD_STATUS.SUCCESS.toString());
					}
					recordService.saveUploadData(ud);
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
		return postOfficeList(model, request, response);// "RecordPOList";
	}
	
	/*
	@RequestMapping(value = "/record/logs", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesDto<LogTransaction> getLogTransactions(
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam(required=false) Integer sSearch,
			@RequestParam Integer recordId,
			@RequestParam String logType,
            Model model, HttpServletRequest request, HttpServletResponse response)
	{
		DataTablesDto<LogTransaction> dt = new DataTablesDto<LogTransaction>();
		List<LogTransaction> logTransactions = recordService
				.getLogTransactions(recordId, logType);
		for (LogTransaction logTransaction : logTransactions) {
			try {
				logTransaction.setAction(Constant.ACTIONS.valueOf(logTransaction.getAction()).getName());
			} catch (Exception ex) {
				logTransaction.setAction(Constant.ACTIONS.NOT_DEFINE.getName());
			}			
		}
		dt.setAaData(logTransactions); 
		dt.setiTotalRecords(logTransactions.size());
		dt.setiTotalDisplayRecords(logTransactions.size());
		dt.setsEcho(sEcho);
		model.addAttribute("recordId", recordId);
		return dt;
	}
	*/
	/****** Loan Documents Waiting CS (STAGE = RMT_RECORD/CS_WAITING) ******/
	@RequestMapping(value = "/record/waitingcs", method = RequestMethod.GET)
	public String waiting(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordCSWaiting> list = recordCSService.getAllRecordCSWaitings_ACL(username,
				STAGE.RMT_RECORD_CS_WAITING.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordCSWaitingList";
	}

	@RequestMapping(value = "/record/waitingcs", method = RequestMethod.POST)
	public String waitingAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						/*if (ACTIONS.SCAN_TO_CM.name.equalsIgnoreCase(btnSubmit)) {
							recordCSService.callInvolve(
									ACTIONS.SCAN_TO_CM.toString(), username,
									STAGE2.RMT_SCAN_CM.getName(), Integer.parseInt(id));
						}*/
					}
				}
			} catch (Exception e) {
				logger.error("waitingAction has error message=" + e.getMessage());
			}
			return "redirect:/record/waitingcs.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/waitingcs.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordCSWaiting> list = recordCSService.getAllRecordCSWaitings_ACL(username,
					STAGE.RMT_RECORD_CS_WAITING.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordCSWaitingList";
		}
	}

	@RequestMapping(value = "/record/detail_waitingcs", method = RequestMethod.GET)
	@ResponseBody
	public Model viewWaitingDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordCSWaiting record = recordCSService.getRecordCSWaitingById(id);
		model.addAttribute("record", record);
		return model;
	}
	/****** END Loan Documents Waiting CS ******/
	
	/****** Loan Documents Completed CS (STAGE = RMT_RECORD/CS_COMPLETED) ******/
	@RequestMapping(value = "/record/completedcs", method = RequestMethod.GET)
	public String completed(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordCSCompleted> list = recordCSService.getAllRecordCSCompleteds_ACL(username,
				STAGE.RMT_RECORD_CS_COMPLETED.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordCSCompletedList";
	}

	@RequestMapping(value = "/record/completedcs", method = RequestMethod.POST)
	public String completedAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if (ACTIONS.RMT_MARK_DONE.name.equalsIgnoreCase(btnSubmit)) {
							recordCSService.callInvolve(request, 
									ACTIONS.RMT_MARK_DONE.toString(), username,
									STAGE.RMT_RECORD_CS_COMPLETED.getName(), Integer.parseInt(id));
						}
					}
				}
			} catch (Exception e) {
				logger.error("completedAction has error message=" + e.getMessage());
			}
			return "redirect:/record/completedcs.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/record/completedcs.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordCSCompleted> list = recordCSService.getAllRecordCSCompleteds_ACL(username,
					STAGE.RMT_RECORD_CS_COMPLETED.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordCSCompletedList";
		}
	}

	@RequestMapping(value = "/record/detail_completedcs", method = RequestMethod.GET)
	@ResponseBody
	public Model completeDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordCSCompleted record = recordCSService.getRecordCSCompletedById(id);
		model.addAttribute("record", record);
		return model;
	}
	/****** END Loan Documents CS Completed (STAGE = RMT_RECORD/CS_COMPLETED) ******/

	
	
}
