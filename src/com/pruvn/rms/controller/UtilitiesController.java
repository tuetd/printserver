package com.pruvn.rms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
import org.w3c.dom.Document;

import com.lowagie.text.pdf.codec.Base64;
import com.mysql.jdbc.StringUtils;
import com.pru.common.gateway.ws.CommonGatewayPortTypeProxy;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.Icm;
import com.pruvn.rms.domain.LogTransaction;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.Screen;
import com.pruvn.rms.dto.DataTablesDto;
import com.pruvn.rms.dto.IBMCMDto;
import com.pruvn.rms.model.CreditShieldForm;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.ICMService;
import com.pruvn.rms.service.LoanService;
import com.pruvn.rms.service.UtilitiesService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.SqlConstant;

@Controller
public class UtilitiesController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(UtilitiesController.class);

	private UtilitiesService utilitiesService;
	
	private ICMService icmservice;
	public ICMService getIcmservice() {
		return icmservice;
	}
	@Autowired
	public void setIcmservice(ICMService icmservice) {
		this.icmservice = icmservice;
	}

	/**
	 * @return the UtilitiesService
	 */
	public UtilitiesService getUtilitiesService() {
		return utilitiesService;
	}

	/**
	 * @param UtilitiesService
	 *            the UtilitiesService to set
	 */
	@Autowired
	public void setUtilitiesService(UtilitiesService utilitiesService) {
		this.utilitiesService = utilitiesService;
	}
	
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


	/****** synchronizeDisbLoan ******/
	@RequestMapping(value = "/utilities/index", method = RequestMethod.GET)
	public String synchronizeDisbLoan(
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		return "SynchronizeDisbLoan";
	}

	@RequestMapping(value = "/utilities/index", method = RequestMethod.POST)
	@ResponseBody
	public String synchronizeDisbLoanAction(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		
		message = utilitiesService.synchronizeData();
		if (CommonUtils.isNullOrEmpty(message)) {
			message = "Synchronize succeffully!!!";
		}

		return message;
	}

	/****** revertLoan ******/
	@RequestMapping(value = "/utilities/revert", method = RequestMethod.GET)
	public String revertLoan(@RequestParam(value = "agreementno", required = false) String agreementno,
			@RequestParam(value = "message", required = false) String message, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		/*System.out.println(agreementno);
		Record record = new Record();
		//String message = "Fail to find record. The agreementno invalid.";
		List<Screen> listScreen = new ArrayList<Screen>();
		if(!StringUtils.isNullOrEmpty(agreementno)) {
			record = utilitiesService.searchRecordByAgreementNo(agreementno);
			if(record != null) {
				listScreen = utilitiesService.getListScreen(record.getStage());
				message = "Find a record successfully!";
			}
		}
		model.addAttribute("record", record);
		model.addAttribute("listScreen", listScreen);*/
		model.addAttribute("message", message);
		return "RevertLoan";
	}

	@RequestMapping(value = "/utilities/revert", method = RequestMethod.POST)
	public String findLoanAction(@RequestParam(value = "agreementno", required = true) String agreementno,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(agreementno);
		String message = "Fail to find loan. The agreementno invalid.";
		Record record = new Record();
		List<Screen> listScreen = new ArrayList<Screen>();
		List<Screen> listScreen2 = new ArrayList<Screen>();
		if(!StringUtils.isNullOrEmpty(agreementno)) {
			record = utilitiesService.searchRecordByAgreementNo(agreementno.trim());
			if(record != null) {				
				listScreen = utilitiesService.getListScreen(record.getStage(), "RECORD");
				listScreen2 = utilitiesService.getListScreen2(record.getStage2(), "RECORD");
			
				message = "Find Successfully!";
				model.addAttribute("record", record);
				model.addAttribute("listScreen", listScreen);
				model.addAttribute("listScreen2", listScreen2);
				Screen screen = utilitiesService.getScreen(record.getStage());
				model.addAttribute("currentScreen",screen != null ? screen : new Screen());
				Screen screen2 = utilitiesService.getScreen(record.getStage2());
				model.addAttribute("currentScreen2",screen2 != null ? screen2 : new Screen());
				
			}
		}
		model.addAttribute("agreementno", agreementno);
		model.addAttribute("message", message);
		return "RevertLoan";
	}
	 
	@RequestMapping(value = "/utilities/doRevert", method = RequestMethod.POST)
	@ResponseBody
	public String revertLoanAjax(@RequestParam(value = "agreementno", required = true) String agreementno,
			@RequestParam(value = "stage", required = true) String stage,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "note", required = true) String note,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(agreementno);
		String message = "";
		String errorMessage = "";
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Record record = utilitiesService.searchRecordByAgreementNo(agreementno);
		Screen oldScreen = null;
		String desc = "";
		if("1".equals(type)) {
			oldScreen = utilitiesService.getScreen(record.getStage());
			desc = "Revert from screen '" + oldScreen.getName() + "'";
			errorMessage = utilitiesService.callInvolve(request, ACTIONS.REVERT_LOAN.toString(), username,
				stage, record.getId(), desc, note);
		} else {
			oldScreen = utilitiesService.getScreen(record.getStage2());
			desc = "Revert from screen '" + oldScreen.getName() + "'";
			errorMessage = utilitiesService.callInvolve(request, ACTIONS.REVERT_LOAN2.toString(), username,
					stage, record.getId(), desc, note);
		}
		if(!CommonUtils.isNullOrEmpty(errorMessage)) {
			message = errorMessage;
		} else {
			Screen newScreen = utilitiesService.getScreen(stage);
			message = "Revert success the agreementno is " + agreementno + " with screen is '" + newScreen.getName() + "'";
		}
		
		return message;
	}
	
	/****** Search Loan ******/
	@RequestMapping(value = "/utilities/search", method = RequestMethod.GET)
	public String searchLoan(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		//Map<String, Object> filters = buildFilter(form);
		//List<Record> list = utilitiesService.getAllRecords(username, filters);
		List<Record> list = new ArrayList<Record>();
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "SearchLoanList";
	}

	
	@RequestMapping(value = "/utilities/icmdocument", method = RequestMethod.GET)
	public String icmdocument(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach Icmdocument$...");
		CreditShieldForm form = new CreditShieldForm();
		model.addAttribute(form);
		return "icmdocument";
	}
	@RequestMapping(value = "/utilities/icmdocument", method = RequestMethod.POST)
	public String getIcmdocument(@ModelAttribute("creditShieldForm") CreditShieldForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach Icmdocument$...");
		ServletContext context = request.getSession().getServletContext();
		String rootpath = context.getRealPath(File.separator);
		String sourceZip= rootpath + "reports" +File.separator + "ibmcm";
		String folderName= rootpath + "reports" +File.separator + "ibmcm" +File.separator;
		String targetZip= rootpath + "reports" +File.separator + "ibmcm.zip";
		Map<String, Object> filters = new HashMap<String, Object>();
		if(CommonUtils.isNullOrEmpty(form.getCallDateFrom())){
			result.reject("", "Please input Disbursal Date !");
			return "icmdocument";
		}
		filters.put("callDateFrom", form.getCallDateFrom());
        List<Record> lst = utilitiesService.getAllRecordsCreditShield(filters);
        List<Icm> ecmlst = new ArrayList<Icm>();
        for (Record record : lst) {
        	Icm icm = new Icm();
        	icm.setAppFormNo(record.getAppFormno());
        	icm.setLoanNo(record.getAgreementno());
        	icm.setArn(record.getAppIdC());
        	ecmlst.add(icm);
		}
    	Properties prop = CommonUtils.getStreamFromFileConfig(Constant.CONST_FILE_CONFIGURATION);
        if(ecmlst != null && ecmlst.size()>0){
        	icmservice.save(ecmlst);
    		//String pathUpload = prop.getProperty("path_document_cache");
    		//String host = prop.getProperty("ibmcm.server");
            List<IBMCMDto> lstIbm = icmservice.zipAgreementNo();
            String resultCode = "";
            FileOutputStream fos = null;
            CommonGatewayPortTypeProxy client = new CommonGatewayPortTypeProxy();
			
			String ws_source_channel = prop
					.getProperty("ws_cm_source_channel");
			String ws_target_channel = prop
					.getProperty("ws_cm_target_channel");
			String ws_interface_name = prop
					.getProperty("ws_cm_interface_download_document");
			String ws_signature = prop.getProperty("ws_signature");
			SimpleDateFormat simpleHeader = new SimpleDateFormat(
					Constant.CONST_API_FORMAT_DATE_HEADER);
			SimpleDateFormat refHeader = new SimpleDateFormat(
					Constant.CONST_API_FORMAT_DATE_FULL_HEADER);
			String wsResult="";
			StringBuilder header=null;
			if(lstIbm != null && lstIbm.size()>0){
				File dir = new File(sourceZip);
				File targetFileZip = new File(targetZip);
				if(targetFileZip.exists()) {
					  boolean delete= targetFileZip.delete();
				}
				if(dir.exists() && dir.isDirectory()) {
				    try {
						for(File tempFile : dir.listFiles()) {
						    boolean de = tempFile.delete();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
            	for (IBMCMDto ibmcmDto : lstIbm) {
            		//String refNo = utilitiesService.getRefNoSeq()
            		String refNo = "RMS_"+refHeader.format(new Date());
            		header = new StringBuilder("<Message><Header>");
        			header.append("<RefNo>");
        			header.append(refNo);
        			header.append("</RefNo>");
        			header.append("<SourceChannel>");
        			header.append(ws_source_channel);
        			header.append("</SourceChannel><TargetChannel>");
        			header.append(ws_target_channel);
        			header.append("</TargetChannel>");
        			header.append("<InterfaceName>");
        			header.append(ws_interface_name);
        			header.append("</InterfaceName>");
        			header.append("<ReqTxnDt>");
        			header.append(simpleHeader.format(new Date()));
        			header.append("</ReqTxnDt>");
        			header.append("<Signature>");
        			header.append(ws_signature);
        			header.append("</Signature></Header><Body>");
        			header.append("<" + Constant.CONST_WS_RESULT_DOC_REF_ID + ">");
        			header.append(ibmcmDto.getDocRefId());
        			header.append("</" + Constant.CONST_WS_RESULT_DOC_REF_ID + ">");
        			header.append("</Body></Message>");
        			try{
        			wsResult = client.getCommonGatewayPortType().process(
        					header.toString());
        			Document objXml = CommonUtils.loadXMLFromString(wsResult);
        			resultCode = CommonUtils.getTextContent(
        					objXml.getDocumentElement(),
        					Constant.CONST_WS_RESULT_CODE);
        			//String fileNameApi = CommonUtils.getTextContent(objXml.getDocumentElement(), "FileName");
        			if (!CommonUtils.isNullOrEmpty(resultCode)
        					&& Constant.CONST_WS_RESULT_CODE_SUCCESS
        							.equals(resultCode)) {
        				String fileContent = CommonUtils.getTextContent(
        						objXml.getDocumentElement(),
        						Constant.CONST_WS_RESULT_FILE_CONTENT);
        				byte[] data = Base64.decode(fileContent);
        				// cacheFile = new File(folderName + agreementNo + ".pdf");
        				File cacheFile = new File(folderName + ibmcmDto.getAgreementNo() + ".pdf");
						 if(!cacheFile.exists()) {
							/*simpleHeader = new SimpleDateFormat(
									SqlConstant.CONST_API_FORMAT_DATE_HEADER);*/
							//cacheFile = new File(folderName + ibmcmDto.getAgreementNo()+ ".pdf");
							fos = new FileOutputStream(cacheFile);
	        				fos.write(data);
	        				fos.flush();
	        				fos.close();
						 }
        				
        			 }
        		   }catch(Exception ex){
        			   logger.debug("Error create ibmcm: " + ex.getMessage());	
        		  }
				}
            	
            	
            	zipFolder(sourceZip, targetZip);
            	model.addAttribute("filename", "ibmcm.zip");
            	/*response.setContentType("application/zip");
            	response.setHeader("Content-Disposition", "attachment; filename=output.zip");
            			 
            	ServletOutputStream sos = response.getOutputStream();
            	sos.write(zip);
            	sos.flush();
            	*/
            }
        }
		return "icmdocument";
	}
	
	private static void zipFolder(String source, String target) {
		FileOutputStream fos= null;
		ZipOutputStream zipOut = null;
		try {
	         fos = new FileOutputStream(target);
	         zipOut = new ZipOutputStream(fos);
	        File fileToZip = new File(source);
	        zipFile(fileToZip, fileToZip.getName(), zipOut);
	        zipOut.flush();
	        zipOut.close();
	        fos.flush();
	        fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
		        zipOut.close();
		        fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
	public static void importData(Connection conn, List<Record> lst) throws Exception {
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement("delete from temp_opt ");
		stmt.execute();
        for (Record record : lst) {
        	try{
				String query = "insert into temp_opt (AFN,LOANNO,ARN) values(?,?,?)";
				stmt = conn.prepareStatement(query);
				stmt.setString(0, record.getAppFormno());
				stmt.setString(1, record.getAgreementno());
				stmt.setString(2, "");
				stmt.execute();
        	}
        	catch(Exception e){
        		System.out.println("Exception: getImageFromUrl() - LoanID " + record.getAgreementno() + " = " + e.getMessage());
        	}
		}
        conn.close();
	}
	@RequestMapping(value = "/utilities/search", method = RequestMethod.POST)
	public String searchLoanAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, 
			Model model,
			HttpServletRequest request,	HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/utilities/search.html";
		} else {
			// get by filter
			Map<String, Object> map = buildFilter(form);
			List<Record> list = utilitiesService.getAllRecords(map);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "SearchLoanList";
		}
	}

	

	@RequestMapping(value = "/utilities/search_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		Record record = utilitiesService.getRecordById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Search Loan ******/
	
	/****** Revert Credit Shield ******/
	@RequestMapping(value = "/utilities/revertCS", method = RequestMethod.GET)
	public String revertCS(@RequestParam(value = "agreementno", required = false) String agreementno,
			@RequestParam(value = "message", required = false) String message, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("message", message);
		return "RevertCS";
	}

	@RequestMapping(value = "/utilities/revertCS", method = RequestMethod.POST)
	public String findCSAction(@RequestParam(value = "agreementno", required = true) String agreementno,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(agreementno);
		String message = "Fail to find loan. The agreementno invalid.";
		CSRecord cs = new CSRecord();
		List<Screen> listScreen = new ArrayList<Screen>();		
		if(!StringUtils.isNullOrEmpty(agreementno)) {
			cs = utilitiesService.searchCSByAgreementNo(agreementno.trim());
			if(cs != null) {				
				listScreen = utilitiesService.getListScreen(cs.getStage(), "CREDIT_SHIELD");

				message = "Find Successfully!";
				model.addAttribute("record", cs);
				model.addAttribute("listScreen", listScreen);
				Screen screen = utilitiesService.getScreen(cs.getStage());
				model.addAttribute("currentScreen",screen != null ? screen : new Screen());
				
			}
		}
		model.addAttribute("agreementno", agreementno);
		model.addAttribute("message", message);
		return "RevertCS";
	}
	 
	@RequestMapping(value = "/utilities/doRevertCS", method = RequestMethod.POST)
	@ResponseBody
	public String revertCSAjax(@RequestParam(value = "agreementno", required = true) String agreementno,
			@RequestParam(value = "stage", required = true) String stage,
			@RequestParam(value = "note", required = true) String note,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(agreementno);
		String message = "";
		String errorMessage = "";
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		CSRecord cs = utilitiesService.searchCSByAgreementNo(agreementno.trim());
		Screen oldScreen = utilitiesService.getScreen(cs.getStage());
		String desc = "Revert from screen '" + oldScreen.getName() + "'";
		errorMessage = utilitiesService.callInvolve(request, ACTIONS.CS_REVERT_LOAN.toString(), username,
			stage, cs.getId(), desc, note);
		
		if(!CommonUtils.isNullOrEmpty(errorMessage)) {
			message = errorMessage;
		} else {
			Screen newScreen = utilitiesService.getScreen(stage);
			message = "Revert CS success the agreementno is " + agreementno + " with screen is '" + newScreen.getName() + "'";
		}
		
		return message;
	}
	@RequestMapping(value = "/utilities/logs", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesDto<LogTransaction> getLogTransactions(
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam(required=false) Integer sSearch,
			@RequestParam Integer recordId,
			@RequestParam String logType,
            Model model, HttpServletRequest request, HttpServletResponse response)
	{
		DataTablesDto<LogTransaction> dt = new DataTablesDto<LogTransaction>();
		List<LogTransaction> logTransactions = utilitiesService
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
	
	/*
	@RequestMapping(value = "/utilities/logs", method = RequestMethod.GET)
	@ResponseBody
	public List<DataTablesDto<LogTransaction>> getLogTransactions(
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam(required=false) Integer sSearch,
			@RequestParam Integer recordId,
			@RequestParam String logType,
            Model model, HttpServletRequest request, HttpServletResponse response)
	{
		List<DataTablesDto<LogTransaction>> listDt = new ArrayList<DataTablesDto<LogTransaction>>();
		
		//Log RECORD
		DataTablesDto<LogTransaction> dt = new DataTablesDto<LogTransaction>();
		List<LogTransaction> logTransactions = utilitiesService
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
		listDt.add(dt);
		
		//LOG LOAN
		dt = new DataTablesDto<LogTransaction>();
		logTransactions = utilitiesService
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
		listDt.add(dt);
		
		model.addAttribute("recordId", recordId);		
		return listDt;
	}
	*/
}

