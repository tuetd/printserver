package com.pruvn.printserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.cfcwebsite.services.CfcCustomerMService;
import com.pruvn.printserver.entity.AuditLog;
import com.pruvn.printserver.entity.BankAccM;
import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.entity.GeneratePassword;
import com.pruvn.printserver.entity.LoanPurposeM;
import com.pruvn.printserver.entity.Signature;
import com.pruvn.printserver.form.DocumentParameterDto;
import com.pruvn.printserver.form.DocumentParametersForm;
import com.pruvn.printserver.form.FCLForm;
import com.pruvn.printserver.form.WsAgreementMgateForm;
import com.pruvn.printserver.form.WsAutoDebitForm;
import com.pruvn.printserver.form.WsCertificateForm;
import com.pruvn.printserver.form.WsCertificateMgateForm;
import com.pruvn.printserver.form.WsClosureLetterForm;
import com.pruvn.printserver.form.WsGenDisbReqTopUpForm;
import com.pruvn.printserver.form.WsHandoverDocMgateForm;
import com.pruvn.printserver.form.WsInsuranceMgateForm;
import com.pruvn.printserver.form.WsOntFclRequestForm;
import com.pruvn.printserver.form.WsReceiptMgateForm;
import com.pruvn.printserver.form.WsRecordMgateForm;
import com.pruvn.printserver.form.WsRegistrationMgateForm;
import com.pruvn.printserver.form.WsRejectLetterForm;
import com.pruvn.printserver.form.WsRepaymentSchedueForm;
import com.pruvn.printserver.form.WsWelcomeLetterForm;
import com.pruvn.printserver.parse.ParseCenter;
import com.pruvn.printserver.services.AuditLogService;
import com.pruvn.printserver.services.BankAccMService;
import com.pruvn.printserver.services.CFCWebservicesService;
import com.pruvn.printserver.services.DocmasterService;
import com.pruvn.printserver.services.GeneratePasswordService;
import com.pruvn.printserver.services.LoanPurposeMService;
import com.pruvn.printserver.services.SignatureService;
import com.pruvn.printserver.utils.Constant;
import com.pruvn.printserver.utils.DateUtils;
import com.pruvn.printserver.utils.ReportUtils;
@Controller
public class ActionController {
	@Autowired
	private CFCWebservicesService cfcWebservicesService;
	@Autowired
	private DocmasterService docmasterService;
	@Autowired
	private SignatureService signatureService;
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private GeneratePasswordService generatePasswordService;
	@Autowired
	private BankAccMService bankAccMService;
	@Autowired
	private LoanPurposeMService loanPurposeMService;
	@Autowired
	private CfcCustomerMService cfcCustomerMService;
	
	
	public CfcCustomerMService getCfcCustomerMService() {
		return cfcCustomerMService;
	}


	public void setCfcCustomerMService(CfcCustomerMService cfcCustomerMService) {
		this.cfcCustomerMService = cfcCustomerMService;
	}


	public LoanPurposeMService getLoanPurposeMService() {
		return loanPurposeMService;
	}


	public void setLoanPurposeMService(LoanPurposeMService loanPurposeMService) {
		this.loanPurposeMService = loanPurposeMService;
	}


	public BankAccMService getBankAccMService() {
		return bankAccMService;
	}


	public void setBankAccMService(BankAccMService bankAccMService) {
		this.bankAccMService = bankAccMService;
	}


	public GeneratePasswordService getGeneratePasswordService() {
		return generatePasswordService;
	}


	public void setGeneratePasswordService(
			GeneratePasswordService generatePasswordService) {
		this.generatePasswordService = generatePasswordService;
	}


