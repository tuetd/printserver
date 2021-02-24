package com.pruvn.tools.printserver.webapp.service.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.StringUtils;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


import com.pruvn.tools.common.hibernate.dwh.service.DWHouseService;
import com.pruvn.tools.common.hibernate.finnone.cas.LEA_REPAYSCH_DTL;
import com.pruvn.tools.common.hibernate.finnone.fa.LOS_APP_APPLICATIONS_FA;
import com.pruvn.tools.common.hibernate.finnone.fa.hibernate.Los_App_Applications_DAO;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PRINTSRV_PRINTED_LOAN_M;
import com.pruvn.tools.common.hibernate.finnone.printsrv.V_WS_LOAN_STATUS;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.Printsrv_Printed_Loan_M_DAO;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.V_Ws_Loan_Status_DAO;
import com.pruvn.tools.common.hibernate.finnone.service.FinnOneService;
import com.pruvn.tools.common.util.Constant;
import com.pruvn.tools.common.util.GlobalConstant;
import com.pruvn.tools.common.util.PDFUtil;
import com.pruvn.tools.common.util.UtilConverter;
import com.pruvn.tools.printserver.AuditlogDAO;
import com.pruvn.tools.printserver.BookmarkmasterDAO;
import com.pruvn.tools.printserver.DatasourcemasterDAO;
import com.pruvn.tools.printserver.DocmasterDAO;
import com.pruvn.tools.printserver.FieldmasterDAO;
import com.pruvn.tools.printserver.FieldtypemasterDAO;
import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.RecordsetmasterDAO;
import com.pruvn.tools.printserver.ServerconfigDAO;
import com.pruvn.tools.printserver.SqlquerymasterDAO;
import com.pruvn.tools.printserver.StatusmasterDAO;
import com.pruvn.tools.printserver.SystemtypemasterDAO;
import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Auditlog;
import com.pruvn.tools.printserver.pojo.Bookmarkmaster;
import com.pruvn.tools.printserver.pojo.Datasourcemaster;
import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.pojo.Fieldmaster;
import com.pruvn.tools.printserver.pojo.Fieldtypemaster;
import com.pruvn.tools.printserver.pojo.Parammaster;
import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;
import com.pruvn.tools.printserver.pojo.Recordsetmaster;
import com.pruvn.tools.printserver.pojo.Serverconfig;
import com.pruvn.tools.printserver.pojo.Sqlquerymaster;
import com.pruvn.tools.printserver.pojo.Systemtypemaster;
import com.pruvn.tools.printserver.pojo.Userdocprinter;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.ExcelExportEngine;
import com.pruvn.tools.printserver.webapp.editor.SQLQueryDto;
import com.pruvn.tools.printserver.webapp.service.DocumentPrintingEngineService;
import com.pruvn.tools.utils.DateUtils;
import com.pruvn.tools.utils.FileUtil;
import com.pruvn.tools.utils.OOHandle;

public class DocumentPrintingEngineServiceImpl implements DocumentPrintingEngineService{
	private static Logger logger = Logger.getLogger(DocumentPrintingEngineServiceImpl.class); 
	 private UsermasterDAO usermasterDAO;
	 private DocmasterDAO docmasterDAO;
	 private UserdocprinterDAO userdocprinterDAO;
	 private ServerconfigDAO serverconfigDAO;
	 private Printsrv_Printed_Loan_M_DAO printsrvPrintedLoanMDAO;
	 private V_Ws_Loan_Status_DAO vWsLoanStatusDAO;
	 private Los_App_Applications_DAO  losAppApplicationsDAO;
	 private ParammasterDAO parammasterDAO;
	 private SystemtypemasterDAO systemtypemasterDAO;
	 private FieldmasterDAO fieldmasterDAO;
	 private FieldtypemasterDAO fieldtypemasterDAO;
	 private DatasourcemasterDAO datasourcemasterDAO;
	 private SqlquerymasterDAO sqlquerymasterDAO;
	 private BookmarkmasterDAO bookmarkmasterDAO;
	 private FinnOneService finnoneDAO;
	 private DWHouseService dWHouseService;
	 private StatusmasterDAO statusmasterDAO;
	 private RecordsetmasterDAO recordsetmasterDAO;
	 private ExcelExportEngine excelExportEngine;
	 private AuditlogDAO auditlogDAO;
	 
	 
	

