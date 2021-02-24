package com.pruvn.rms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.rms.domain.AutoDebit;
import com.pruvn.rms.domain.AutoDebitHistory;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.model.AutoDebitForm;
import com.pruvn.rms.service.AutoDebitHistoryService;
import com.pruvn.rms.service.AutoDebitService;
import com.pruvn.rms.service.Cm_doc_printed_trialService;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.MailUtil;

@Controller
public class AutoDebitController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(AutoDebitController.class);

	private AutoDebitService autoDebitService;
	private AutoDebitHistoryService autoDebitHistoryService;
	private Cm_doc_printed_trialService cm_doc_printed_trialService;
	private UserMService userMService;
	private UserLogService userLogService;
	

	public UserMService getUserMService() {
		return userMService;
	}
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}
	public Cm_doc_printed_trialService getCm_doc_printed_trialService() {
		return cm_doc_printed_trialService;
	}
	@Autowired
	public void setCm_doc_printed_trialService(
			Cm_doc_printed_trialService cm_doc_printed_trialService) {
		this.cm_doc_printed_trialService = cm_doc_printed_trialService;
	}
	public AutoDebitService getAutoDebitService() {
		return autoDebitService;
	}
	@Autowired
	public void setAutoDebitService(AutoDebitService autoDebitService) {
		this.autoDebitService = autoDebitService;
	}
	

	public AutoDebitHistoryService getAutoDebitHistoryService() {
		return autoDebitHistoryService;
	}
	@Autowired
	public void setAutoDebitHistoryService(
			AutoDebitHistoryService autoDebitHistoryService) {
		this.autoDebitHistoryService = autoDebitHistoryService;
	}
	
	public UserLogService getUserLogService() {
		return userLogService;
	}
	@Autowired
	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}
	/****** Disbursal Loan (STAGE = FRESH_LOAN) 
	 * @throws IOException 
	 * @throws UserException ******/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/autodebit/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, UserException {
		logger.debug("reach RecordController$autodebit...");
		
		AutoDebitForm form = createFilterADForm(request);
		Map<String, Object> filters = buildADFilter(form);
		filters.put("status", "None");		
		int page =0;
		String pageStr = request.getParameter("d-49216-p");
		if(pageStr== null)
		{
			page =1;
		}
		else
		{
			page= Integer.parseInt(pageStr);
		}
		List<AutoDebit> banklist = autoDebitService.getlistBank();
		List<AutoDebit> branchlist = autoDebitService.getlistBranch();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserM userM  = userMService.getUserByUserName(username);
		List<GroupM> groupMs= userMService.getAllGroupByUser(userM);
		int flag = 0;
		for(int i= 0; i<groupMs.size(); i++)
		{
			if(groupMs.get(i).getGroupcode().equals("ROLE_AD_ACCEPT"))
			{
				flag=1;
				filters.put("branchDesc", "Direct To Home");
			}
		}
		if(flag==0)
		{
			model.addAttribute("branchSend", "1");
		}
		List<AutoDebit> list = autoDebitService.getAllRecords_AD(username, "", filters);
		int sizelist = list.size();
	//	listEnd.addAll(list);
		ArrayList<String> valueReceivelist = new ArrayList<String>(); 
		ArrayList<String> valueReturnlist = new ArrayList<String>(); 
		InputStream input = null;
		Properties props = System.getProperties(); 
		input = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		valueReceivelist.add(props.getProperty("receive.do"));
		valueReceivelist.add(props.getProperty("receive.not_first_due"));
		valueReturnlist.add(props.getProperty("return.wrong_period"));
		valueReturnlist.add(props.getProperty("return.wrong_info"));
		Boolean check;
		int startitem = 0;
		int enditem = 0;
		if (filters != null) {
			Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
			String key, value;
			while (im.hasNext()) {
				Entry<String, Object> me = (Entry<String, Object>) im.next();
				key = me.getKey();
				value = me.getValue().toString();
				if ("PageSize".equals(key)) {
					int pagesize = Integer.parseInt(value);
					if(pagesize==0)
					{
						enditem=sizelist-1;
					}
					else
					{
						startitem = page*pagesize-pagesize;
						enditem = page*pagesize-1;
						if(enditem>sizelist)
						{
							enditem=sizelist-1;
						}
					}
				}
			}
		}
		/*for(int i=startitem; i<=enditem; i++)
		{
			check = cm_doc_printed_trialService.checkCM(list.get(i).getLoanNo());
			if(check==true)
			{
				list.get(i).setCm("YES");
			}
			else
			{
				list.get(i).setCm("NO");
			}
		}	*/
		
		model.addAttribute(form);
		model.addAttribute("bank", form.getBankCode());
		model.addAttribute("branch", form.getBranchDesc());
		model.addAttribute("banklist", banklist);
		model.addAttribute("branchlist", branchlist);
		model.addAttribute("recordList", list);
		model.addAttribute("valueReceivelist", valueReceivelist);
		model.addAttribute("valueReturnlist", valueReturnlist);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : sizelist);
		return "AutodebitList";
	}
	
	@Transactional
	@RequestMapping(value = "/autodebit/index", method = RequestMethod.POST)
	public String action(@ModelAttribute("autoDebitForm") AutoDebitForm form, BindingResult result, 
			@RequestParam(value = "note", required = false) String note, Model model,
			HttpServletRequest request,	HttpServletResponse response) throws IOException, UserException {
		logger.debug("reach RecordController$autodebit...");
		String[] ids = request.getParameterValues("checkbox");
		InputStream input = null;
		Properties props = System.getProperties(); 
		input = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		if (request.getParameter("btnSubmit") != null) {
			String btnSubmit = request.getParameter("btnSubmit");

			//Save log into User Log
			UserLog userlog= new UserLog();
			userlog.setLogDate(new Date());
			userlog.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());    	
			userlog.setRemoteIP(request.getRemoteAddr());
			userlog.setSession(request.getSession().getId());    	
			userlog.setLogType(LOG_TYPE.AUTO_DEBIT.toString());
			userlog.setActivity(btnSubmit);
			userlog.setInput("Record IDS: " + ids + "; Note: " + note);
			userlog.setStatus(LOG_STATUS.SUCCESS.toString());
			userLogService.save(userlog);
			if(!CommonUtils.isNullOrEmpty(btnSubmit)){
				for (String id : ids) {
					try {
						java.util.Date date= new java.util.Date();
						if ("Send to OP".equalsIgnoreCase(btnSubmit)) {
							AutoDebit autoDebit= autoDebitService.findById(Long.parseLong(id));
							autoDebit.setStatus(props.getProperty("send"));
							autoDebit.setSendDate(new Timestamp(date.getTime()));
							autoDebit.setSendername(SecurityContextHolder.getContext().getAuthentication().getName());
							autoDebitService.saveOrUpdate(autoDebit);
							AutoDebitHistory autoDebitHistory = new AutoDebitHistory();
							autoDebitHistory.setUserAction(SecurityContextHolder.getContext().getAuthentication().getName());
							
							autoDebitHistory.setDateAction(new Timestamp(date.getTime()));
							autoDebitHistory.setIdRecordAction(Long.parseLong(id));
							autoDebitHistory.setAction(props.getProperty("send"));
							autoDebitHistoryService.saveOrUpdate(autoDebitHistory);
						} else if ("Receive with reason".equalsIgnoreCase(btnSubmit)) {
							AutoDebit autoDebit= autoDebitService.findById(Long.parseLong(id));
							autoDebit.setStatus(props.getProperty("receive"));
							autoDebit.setReason(note);
							autoDebit.setReceiveOrReturnDate(new Timestamp(date.getTime()));
							autoDebitService.saveOrUpdate(autoDebit);
							AutoDebitHistory autoDebitHistory = new AutoDebitHistory();
							autoDebitHistory.setUserAction(SecurityContextHolder.getContext().getAuthentication().getName());							
							autoDebitHistory.setDateAction(new Timestamp(date.getTime()));
							autoDebitHistory.setIdRecordAction(Long.parseLong(id));
							autoDebitHistory.setAction(props.getProperty("receive")+ " : " + note);
							autoDebitHistoryService.saveOrUpdate(autoDebitHistory);
						} else if ("Return with reason".equalsIgnoreCase(btnSubmit)) {
							AutoDebit autoDebit= autoDebitService.findById(Long.parseLong(id));
							autoDebit.setStatus(props.getProperty("return"));
							autoDebit.setReason(note);
							autoDebit.setReceiveOrReturnDate(new Timestamp(date.getTime()));
							autoDebitService.saveOrUpdate(autoDebit);
							AutoDebitHistory autoDebitHistory = new AutoDebitHistory();
							autoDebitHistory.setUserAction(SecurityContextHolder.getContext().getAuthentication().getName());							
							autoDebitHistory.setDateAction(new Timestamp(date.getTime()));
							autoDebitHistory.setIdRecordAction(Long.parseLong(id));
							autoDebitHistory.setAction(props.getProperty("return")+ " : " + note);
							autoDebitHistoryService.saveOrUpdate(autoDebitHistory);
						}
					} catch (Exception e) {

					}
				}
			}
			return "redirect:/autodebit/index.html";
		}else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/autodebit/index.html";
		} else {
			Map<String, Object> filters = buildADFilter(form);
			List<AutoDebit> banklist = autoDebitService.getlistBank();
			List<AutoDebit> branchlist = autoDebitService.getlistBranch();
			
			int page =0;
			String pageStr = request.getParameter("d-49216-p");
			if(pageStr== null)
			{
				page =1;
			}
			else
			{
				page= Integer.parseInt(pageStr);
			}
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			UserM userM  = userMService.getUserByUserName(username);
			List<GroupM> groupMs= userMService.getAllGroupByUser(userM);
			int flag = 0;
			for(int i= 0; i<groupMs.size(); i++)
			{
				if(groupMs.get(i).getGroupcode().equals("ROLE_AD_ACCEPT"))
				{
					flag=1;					
				}
			}
			if(flag==0)
			{
				model.addAttribute("branchSend", "1");
			}
			List<AutoDebit> list = autoDebitService.getAllRecords_AD(username, "", filters);
			int sizelist = list.size();
			ArrayList<String> valueReceivelist = new ArrayList<String>(); 
			ArrayList<String> valueReturnlist = new ArrayList<String>(); 
			input = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
			props.load(input);
			valueReceivelist.add(props.getProperty("receive.do"));
			valueReceivelist.add(props.getProperty("receive.not_first_due"));
			valueReturnlist.add(props.getProperty("return.wrong_period"));
			valueReturnlist.add(props.getProperty("return.wrong_info"));
			Boolean check;
			int startitem = 0;
			int enditem = 0;
			if (filters != null) {
				Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
				String key, value;
				while (im.hasNext()) {
					Entry<String, Object> me = (Entry<String, Object>) im.next();
					key = me.getKey();
					value = me.getValue().toString();
					if ("PageSize".equals(key)) {
						int pagesize = Integer.parseInt(value);
						if(pagesize==0)
						{
							enditem=sizelist-1;
						}
						else
						{
							startitem = page*pagesize-pagesize;
							enditem = page*pagesize-1;
							if(enditem>sizelist)
							{
								enditem=sizelist-1;
							}
						}					
					}
				}
			}			
			/*for(int i=startitem; i<=enditem; i++)
			{
				check = cm_doc_printed_trialService.checkCM(list.get(i).getLoanNo());
				if(check==true)
				{
					list.get(i).setCm("YES");
				}
				else
				{
					list.get(i).setCm("NO");
				}
			}	*/
			model.addAttribute(form);
			model.addAttribute("bank", form.getBankCode());
			model.addAttribute("branch", form.getBranchDesc());
			model.addAttribute("banklist", banklist);
			model.addAttribute("branchlist", branchlist);
			model.addAttribute("recordList", list);
			model.addAttribute("valueReceivelist", valueReceivelist);
			model.addAttribute("valueReturnlist", valueReturnlist);

			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : sizelist);
			
			return "AutodebitList";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/autodebit/report", method = RequestMethod.GET)
	public String report(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, RowsExceededException, WriteException {
		logger.debug("reach RecordController$autodebit...");
		
		AutoDebitForm form = createFilterADForm(request);
		Map<String, Object> filters = buildADFilter(form);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<AutoDebit> list = autoDebitService.getAllRecords_AD(username, "",filters);
	//	listEnd.addAll(list);
		Boolean check;
		/*for(int i=0; i<list.size(); i++)
		{
			check = cm_doc_printed_trialService.checkCM(list.get(i).getLoanNo());
			if(check==true)
			{
				list.get(i).setCm("YES");
			}
			else
			{
				list.get(i).setCm("NO");
			}
		}	*/
		
		String getPath = request.getRealPath("/");
		WritableWorkbook wworkbook;
	      wworkbook = Workbook.createWorkbook(new File(getPath+"\\WEB-INF\\templateReport\\ReportEmailEngine.xls"));
	      WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
	      Label title = new Label(0, 0, "Report Auto Debit");
	      wsheet.addCell(title);
	      Label AA = new Label(0, 2, "STT");
	      Label A = new Label(1, 2, "Bank name");
	      Label B = new Label(2, 2, "Type autodebit");
	      Label C = new Label(3, 2, "Loan no");
	      Label D = new Label(4, 2, "Customer name");
	      Label E = new Label(5, 2, "Firstdue date");
	      Label F = new Label(6, 2, "Authorized date");
	      Label G = new Label(7, 2, "Disbursal date");
	      Label H = new Label(8, 2, "RO name");
	      Label I = new Label(9, 2, "Status");
	      Label J = new Label(10, 2, "Reason");
	      Label K = new Label(11, 2, "CM");
	      Label L = new Label(12, 2, "Send date");
	      Label M = new Label(13, 2, "Receive/return date");
	      wsheet.addCell(AA); wsheet.addCell(A); wsheet.addCell(B); wsheet.addCell(C); wsheet.addCell(D); wsheet.addCell(E);
	      wsheet.addCell(F); wsheet.addCell(G); wsheet.addCell(H); wsheet.addCell(I); wsheet.addCell(J); wsheet.addCell(K);
	      wsheet.addCell(L); wsheet.addCell(M);
	      int k =3;
	      for(int i=0; i<list.size(); i++)
	      {
	    	  String firstDueDate = "";
	    	  if(list.get(i).getFirstDueDate()!=null)
	    	  {
	    		  firstDueDate = new SimpleDateFormat("dd-MM-yyyy").format( list.get(i).getFirstDueDate());
	    	  }	
	    	  String authorizedDate = "";
	    	  if(list.get(i).getAuthorizedDate()!=null)
	    	  {
	    		  authorizedDate = new SimpleDateFormat("dd-MM-yyyy").format( list.get(i).getAuthorizedDate());
	    	  }	
	    	  String disbursalDate = "";
	    	  if(list.get(i).getDisbursalDate()!=null)
	    	  {
	    		  disbursalDate = new SimpleDateFormat("dd-MM-yyyy").format( list.get(i).getDisbursalDate());
	    	  }	
	    	  String sendDate = "";
	    	  if(list.get(i).getSendDate()!=null)
	    	  {
	    		  sendDate = new SimpleDateFormat("dd-MM-yyyy").format( list.get(i).getSendDate());
	    	  }
	    	  String receiveOrReturnDate = "";
	    	  if(list.get(i).getReceiveOrReturnDate()!=null)
	    	  {
	    		  receiveOrReturnDate = new SimpleDateFormat("dd-MM-yyyy").format( list.get(i).getReceiveOrReturnDate());
	    	  }
	    	  
	    	  Label AA1 = new Label(0, k+i, i+1+"");
	    	  Label A1 = new Label(1, k+i, list.get(i).getBankName());
		      Label B1 = new Label(2, k+i, list.get(i).getTypeAutoDebit());
		      Label C1 = new Label(3, k+i, list.get(i).getLoanNo());
		      Label D1 = new Label(4, k+i, list.get(i).getCustomerName());
		      
		      Label E1 = new Label(5, k+i,firstDueDate);
		      Label F1 = new Label(6, k+i, authorizedDate);
		      Label G1 = new Label(7, k+i, disbursalDate);
		      Label H1 = new Label(8, k+i, list.get(i).getRoName());
		      Label I1 = new Label(9, k+i, list.get(i).getStatus());
		      Label J1 = new Label(10,k+i, list.get(i).getReason());
		      Label K1 = new Label(11,k+i, list.get(i).getCm());
		      Label L1 = new Label(12,k+i, sendDate);
		      Label M1 = new Label(13,k+i, receiveOrReturnDate);
		      wsheet.addCell(AA1); wsheet.addCell(A1); wsheet.addCell(B1); wsheet.addCell(C1); wsheet.addCell(D1); wsheet.addCell(E1);
		      wsheet.addCell(F1); wsheet.addCell(G1); wsheet.addCell(H1); wsheet.addCell(I1); wsheet.addCell(J1); wsheet.addCell(K1);
		      wsheet.addCell(L1); wsheet.addCell(M1);
	      }
	      wworkbook.write();
	      wworkbook.close();
	      response.setHeader("Content-disposition","attachment; filename=ReportEmailEngine.xls");
	      File xls = new File(getPath+"\\WEB-INF\\templateReport\\ReportEmailEngine.xls"); // or whatever your file is
	      FileInputStream in = new FileInputStream(xls);
	     
	      OutputStream out = response.getOutputStream();

	      byte[] buffer= new byte[8192]; // use bigger if you want
	      int length = 0;

	      while ((length = in.read(buffer)) > 0){
	           out.write(buffer, 0, length);
	      }
	      in.close();
	      out.close();
		return null;
	}
}
