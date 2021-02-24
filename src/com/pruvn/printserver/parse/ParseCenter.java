package com.pruvn.printserver.parse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pruvn.printserver.form.RejectLetterForm;
import com.pruvn.printserver.form.RepaymentForm;
import com.pruvn.printserver.form.RepaymentPLForm;
import com.pruvn.printserver.form.WsAgreementMgateForm;
import com.pruvn.printserver.form.WsAutoDebitForm;
import com.pruvn.printserver.form.WsCertificateForm;
import com.pruvn.printserver.form.WsCertificateMgateForm;
import com.pruvn.printserver.form.WsClosureLetterForm;
import com.pruvn.printserver.form.WsGenDisbReqTopUpForm;
import com.pruvn.printserver.form.WsHandoverDocMgateForm;
import com.pruvn.printserver.form.WsInsuranceMgateForm;
import com.pruvn.printserver.form.WsOntFclRequestForm;
import com.pruvn.printserver.form.WsOverdueEmiFclForm;
import com.pruvn.printserver.form.WsReceiptMgateForm;
import com.pruvn.printserver.form.WsRecordMgateForm;
import com.pruvn.printserver.form.WsRegistrationMgateForm;
import com.pruvn.printserver.form.WsRejectLetterForm;
import com.pruvn.printserver.form.WsRepaymentSchedueForm;
import com.pruvn.printserver.form.WsWelcomeLetterForm;
import com.pruvn.printserver.utils.Constant;
import com.pruvn.printserver.utils.DateUtils;