	public AuditLogService getAuditLogService() {
		return auditLogService;
	}


	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}


	public SignatureService getSignatureService() {
		return signatureService;
	}


	public void setSignatureService(SignatureService signatureService) {
		this.signatureService = signatureService;
	}


	public DocmasterService getDocmasterService() {
		return docmasterService;
	}


	public void setDocmasterService(DocmasterService docmasterService) {
		this.docmasterService = docmasterService;
	}


	public CFCWebservicesService getCfcWebservicesService() {
		return cfcWebservicesService;
	}


	public void setCfcWebservicesService(CFCWebservicesService cfcWebservicesService) {
		this.cfcWebservicesService = cfcWebservicesService;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="printDocument.html",method=RequestMethod.POST)
	public String printDocument(@ModelAttribute("paradocumentForm") DocumentParametersForm paradocumentForm,@RequestParam("paramnamelist") List<String> paramnamelist,@RequestParam("paramtypelist") List<String> paramtypelist,
			@RequestParam("documentname") String documentname,Model model,HttpServletRequest request,HttpServletResponse response) throws  Exception {
		InputStream inputStreams = null;
		try {	
			String  rep = null;
			List<String> paramlist = new ArrayList<String>();
			AuditLog auditlog = new AuditLog();
			auditlog.setAction("Print Document");
			auditlog.setCreatedDate(new Date());
			
			paramnamelist=pasrseList(paramnamelist);
    		paramtypelist=pasrseList(paramtypelist);
			
			//CHECK CO APPLID HAY KHONG
			int flag1 = 0;
        	for(int i = 0; i< paramnamelist.size(); i ++)
    		{
    			if( paramnamelist.get(i).equals("applid") || paramnamelist.get(i).equals("APPLID")  || paramnamelist.get(i).equals("APP_ID_C") || paramnamelist.get(i).equals("app_id_c"))
    			{
    				flag1 = 1;
    			}
    		}
        	
        	//GET INFOR KEYIN
        	if(paradocumentForm.getList()!=null){
				for (DocumentParameterDto documentParameterDto : paradocumentForm.getList()) {
					paramlist.add(documentParameterDto.getParamType());
				}
			}
        	
        	
    		if(flag1 == 1)
    		{
    			String appid = paramlist.get(0).toString();
    			if("".equalsIgnoreCase(appid))
    			{
    				throw new Exception("Application id is not null!");
    			}
    		}
			//CHECK WEBSERVICE
    		//    		if(cfcWebservicesService.checkConnectWebserive()!=200){
    		//    			throw new Exception("Webservice connect failed !");
    		//    		}
    		////    		
				Map paramMap = new HashMap();
				Map<String, String> lstParamAndValue=new HashMap();
				String rootpath = request.getSession().getServletContext().getRealPath(File.separator);
				String inputfile="templates"+File.separator;
				String signature_root="images"+File.separator+"signature"+File.separator;
				String namedoc="";
				boolean flag=false;
				// get infor cua docmaster
				List<Docmaster> lst=docmasterService.findByNameDocmaster(paradocumentForm.getDocumentname());
				if(lst!=null && lst.size() == 1 ){
				//get Paramconfig	
				Docmaster doc=lst.get(0);
				inputfile=inputfile+doc.getTemplatefile();
				namedoc=doc.getName_doc()+"_"+DateUtils.getCurrentTimeMillis();
				Signature sg=null;
				if(doc.getSignature()!=null){
				sg=signatureService.findByid(doc.getSignature());
				}
				
					if(doc.getName_doc().equals(Constant.DOCNAME_CLOSERLETTER)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_CLOSURE_LETTER,lstParamAndValue);
						WsClosureLetterForm closerform= ParseCenter.toParser_CLOSURE_LETTER(rep);
						if(closerform.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;	
				        paramMap.put("closeletter", closerform);
				        paramMap.put("signature", request.getSession().getServletContext().getRealPath(File.separator)+signature_root+sg.getNamesignature());
				        paramMap.put("signform", sg);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_REJECTLETTER)){
						String daterun=DateUtils.toPattern(paramlist.get(0), DateUtils.ddMMyyyy, DateUtils.ddMonyy);
						lstParamAndValue.put(paramnamelist.get(0), daterun);
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_REJECT_LETTER,lstParamAndValue);
						WsRejectLetterForm rejectform= ParseCenter.toParser_WS_REJECT_LETTER(rep);
						if(rejectform.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        paramMap.put("signature", request.getSession().getServletContext().getRealPath(File.separator)+signature_root+sg.getNamesignature());
				        paramMap.put("signform", sg);
				        //Xuat report
				        ReportUtils.xuatReportWithCollectionDataSource(inputfile, paramMap, rootpath, "pdf", namedoc, rejectform.getLstReject());
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_WELCOMELETTER)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_WELCOME_LETTER,lstParamAndValue);
						WsWelcomeLetterForm welcomeform= ParseCenter.toParser_WS_WELCOME_LETTER(rep);
						if(welcomeform.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("welcomeletter", welcomeform);
				        //Xuat report
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_SUMMARY_VIOLATIONS)){
						flag=true;
				        //Xuat report
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
					}else if(doc.getName_doc().equals(Constant.DOCNAME_FEE_SCHEDULE)){
						flag=true;
				        //Xuat report
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
					}else if(doc.getName_doc().equals(Constant.DOCNAME_FEE_SCHEDULE_MGAGE)){
						flag=true;
				        //Xuat report
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
					}else if(doc.getName_doc().equals(Constant.DOCNAME_CERTIFICATE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						//check in duoc hop dong hay khong
						//check document duoc in
		    			String checkAllow = docmasterService.printAllow(paramlist.get(0));
		    			if(!"0".equalsIgnoreCase(checkAllow)){
		    				throw new Exception(checkAllow+ "- Agreement No not allow print !!!");
		    			}
						
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_CERTIFICATE,lstParamAndValue);
						WsCertificateForm certificatefrom= ParseCenter.toParser_WS_CERTIFICATE(rep);
						if(certificatefrom.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
							if(certificatefrom.getDUEDATE().contains("1") || certificatefrom.getDUEDATE().contains("5") || 
							   certificatefrom.getDUEDATE().contains("10") || certificatefrom.getDUEDATE().contains("16")){
								flag=true;
								//get purpose loan
								LoanPurposeM loanpurpose=loanPurposeMService.findByPurposeCode(certificatefrom.getPURPOSE());
								if(loanpurpose!=null){
									certificatefrom.setPURPOSE(loanpurpose.getVn());
								}
								paramMap.put("certificate", certificatefrom);
								//check chu ky CEO
								String signature=request.getSession().getServletContext().getRealPath(File.separator)+signature_root+sg.getNamesignature();
								if(paramlist.get(1)==null){
									sg=signatureService.findByid(doc.getSignature_sip());
									signature="";
								}
								paramMap.put("signform", sg);
								paramMap.put("signature", signature );
								paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
						        //Xuat report
						        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						        //insert log chu ky CEO va update Record Managerment System
						        docmasterService.saveNoSignatureWithCEO(certificatefrom.getLOANNO(),SecurityContextHolder.getContext().getAuthentication().getName(),doc.getName_doc(),paramlist.get(1));
						        
						}else{
							throw new Exception("Application has a due date "+certificatefrom.getDUEDATE()+", not on the list of due dates (1,5,10,16), please check !!! ");
						}
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_REPAYMENTSCHEDUE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_CERTIFICATE,lstParamAndValue);
						WsRepaymentSchedueForm certificatefrom= ParseCenter.toParser_WS_REPAYMENTSCHEDUE(rep);
						if(certificatefrom.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
								flag=true;
								paramMap.put("repaymentschedue", certificatefrom);
						        //Xuat report
						        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						        ReportUtils.xuatReportWithCollectionDataSource(inputfile, paramMap, rootpath, "pdf", namedoc, certificatefrom.getListpaymet());
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_AUTODEBITFORM)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_AUTO_DEBIT_FORM,lstParamAndValue);
						WsAutoDebitForm wsAutoDebitForm= ParseCenter.toParser_WS_AUTO_DEBIT_FORM(rep);
						if(wsAutoDebitForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						String logo=request.getSession().getServletContext().getRealPath(File.separator)+"images"+File.separator+"logo_";
						if(wsAutoDebitForm.getBANK_CODE()!=null){
							BankAccM bankname=bankAccMService.findByBankCode(wsAutoDebitForm.getBANK_CODE());
							if(bankname!=null){
								inputfile=inputfile.replaceAll("xxx", bankname.getBank_name());
								logo=logo+bankname.getBank_name()+".png";
							}else {
								throw new Exception("Bank Account not found !");
							}
						}
						paramMap.put("autodebitform", wsAutoDebitForm);
						paramMap.put("logo",logo);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_EARLYTERMINATION)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						String date_fcl=DateUtils.toPattern(paramlist.get(1), DateUtils.ddMMyyyy, DateUtils.ddMonyy);
						lstParamAndValue.put(paramnamelist.get(1), date_fcl);
						lstParamAndValue.put(paramnamelist.get(2), paramlist.get(2));
						//check xem in duoc form tat toan hay khong
						String printCheckFCL = docmasterService.printCheckFCL(paramlist.get(0).toString() , paramlist.get(2).toString());
						if(!"0".equalsIgnoreCase(printCheckFCL)){
							throw new Exception(printCheckFCL);
						}
						
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_ONT_FCL_REQUEST,lstParamAndValue);
						WsOntFclRequestForm wsOntFclRequestForm= ParseCenter.toParser_WS_ONT_FCL_REQUEST(rep);
						if(wsOntFclRequestForm.getResult_Code().contains(Constant.WEBSERVICE_CODE_SUCCESS)){
						//get percent of fcl	
						String fclPercent = docmasterService.printFCLPercent(wsOntFclRequestForm.getAGREEMENTNO(),paramlist.get(2).toString());
						if("-1".equalsIgnoreCase(fclPercent)){
							throw new Exception("Error when get percent for FCL");
						}else{
							wsOntFclRequestForm.setFCLPERCENT(fclPercent);
						}
							
						//check in version cua form tat toan, neu nhu giai ngan sau ngay '01-08-2016' va khong nam trong bang Foreclosure_V9 thi ap dung V10
						//get ngay bat dau tinh lai
						FCLForm  fcl=cfcCustomerMService.getInstartDate(paramlist.get(0));
						if(fcl==null){
							throw new Exception("Instart Date is not found !");
						}
						//check credit shield and version
						int checkversion=docmasterService.checkVersionFCL(fcl.getAgreementno(),fcl.getStartIntDate(),fcl.getInstlnum(),fcl.getDuedate(),wsOntFclRequestForm.getCHECK_CREDIT_SHEILD());
						if (checkversion==0) {	
							inputfile=inputfile.replaceAll("xxx", "");
						}else if(checkversion==1){
							inputfile=inputfile.replaceAll("xxx", "CShield");
						}else if(checkversion==2){
							inputfile=inputfile.replaceAll("xxx", "V10");
						}else if(checkversion==3){
							inputfile=inputfile.replaceAll("xxx", "CShield_V10");
						}	
						//	
						flag=true;
						paramMap.put("earlyterminal", wsOntFclRequestForm);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_TERMSCONDITIONS)){
						flag=true;
				        //Xuat report
						paramMap.put("signature", request.getSession().getServletContext().getRealPath(File.separator)+signature_root+sg.getNamesignature());
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
					}else if(doc.getName_doc().equals(Constant.DOCNAME_PAYMENTGUIDANCEANDACCOUNT)){
						//update lai password.
						int result=generatePasswordService.generatePasswordDefault(paramlist.get(0));
						GeneratePassword generatepassword=generatePasswordService.findByApplid(paramlist.get(0));
						//update lai password ben website
						int result_update=0;
						if(generatepassword!=null){
							result_update=cfcCustomerMService.generatePasswordDefault(generatepassword.getPAN_NO(),generatepassword.getAPP_ID_C(),generatepassword.getPASSWORD(),generatepassword.getINITIAL_PASSWORD());
						}
						if(generatepassword!=null&&result_update==1&&result==1){
						flag=true;
				        //Xuat report
						paramMap.put("generatepassword", generatepassword);
						paramMap.put("SUBREPORT_DIR", rootpath+File.separator+"templates"+File.separator);
						ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_EARLYTERMINATIONANDTRANSFERPAYMENT)){
						//new app
						String date_fcl=DateUtils.toPattern(paramlist.get(1), DateUtils.ddMMyyyy, DateUtils.ddMonyy);
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						lstParamAndValue.put(paramnamelist.get(1), date_fcl);
						lstParamAndValue.put(paramnamelist.get(2), paramlist.get(2));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_GEN_DISB_REQ_TOPUP_V10,lstParamAndValue);
						WsGenDisbReqTopUpForm wsGenDisbReqTopUpForm=ParseCenter.toParser_WS_GEN_DISB_REQ_TOPUP_V10(rep);
						if(Constant.WEBSERVICE_CODE_SUCCESS.equals(wsGenDisbReqTopUpForm.getResult_Code())){
							lstParamAndValue.clear();
							//get old app and FCL date 
							lstParamAndValue.put(paramnamelist.get(0), wsGenDisbReqTopUpForm.getOLDAPP());
							lstParamAndValue.put(paramnamelist.get(1), date_fcl);
							rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_ONT_FCL_REQUEST,lstParamAndValue);
							WsOntFclRequestForm wsOntFclRequestForm= ParseCenter.toParser_WS_ONT_FCL_REQUEST(rep);
							if(paramlist.get(2)!=null){
								wsOntFclRequestForm.setFCLREASON(paramlist.get(2));
							}
							if(Constant.WEBSERVICE_CODE_SUCCESS.equals(wsGenDisbReqTopUpForm.getResult_Code())){
								if(wsOntFclRequestForm.getFCLAMT()== wsGenDisbReqTopUpForm.getFCL_AMOUNT_OLDAPP()){
									flag=true;
									paramMap.put("earlyterminal", wsOntFclRequestForm);
									paramMap.put("transferpayment", wsGenDisbReqTopUpForm);
									paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
							        //Xuat report
							        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
									}
								}
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_AGREEMENTMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_AGREEMENT_MGAGE,lstParamAndValue);
						WsAgreementMgateForm certificatefrom= ParseCenter.toParser_WS_AGREEMENT_MGAGE(rep);
						if(certificatefrom.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("certificate", certificatefrom);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_HANDOVERRECEIPTMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_RECEIPT_MGAGE,lstParamAndValue);
						WsReceiptMgateForm certificatefrom= ParseCenter.toParser_WS_RECEIPT_MGAGE(rep);
						if(certificatefrom.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("certificate", certificatefrom);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_PROPERTYINSURANCEMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_INSURANCE_MGAGE,lstParamAndValue);
						WsInsuranceMgateForm insurance= ParseCenter.toParser_WS_INSURANCE_MGAGE(rep);
						if(insurance.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("insurance", insurance);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_COLLATERALREGISTRATIONMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_REGISTRATION_MGAGE,lstParamAndValue);
						WsRegistrationMgateForm registration= ParseCenter.toParser_WS_REGISTRATION_MGAGE(rep);
						if(registration.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("registration", registration);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_HANDOVERMINUTECUSMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_HANDOVER_DOC_MGAGE,lstParamAndValue);
						WsHandoverDocMgateForm handovercus= ParseCenter.toParser_WS_HANDOVER_DOC_MGAGE(rep);
						if(handovercus.getResult_Code().contains(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("handovercus", handovercus);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_HANDOVERMINUTERECORDMORTGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_RECORD_MGAGE,lstParamAndValue);
						WsRecordMgateForm handoverrecord= ParseCenter.toParser_WS_RECORD_MGAGE(rep);
						if(handoverrecord.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("handoverrecord", handoverrecord);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}else if(doc.getName_doc().equals(Constant.DOCNAME_CERTIFICATEMGAGE)){
						lstParamAndValue.put(paramnamelist.get(0), paramlist.get(0));
						rep = cfcWebservicesService.call_webserivce_with_lstParam(Constant.WEBSERVICE_WS_CERTIFICATE_MGAGE,lstParamAndValue);
						WsCertificateMgateForm certificatemgage= ParseCenter.toParser_WS_CERTIFICATE_MGAGE(rep);
						if(certificatemgage.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
						flag=true;
						paramMap.put("certificatemgage", certificatemgage);
						paramMap.put("SUBREPORT_DIR",rootpath+File.separator+"templates"+File.separator);
				        //Xuat report
				        ReportUtils.xuatReport(inputfile, paramMap, rootpath, "pdf", namedoc);
						}
					}
					
					if(flag){
						inputStreams = new FileInputStream(rootpath+File.separator+"templates"+File.separator+"pdf"+File.separator+namedoc + ".pdf");
					   	response.setContentType("application/pdf");
					    response.setHeader("Content-Disposition", "inline;filename="+namedoc+".pdf");
				        IOUtils.copy(inputStreams, response.getOutputStream());
				        response.flushBuffer();
				     // auditlog user printer docmaster
						StringBuilder input=new StringBuilder();
						StringBuilder output=new StringBuilder();
						input.append(SecurityContextHolder.getContext().getAuthentication().getName() + " - " + documentname);
						input.append("\r\n");
						for (int k = 0; k < paramnamelist.size(); k++) {
							if(paramlist!=null&&paramlist.size()>0){
							input.append(paramnamelist.get(k)+": "+paramlist.get(k)+"\r\n");
							}
						}
						output.append(SecurityContextHolder.getContext().getAuthentication().getName() + " - " + documentname);
						output.append("\r\n");
						output.append(rep);
						// auditlog user printer docmaster
						if(doc!=null){
							auditlog.setDetail(input.toString());
							auditlog.setEntityId(doc.getId());
							auditlog.setEntityName("DOCMASTER");
							auditlog.setDetailOut(output.toString());
							auditLogService.saveAuditLog(auditlog);
						}
					}else{
						model.addAttribute("message", "Record is not found !");
					}
				}
				model.addAttribute("backUrl", "listDocument.html");
		        return "document/common_success";
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				String[] err=  errors.toString().split("\r\n");
				System.out.println(errors.toString());
				model.addAttribute("message",err[0]);
				model.addAttribute("backUrl", "listDocument.html");
				return "document/common_success";
			} finally {
				if(inputStreams!=null){
				inputStreams.close();
				}
			}
	}

	private List<String> pasrseList(List<String> source){
		List<String> dest= new ArrayList<String>();
	  	int count=1;
		 for (int i = 0; i < source.size(); i++) {
			 String para=source.get(i);
			if(count==1){
				para=para.substring(1,para.length());
				
			}
			if(count==source.size()){
				para = para.substring(0,para.length()-1);
			}
			dest.add(para);
			count++;
		}
		return dest;
	}


	//Test main
	/*
	private InputStream print(String inputfile,Map paramMap,String rootpath,String type,Collection collections,String namedoc) {
		// TODO Auto-generated method stub
		try {
			ReportUtils.xuatReportWithCollectionDataSource(inputfile, paramMap, rootpath, "pdf", namedoc, collections);
			return new FileInputStream(rootpath+File.separator+"templates"+File.separator+"pdf"+File.separator+namedoc + ".pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		CFCWebservicesService webservice=new CFCWebservicesServiceImpl();
		//Test webservice WS_CERTIFICATE
//		String WS_CERTIFICATE=webservice.call_webserivce_with_applid("WS_CERTIFICATE", "10114157");
//		WsCertificateFrom certificateForm= ParseCenter.toParser_WS_CERTIFICATE(WS_CERTIFICATE);
//		System.out.println(certificateForm.getAPP_ID_C());
		
//		String WS_WELCOME_LETTER=webservice.call_webserivce_with_applid("WS_WELCOME_LETTER", "10114157");//done
//		WsWelcomeLetterForm wsWelcomeLetterForm= ParseCenter.toParser_WS_WELCOME_LETTER(WS_WELCOME_LETTER);
//		System.out.println(wsWelcomeLetterForm.getAGREEMENTNO());
		
//		String WS_AUTO_DEBIT_FORM=webservice.call_webserivce_with_applid("WS_AUTO_DEBIT_FORM", "10775535");//done
//		WsAutoDebitFrom autodebitform=ParseCenter.toParser_WS_AUTO_DEBIT_FORM(WS_AUTO_DEBIT_FORM);
//		System.out.println(autodebitform.getAPPLID());
		
//		String WS_OVERDUE_EMI_FCL=webservice.call_webserivce_with_applid("WS_OVERDUE_EMI_FCL", "10114157");//done
//		WsOverdueEmiFclForm wsOverdueEmiFclForm=ParseCenter.toParser_WS_OVERDUE_EMI_FCL(WS_OVERDUE_EMI_FCL);
//		System.out.println(wsOverdueEmiFclForm.getAPPLID());
		
//		String WS_REJECT_LETTER=webservice.call_webserivce_with_applid("WS_REJECT_LETTER", "10114157");//done
//		WsRejectLetterForm rejectletter=ParseCenter.toParser_WS_REJECT_LETTER(WS_REJECT_LETTER);
//		System.out.println(rejectletter.getLstReject().size());
		
//		String WS_GEN_DISB_REQ_TOPUP_V10=webservice.call_webserivce_with_applid("WS_GEN_DISB_REQ_TOPUP_V10", "10114157");//done
//		WsGenDisbReqTopUpForm topup=ParseCenter.toParser_WS_GEN_DISB_REQ_TOPUP_V10(WS_GEN_DISB_REQ_TOPUP_V10);
//		System.out.println(topup.getAPPLID());
		
//		String Ont_fcl_request=webservice.call_webserivce_with_applid("WS_ONT_FCL_REQUEST", "10114157");//done
//		WsOntFclRequestForm topup=ParseCenter.toParser_WS_ONT_FCL_REQUEST(Ont_fcl_request);
//		System.out.println(topup.getAPPLID());
		
//		String WS_CERTIFICATE_MGAGE=webservice.call_webserivce_with_applid("WS_CERTIFICATE_MGAGE", "10114157");//done
//		WsCertificateMgateForm wsCertificateMgateForm=ParseCenter.toParser_WS_CERTIFICATE_MGAGE(WS_CERTIFICATE_MGAGE);
//		System.out.println(wsCertificateMgateForm.getAGREEMENTNO());
//		String WS_HANDOVER_DOC_MGAGE=webservice.call_webserivce_with_applid("WS_HANDOVER_DOC_MGAGE", "10114157");//done
//		WsHandoverDocMgateForm wsHandoverDocMgateForm=ParseCenter.toParser_WS_HANDOVER_DOC_MGAGE(WS_HANDOVER_DOC_MGAGE);
//		System.out.println(wsHandoverDocMgateForm.getAGREEMENTNO());
//		String WS_AGREEMENT_MGAGE=webservice.call_webserivce_with_applid("WS_AGREEMENT_MGAGE", "10114157");//done
//		WsAgreementMgateForm WsAgreementMgateForm=ParseCenter.toParser_WS_AGREEMENT_MGAGE(WS_AGREEMENT_MGAGE);
//		System.out.println(WsAgreementMgateForm.getAGREEMENTNO());
		
//		String WS_INSURANCE_MGAGE=webservice.call_webserivce_with_applid("WS_INSURANCE_MGAGE", "10114157");//done
//		WsInsuranceMgateForm wsInsuranceMgateForm=ParseCenter.toParser_WS_INSURANCE_MGAGE(WS_INSURANCE_MGAGE);
//		System.out.println(wsInsuranceMgateForm.getAPPLICANT_NAME_1());
		
//		String WS_RECEIPT_MGAGE=webservice.call_webserivce_with_applid("WS_RECEIPT_MGAGE", "10114157");//done
//		WsReceiptMgateForm wsReceiptMgateForm=ParseCenter.toParser_WS_RECEIPT_MGAGE(WS_RECEIPT_MGAGE);
//		System.out.println(wsReceiptMgateForm.getAGREEMENTNO());
		
//		String WS_REGISTRATION_MGAGE=webservice.call_webserivce_with_applid("WS_REGISTRATION_MGAGE", "10114157");//done
//		WsRegistrationMgateForm wsRegistrationMgateForm=ParseCenter.toParser_WS_REGISTRATION_MGAGE(WS_REGISTRATION_MGAGE);
//		System.out.println(wsRegistrationMgateForm.getAPPLICANT_NAME_1());
		
//		String WS_RECORD_MGAGE=webservice.call_webserivce_with_applid("WS_RECORD_MGAGE", "10114157");
//		WsRecordMgateForm wsRecordMgateForm=ParseCenter.toParser_WS_RECORD_MGAGE(WS_RECORD_MGAGE);
//		System.out.println(wsRecordMgateForm.getADDRESS_COLLATERAL());
		
//		System.out.println(WS_OVERDUE_EMI_FCL);
	}
	*/
}
