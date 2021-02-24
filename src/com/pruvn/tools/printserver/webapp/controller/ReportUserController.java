package com.pruvn.tools.printserver.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.ReportUserForm;
import com.pruvn.tools.utils.PagingInfo;
import com.pruvn.tools.utils.ParameterApplication;
import com.pruvn.tools.utils.ReportUtils;

@Controller
public class ReportUserController {
	private static Log logger = LogFactory.getLog(ReportUserController.class);
	private List<Usermaster> lstBySearch;
	private List<Usermaster> lstBySearchReport;
	private UsermasterDAO usermasterDAO;
	private Date todatecurrent;
	private Date fromdatecurent;
	private int statuscurrent;
	private int numOfRecords = 0;
	private String messreuslt="";
	@RequestMapping(value="/reportUser.html",method=RequestMethod.GET)
	public String viewReportUser(Model model){
		model.addAttribute("liststatus", ParameterApplication.class);
		model.addAttribute("reportUserSearch", new ReportUserForm());
		model.addAttribute("Usermasterlist",null);
		return "usermanager/reportUser";
	}
	
	@RequestMapping(value="/searchreportUser.html",method=RequestMethod.GET)
	public String searchReportUser(@ModelAttribute("reportUserSearch") ReportUserForm reportform,Model model,HttpSession session,@RequestParam("fromdate") String from,@RequestParam("todate") String to,@RequestParam("status") int status,Integer pageNum, Integer records){
		 PagingInfo paging = new PagingInfo();
	        if((pageNum != null) && (pageNum > 0)) {
	            paging.setCurrentPage(pageNum);
	        }
	        if((records != null) && (records > 0)) {
	            paging.setItemsPerPage(records);
	        }
	        paging.setTotalItems(numOfRecords);
		lstBySearch = usermasterDAO.findByReportPagination(paging.getCurrentPage(), paging.getItemsPerPage(),todatecurrent,fromdatecurent,statuscurrent);
		session.setAttribute("__SEARCH__", "/searchreportUser.html?fromdate="+from+"&todate="+to+"&status"+status);
		model.addAttribute("pageUrl", "/searchreportUser.html?fromdate="+from+"&todate="+to+"&status="+status);
		model.addAttribute("liststatus", ParameterApplication.class);
		model.addAttribute("reportUserSearch", new ReportUserForm());
		model.addAttribute("Usermasterlist",lstBySearch);
		model.addAttribute("message_result","Result search: "+messreuslt);
		model.addAttribute("pagingInfo",paging);
		return "usermanager/reportUser";
	}
	@RequestMapping(value="/searchreportUser.html",method=RequestMethod.POST)
	public String searchReportUser(HttpSession session,Integer pageNum, Integer records,@ModelAttribute("reportUserSearch") ReportUserForm reportform,
			BindingResult result,Model model){
		lstBySearch = usermasterDAO.findByReport(reportform.getTodateForm(),reportform.getFromdateForm(),reportform.getStatusForm());
		lstBySearchReport = lstBySearch;
		 PagingInfo paging = new PagingInfo();
		
		 numOfRecords = lstBySearch.size();
        if((pageNum != null) && (pageNum > 0)) {
            paging.setCurrentPage(pageNum);
        }
        if((records != null) && (records > 0)) {
            paging.setItemsPerPage(records);
        }
        paging.setTotalItems(numOfRecords);
        lstBySearch = usermasterDAO.findByReportPagination(paging.getCurrentPage(), paging.getItemsPerPage(),reportform.getTodateForm(),reportform.getFromdateForm(),reportform.getStatusForm());
		todatecurrent=reportform.getTodateForm();
		fromdatecurent=reportform.getFromdateForm();
		statuscurrent=reportform.getStatusForm();
		SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy");
		String paramfromdate=(fromdatecurent!=null ? simple.format(fromdatecurent) : "");
		String paramtodate=(todatecurrent!=null ? simple.format(todatecurrent) : "");
		messreuslt=(fromdatecurent!=null ? " From - "+simple.format(fromdatecurent)+";" : " ")+ (todatecurrent!=null ? " To - "+simple.format(todatecurrent)+";" :"" )+ " Status - " + ParameterApplication.convertStatus(statuscurrent);
		session.setAttribute("__SEARCH__", "/searchreportUser.html?fromdate="+paramfromdate+"&todate="+paramtodate+"&status"+statuscurrent);
		model.addAttribute("pageUrl", "/searchreportUser.html?fromdate="+paramfromdate+"&todate="+paramtodate+"&status="+statuscurrent);
		model.addAttribute("Usermasterlist",lstBySearch);
		model.addAttribute("message_result","Result search: "+messreuslt);
		model.addAttribute("liststatus", ParameterApplication.class);
		model.addAttribute("reportUserSearch", new ReportUserForm());
		model.addAttribute("pagingInfo",paging);
		return "usermanager/reportUser";
	}
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	 @RequestMapping(value="/getMyReport.html", method=RequestMethod.POST)
     public String runReport(@ModelAttribute("reportUserSearch") ReportUserForm reportform,HttpServletRequest request,HttpServletResponse response,Model model)
     {
	     
//         InputStream is = request.getSession().getServletContext().getResourceAsStream("/reports/templace/danhsachusermaster.jrxml");
         String inputfile="reports/templace/danhsachusermaster.jrxml";
         String rootpath = request.getSession().getServletContext().getRealPath("/");
         SimpleDateFormat simple=new SimpleDateFormat("dd-MM-yyyy");
         String code =RandomStringUtils.randomAlphanumeric(5);
         String namefile=(fromdatecurent!=null ? simple.format(fromdatecurent) : "")+"dsusermaster"+(todatecurrent!=null ?simple.format(todatecurrent) :"")+code;
		 Map paramMap = new HashMap();
         paramMap.put("fromdate", fromdatecurent);
         paramMap.put("todate", todatecurrent);
         paramMap.put("tinhtrang", ParameterApplication.convertStatus(statuscurrent));
         ReportUtils.xuatReportWithCollectionDataSource(inputfile, paramMap,rootpath, "pdf", namefile, lstBySearchReport);
	   	model.addAttribute("backUrl", "reportUser.html");
 		model.addAttribute("filename",namefile);
         return "viewReport";
     }

	public static Log getLogger() {
		return logger;
	}
	public static void setLogger(Log logger) {
		ReportUserController.logger = logger;
	}
	public List<Usermaster> getLstBySearch() {
		return lstBySearch;
	}
	public void setLstBySearch(List<Usermaster> lstBySearch) {
		this.lstBySearch = lstBySearch;
	}
	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}
	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}
	public Date getTodatecurrent() {
		return todatecurrent;
	}
	public void setTodatecurrent(Date todatecurrent) {
		this.todatecurrent = todatecurrent;
	}
	public Date getFromdatecuurent() {
		return fromdatecurent;
	}
	public void setFromdatecuurent(Date fromdatecuurent) {
		this.fromdatecurent = fromdatecuurent;
	}
	public int getStatuscurrent() {
		return statuscurrent;
	}
	public void setStatuscurrent(int statuscurrent) {
		this.statuscurrent = statuscurrent;
	}

	public List<Usermaster> getLstBySearchReport() {
		return lstBySearchReport;
	}

	public void setLstBySearchReport(List<Usermaster> lstBySearchReport) {
		this.lstBySearchReport = lstBySearchReport;
	}


	
	
}