public class ParseCenter {
	public static WsClosureLetterForm toParser_CLOSURE_LETTER(String result){
		WsClosureLetterForm closeform=new WsClosureLetterForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               closeform.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               closeform.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(closeform.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	               closeform.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	               closeform.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	               closeform.setADDRL1(eElement.getElementsByTagName("ADDRL1").item(0).getTextContent());
	               closeform.setADDRL2(eElement.getElementsByTagName("ADDRL2").item(0).getTextContent());
	               closeform.setADDRL3(eElement.getElementsByTagName("ADDRL3").item(0).getTextContent());
	               closeform.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
				   closeform.setCLOSUREDATE(DateUtils.toPattern(eElement.getElementsByTagName("CLOSUREDATE").item(0).getTextContent(),DateUtils.yyMMdd,DateUtils.ddFMMFyyyy));
	               closeform.setCHEQUEDATE(eElement.getElementsByTagName("CHEQUEDATE").item(0).getTextContent());
	               closeform.setDAY(eElement.getElementsByTagName("DAY").item(0).getTextContent());
	               closeform.setMONTH(eElement.getElementsByTagName("MONTH").item(0).getTextContent());
	               closeform.setYEAR(eElement.getElementsByTagName("YEAR").item(0).getTextContent());
	               closeform.setAPP_FORMNO(eElement.getElementsByTagName("APP_FORMNO").item(0).getTextContent());
	               closeform.setPRODUCTFLAG(eElement.getElementsByTagName("PRODUCTFLAG").item(0).getTextContent());
	               closeform.setAMOUNT(eElement.getElementsByTagName("AMOUNT").item(0).getTextContent());
	               closeform.setWAIVEOFF(eElement.getElementsByTagName("WAIVEOFF").item(0).getTextContent());
	               closeform.setCOMP_NAME(eElement.getElementsByTagName("COMP_NAME").item(0).getTextContent());
	               closeform.setPAN_NO(eElement.getElementsByTagName("PAN_NO").item(0).getTextContent());
	               closeform.setMOBILE(eElement.getElementsByTagName("MOBILE").item(0).getTextContent());
	               closeform.setHOMEPHONE(eElement.getElementsByTagName("HOMEPHONE").item(0).getTextContent());
	               closeform.setNPA_STAGEID(eElement.getElementsByTagName("NPA_STAGEID").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closeform;
	}
	
	
	
	public static WsCertificateForm toParser_WS_CERTIFICATE(String result){
		WsCertificateForm certificateForm=new WsCertificateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        List<RepaymentPLForm> lstRepaymentForm=new ArrayList<RepaymentPLForm>();
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               certificateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               certificateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(certificateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   certificateForm.setAPP_ID_C(eElement.getElementsByTagName("APP_ID_C").item(0).getTextContent());
	            	   certificateForm.setRO_NAME(eElement.getElementsByTagName("RO_NAME").item(0).getTextContent());
	            	   certificateForm.setPRODUCT(eElement.getElementsByTagName("PRODUCT").item(0).getTextContent());
	            	   certificateForm.setPRINTDATE(DateUtils.toPattern(eElement.getElementsByTagName("PRINTDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   certificateForm.setLOANNO(eElement.getElementsByTagName("LOANNO").item(0).getTextContent());
	            	   certificateForm.setFINISHDATE(DateUtils.toPattern(eElement.getElementsByTagName("FINISHDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   certificateForm.setLOANAMOUNT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("LOANAMOUNT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   certificateForm.setPAN_NO(eElement.getElementsByTagName("PAN_NO").item(0).getTextContent());
	            	   certificateForm.setPAN_NO_ISSUE_DATE(DateUtils.toPattern(eElement.getElementsByTagName("PAN_NO_ISSUE_DATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   certificateForm.setSSTNUM(eElement.getElementsByTagName("SSTNUM").item(0).getTextContent());
	            	   certificateForm.setLOANAMOUNTTEXT(DateUtils.fSoThanhChu(Long.parseLong(eElement.getElementsByTagName("LOANAMOUNT").item(0).getTextContent())));
	            	   certificateForm.setDOB(DateUtils.toPattern(eElement.getElementsByTagName("DOB").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   certificateForm.setADDR1(eElement.getElementsByTagName("ADDR1").item(0).getTextContent());
	            	   certificateForm.setADDR2(eElement.getElementsByTagName("ADDR2").item(0).getTextContent());
	            	   certificateForm.setADDR3(eElement.getElementsByTagName("ADDR3").item(0).getTextContent());
	            	   certificateForm.setADDR4(eElement.getElementsByTagName("ADDR4").item(0).getTextContent());
	            	   certificateForm.setPHONE1(eElement.getElementsByTagName("PHONE1").item(0).getTextContent());
	            	   certificateForm.setFAX(eElement.getElementsByTagName("FAX").item(0).getTextContent());
	            	   certificateForm.setTENURE(eElement.getElementsByTagName("TENURE").item(0).getTextContent());
	            	   certificateForm.setINTERESTRATE(eElement.getElementsByTagName("INTERESTRATE").item(0).getTextContent());
	            	   certificateForm.setDUEDATE(eElement.getElementsByTagName("DUEDATE").item(0).getTextContent());
	            	   String duedate=eElement.getElementsByTagName("DUEDATE").item(0).getTextContent();
	            	   if(!duedate.isEmpty()){
							String dueday= DateUtils.toPattern(duedate, DateUtils.ddMonyy, DateUtils.dd);
							certificateForm.setDUEDATE(dueday);
	            	   }
	            	   certificateForm.setFIRSTINSTAMT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("FIRSTINSTAMT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setFIRSTINSTAMTTEXT(DateUtils.fSoThanhChu(Long.parseLong(eElement.getElementsByTagName("FIRSTINSTAMT").item(0).getTextContent())));
	            	   certificateForm.setFIRSTDUEDATE(DateUtils.toPattern(eElement.getElementsByTagName("FIRSTDUEDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   certificateForm.setBEFORELASTINSTAMT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("BEFORELASTINSTAMT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setBEFORELASTINSTAMTTEXT(DateUtils.fSoThanhChu(Long.parseLong(eElement.getElementsByTagName("BEFORELASTINSTAMT").item(0).getTextContent())));
	            	   certificateForm.setLASTINSTAMT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("LASTINSTAMT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setLASTINSTAMTTEXT(DateUtils.fSoThanhChu(Long.parseLong(eElement.getElementsByTagName("LASTINSTAMT").item(0).getTextContent())));
	            	   certificateForm.setPROCESSINGFEE(eElement.getElementsByTagName("PROCESSINGFEE").item(0).getTextContent());
	            	   certificateForm.setSEMILAST(DateUtils.getFormattedNumber(eElement.getElementsByTagName("SEMILAST").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setCORPILPHRASE(eElement.getElementsByTagName("CORPILPHRASE").item(0).getTextContent());
	            	   certificateForm.setCELP(eElement.getElementsByTagName("CELP").item(0).getTextContent());
	            	   certificateForm.setPASSPORTNO(eElement.getElementsByTagName("PASSPORTNO").item(0).getTextContent());
	            	   certificateForm.setSTATUS(eElement.getElementsByTagName("STATUS").item(0).getTextContent());
	            	   certificateForm.setTOTALINSTALLMENT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("TOTALINSTALLMENT").item(0).getTextContent(),"#,###"));
	            	   NodeList nNode_records = doc.getElementsByTagName("INSTALLMENTS");
	       	              for (int j = 0; j < nNode_records.getLength(); j++) {
	       	            	 Node nNode_record = nNode_records.item(j);
	       	            	if (nNode_record.getNodeType() == Node.ELEMENT_NODE) {
	       	            		Element eElement_record = (Element) nNode_record;
	       	            		RepaymentPLForm repayment_pl=new RepaymentPLForm();
	       	            		repayment_pl.setINSTLNUM(eElement_record.getElementsByTagName("INSTLNUM").item(0).getTextContent());
	       	            		repayment_pl.setDUEDATE(DateUtils.toPattern(eElement_record.getElementsByTagName("DUEDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	       	            		repayment_pl.setBALANCEOUTSTANDING(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("BALANCEOUTSTANDING").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setFORECLOSUREPAYMENT(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("FORECLOSUREPAYMENT").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setINTCOMP(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("INTCOMP").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setINSTALLMENTAMOUNT(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("INSTALLMENTAMOUNT").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setFORECLOSURERATE(eElement_record.getElementsByTagName("FORECLOSURERATE").item(0).getTextContent());
	       	            		lstRepaymentForm.add(repayment_pl);
	       	            	}
						}
	            	   certificateForm.setListpaymet(lstRepaymentForm);
	            	   certificateForm.setENDFORECLOSURE(eElement.getElementsByTagName("ENDFORECLOSURE").item(0).getTextContent());
	            	   certificateForm.setCHARGEAMT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("CHARGEAMT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setDISBAMT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("DISBAMT").item(0).getTextContent(),"#,###"));
	            	   certificateForm.setACCOUNT_NO(eElement.getElementsByTagName("ACCOUNT_NO").item(0).getTextContent());
	            	   certificateForm.setBANK_NAME(eElement.getElementsByTagName("BANK_NAME").item(0).getTextContent());
	            	   certificateForm.setBRANCH_NAME(eElement.getElementsByTagName("BRANCH_NAME").item(0).getTextContent());
	            	   certificateForm.setADDRL(eElement.getElementsByTagName("ADDRL").item(0).getTextContent());
	            	   certificateForm.setMOBILE(eElement.getElementsByTagName("MOBILE").item(0).getTextContent());
	            	   certificateForm.setHOMEPHONE(eElement.getElementsByTagName("HOMEPHONE").item(0).getTextContent());
	            	   certificateForm.setEMAIL(eElement.getElementsByTagName("EMAIL").item(0).getTextContent());
	            	   certificateForm.setADDRL_RES(eElement.getElementsByTagName("ADDRL_RES").item(0).getTextContent());
	            	   certificateForm.setADDRL_OFF(eElement.getElementsByTagName("ADDRL_OFF").item(0).getTextContent());
	            	   certificateForm.setPURPOSE(eElement.getElementsByTagName("PURPOSE").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return certificateForm;
	}
	
	
	public static WsRepaymentSchedueForm toParser_WS_REPAYMENTSCHEDUE(String result){
		WsRepaymentSchedueForm repaymentForm=new WsRepaymentSchedueForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        List<RepaymentPLForm> lstRepaymentForm=new ArrayList<RepaymentPLForm>();
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               repaymentForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               repaymentForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(repaymentForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   repaymentForm.setAPP_ID_C(eElement.getElementsByTagName("APP_ID_C").item(0).getTextContent());
	            	   repaymentForm.setRO_NAME(eElement.getElementsByTagName("RO_NAME").item(0).getTextContent());
	            	   repaymentForm.setLOANNO(eElement.getElementsByTagName("LOANNO").item(0).getTextContent());
	            	   repaymentForm.setLOANAMOUNT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("LOANAMOUNT").item(0).getTextContent(),"#,###"));
	            	   repaymentForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   repaymentForm.setPAN_NO(eElement.getElementsByTagName("PAN_NO").item(0).getTextContent());
	            	   repaymentForm.setPAN_NO_ISSUE_DATE(DateUtils.toPattern(eElement.getElementsByTagName("PAN_NO_ISSUE_DATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   repaymentForm.setTOTALINSTALLMENT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("TOTALINSTALLMENT").item(0).getTextContent(),"#,###"));
	            	   repaymentForm.setTENURE(eElement.getElementsByTagName("TENURE").item(0).getTextContent());
	            	   NodeList nNode_records = doc.getElementsByTagName("INSTALLMENTS");
	       	              for (int j = 0; j < nNode_records.getLength(); j++) {
	       	            	 Node nNode_record = nNode_records.item(j);
	       	            	if (nNode_record.getNodeType() == Node.ELEMENT_NODE) {
	       	            		Element eElement_record = (Element) nNode_record;
	       	            		RepaymentPLForm repayment_pl=new RepaymentPLForm();
	       	            		repayment_pl.setINSTLNUM(eElement_record.getElementsByTagName("INSTLNUM").item(0).getTextContent());
	       	            		repayment_pl.setDUEDATE(DateUtils.toPattern(eElement_record.getElementsByTagName("DUEDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	       	            		repayment_pl.setBALANCEOUTSTANDING(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("BALANCEOUTSTANDING").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setFORECLOSUREPAYMENT(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("FORECLOSUREPAYMENT").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setINTCOMP(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("INTCOMP").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setINSTALLMENTAMOUNT(DateUtils.getFormattedNumber(eElement_record.getElementsByTagName("INSTALLMENTAMOUNT").item(0).getTextContent(),"#,###"));
	       	            		repayment_pl.setFORECLOSURERATE(eElement_record.getElementsByTagName("FORECLOSURERATE").item(0).getTextContent());
	       	            		lstRepaymentForm.add(repayment_pl);
	       	            	}
						}
	       	           repaymentForm.setListpaymet(lstRepaymentForm);
	       	           
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repaymentForm;
	}
	
	public static WsOntFclRequestForm toParser_WS_ONT_FCL_REQUEST(String result){
		WsOntFclRequestForm wsOntFclRequestForm=new WsOntFclRequestForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsOntFclRequestForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsOntFclRequestForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsOntFclRequestForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsOntFclRequestForm.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsOntFclRequestForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsOntFclRequestForm.setDAY(eElement.getElementsByTagName("DAY").item(0).getTextContent());
	            	   wsOntFclRequestForm.setMONTH(eElement.getElementsByTagName("MONTH").item(0).getTextContent());
	            	   wsOntFclRequestForm.setYEAR(eElement.getElementsByTagName("YEAR").item(0).getTextContent());
	            	   wsOntFclRequestForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   wsOntFclRequestForm.setDOB(eElement.getElementsByTagName("DOB").item(0).getTextContent());
	            	   wsOntFclRequestForm.setPAN_NO_ISSUE_DATE(DateUtils.toPattern(eElement.getElementsByTagName("PAN_NO_ISSUE_DATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   wsOntFclRequestForm.setSSTNUM(eElement.getElementsByTagName("SSTNUM").item(0).getTextContent());
	            	   wsOntFclRequestForm.setAGREEMENTDATE(eElement.getElementsByTagName("AGREEMENTDATE").item(0).getTextContent());
	            	   wsOntFclRequestForm.setPAN_NO(eElement.getElementsByTagName("PAN_NO").item(0).getTextContent());
	            	   wsOntFclRequestForm.setPRODUCTFLAG(eElement.getElementsByTagName("PRODUCTFLAG").item(0).getTextContent());
	            	   wsOntFclRequestForm.setBRANCHDESC(eElement.getElementsByTagName("BRANCHDESC").item(0).getTextContent());
	            	   wsOntFclRequestForm.setACCOUNT_NO(eElement.getElementsByTagName("ACCOUNT_NO").item(0).getTextContent());
	            	   wsOntFclRequestForm.setBANK_NAME(eElement.getElementsByTagName("BANK_NAME").item(0).getTextContent());
	            	   wsOntFclRequestForm.setBRANCH_NAME(eElement.getElementsByTagName("BRANCH_NAME").item(0).getTextContent());
	            	   wsOntFclRequestForm.setOVDPAYMENTFEE(eElement.getElementsByTagName("OVDPAYMENTFEE").item(0).getTextContent());
	            	   wsOntFclRequestForm.setESTFCLDATE(DateUtils.toPattern(eElement.getElementsByTagName("ESTFCLDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   wsOntFclRequestForm.setNEXTESTFCLDATE(DateUtils.toPattern(eElement.getElementsByTagName("NEXTESTFCLDATE").item(0).getTextContent(),DateUtils.ddMMMyy,DateUtils.ddFMMFyyyy));
	            	   wsOntFclRequestForm.setOSTPRINBAL(eElement.getElementsByTagName("OSTPRINBAL").item(0).getTextContent());
	            	   wsOntFclRequestForm.setFCLCHARGE(eElement.getElementsByTagName("FCLCHARGE").item(0).getTextContent());
	            	   wsOntFclRequestForm.setLPP(eElement.getElementsByTagName("LPP").item(0).getTextContent());
	            	   wsOntFclRequestForm.setEXCESSAMT(eElement.getElementsByTagName("EXCESSAMT").item(0).getTextContent());
	            	   wsOntFclRequestForm.setINTINCURRED(eElement.getElementsByTagName("INTINCURRED").item(0).getTextContent());
	            	   wsOntFclRequestForm.setFCLPERCENT(eElement.getElementsByTagName("FCLPERCENT").item(0).getTextContent());
	            	   wsOntFclRequestForm.setOVDINT(eElement.getElementsByTagName("OVDINT").item(0).getTextContent());
	            	   wsOntFclRequestForm.setOVDPRIN(eElement.getElementsByTagName("OVDPRIN").item(0).getTextContent());
	            	   wsOntFclRequestForm.setFCLAMT(eElement.getElementsByTagName("FCLAMT").item(0).getTextContent());
	            	   wsOntFclRequestForm.setFCLAMTWORD(eElement.getElementsByTagName("FCLAMTWORD").item(0).getTextContent());
	            	   wsOntFclRequestForm.setCHECK_CREDIT_SHEILD(eElement.getElementsByTagName("CHECK_CREDIT_SHEILD").item(0).getTextContent());
	            	   wsOntFclRequestForm.setCHECK_AUTODEBIT(eElement.getElementsByTagName("CHECK_AUTODEBIT").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsOntFclRequestForm;
	}
	
	public static WsWelcomeLetterForm toParser_WS_WELCOME_LETTER(String result){
		WsWelcomeLetterForm wsWelcomeLetterForm=new WsWelcomeLetterForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsWelcomeLetterForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsWelcomeLetterForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsWelcomeLetterForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsWelcomeLetterForm.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setADDRL1(eElement.getElementsByTagName("ADDRL1").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setADDRL2(eElement.getElementsByTagName("ADDRL2").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setADDRL3(eElement.getElementsByTagName("ADDRL3").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setPRODUCTFLAG(eElement.getElementsByTagName("PRODUCTFLAG").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setAPP_FORMNO(eElement.getElementsByTagName("APP_FORMNO").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setDAY(eElement.getElementsByTagName("DAY").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setMONTH(eElement.getElementsByTagName("MONTH").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setYEAR(eElement.getElementsByTagName("YEAR").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setPLPHRASE(eElement.getElementsByTagName("PLPHRASE").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setCREDITSHIELD(eElement.getElementsByTagName("CREDITSHIELD").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setCOMP_NAME(eElement.getElementsByTagName("COMP_NAME").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setMOBILE(eElement.getElementsByTagName("MOBILE").item(0).getTextContent());
	            	   wsWelcomeLetterForm.setHOMEPHONE(eElement.getElementsByTagName("HOMEPHONE").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsWelcomeLetterForm;
	}
	
	
	public static WsAutoDebitForm toParser_WS_AUTO_DEBIT_FORM(String result){
		WsAutoDebitForm wsAutoDebitFrom=new WsAutoDebitForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsAutoDebitFrom.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsAutoDebitFrom.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsAutoDebitFrom.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsAutoDebitFrom.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsAutoDebitFrom.setCUSTOMER_NAME(eElement.getElementsByTagName("CUSTOMER_NAME").item(0).getTextContent());
	            	   wsAutoDebitFrom.setDATE_OF_BIRTH(eElement.getElementsByTagName("DATE_OF_BIRTH").item(0).getTextContent());
	            	   wsAutoDebitFrom.setNATIONAL_ID(eElement.getElementsByTagName("NATIONAL_ID").item(0).getTextContent());
					   wsAutoDebitFrom.setISSUED_DATE(DateUtils.toPattern(eElement.getElementsByTagName("ISSUED_DATE").item(0).getTextContent(),DateUtils.ddMMyy,DateUtils.ddFMMFyyyy));
	            	   wsAutoDebitFrom.setISSUED_PLACE(eElement.getElementsByTagName("ISSUED_PLACE").item(0).getTextContent());
	            	   wsAutoDebitFrom.setMAILING_ADDRESS(eElement.getElementsByTagName("MAILING_ADDRESS").item(0).getTextContent());
	            	   wsAutoDebitFrom.setMOBIPHONE_NUMBER(eElement.getElementsByTagName("MOBIPHONE_NUMBER").item(0).getTextContent());
	            	   wsAutoDebitFrom.setACCOUNT_NO(eElement.getElementsByTagName("ACCOUNT_NO").item(0).getTextContent());
	            	   wsAutoDebitFrom.setSECOND_EMI_AMOUNT(DateUtils.getFormattedNumber(eElement.getElementsByTagName("SECOND_EMI_AMOUNT").item(0).getTextContent(),"#,###"));
	            	   wsAutoDebitFrom.setCODING(eElement.getElementsByTagName("CODING").item(0).getTextContent());
	            	   wsAutoDebitFrom.setLOAN_NO(eElement.getElementsByTagName("LOAN_NO").item(0).getTextContent());
	            	   wsAutoDebitFrom.setDUE_1_5(eElement.getElementsByTagName("DUE_1_5").item(0).getTextContent());
	            	   wsAutoDebitFrom.setFIRST_DUE_DATE(DateUtils.toPattern(eElement.getElementsByTagName("FIRST_DUE_DATE").item(0).getTextContent(),DateUtils.yyMMdd,DateUtils.ddFMMFyyyy));
	            	   wsAutoDebitFrom.setBRANCH_NAME(eElement.getElementsByTagName("BRANCH_NAME").item(0).getTextContent());
	            	   wsAutoDebitFrom.setBANK_NAME(eElement.getElementsByTagName("BANK_NAME").item(0).getTextContent());
	            	   wsAutoDebitFrom.setBANK_CODE(eElement.getElementsByTagName("BANK_CODE").item(0).getTextContent());
	            	   wsAutoDebitFrom.setTEXT_AMOUNT(DateUtils.fSoThanhChu(Long.parseLong(eElement.getElementsByTagName("SECOND_EMI_AMOUNT").item(0).getTextContent())));
	               }
	            }
	         }	
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsAutoDebitFrom;
	}
	
	public static WsGenDisbReqTopUpForm toParser_WS_GEN_DISB_REQ_TOPUP_V10(String result){
		WsGenDisbReqTopUpForm wsGenDisbReqTopUpForm=new WsGenDisbReqTopUpForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsGenDisbReqTopUpForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsGenDisbReqTopUpForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsGenDisbReqTopUpForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsGenDisbReqTopUpForm.setAPPLID (eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setDOB(eElement.getElementsByTagName("DOB").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setPAN_NO(eElement.getElementsByTagName("PAN_NO").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setPAN_NO_ISSUED_DATE(eElement.getElementsByTagName("PAN_NO_ISSUED_DATE").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setPASSPORTNO(eElement.getElementsByTagName("PASSPORTNO").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setPASSPORT_ISSUED_DATE(eElement.getElementsByTagName("PASSPORT_ISSUED_DATE").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setNEW_AGREEMENTNO(eElement.getElementsByTagName("NEW_AGREEMENTNO").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setNEW_DISB_DATE(eElement.getElementsByTagName("NEW_DISB_DATE").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setDISBAMT(eElement.getElementsByTagName("DISBAMT").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setLOANAMOUNTTEXT(eElement.getElementsByTagName("LOANAMOUNTTEXT").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setACCOUNT_NO(eElement.getElementsByTagName("ACCOUNT_NO").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setBANK_NAME(eElement.getElementsByTagName("BANK_NAME").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setBRANCH_NAME(eElement.getElementsByTagName("BRANCH_NAME").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setFCL_AMOUNT_OLDAPP(eElement.getElementsByTagName("FCL_AMOUNT_OLDAPP").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setFCL_AMOUNT_TEXT(eElement.getElementsByTagName("FCL_AMOUNT_TEXT").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setOLD_AGREEMENTNO(eElement.getElementsByTagName("OLD_AGREEMENTNO").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setOLDAPP(eElement.getElementsByTagName("OLDAPP").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setDISBDATE_OLD(eElement.getElementsByTagName("DISBDATE_OLD").item(0).getTextContent());
	            	   wsGenDisbReqTopUpForm.setRO_NAME(eElement.getElementsByTagName("RO_NAME").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsGenDisbReqTopUpForm;
	}
	
	
	public static WsOverdueEmiFclForm toParser_WS_OVERDUE_EMI_FCL(String result){
		WsOverdueEmiFclForm wsOverdueEmiFclForm=new WsOverdueEmiFclForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsOverdueEmiFclForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsOverdueEmiFclForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsOverdueEmiFclForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsOverdueEmiFclForm.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setEMI_NUM(eElement.getElementsByTagName("EMI_NUM").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setEMI(eElement.getElementsByTagName("EMI").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setEMI_RECD(eElement.getElementsByTagName("EMI_RECD").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setOVERDUE_EMI(eElement.getElementsByTagName("OVERDUE_EMI").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setPRINCIPAL(eElement.getElementsByTagName("PRINCIPAL").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setPRINCIPAL_RECD(eElement.getElementsByTagName("PRINCIPAL_RECD").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setOVERDUE_PRIN(eElement.getElementsByTagName("OVERDUE_PRIN").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setINTEREST(eElement.getElementsByTagName("INTEREST").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setINTEREST_RECD(eElement.getElementsByTagName("INTEREST_RECD").item(0).getTextContent());
	            	   wsOverdueEmiFclForm.setOVERDUE_INT(eElement.getElementsByTagName("OVERDUE_INT").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsOverdueEmiFclForm;
	}
	
	public static WsRejectLetterForm toParser_WS_REJECT_LETTER(String result){
		WsRejectLetterForm wsRejectLetterForm=new WsRejectLetterForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        List<RejectLetterForm> lstRejectLetterForm=new ArrayList<RejectLetterForm>();
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsRejectLetterForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsRejectLetterForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsRejectLetterForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            		   NodeList nNode_records = doc.getElementsByTagName("REJ_APPL");
	       	              for (int j = 0; j < nNode_records.getLength(); j++) {
	       	            	 Node nNode_record = nNode_records.item(j);
	       	            	if (nNode_record.getNodeType() == Node.ELEMENT_NODE) {
	       	            		Element eElement_record = (Element) nNode_record;
	       	            		RejectLetterForm rejectLetterForm=new RejectLetterForm();
	       	            		rejectLetterForm.setAPPLID(eElement_record.getElementsByTagName("APPLID").item(0).getTextContent());
	       	            		rejectLetterForm.setDAY(eElement_record.getElementsByTagName("DAY").item(0).getTextContent());
	       	            		rejectLetterForm.setMONTH(eElement_record.getElementsByTagName("MONTH").item(0).getTextContent());
	       	            		rejectLetterForm.setYEAR(eElement_record.getElementsByTagName("YEAR").item(0).getTextContent());
	       	            		rejectLetterForm.setCUSTOMERNAME(eElement_record.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	       	            		rejectLetterForm.setLAA_APP_FORMNO(eElement_record.getElementsByTagName("LAA_APP_FORMNO").item(0).getTextContent());
	       	            		rejectLetterForm.setADDRESSID(eElement_record.getElementsByTagName("ADDRESSID").item(0).getTextContent());
	       	            		rejectLetterForm.setADDR1(eElement_record.getElementsByTagName("ADDR1").item(0).getTextContent());
	       	            		rejectLetterForm.setADDR2(eElement_record.getElementsByTagName("ADDR2").item(0).getTextContent());
	       	            		rejectLetterForm.setSTATEDESC(eElement_record.getElementsByTagName("STATEDESC").item(0).getTextContent());
	       	            		rejectLetterForm.setRUN_DATE(eElement_record.getElementsByTagName("RUN_DATE").item(0).getTextContent());
	       	            		lstRejectLetterForm.add(rejectLetterForm);
	       	            	}
						}
	            	   wsRejectLetterForm.setLstReject(lstRejectLetterForm);
	            }
	        }
	        }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsRejectLetterForm;
	}
	
	
	public static WsCertificateMgateForm toParser_WS_CERTIFICATE_MGAGE(String result){
		WsCertificateMgateForm wsCertificateMgateForm=new WsCertificateMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        List<RepaymentForm> lstRepaymentForm=new ArrayList<RepaymentForm>();
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsCertificateMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsCertificateMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsCertificateMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsCertificateMgateForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAPPLICATION_DATE(eElement.getElementsByTagName("APPLICATION_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setFINISH_DATE(eElement.getElementsByTagName("FINISH_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAPPROVER(eElement.getElementsByTagName("APPROVER").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAPPROVER_TITLE(eElement.getElementsByTagName("APPROVER_TITLE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAPPLICANT_NAME_1(eElement.getElementsByTagName("APPLICANT_NAME_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_NO_1(eElement.getElementsByTagName("VALID_ID_NO_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_DATE_1(eElement.getElementsByTagName("VALID_ID_DATE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_ISSUED_PLACE_1(eElement.getElementsByTagName("VALID_ID_ISSUED_PLACE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setCITIZEN_ID_1(eElement.getElementsByTagName("CITIZEN_ID_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_CITIZEN_ID_DATE_1(eElement.getElementsByTagName("VALID_CITIZEN_ID_DATE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_CITIZEN_ID_ISSUED_PLACE_1(eElement.getElementsByTagName("VALID_CITIZEN_ID_ISSUED_PLACE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setCURRENT_RESIDENCE_1(eElement.getElementsByTagName("CURRENT_RESIDENCE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAPPLICANT_NAME_2(eElement.getElementsByTagName("APPLICANT_NAME_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_NO_2(eElement.getElementsByTagName("VALID_ID_NO_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_DATE_2(eElement.getElementsByTagName("VALID_ID_DATE_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_ID_ISSUED_PLACE_2(eElement.getElementsByTagName("VALID_ID_ISSUED_PLACE_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setCITIZEN_ID_2(eElement.getElementsByTagName("CITIZEN_ID_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_CITIZEN_ID_DATE_2(eElement.getElementsByTagName("VALID_CITIZEN_ID_DATE_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setVALID_CITIZEN_ID_ISSUED_PLACE_2(eElement.getElementsByTagName("VALID_CITIZEN_ID_ISSUED_PLACE_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setCURRENT_RESIDENCE_2(eElement.getElementsByTagName("CURRENT_RESIDENCE_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAMOUNT_LOAN(eElement.getElementsByTagName("AMOUNT_LOAN").item(0).getTextContent());
	            	   wsCertificateMgateForm.setTENOR_LOAN(eElement.getElementsByTagName("TENOR_LOAN").item(0).getTextContent());
	            	   wsCertificateMgateForm.setAMOUNT_LOAN_WORDS(eElement.getElementsByTagName("AMOUNT_LOAN_WORDS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setINTEREST_RATE_YEAR(eElement.getElementsByTagName("INTEREST_RATE_YEAR").item(0).getTextContent());
	            	   wsCertificateMgateForm.setFIRST_PAYMENT_DATE(eElement.getElementsByTagName("FIRST_PAYMENT_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setFIRST_EMI(eElement.getElementsByTagName("FIRST_EMI").item(0).getTextContent());
	            	   wsCertificateMgateForm.setFIRST_EMI_WORDS(eElement.getElementsByTagName("FIRST_EMI_WORDS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setDUE_DATE(eElement.getElementsByTagName("DUE_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setEMI(eElement.getElementsByTagName("EMI").item(0).getTextContent());
	            	   wsCertificateMgateForm.setEMI_WORDS(eElement.getElementsByTagName("EMI_WORDS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setLAST_EMI(eElement.getElementsByTagName("LAST_EMI").item(0).getTextContent());
	            	   wsCertificateMgateForm.setLAST_EMI_WORDS(eElement.getElementsByTagName("LAST_EMI_WORDS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setINTEREST_CHANGE_FREQUENCY(eElement.getElementsByTagName("INTEREST_CHANGE_FREQUENCY").item(0).getTextContent());
	            	   wsCertificateMgateForm.setPENALTY_RATE_1(eElement.getElementsByTagName("PENALTY_RATE_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setLAND_ADDRESS(eElement.getElementsByTagName("LAND_ADDRESS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setLAND_AREA(eElement.getElementsByTagName("LAND_AREA").item(0).getTextContent());
	            	   wsCertificateMgateForm.setPROP_OWSHIP_CERTI_NO(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_NO").item(0).getTextContent());
	            	   wsCertificateMgateForm.setPROP_OWSHIP_CERTI_ISSUER(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER").item(0).getTextContent());
	            	   wsCertificateMgateForm.setPROP_OWSHIP_CERTI_ISSUER_DATE(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setDOC_CONTACT_1(eElement.getElementsByTagName("DOC_CONTACT_1").item(0).getTextContent());
	            	   wsCertificateMgateForm.setDOC_CONTACT_2(eElement.getElementsByTagName("DOC_CONTACT_2").item(0).getTextContent());
	            	   wsCertificateMgateForm.setDOC_CONTACT_3(eElement.getElementsByTagName("DOC_CONTACT_3").item(0).getTextContent());
	            	   wsCertificateMgateForm.setDISBURSAL_DATE(eElement.getElementsByTagName("DISBURSAL_DATE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setINTEREST_RATE_MONTH(eElement.getElementsByTagName("INTEREST_RATE_MONTH").item(0).getTextContent());
	            	   wsCertificateMgateForm.setBANK_ACCOUNT_NO(eElement.getElementsByTagName("BANK_ACCOUNT_NO").item(0).getTextContent());
	            	   wsCertificateMgateForm.setBANK_NAME(eElement.getElementsByTagName("BANK_NAME").item(0).getTextContent());
	            	   wsCertificateMgateForm.setBANK_BRANCH(eElement.getElementsByTagName("BANK_BRANCH").item(0).getTextContent());
	            	   wsCertificateMgateForm.setADDRESS(eElement.getElementsByTagName("ADDRESS").item(0).getTextContent());
	            	   wsCertificateMgateForm.setMOBILE_PHONE(eElement.getElementsByTagName("MOBILE_PHONE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setHOME_PHONE(eElement.getElementsByTagName("HOME_PHONE").item(0).getTextContent());
	            	   wsCertificateMgateForm.setEMAIL(eElement.getElementsByTagName("EMAIL").item(0).getTextContent());
	            	   NodeList nList_Records = doc.getElementsByTagName("Records");
	            	   for (int i = 0; i < nList_Records.getLength(); i++) {
	            		   NodeList nNode_records = doc.getElementsByTagName("Record");
	       	              for (int j = 0; j < nNode_records.getLength(); j++) {
	       	            	 Node nNode_record = nNode_records.item(j);
	       	            	if (nNode_record.getNodeType() == Node.ELEMENT_NODE) {
	       	            		Element eElement_record = (Element) nNode_record;
	       	            		RepaymentForm repayment_scheduler=new RepaymentForm();
	       	            		repayment_scheduler.setTENURE(eElement_record.getElementsByTagName("TENURE").item(0).getTextContent());
	       	            		repayment_scheduler.setDUE_DATE(eElement_record.getElementsByTagName("DUE_DATE").item(0).getTextContent());
	       	            		repayment_scheduler.setMONTLY_INSTALMENT(eElement_record.getElementsByTagName("MONTLY_INSTALMENT").item(0).getTextContent());
	       	            		repayment_scheduler.setPAYABLE_INTEREST(eElement_record.getElementsByTagName("PAYABLE_INTEREST").item(0).getTextContent());
	       	            		repayment_scheduler.setDEDUCTED_PRINCIPAL(eElement_record.getElementsByTagName("DEDUCTED_PRINCIPAL").item(0).getTextContent());
	       	            		repayment_scheduler.setOUTSTANDING_PRINCIPAL(eElement_record.getElementsByTagName("OUTSTANDING_PRINCIPAL").item(0).getTextContent());
	       	            		lstRepaymentForm.add(repayment_scheduler);
	       	            	}
						}
					}
	            	   wsCertificateMgateForm.setListpaymet(lstRepaymentForm); 	
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsCertificateMgateForm;
	}
	
	
	
	public static WsHandoverDocMgateForm toParser_WS_HANDOVER_DOC_MGAGE(String result){
		WsHandoverDocMgateForm wsHandoverDocMgateForm=new WsHandoverDocMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsHandoverDocMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsHandoverDocMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsHandoverDocMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsHandoverDocMgateForm.setAPPLICANT_NAME(eElement.getElementsByTagName("APPLICANT_NAME").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setDOB(eElement.getElementsByTagName("DOB").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setVALID_ID_NO(eElement.getElementsByTagName("VALID_ID_NO").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setVALID_ID_DATE(eElement.getElementsByTagName("VALID_ID_DATE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setVALID_ID_ISSUED_PLACE(eElement.getElementsByTagName("VALID_ID_ISSUED_PLACE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setCURRENT_RESIDENCE(eElement.getElementsByTagName("CURRENT_RESIDENCE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setAPPLICATION_DATE(eElement.getElementsByTagName("APPLICATION_DATE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setFINISH_DATE(eElement.getElementsByTagName("FINISH_DATE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setFINISH_DATE_MORTGAGE(eElement.getElementsByTagName("FINISH_DATE_MORTGAGE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setPROP_OWSHIP_RECORD_NO(eElement.getElementsByTagName("PROP_OWSHIP_RECORD_NO").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setPROP_OWSHIP_CERTI_ISSUER(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setPROP_OWSHIP_CERTI_ISSUER_DATE(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER_DATE").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setDOC_CONTACT_1(eElement.getElementsByTagName("DOC_CONTACT_1").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setDOC_CONTACT_2(eElement.getElementsByTagName("DOC_CONTACT_2").item(0).getTextContent());
	            	   wsHandoverDocMgateForm.setDOC_CONTACT_3(eElement.getElementsByTagName("DOC_CONTACT_3").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsHandoverDocMgateForm;
	}
	
	
	public static WsAgreementMgateForm toParser_WS_AGREEMENT_MGAGE(String result){
		WsAgreementMgateForm wsAgreementMgateForm=new WsAgreementMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsAgreementMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsAgreementMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsAgreementMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsAgreementMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsAgreementMgateForm.setFINISH_DATE_MORTGAGE(eElement.getElementsByTagName("FINISH_DATE_MORTGAGE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAPPLICANT_NAME_1(eElement.getElementsByTagName("APPLICANT_NAME_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setDOB_1(eElement.getElementsByTagName("DOB_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_ID_NO_1(eElement.getElementsByTagName("VALID_ID_NO_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_ID_DATE_1(eElement.getElementsByTagName("VALID_ID_DATE_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setCITIZEN_ID_1(eElement.getElementsByTagName("CITIZEN_ID_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_CITIZEN_ID_DATE_1(eElement.getElementsByTagName("VALID_CITIZEN_ID_DATE_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setCURRENT_RESIDENCE_1(eElement.getElementsByTagName("CURRENT_RESIDENCE_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setMOBILE_PHONE_1(eElement.getElementsByTagName("MOBILE_PHONE_1").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAPPLICANT_NAME_2(eElement.getElementsByTagName("APPLICANT_NAME_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setDOB_2(eElement.getElementsByTagName("DOB_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_ID_NO_2(eElement.getElementsByTagName("VALID_ID_NO_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_ID_DATE_2(eElement.getElementsByTagName("VALID_ID_DATE_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setCITIZEN_ID_2(eElement.getElementsByTagName("CITIZEN_ID_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setVALID_CITIZEN_ID_DATE_2(eElement.getElementsByTagName("VALID_CITIZEN_ID_DATE_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setCURRENT_RESIDENCE_2(eElement.getElementsByTagName("CURRENT_RESIDENCE_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setMOBILE_PHONE_2(eElement.getElementsByTagName("MOBILE_PHONE_2").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAPPROVER(eElement.getElementsByTagName("APPROVER").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAPPROVER_TITLE(eElement.getElementsByTagName("APPROVER_TITLE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsAgreementMgateForm.setAPPLICATION_DATE(eElement.getElementsByTagName("APPLICATION_DATE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setFINISH_DATE(eElement.getElementsByTagName("FINISH_DATE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_OWSHIP_CERTI_NO(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_NO").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_OWSHIP_CERTI_ISSUER(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_OWSHIP_CERTI_ISSUER_DATE(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER_DATE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_PLOT_NO(eElement.getElementsByTagName("LAND_PLOT_NO").item(0).getTextContent());
	            	   wsAgreementMgateForm.setMAP_NO(eElement.getElementsByTagName("MAP_NO").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_ADDRESS(eElement.getElementsByTagName("LAND_ADDRESS").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_AREA(eElement.getElementsByTagName("LAND_AREA").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_AREA_WORDS(eElement.getElementsByTagName("LAND_AREA_WORDS").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPRIVATE_LAND_AREA(eElement.getElementsByTagName("PRIVATE_LAND_AREA").item(0).getTextContent());
	            	   wsAgreementMgateForm.setCOMMON_LAND_AREA(eElement.getElementsByTagName("COMMON_LAND_AREA").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_USAGE_PURPOSE(eElement.getElementsByTagName("LAND_USAGE_PURPOSE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_USAGE_TIMELINE(eElement.getElementsByTagName("LAND_USAGE_TIMELINE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_USAGE_ORIGINATION(eElement.getElementsByTagName("LAND_USAGE_ORIGINATION").item(0).getTextContent());
	            	   wsAgreementMgateForm.setLAND_USAGE_LIMITATION(eElement.getElementsByTagName("LAND_USAGE_LIMITATION").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_ATT_LAND_ADDR(eElement.getElementsByTagName("PROP_ATT_LAND_ADDR").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_ATT_LAND_FLOOR_AREAR(eElement.getElementsByTagName("PROP_ATT_LAND_FLOOR_AREAR").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_ATT_LAND_STRUCTURE(eElement.getElementsByTagName("PROP_ATT_LAND_STRUCTURE").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROP_ATT_LAND_FLOOR_NUMBER(eElement.getElementsByTagName("PROP_ATT_LAND_FLOOR_NUMBER").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROPERTY_VALUATION(eElement.getElementsByTagName("PROPERTY_VALUATION").item(0).getTextContent());
	            	   wsAgreementMgateForm.setPROPERTY_VALUATION_WORDS(eElement.getElementsByTagName("PROPERTY_VALUATION_WORDS").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsAgreementMgateForm;
	}
	
	
	public static WsInsuranceMgateForm toParser_WS_INSURANCE_MGAGE(String result){
		WsInsuranceMgateForm wsInsuranceMgateForm=new WsInsuranceMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsInsuranceMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsInsuranceMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsInsuranceMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsInsuranceMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsInsuranceMgateForm.setNAME_INSURRANCE_COMPANNY(eElement.getElementsByTagName("NAME_INSURRANCE_COMPANNY").item(0).getTextContent());
	            	   wsInsuranceMgateForm.setINSURANCE_POLICY_NO(eElement.getElementsByTagName("INSURANCE_POLICY_NO").item(0).getTextContent());
	            	   wsInsuranceMgateForm.setINSURANCE_POLICY_DATE(eElement.getElementsByTagName("INSURANCE_POLICY_DATE").item(0).getTextContent());
	            	   wsInsuranceMgateForm.setAPPLICANT_NAME_1(eElement.getElementsByTagName("APPLICANT_NAME_1").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsInsuranceMgateForm;
	}
	
	
	public static WsReceiptMgateForm toParser_WS_RECEIPT_MGAGE(String result){
		WsReceiptMgateForm wsReceiptMgateForm=new WsReceiptMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsReceiptMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsReceiptMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsReceiptMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsReceiptMgateForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsReceiptMgateForm.setAPPLICATION_DATE(eElement.getElementsByTagName("APPLICATION_DATE").item(0).getTextContent());
	            	   wsReceiptMgateForm.setCUSTOMERNAME(eElement.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent());
	            	   wsReceiptMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsReceiptMgateForm.setFINISH_DATE_MORTGAGE(eElement.getElementsByTagName("FINISH_DATE_MORTGAGE").item(0).getTextContent());
	            	   wsReceiptMgateForm.setVOUCHERDATE(eElement.getElementsByTagName("VOUCHERDATE").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsReceiptMgateForm;
	}
	
	
	public static WsRegistrationMgateForm toParser_WS_REGISTRATION_MGAGE(String result){
		WsRegistrationMgateForm wsRegistrationMgateForm=new WsRegistrationMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsRegistrationMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsRegistrationMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsRegistrationMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsRegistrationMgateForm.setAPPLICANT_NAME_1(eElement.getElementsByTagName("APPLICANT_NAME_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setCURRENT_RESIDENCE_1(eElement.getElementsByTagName("CURRENT_RESIDENCE_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setMOBILE_PHONE_1(eElement.getElementsByTagName("MOBILE_PHONE_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_NO_1(eElement.getElementsByTagName("VALID_ID_NO_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_ISSUED_PLACE_1(eElement.getElementsByTagName("VALID_ID_ISSUED_PLACE_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_DATE_1(eElement.getElementsByTagName("VALID_ID_DATE_1").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setAPPLICANT_NAME_2(eElement.getElementsByTagName("APPLICANT_NAME_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setCURRENT_RESIDENCE_2(eElement.getElementsByTagName("CURRENT_RESIDENCE_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setMOBILE_PHONE_2(eElement.getElementsByTagName("MOBILE_PHONE_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_NO_2(eElement.getElementsByTagName("VALID_ID_NO_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_ISSUED_PLACE_2(eElement.getElementsByTagName("VALID_ID_ISSUED_PLACE_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setVALID_ID_DATE_2(eElement.getElementsByTagName("VALID_ID_DATE_2").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setLAND_PLOT_NO(eElement.getElementsByTagName("LAND_PLOT_NO").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setMAP_NO(eElement.getElementsByTagName("MAP_NO").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setLAND_ADDRESS(eElement.getElementsByTagName("LAND_ADDRESS").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setLAND_AREA(eElement.getElementsByTagName("LAND_AREA").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setLAND_AREA_WORDS(eElement.getElementsByTagName("LAND_AREA_WORDS").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_OWSHIP_CERTI_NO(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_NO").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_OWSHIP_RECORD_NO(eElement.getElementsByTagName("PROP_OWSHIP_RECORD_NO").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_OWSHIP_CERTI_ISSUER(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_OWSHIP_CERTI_ISSUER_DATE(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER_DATE").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_ATTACHED_LAND_ADDR(eElement.getElementsByTagName("PROP_ATTACHED_LAND_ADDR").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_ATTACHED_LAND_USAGE(eElement.getElementsByTagName("PROP_ATTACHED_LAND_USAGE").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_ATTACHED_LAND_FLOOR_AREAR(eElement.getElementsByTagName("PROP_ATTACHED_LAND_FLOOR_AREAR").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_ATTACHED_LAND_STRUCTURE(eElement.getElementsByTagName("PROP_ATTACHED_LAND_STRUCTURE").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setPROP_ATTACHED_LAND_FLOOR_NUMBER(eElement.getElementsByTagName("PROP_ATTACHED_LAND_FLOOR_NUMBER").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsRegistrationMgateForm.setFINISH_DATE_MORTGAGE(eElement.getElementsByTagName("FINISH_DATE_MORTGAGE").item(0).getTextContent());
	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsRegistrationMgateForm;
	}
	
	
	
	public static WsRecordMgateForm toParser_WS_RECORD_MGAGE(String result){
		WsRecordMgateForm wsRecordMgateForm=new WsRecordMgateForm();
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(result);
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Body");
	        System.out.println("----------------------------");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               wsRecordMgateForm.setResult_Code(eElement.getElementsByTagName("Result_Code").item(0).getTextContent());
	               wsRecordMgateForm.setResult_Msg(eElement.getElementsByTagName("Result_Msg").item(0).getTextContent());
	               if(wsRecordMgateForm.getResult_Code().equals(Constant.WEBSERVICE_CODE_SUCCESS)){
	            	   wsRecordMgateForm.setAPPLID(eElement.getElementsByTagName("APPLID").item(0).getTextContent());
	            	   wsRecordMgateForm.setAPPLICATION_DATE(eElement.getElementsByTagName("APPLICATION_DATE").item(0).getTextContent());
	            	   wsRecordMgateForm.setAGREEMENTNO(eElement.getElementsByTagName("AGREEMENTNO").item(0).getTextContent());
	            	   wsRecordMgateForm.setFINISH_DATE(eElement.getElementsByTagName("FINISH_DATE").item(0).getTextContent());
	            	   wsRecordMgateForm.setAPPLICANT_NAME(eElement.getElementsByTagName("APPLICANT_NAME").item(0).getTextContent());
	            	   wsRecordMgateForm.setADDRESS_COLLATERAL(eElement.getElementsByTagName("ADDRESS_COLLATERAL").item(0).getTextContent());
	            	   wsRecordMgateForm.setVALID_ID_NO(eElement.getElementsByTagName("VALID_ID_NO").item(0).getTextContent());
	            	   wsRecordMgateForm.setRESIDENCE_BOOK_NO(eElement.getElementsByTagName("RESIDENCE_BOOK_NO").item(0).getTextContent());
	            	   wsRecordMgateForm.setMORTGAGE_AGREEMENT_NO(eElement.getElementsByTagName("MORTGAGE_AGREEMENT_NO").item(0).getTextContent());
	            	   wsRecordMgateForm.setFINISH_DATE_MORTGAGE(eElement.getElementsByTagName("FINISH_DATE_MORTGAGE").item(0).getTextContent());
	            	   wsRecordMgateForm.setPROP_OWSHIP_RECORD_NO(eElement.getElementsByTagName("PROP_OWSHIP_RECORD_NO").item(0).getTextContent());
	            	   wsRecordMgateForm.setPROP_OWSHIP_CERTI_ISSUER(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER").item(0).getTextContent());
	            	   wsRecordMgateForm.setPROP_OWSHIP_CERTI_ISSUER_DATE(eElement.getElementsByTagName("PROP_OWSHIP_CERTI_ISSUER_DATE").item(0).getTextContent());
	            	   wsRecordMgateForm.setDOC_CONTACT_1(eElement.getElementsByTagName("DOC_CONTACT_1").item(0).getTextContent());
	            	   wsRecordMgateForm.setDOC_CONTACT_2(eElement.getElementsByTagName("DOC_CONTACT_2").item(0).getTextContent());
	            	   wsRecordMgateForm.setDOC_CONTACT_3(eElement.getElementsByTagName("DOC_CONTACT_3").item(0).getTextContent());

	               }
	            }
	         }	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wsRecordMgateForm;
	}
	
}