	public FileInputStream printDocument(String documentname,
			List<String> paramlist, List<String> paramnamelist,
			List<String> paramtypelist)  throws Exception{
		Auditlog auditlog = new Auditlog();
		auditlog.setAction("Print Document");
		auditlog.setCreatedDate(new Date());

		try {
			List<Object> paramValues = new ArrayList<Object>();
			String agreementnoForFCLRequest = null;
			if (null != paramlist && paramlist.size()>0) {
				for (int i = 0; i < paramnamelist.size(); i++)	{
					if (GlobalConstant.DATATYPE_NUMBER.equals(paramtypelist.get(i))) {
						if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_INFO_CARD)) || documentname.equals(Constant.docMapping.get(Constant.DOCNAME_INFO_CARD_IMPORT)))
							paramValues.add(UtilConverter.convertStringToBigDecimal(paramlist.get(i), new BigDecimal(-1)));
						else
							paramValues.add(UtilConverter.convertStringToBigDecimal(paramlist.get(i), null));
					}
					else {
						paramValues.add(paramlist.get(i));
					}				
				}	
			}
			
			Docmaster docmaster= docmasterDAO.findByName(documentname).get(0);
			Usermaster usermaster=usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			Userdocprinter userdocprinter = userdocprinterDAO.getUserDocPrinterByDocAndUser(docmaster,usermaster);
			FileInputStream fis = null;
			logger.info("documentname"+documentname+"--docmaster"+docmaster.getName()+"---userdocprinter"+userdocprinter.getPrintername());
			// Anh Ha added on 14Sep2011 
			// Check & update one-time print for Sale Finance per Huynh Kim Binh request
		
			PRINTSRV_PRINTED_LOAN_M loan = new PRINTSRV_PRINTED_LOAN_M();
			if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_FINAL_SANCTION_LETTER)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_SF_VERSION_6_1_CERTIFICATE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_SF_PHU_LUC_1_V6_1))) {
				loan.setAPP_ID_C(Integer.parseInt((paramValues.get(0).toString()).trim()));
				loan.setDOC_NAME(documentname);
				loan.setPRINTED_USER(SecurityContextHolder.getContext().getAuthentication().getName());
				loan.setPRINTED_DATE(Calendar.getInstance().getTime());
				
				checkPrintedDoc(loan);
			}// End added by Anh Ha on 14Sep2011
			if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_REPAYMENT_SCHEDULE)) ||
					documentname.equals(Constant.docMapping.get(Constant.SF_VERSION_5_PROMISSORY_NOTES)) ||
					documentname.equals(Constant.docMapping.get(Constant.SF_VERSION_5_CERTIFICATE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_DISBURSAL_REQUEST)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_PROMISSORY_NOTE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)+" - CELP") ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_AUTODEBIT_FORM)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_AUTHORIZE_FORM))) {
				loankitChecking(paramValues.get(0).toString());
			}
			
			Docmaster docmaster1;			
			if (documentname.equals(Constant.docMapping.get(Constant.RO_REPAY_SCH)) || 
					documentname.equals(Constant.docMapping.get(Constant.RO_PROM_NOTE)) ||
					documentname.equals(Constant.docMapping.get(Constant.RO_CERTIFICATE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_REPAYMENT_SCHEDULE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_PROMISSORY_NOTE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)+" - CELP") ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_AUTODEBIT_FORM)) ||
					documentname.equals(Constant.docMapping.get(Constant.DOCNAME_AUTHORIZE_FORM))) {
				docmaster1 = docmasterDAO.findByName(roPrinting(paramValues.get(0).toString(),documentname)).get(0);
			} else if(documentname.equals(Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_ESTIMATED))){
				// check docname fcl credit shield
				docmaster1 = docmasterDAO.findByName(checkCreditShield(paramValues.get(0).toString(),paramValues.get(2).toString())).get(0);
			}
			else {
				docmaster1 = docmasterDAO.findByName(documentname).get(0);
			}
			
			Datasourcemaster datasourcemaster=datasourcemasterDAO.getById(docmaster1.getDatasourceId());
			Serverconfig serverconfig = serverconfigDAO.getById(datasourcemaster.getServerconfigid());
			Systemtypemaster conneSystem= systemtypemasterDAO.getById(serverconfig.getTypeid());
		
			
			
			List<String> fieldNames = new ArrayList<String>();
			List<String> fieldTypes = new ArrayList<String>();		
			List<Fieldmaster> iteratorfield = fieldmasterDAO.findByDatasourceid(docmaster1.getDatasourceId());
			for (Fieldmaster fieldmaster : iteratorfield) {
				if (null == fieldmaster.getSqlqueryid()) {
					continue;
				}
				Fieldtypemaster fieldtypemaster= fieldtypemasterDAO.getById(fieldmaster.getTypeid());
				fieldNames.add(fieldmaster.getName());
				fieldTypes.add(fieldtypemaster.getName());
			}
		
			
			//SR 452
			if (documentname.equalsIgnoreCase(Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_ESTIMATED))){
				String checkFCL = finnoneDAO.checkDueDate(paramValues.get(0).toString() , paramValues.get(2).toString());
				if(!"0".equalsIgnoreCase(checkFCL)){
					throw new Exception(checkFCL);
				}
				
				String fclPercent = finnoneDAO.printFCLPercent(paramValues.get(0).toString());
				if("-1".equalsIgnoreCase(fclPercent)){
					throw new Exception("Error when get percent for FCL");
				}else{
					paramValues.set(5, fclPercent);	
				}
				
				String currDate = paramValues.get(1).toString();
				GregorianCalendar cal = new GregorianCalendar();				
				currDate = DateUtils.toDateString(cal.getTime(), DateUtils.ddMMyyyy);
				paramValues.set(1, currDate);
				
				List<Double> listFCL = finnoneDAO.getDataFCL(paramValues.get(0).toString() , paramValues.get(2).toString(), fclPercent);
				if(listFCL!=null && listFCL.size()>0){
					//15072015
					double chck = listFCL.get(1) + listFCL.get(2) + listFCL.get(3) + listFCL.get(4) + listFCL.get(6) - listFCL.get(5);
					if(chck<=0){
						throw new Exception("Please contact CS team for more information");
					}
					paramValues.set(3, listFCL.get(6));
					paramValues.set(4, listFCL.get(0));
					paramValues.set(5, listFCL.get(1));
					paramValues.set(6, listFCL.get(2) + listFCL.get(3));
					paramValues.set(7, listFCL.get(4));
					paramValues.set(8, listFCL.get(5));
				}else{
					throw new Exception("Please contact CS team for more information");
				}
			}
			
			System.out.println("datasourcemaster.getId()"+datasourcemaster.getId());
			List<Sqlquerymaster> setQuery = sqlquerymasterDAO.findByDatasourceid(datasourcemaster.getId());
			SQLQueryDto[] arrayQuery = new SQLQueryDto[setQuery.size()];
			
			Iterator<Sqlquerymaster> iteratorquery = setQuery.iterator();
			int queryCount = 0;
			while (iteratorquery.hasNext()) {
				Sqlquerymaster sqlquerymaster = iteratorquery.next();
				SQLQueryDto dto = new SQLQueryDto();
				dto.setINDEXNUM(sqlquerymaster.getIndexnum());
				dto.setTYPE(sqlquerymaster.getType());
				dto.setVALUE(sqlquerymaster.getValue());
				arrayQuery[queryCount] = dto;
				queryCount++;
			}
			Arrays.sort(arrayQuery);
			
			for (SQLQueryDto query : arrayQuery) {			
				if (new Integer(1).equals(query.getTYPE())) {
					//Execute insert/update/delete
					if (GlobalConstant.SYSTEMTYPE_ORACLEDB.equals(conneSystem.getName())) {
						if(serverconfig.getConfigcode().equalsIgnoreCase("FINNONE")){
							finnoneDAO.executeDDLQuery(query.getVALUE());
						}else if(serverconfig.getConfigcode().equalsIgnoreCase("DWH")){
							dWHouseService.executeDDLQuery(query.getVALUE());
						}
						continue;
					}
				}			
				if (new Integer(2).equals(query.getTYPE())) {
					//Execute store procedure
					System.out.println("query.getVALUE()"+query.getVALUE());
					if (GlobalConstant.SYSTEMTYPE_ORACLEDB.equals(conneSystem.getName())) {
						if(serverconfig.getConfigcode().equalsIgnoreCase("FINNONE")){
							finnoneDAO.executeStoredProcedure(query.getVALUE(), paramnamelist, paramValues);
						}else if(serverconfig.getConfigcode().equalsIgnoreCase("DWH")){
							dWHouseService.executeStoredProcedure(query.getVALUE(), paramnamelist, paramValues);
						}
						continue;
					}
				}
				
				List<Bookmarkmaster> setbookmark = bookmarkmasterDAO.findByDocid(docmaster1.getId());
				Parammaster parammaster = parammasterDAO.getParamByName(Constant.PS_DOC_PRT_PLC);		
				String closureDate = "";
				String month="", day="", year="";
				if (documentname.equalsIgnoreCase(Constant.docMapping.get(Constant.FORECLOSURE_FOR_RECOVERY)) || documentname.equalsIgnoreCase(Constant.docMapping.get(Constant.CLOSE_AGREEMENT_FOR_RECOVERY))) {
					closureDate = (String) paramValues.get(1);
					paramnamelist.remove(1);
					paramValues.remove(1);
					day = closureDate.substring(0, 2);
					month = closureDate.substring(2, 4);
					year = closureDate.substring(4, closureDate.length());
				}
				
				// Execute normal select query
				String[][] data=null;
						
						if (GlobalConstant.SYSTEMTYPE_ORACLEDB.equals(conneSystem.getName())) {
							if(serverconfig.getConfigcode().equalsIgnoreCase("FINNONE")){
								data=	finnoneDAO.executeQuery(query.getVALUE(), fieldNames, fieldTypes, paramnamelist, paramValues);
							}else if(serverconfig.getConfigcode().equalsIgnoreCase("DWH")){
								data=	dWHouseService.executeQuery(query.getVALUE(), fieldNames, fieldTypes, paramnamelist, paramValues);
							}
						}
						
						
				if (data.length == 0) {
					throw new Exception("No record founded");
				}
				// Only set data for value array
				if (docmaster1.getName().equals(Constant.docMapping.get(Constant.DOCNAME_INFO_CARD))) {
					data = processDataForInfoCard(data);
				}
				// Check to allow printing signed document or not
				String docname = docmaster1.getLocalfile();
				if (paramValues.size() > 0 && paramnamelist.contains("SIGNATURE_CEO")) {
					//update check no sign 1068 option yes/no 
					//docname = checkNosignDocument(docmaster, paramValues.get(0).toString());
					String check=paramValues.get(1).toString();
					if(check.isEmpty()){
						docname = docname.substring(0, docname.lastIndexOf(".")) + Constant.NOSIGN_DOCNAME_SUFFIX + docname.substring(docname.lastIndexOf("."));
					}
					//save no sign ceo to longnd database
					if(documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)) || documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE)+" - CELP")){
						saveNoSignatureWithCEO(new BigDecimal((paramValues.get(0).toString()).trim()),SecurityContextHolder.getContext().getAuthentication().getName(),documentname,check);
					}
					
				}
				
				
				for (int i = 0; i < data.length; i++) {
					List<Recordsetmaster> list = new ArrayList<Recordsetmaster>();
					Iterator<Bookmarkmaster> iteratorbookmark = setbookmark.iterator();
					String[] bookmarkNamesArray = new String[setbookmark.size()];
					String[] valuesArray = new String[setbookmark.size()];				

					int bookmarkcount = 0;
					Recordsetmaster recordsetmaster = new Recordsetmaster();
					List<Recordsetfieldlink> listrecord = new ArrayList<Recordsetfieldlink>();				
					while (iteratorbookmark.hasNext()) {
						Bookmarkmaster bookmarkmaster = iteratorbookmark.next();
						bookmarkNamesArray[bookmarkcount] = bookmarkmaster.getName();
						valuesArray[bookmarkcount] = getValueForBookmark(data[i], fieldNames, bookmarkmaster);
						Recordsetfieldlink recordsetfieldlink = new Recordsetfieldlink();
						recordsetfieldlink.setData(getValueForBookmark(data[i], fieldNames, bookmarkmaster));
						recordsetfieldlink.setFieldid(bookmarkmaster.getFieldid());
						recordsetfieldlink.setRecordsetid(recordsetmaster.getId());
						if (!checkContains(listrecord, recordsetfieldlink)) {
							listrecord.add(recordsetfieldlink);
						}
						bookmarkcount++;
						
						if ("AGREEMENTNO".equals(bookmarkmaster.getName())) {
							if (Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_1A3).equals(documentname) || Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_2).equals(documentname)
									|| Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_ESTIMATED).equals(documentname)) {
								agreementnoForFCLRequest = getValueForBookmark(data[i], fieldNames, bookmarkmaster);
							}
						}
					}
					// INFO CARD process
					if (docmaster1.getName().equals(Constant.docMapping.get(Constant.DOCNAME_INFO_CARD))) {
						valuesArray = data[i];
					}
					
					if (documentname.equalsIgnoreCase(Constant.docMapping.get(Constant.FORECLOSURE_FOR_RECOVERY)) || documentname.equalsIgnoreCase(Constant.docMapping.get(Constant.CLOSE_AGREEMENT_FOR_RECOVERY))) {
						int countIdx = 0;
						for (String item : bookmarkNamesArray) {
							if (item.equalsIgnoreCase("DAY")){
								valuesArray[countIdx] = day;
							}
							
							if (item.equalsIgnoreCase("MONTH")){
								valuesArray[countIdx] = month;
							}
							
							if (item.equalsIgnoreCase("YEAR")){
								valuesArray[countIdx] = year;
							}
							
							if (item.startsWith("CLOSURE")){
								valuesArray[countIdx] = day + "/" + month + "/" + year;
							}
							
							if (item.startsWith("VOUCHERDATE")) {
								valuesArray[countIdx] = day + "/" + month + "/" + year;
							}
							
							countIdx++;
						}
					}
					
					if (i == 0) {
						// Check to allow printing signed document or not
						fis = print(docname, bookmarkNamesArray, valuesArray, docmaster1, parammaster.getValue(), true);
					}
					else {
						fis = print(docname, bookmarkNamesArray, valuesArray, docmaster1, parammaster.getValue(), false);
					}
					
					// auditlog user printer docmaster
					StringBuilder input=new StringBuilder();
					StringBuilder output=new StringBuilder();
					input.append(SecurityContextHolder.getContext().getAuthentication().getName() + " - " + docname);
					input.append("\r\n");
					for (int k = 0; k < paramnamelist.size(); k++) {
						if(paramlist!=null&&paramlist.size()>0){
						input.append(paramnamelist.get(k)+": "+paramlist.get(k)+"\r\n");
						}
					}
					output.append(SecurityContextHolder.getContext().getAuthentication().getName() + " - " + docname);
					output.append("\r\n");
					for (int n = 0; n < bookmarkNamesArray.length; n++) {
						if(valuesArray!=null&&valuesArray.length>0){
						output.append(bookmarkNamesArray[n]+": "+valuesArray[n]+"\r\n");
						}
					}
					// auditlog user printer docmaster
					if(docmaster!=null){
						auditlog.setDetail(input.toString());
						auditlog.setEntityId(docmaster.getId());
						auditlog.setEntityName("DOCMASTER");
						auditlog.setDetailOut(output.toString());
						auditlogDAO.save(auditlog);
					}
					
					recordsetmaster.setDatetime(new Timestamp(new Date().getTime()));
					recordsetmaster.setUserid((usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
					recordsetmaster.setStatusid((statusmasterDAO.getStatus(Constant.STATUS_PRINTED).getId()));
					Set<Recordsetfieldlink> setrecord = new LinkedHashSet<Recordsetfieldlink>();
					setrecord.addAll(listrecord);	
					list.add(recordsetmaster);
				}
				
				
				
				if (null != userdocprinter.getPrintername()) {
					PDFUtil.printPDF(parammaster.getValue() + UtilConverter.getHttpSessionID() + ".pdf", userdocprinter.getPrintername());
				}
				if (Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_1A3).equals(documentname) || Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_2).equals(documentname)
						|| Constant.docMapping.get(Constant.DOCNAME_FCL_REQ_ESTIMATED).equals(documentname)) {
					Parammaster fclReqPDFFileLocParam = parammasterDAO.getParamByName(Constant.FCL_FORM_PLC);
					FileUtil.copy(parammaster.getValue() + UtilConverter.getHttpSessionID() + ".pdf", fclReqPDFFileLocParam.getValue() + agreementnoForFCLRequest + ".pdf");
				}	
				
			}
		            	// Anh Ha added on 14Sep2011 
						// Check & update one-time print for Sale Finance per Huynh Kim Binh request
						if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_FINAL_SANCTION_LETTER)) ||
								documentname.equals(Constant.docMapping.get(Constant.DOCNAME_SF_VERSION_6_1_CERTIFICATE)) ||
								documentname.equals(Constant.docMapping.get(Constant.DOCNAME_SF_PHU_LUC_1_V6_1))) {
							if (loan != null && !StringUtils.isEmptyOrWhitespaceOnly(loan.getDOC_NAME()) && loan.getAPP_ID_C() != null) {
//								loan.setAPP_ID_C(Integer.parseInt(((String) paramValues.get(0)).trim()));
//								loan.setDOC_NAME(documentname);
								loan.setPRINTED_USER(SecurityContextHolder.getContext().getAuthentication().getName());
								loan.setPRINTED_DATE(Calendar.getInstance().getTime());
								printsrvPrintedLoanMDAO.saveOrUpdate(loan);
							}
						}// End added by Anh Ha on 14Sep2011
			return fis;
		} finally {
			finnoneDAO.closeSession();
			dWHouseService.closeSession();
		}
	}
	
	
	

	
	
	
	
	// Check to allow printing signed document or not
	private String checkNosignDocument(Docmaster docmaster, String applid) {
		if (docmaster.getLocalfile().contains(Constant.NOSIGN_DOCNAME_SUFFIX)) {
			return docmaster.getLocalfile();
		}
		
		Boolean isNoSignApplid = finnoneDAO.checkNoSignDocument(applid);
		
		if (isNoSignApplid){
			String doc_code_to_print = Constant.docMappingReverse.get(docmaster.getName());
			
			if (finnoneDAO.checkEnableNoSignDocument(doc_code_to_print)) {				
				// Set local file to assign prefix _nosign to docname
				String docname = docmaster.getLocalfile();
				docname = docname.substring(0, docname.lastIndexOf(".")) + Constant.NOSIGN_DOCNAME_SUFFIX + docname.substring(docname.lastIndexOf("."));
				return docname;
			}
		}
		
		return docmaster.getLocalfile();
	}


	private String getAgreement(String[] bookmarkNamesArray, String[] valuesArray) throws Exception {
		for (int i = 0; i < bookmarkNamesArray.length; i++) {
			if ("AGREEMENTNO".equals(bookmarkNamesArray[i])) {
				return valuesArray[i];
			}
		}
		throw new Exception("Can not find agreement number");
	}
	
	private String[] preProcessDataBeforeExport(String[] valuesArray) {
		String[] ret = new String[4];
		if (valuesArray.length != 4) {
			for (int i = 0; i < ret.length; i++) {
				try {
					ret[i] = valuesArray[i];
				} catch (Exception e) {
					ret[i] = "";
				}
			}
		} else {
			return valuesArray;
		}
		
		return ret;
	}

	
	private FileInputStream print(String templatefile, String[] bookmarkNamesArray, String[] valuesArray, Docmaster docmaster, String docplace, 
			boolean first) throws Exception {							
		Parammaster parammaster = parammasterDAO.findByName(Constant.ONT_SIMPLE_SOA).get(0);
		if (docmaster.getName().equals(parammaster.getValue())) {
			excelExportEngine.exportSOA(getAgreement(bookmarkNamesArray, valuesArray), 
					SecurityContextHolder.getContext().getAuthentication().getName(), docmaster.getName(), templatefile, docplace);
		} else {
			if (docmaster.getName().equals(Constant.docMapping.get(Constant.DOCNAME_INFO_CARD)) && valuesArray != null && valuesArray.length > 0) {
				valuesArray = preProcessDataBeforeExport(valuesArray);
				String returnFilePath = excelExportEngine.exportInfoCard(valuesArray[0], valuesArray[2], 
						SecurityContextHolder.getContext().getAuthentication().getName(), docmaster.getName(), 
						valuesArray[1], valuesArray[3], null, null, first);
				if (first) {
					OOHandle.printPDFPOI("file:///" + returnFilePath, 
							"file:///" + docplace + UtilConverter.getHttpSessionID() + ".pdf");
				}
				else {
					OOHandle.printPDFPOI("file:///" + returnFilePath, 
							"file:///" + docplace + UtilConverter.getHttpSessionID() + 
							SecurityContextHolder.getContext().getAuthentication().getName()+ ".pdf");		
				}
			}else {
				if (first) {
					OOHandle.printPOI(bookmarkNamesArray, valuesArray, GlobalConstant.EXPORTPDF, 
						    parammasterDAO.getParamByName(Constant.PS_TMPL_PLC).getValue() + templatefile,
							docplace + UtilConverter.getHttpSessionID() + ".pdf");
					
				} else {
					OOHandle.printPOI(bookmarkNamesArray, valuesArray, GlobalConstant.EXPORTPDF, 
						    parammasterDAO.getParamByName(Constant.PS_TMPL_PLC).getValue() + templatefile,
							docplace + UtilConverter.getHttpSessionID() + 
							SecurityContextHolder.getContext().getAuthentication().getName()+ ".pdf");
				}
			}
		}
		
		if (!first) {
			List<String> filelist = new ArrayList<String>();
			filelist.add(docplace + UtilConverter.getHttpSessionID() + ".pdf");
			filelist.add(docplace + UtilConverter.getHttpSessionID() + 
					SecurityContextHolder.getContext().getAuthentication().getName()+ ".pdf");
			PDFUtil.mergePDF(filelist, docplace + UtilConverter.getHttpSessionID() + ".pdf");				
		}
		
		return new FileInputStream(docplace + UtilConverter.getHttpSessionID() + ".pdf");		
	}
	
	
	private String roPrinting(String applid,String documentname) throws Exception {
		try {
			String errorCode=finnoneDAO.errorCodeCheck(applid);
			if (!"0".equals(errorCode)) {			
				if ("1".equals(errorCode)) {						
					throw new Exception(Constant.DIFFERENTFLATRATE);					
				}
				else if ("2".equals(errorCode)) {						
					throw new Exception(Constant.APPLICATIONNOTEXIST);
				}
				else if ("3".equals(errorCode)) {						
					throw new Exception(Constant.NOTPERSONALLOAN);					
				}
				else if ("4".equals(errorCode)) {						
					throw new Exception(Constant.WRONGTENURE);					
				}
				else if ("5".equals(errorCode)) {						
					throw new Exception(Constant.NOTAPPROVED);					
				}
				else if ("6".equals(errorCode)) {						
					throw new Exception(Constant.NOREPAYMENTSCHEDULE);					
				}				
			}
			Parammaster parammaster=null;
			if(!parammasterDAO.findByName((Constant.RO_REPAY_SCH)).isEmpty()){
				 parammaster = parammasterDAO.findByName((Constant.RO_REPAY_SCH)).get(0);
			}
			
			if (documentname.equals(parammaster.getValue())) {
				LOS_APP_APPLICATIONS_FA los_app_applications = losAppApplicationsDAO.getApplicationByID(applid);			
				
				if (new Integer(12).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM12);
				}
				else if (new Integer(18).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM18);
				}
				else if (new Integer(24).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM24);
				}
				else if (new Integer(36).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM36);
				}
				else if (new Integer(48).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM48);
				}
				else if (new Integer(30).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.ROREPAYMENTTERM30);
				}
			}
			
			if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_REPAYMENT_SCHEDULE))) {				
				LOS_APP_APPLICATIONS_FA los_app_applications =losAppApplicationsDAO.getApplicationByID(applid);			
				
				if (new Integer(12).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS12);
				}
				else if (new Integer(18).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS18);
				}
				else if (new Integer(24).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS24);
				}
				else if (new Integer(36).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS36);
				}
				else if (new Integer(48).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS48);
				}
				else if (new Integer(30).equals(los_app_applications.getLaa_app_loandtl_term_n())) {
					documentname = Constant.docMapping.get(Constant.PLRS30);
				}
				
			}			
			
			if (documentname.equals(Constant.docMapping.get(Constant.DOCNAME_CERTIFICATE))) {
				com.pruvn.tools.common.hibernate.finnone.cas.LOS_APP_APPLICATIONS los_app_applications = 	(com.pruvn.tools.common.hibernate.finnone.cas.LOS_APP_APPLICATIONS) losAppApplicationsDAO.queryUnique(new String[]{"APP_ID_C"}, 
							new Object[]{applid}, new String[]{GlobalConstant.HIBERNATE_OPERATOR_EQUAL}, 
							com.pruvn.tools.common.hibernate.finnone.cas.LOS_APP_APPLICATIONS.class);
				LEA_REPAYSCH_DTL lea_repaysch_dtl = (LEA_REPAYSCH_DTL)losAppApplicationsDAO.queryUnique(new String[]{"PROPOSALID", "INSTLNUM"}, 
						new Object[]{Integer.valueOf(applid), los_app_applications.getLAA_APP_REQ_TERM_N().shortValue()}, 
						new String[]{GlobalConstant.HIBERNATE_OPERATOR_EQUAL, GlobalConstant.HIBERNATE_OPERATOR_EQUAL}, 
						LEA_REPAYSCH_DTL.class);				
				if (null != lea_repaysch_dtl && lea_repaysch_dtl.getINSTLAMT().equals(BigDecimal.ZERO)) {
					lea_repaysch_dtl = (LEA_REPAYSCH_DTL)losAppApplicationsDAO.queryUnique(new String[]{"PROPOSALID", "INSTLNUM"}, 
							new Object[]{Integer.valueOf(applid), (short)(los_app_applications.getLAA_APP_REQ_TERM_N().shortValue() - (short)1)}, 
							new String[]{GlobalConstant.HIBERNATE_OPERATOR_EQUAL, GlobalConstant.HIBERNATE_OPERATOR_EQUAL}, 
							LEA_REPAYSCH_DTL.class);				
					if (null != lea_repaysch_dtl && lea_repaysch_dtl.getINSTLAMT().equals(BigDecimal.ZERO)) {
						documentname = Constant.docMapping.get(Constant.PL_Certificate_Third);
					}
				}
			}
			return documentname;
		} finally {
		}
	}
	
	private boolean checkContains(List<Recordsetfieldlink> listrecord, Recordsetfieldlink recordsetfieldlink) {
		for (int i = 0; i < listrecord.size(); i++) {
			if (recordsetfieldlink.getFieldid().equals(listrecord.get(i).getFieldid())) {
				return true;
			}
		}
		return false;
	}
	
	private String getValueForBookmark(String[] array, List<String> fieldNames, Bookmarkmaster bookmarkmaster) {
		if ("USERDESC".equals(bookmarkmaster.getName())) {
			return (String)(GlobalConstant.SESSION_KEY_USER_DESC);
		}
		if ("USERPLACE".equals(bookmarkmaster.getName())) {
			return (String)(GlobalConstant.SESSION_KEY_USER_PLACE);
		}
		
		Fieldmaster fm=fieldmasterDAO.getById(bookmarkmaster.getFieldid());
		String fieldname = fm.getName(); 
		for (int i = 0; i < fieldNames.size(); i++) {
			if (fieldname.equals(fieldNames.get(i))) {
				return array[i];
			}
		}
		return null;
	}

	private void loankitChecking(String app_id_c) throws Exception{
		// TODO Auto-generated method stub
		V_WS_LOAN_STATUS v_ws_loan_status = vWsLoanStatusDAO.getWSLoanStatusByApplid(app_id_c);
		if (v_ws_loan_status == null) {
			throw new Exception(Constant.APPLICATIONNOTEXIST + " (application id: " + app_id_c + " )");
		}
		if (v_ws_loan_status.getPRINT_STATUS().equals(new BigDecimal(7))) {
			throw new Exception(Constant.APPLICATION_REACHED_FINISH_STAGE + " (application id: " + app_id_c + " )");
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) &&
					BigDecimal.ONE.equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.DIFFERENTFLATRATE + " (application id: " + app_id_c + " )");	
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) &&
					new BigDecimal(2).equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.APPLICATIONNOTEXIST + " (application id: " + app_id_c + " )");
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) && 
					new BigDecimal(3).equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.NOTPERSONALLOAN + " (application id: " + app_id_c + " )");	
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) &&
					new BigDecimal(4).equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.WRONGTENURE + " (application id: " + app_id_c + " )");
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) &&
					new BigDecimal(5).equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.NOTAPPROVED + " (application id: " + app_id_c + " )");
		}
		else if ("PERSONAL".equals(v_ws_loan_status.getNPM_LPP_PRODCAT_C()) &&
					new BigDecimal(6).equals(v_ws_loan_status.getOLD_PRINT_STATUS())) {						
			throw new Exception(Constant.NOREPAYMENTSCHEDULE + " (application id: " + app_id_c + " )");
		}	
	}


	private String[][] processDataForInfoCard(String[][] data) {
		String [][] ret = new String[1][1];
		String dataTemp = "";
		for (String[] item : data) {
			Arrays.sort(item);
			for (String e : item) {
				dataTemp += e + ",";
			}
		}
		
		dataTemp = dataTemp.substring(0, dataTemp.length() - 1);
		
		String[] test = dataTemp.split(",");
		List<String[]> lst = new ArrayList<String[]>();
		lst.add(test);
		
		lst.toArray(ret);
		
		return ret;
	}

	private String checkCreditShield(String applid,String runDate) throws Exception{
	String name_doc="";
	String code=finnoneDAO.checkCreditShield(applid,runDate);
		if ("0".equals(code)) {	
			name_doc= "Foreclosure Request Estimated";
		}else if("1".equals(code)){
			name_doc= "Foreclosure Request Estimated Credit Shield";
		}	
	return name_doc;	
	}
	
	
	private void saveNoSignatureWithCEO(BigDecimal applid,String username,String docname,String no_sign) throws Exception {
		try {
			String sql = "{call longnd.P_PRT_NO_SIGNATURE(?,?,?,?)}";
			List<Object> param_values = new ArrayList<Object>();
			param_values.add(applid);
			param_values.add(username);
			param_values.add(docname);
			param_values.add(no_sign);
			List<String> name = new ArrayList<String>();
			name.add("p_applid");
			name.add("p_username");
			name.add("p_docname");
			name.add("p_no_sign");
			dWHouseService.executeStoredProcedure(sql,name,param_values);
		} finally {
			dWHouseService.closeSession();
		}
		
	}
	
	private void checkPrintedDoc(PRINTSRV_PRINTED_LOAN_M loan) throws Exception {
		printsrvPrintedLoanMDAO.checkPrintedDoc(loan);
		
	}

	public V_Ws_Loan_Status_DAO getvWsLoanStatusDAO() {
		return vWsLoanStatusDAO;
	}
	@Autowired
	public void setvWsLoanStatusDAO(V_Ws_Loan_Status_DAO vWsLoanStatusDAO) {
		this.vWsLoanStatusDAO = vWsLoanStatusDAO;
	}

	
	public ParammasterDAO getParammasterDAO() {
		return parammasterDAO;
	}

	@Autowired
	public void setParammasterDAO(ParammasterDAO parammasterDAO) {
		this.parammasterDAO = parammasterDAO;
	}


	public Los_App_Applications_DAO getLosAppApplicationsDAO() {
		return losAppApplicationsDAO;
	}

	@Autowired
	public void setLosAppApplicationsDAO(
			Los_App_Applications_DAO losAppApplicationsDAO) {
		this.losAppApplicationsDAO = losAppApplicationsDAO;
	}


	public Printsrv_Printed_Loan_M_DAO getPrintsrvPrintedLoanMDAO() {
		return printsrvPrintedLoanMDAO;
	}

	@Autowired
	public void setPrintsrvPrintedLoanMDAO(
			Printsrv_Printed_Loan_M_DAO printsrvPrintedLoanMDAO) {
		this.printsrvPrintedLoanMDAO = printsrvPrintedLoanMDAO;
	}


	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}

	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}


	public DocmasterDAO getDocmasterDAO() {
		return docmasterDAO;
	}

	@Autowired
	public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
		this.docmasterDAO = docmasterDAO;
	}


	public UserdocprinterDAO getUserdocprinterDAO() {
		return userdocprinterDAO;
	}

	@Autowired
	public void setUserdocprinterDAO(UserdocprinterDAO userdocprinterDAO) {
		this.userdocprinterDAO = userdocprinterDAO;
	}


	public ServerconfigDAO getServerconfigDAO() {
		return serverconfigDAO;
	}

	@Autowired
	public void setServerconfigDAO(ServerconfigDAO serverconfigDAO) {
		this.serverconfigDAO = serverconfigDAO;
	}


	public SystemtypemasterDAO getSystemtypemasterDAO() {
		return systemtypemasterDAO;
	}

	@Autowired
	public void setSystemtypemasterDAO(SystemtypemasterDAO systemtypemasterDAO) {
		this.systemtypemasterDAO = systemtypemasterDAO;
	}


	public FieldmasterDAO getFieldmasterDAO() {
		return fieldmasterDAO;
	}

	@Autowired
	public void setFieldmasterDAO(FieldmasterDAO fieldmasterDAO) {
		this.fieldmasterDAO = fieldmasterDAO;
	}


	public FieldtypemasterDAO getFieldtypemasterDAO() {
		return fieldtypemasterDAO;
	}

	@Autowired
	public void setFieldtypemasterDAO(FieldtypemasterDAO fieldtypemasterDAO) {
		this.fieldtypemasterDAO = fieldtypemasterDAO;
	}


	public DatasourcemasterDAO getDatasourcemasterDAO() {
		return datasourcemasterDAO;
	}

	@Autowired
	public void setDatasourcemasterDAO(DatasourcemasterDAO datasourcemasterDAO) {
		this.datasourcemasterDAO = datasourcemasterDAO;
	}


	public SqlquerymasterDAO getSqlquerymasterDAO() {
		return sqlquerymasterDAO;
	}

	@Autowired
	public void setSqlquerymasterDAO(SqlquerymasterDAO sqlquerymasterDAO) {
		this.sqlquerymasterDAO = sqlquerymasterDAO;
	}


	public FinnOneService getFinnoneDAO() {
		return finnoneDAO;
	}

	@Autowired
	public void setFinnoneDAO(FinnOneService finnoneDAO) {
		this.finnoneDAO = finnoneDAO;
	}


	public BookmarkmasterDAO getBookmarkmasterDAO() {
		return bookmarkmasterDAO;
	}

	@Autowired
	public void setBookmarkmasterDAO(BookmarkmasterDAO bookmarkmasterDAO) {
		this.bookmarkmasterDAO = bookmarkmasterDAO;
	}


	public StatusmasterDAO getStatusmasterDAO() {
		return statusmasterDAO;
	}

	@Autowired
	public void setStatusmasterDAO(StatusmasterDAO statusmasterDAO) {
		this.statusmasterDAO = statusmasterDAO;
	}


	public RecordsetmasterDAO getRecordsetmasterDAO() {
		return recordsetmasterDAO;
	}

	@Autowired
	public void setRecordsetmasterDAO(RecordsetmasterDAO recordsetmasterDAO) {
		this.recordsetmasterDAO = recordsetmasterDAO;
	}

	public ExcelExportEngine getExcelExportEngine() {
		return excelExportEngine;
	}

	public void setExcelExportEngine(ExcelExportEngine excelExportEngine) {
		this.excelExportEngine = excelExportEngine;
	}


	public DWHouseService getdWHouseService() {
		return dWHouseService;
	}

	@Autowired
	public void setdWHouseService(DWHouseService dWHouseService) {
		this.dWHouseService = dWHouseService;
	}

	
	public AuditlogDAO getAuditlogDAO() {
		return auditlogDAO;
	}

	@Autowired
	public void setAuditlogDAO(AuditlogDAO auditlogDAO) {
		this.auditlogDAO = auditlogDAO;
	}
	
	


	
	
	
	
	
}
