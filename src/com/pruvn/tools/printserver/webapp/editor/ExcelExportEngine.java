package com.pruvn.tools.printserver.webapp.editor;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jsystem.utils.StringUtils;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.tools.common.hibernate.finnone.cas.hibernate.Nbfc_Product_M_DAO;
import com.pruvn.tools.common.hibernate.finnone.fa.FA_VOUCHER_DTL;
import com.pruvn.tools.common.hibernate.finnone.fa.hibernate.Fa_Voucher_Dtl_DAO;
import com.pruvn.tools.common.hibernate.finnone.lending.LEA_AGREEMENT_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.LEA_RUU_UPLOADED_RECORD_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.NBFC_CHEQUE_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Lea_Agreement_Dtl_DAO;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Lea_Ruu_Uploaded_Record_Dtl_DAO;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Nbfc_Cheque_Dtl_DAO;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Nbfc_Txn_Charge_Dtl_DAO;
import com.pruvn.tools.common.hibernate.finnone.printsrv.ONT_SOA_M;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.Ont_Soa_M_DAO;
import com.pruvn.tools.common.util.GlobalConstant;
import com.pruvn.tools.common.util.JxlUtil;
import com.pruvn.tools.common.util.UtilConverter;
import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.pojo.Parammaster;
import com.pruvn.tools.utils.OOHandle;
import com.pruvn.tools.utils.ReportUtils;

public class ExcelExportEngine {
	private Lea_Agreement_Dtl_DAO leaAgreementDtlDAO;
	private Nbfc_Product_M_DAO nbfcProductMDAO;
	private ParammasterDAO parammasterDAO;
	private Nbfc_Txn_Charge_Dtl_DAO nbfcTxnChargeDtlDAO;
	private Ont_Soa_M_DAO ontSoaMDAO;
	private Nbfc_Cheque_Dtl_DAO nbfcChequeDtlDAO;
	private Lea_Ruu_Uploaded_Record_Dtl_DAO leaRuuUploadedRecordDtlDAO;
	private Fa_Voucher_Dtl_DAO faVoucherDtlDAO;
	public void exportSOA(String agreementno, String username, String docname,  String templatepath, 
			String printedplace) throws Exception{
			LEA_AGREEMENT_DTL lea_agreement_dtl = leaAgreementDtlDAO.getAgreementByNo(agreementno);
			if (null != lea_agreement_dtl) {
				if (!"AUTO".equals(nbfcProductMDAO.getProductByCode(lea_agreement_dtl.getPRODUCTFLAG()).getNPM_LPP_PRODCAT_C())) {
					exportPLSOA(agreementno, username, docname,    lea_agreement_dtl, templatepath, printedplace);
				}
				else {
					exportSFSOA(agreementno, username, docname, lea_agreement_dtl, templatepath, printedplace);
				}
			}
		
	}
	
	private void exportSFSOA(String agreementno, String username, String docname,
			 LEA_AGREEMENT_DTL lea_agreement_dtl, String templatepath, String printedplace) throws Exception {
			JxlUtil util = new JxlUtil();
			
			String sheetname = "SOA";
			WritableWorkbook workBook;
			
			Parammaster parammaster = parammasterDAO.getParamByName("PS_TMPL_PLC");
			if (null == templatepath) {
				templatepath = parammaster.getValue();
			}
			parammaster = parammasterDAO.getParamByName("PS_DOC_PRT_PLC");
			if (null == printedplace) {
				printedplace = parammaster.getValue();
			}
			
			Workbook wb = Workbook.getWorkbook(new File(templatepath + "SOATemplate_SF.xls"));		
			workBook = Workbook.createWorkbook(new File(printedplace + "SimpleSOA_SF" + agreementno + username + docname + ".xls"), wb);
			
			
			WritableCellFormat cf1 = util.createFormattedCell(8, WritableFont.ARIAL, false);
			WritableCellFormat cf2 = util.createFormattedCell(8, WritableFont.ARIAL, Alignment.RIGHT);
			WritableCellFormat cf3 = util.createFormattedCell(10, WritableFont.ARIAL, true);
			cf3.setAlignment(Alignment.RIGHT);
			cf3.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			BigDecimal deductDisbAmt = nbfcTxnChargeDtlDAO.getSumChargeByTnxIDAndDescLike("ADV EMI DEDUCTED FRM DISB", String.valueOf(lea_agreement_dtl.getAPPLID()));
			String status = "Đang hiệu lực";
			if (!"A".equals(lea_agreement_dtl.getSTATUS())) {
				status = "Hết hiệu lực";
			}
			
			ONT_SOA_M ont_soa_m = ontSoaMDAO.getOnTSOAMByAgreementNo(agreementno);		
			if (null != ont_soa_m) {
				util.addCellToSheet('C' - 'A', 3, ont_soa_m.getCUSTOMERNAME().toUpperCase(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', 4, ont_soa_m.getADDRL1().toUpperCase(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', 5, ont_soa_m.getADDRL2().toUpperCase(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', 6, ont_soa_m.getADDRL3().toUpperCase(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', 7, ont_soa_m.getCUSTOMERID().toPlainString(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', 8, ont_soa_m.getPHONE(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('I' - 'A', 2, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, new Date()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('F' - 'A', 4, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getDISBURSALDATE()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('H' - 'A', 4, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, new Date()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('G' - 'A', 7, ont_soa_m.getAGREEMENTNO(), cf1, workBook.getSheet(sheetname));			
				util.addCellToSheet('C' - 'A', 11, ont_soa_m.getPRODUCT() + " " + ont_soa_m.getPRODDESC(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('G' - 'A', 11, ont_soa_m.getTENURE().add(ont_soa_m.getADVEMI()).toPlainString(), cf1, workBook.getSheet(sheetname));
//				util.addCellToSheet('I' - 'A', 12, ont_soa_m.getADVEMI().toPlainString(), cf1, workBook.getSheet(sheetname));				
				util.addCellToSheet('I' - 'A', 12, UtilConverter.getFormattedNumber(deductDisbAmt, "###,###,###,###") + " VND", cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('G' - 'A', 13, ont_soa_m.getTENURE().toPlainString(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('F' - 'A', 14, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getREPAYMENTFIRST()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('H' - 'A', 14, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getREPAYMENTLAST()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('D' - 'A', 18, UtilConverter.getFormattedNumber(ont_soa_m.getLOANAMOUNT(), "###,###,###,###"), cf2, workBook.getSheet(sheetname));
				util.addCellToSheet('D' - 'A', 19, UtilConverter.getFormattedNumber(ont_soa_m.getFIRSTAMOUNT(), "###,###,###,###"), cf2, workBook.getSheet(sheetname));
				util.addCellToSheet('D' - 'A', 20, UtilConverter.getFormattedNumber(ont_soa_m.getSEMIFINALAMT(), "###,###,###,###"), cf2, workBook.getSheet(sheetname));
				util.addCellToSheet('D' - 'A', 21, UtilConverter.getFormattedNumber(ont_soa_m.getFINALAMT(), "###,###,###,###"), cf2, workBook.getSheet(sheetname));
				util.addCellToSheet('I' - 'A', 19, ont_soa_m.getDUEDATE(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('I' - 'A', 20, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getREPAYMENTFIRST()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('I' - 'A', 18, status, cf1, workBook.getSheet(sheetname));
			}
			
			util.addCellToSheet('B' - 'A', 3, "Khách hàng", cf1, workBook.getSheet(sheetname));
			util.addCellToSheet('H' - 'A', 2, "Ngày", cf1, workBook.getSheet(sheetname));
			util.addCellToSheet('C' - 'A', 12, "Hàng tháng", cf1, workBook.getSheet(sheetname));
			util.addCellToSheet('B' - 'A', 26, "Ngày thanh toán", cf1, workBook.getSheet(sheetname));
			util.addCellToSheet('C' - 'A', 26, "Phiếu thu", cf1, workBook.getSheet(sheetname));
			util.addCellToSheet('D' - 'A', 26, "Số tiền thanh toán (VND)", cf2, workBook.getSheet(sheetname));
			
			BigDecimal sum = new BigDecimal(0);
			
			List<NBFC_CHEQUE_DTL> chequelist = nbfcChequeDtlDAO.getChequeListByCustIDLikeChequeNumOrderByChequeDate(
					lea_agreement_dtl.getLESSEEID()	, "RFHD" + lea_agreement_dtl.getAGREEMENTNO());
			chequelist.addAll(nbfcChequeDtlDAO.getChequeListByCustIDLikeChequeNumOrderByChequeDate(lea_agreement_dtl.getLESSEEID(), 
					"RF" + lea_agreement_dtl.getAGREEMENTNO()));
			
			
			
			List<LEA_RUU_UPLOADED_RECORD_DTL> list = leaRuuUploadedRecordDtlDAO.getUploadRecordListWithAgreementNoAndNotEQBankAccOrderUploadDateNormal(agreementno, new Integer(17));
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				List<FA_VOUCHER_DTL> listvoucher = faVoucherDtlDAO.getVoucherDtlList(list.get(i).getChequeid());
				if (checkReversal(listvoucher)) {
					continue;
				}
				while (true) {
					if (chequelist.isEmpty() || list.get(i).getReceipt_date().compareTo(chequelist.get(0).getCHEQUEDATE()) < 0) {
						break;
					}					
					util.addCellToSheet('B' - 'A', count + 27, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, chequelist.get(0).getCHEQUEDATE()), cf1, workBook.getSheet(sheetname));
					util.addCellToSheet('C' - 'A', count + 27, chequelist.get(0).getCHEQUENUM() + " - Prudential hoàn trả tiền dư", cf1, workBook.getSheet(sheetname));
					util.addCellToSheet('D' - 'A', count + 27, "(" + UtilConverter.getFormattedNumber(chequelist.get(0).getCHEQUEAMT(), "###,###,###,###") + ")", cf2, workBook.getSheet(sheetname));
					sum = sum.subtract(chequelist.get(0).getCHEQUEAMT());
					chequelist.remove(0);					
					count++;
				}
				util.addCellToSheet('B' - 'A', count + 27, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, list.get(i).getReceipt_date()), cf1, workBook.getSheet(sheetname));
				
				if (list.get(i).getRemarks() != null)
					util.addCellToSheet('C' - 'A', count + 27, list.get(i).getCheque_no() + "_" + list.get(i).getRemarks(), cf1, workBook.getSheet(sheetname));
				else
					util.addCellToSheet('C' - 'A', count + 27, list.get(i).getCheque_no(), cf1, workBook.getSheet(sheetname));
				
				util.addCellToSheet('D' - 'A', count + 27, UtilConverter.getFormattedNumber(list.get(i).getReceipt_amt(), "###,###,###,###"), cf2, workBook.getSheet(sheetname));
				sum = sum.add(list.get(i).getReceipt_amt());
				count++;
			}
			for (int i = 0; i < chequelist.size(); i++) {
				util.addCellToSheet('B' - 'A', count + 27, UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, chequelist.get(i).getCHEQUEDATE()), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('C' - 'A', count + 27, chequelist.get(i).getCHEQUENUM() + " - Prudential hoàn trả tiền dư", cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('D' - 'A', count + 27, "(" + UtilConverter.getFormattedNumber(chequelist.get(i).getCHEQUEAMT(), "###,###,###,###") + ")", cf2, workBook.getSheet(sheetname));
				sum = sum.subtract(chequelist.get(i).getCHEQUEAMT());
				count++;
			}
			
			util.addCellToSheet('C' - 'A', count + 27, "Tổng cộng", cf3, workBook.getSheet(sheetname));
			util.addCellToSheet('D' - 'A', count + 27, UtilConverter.getFormattedNumber(sum, "###,###,###,###"), cf3, workBook.getSheet(sheetname));
					
			workBook.write();
			workBook.close();
			wb.close();

			OOHandle.printPDFPOI(printedplace + "SimpleSOA_SF" + agreementno + username + docname + ".xls", 
					printedplace + UtilConverter.getHttpSessionID() + ".pdf");
			
	}
	
	private boolean checkReversal(List<FA_VOUCHER_DTL> listvoucher) {
		for (int i = 0; i < listvoucher.size(); i++) {
			if (listvoucher.get(i).getCramt().compareTo(new BigDecimal(0)) > 0) {
				return true;
			}
		}
		return false;
	}
	
	private void exportPLSOA(String agreementno, String username, String docname,  
			 LEA_AGREEMENT_DTL lea_agreement_dtl, String templatepath, String printedplace) throws Exception{
			Parammaster parammaster = parammasterDAO.getParamByName("PS_TMPL_PLC");
			if (null != parammaster) {
				templatepath = parammaster.getValue() + templatepath;
			}
			parammaster = parammasterDAO.getParamByName("PS_DOC_PRT_PLC");
			if (null == printedplace) {
				printedplace = parammaster.getValue();
			}
			
			String status = "Đang hiệu lực";
			if (!"A".equals(lea_agreement_dtl.getSTATUS())) {
				status = "Hết hiệu lực";
			}
			
			String CUSTOMERNAME = "";
			String ADDRL1 = "";
			String ADDRL2 = "";
			String ADDRL3 = "";
			String CUSTOMERID = "";
			String PHONE = "";
			String CURR_DATE = "";
			String DISBURSALDATE = "";
			String AGREEMENTNO = "";
			String BRANCHDESC = "";
			String PRODUCT = "";
			String TENURE = "";
			String REPAYMENTFIRST = "";
			String REPAYMENTLAST = "";
			String LOANAMOUNT = "";
			String FIRSTAMOUNT = "";
			String SEMIFINALAMT = "";
			String FINALAMT = "";
			String DUEDATE = "";
			String P_STATUS = "";
			
			List<ChequeDto> dtos = new ArrayList<ChequeDto>();
			
			ONT_SOA_M ont_soa_m = ontSoaMDAO.getOnTSOAMByAgreementNo(agreementno);		
			if (null != ont_soa_m) {
				CUSTOMERNAME = ont_soa_m.getCUSTOMERNAME().toUpperCase();
				ADDRL1 = ont_soa_m.getADDRL1().toUpperCase();
				ADDRL2 = ont_soa_m.getADDRL2().toUpperCase();
				ADDRL3 = ont_soa_m.getADDRL3().toUpperCase();
				CUSTOMERID = ont_soa_m.getCUSTOMERID().toPlainString();
				PHONE = ont_soa_m.getPHONE();
				CURR_DATE = UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, new Date());
				DISBURSALDATE = UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getDISBURSALDATE());
				AGREEMENTNO = ont_soa_m.getAGREEMENTNO();
				BRANCHDESC = ont_soa_m.getBRANCHDESC();
				PRODUCT = ont_soa_m.getPRODUCT();
				TENURE = ont_soa_m.getTENURE().toPlainString();
				REPAYMENTFIRST = UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getREPAYMENTFIRST());
				REPAYMENTLAST = UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, ont_soa_m.getREPAYMENTLAST());
				LOANAMOUNT = UtilConverter.getFormattedNumber(ont_soa_m.getLOANAMOUNT(), "###,###,###,###");
				FIRSTAMOUNT = UtilConverter.getFormattedNumber(ont_soa_m.getFIRSTAMOUNT(), "###,###,###,###");
				SEMIFINALAMT = UtilConverter.getFormattedNumber(ont_soa_m.getSEMIFINALAMT(), "###,###,###,###");
				FINALAMT = UtilConverter.getFormattedNumber(ont_soa_m.getFINALAMT(), "###,###,###,###");
				DUEDATE = ont_soa_m.getDUEDATE();
				P_STATUS = status;
			}
			
			BigDecimal sum = new BigDecimal(0);
			
			List<NBFC_CHEQUE_DTL> chequelist = nbfcChequeDtlDAO.getChequeListByCustIDLikeChequeNumOrderByChequeDate(
					lea_agreement_dtl.getLESSEEID()	, "RFHD" + lea_agreement_dtl.getAGREEMENTNO());
			chequelist.addAll(nbfcChequeDtlDAO.getChequeListByCustIDLikeChequeNumOrderByChequeDate(lea_agreement_dtl.getLESSEEID(), 
					"RF" + lea_agreement_dtl.getAGREEMENTNO()));
			
			List<LEA_RUU_UPLOADED_RECORD_DTL> list = leaRuuUploadedRecordDtlDAO.getUploadRecordListWithAgreementNoAndNotEQBankAccOrderUploadDateNormal(agreementno, new Integer(17));
			for (int i = 0; i < list.size(); i++) {
				List<FA_VOUCHER_DTL> listvoucher = faVoucherDtlDAO.getVoucherDtlList(list.get(i).getChequeid());
				if (checkReversal(listvoucher)) {
					continue;
				}
				while (true) {
					if (chequelist.isEmpty() || list.get(i).getReceipt_date().compareTo(chequelist.get(0).getCHEQUEDATE()) < 0) {
						break;
					}					
					ChequeDto dto = new ChequeDto();
					dto.setChequeamt("(" + UtilConverter.getFormattedNumber(chequelist.get(0).getCHEQUEAMT(), "###,###,###,###") + ")");
					dto.setChequedate(UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, chequelist.get(0).getCHEQUEDATE()));
					dto.setChequenum(chequelist.get(0).getCHEQUENUM() + " - Prudential hoàn trả tiền dư");
					dtos.add(dto);					
					sum = sum.subtract(chequelist.get(0).getCHEQUEAMT());
					chequelist.remove(0);				
				}
				
				ChequeDto dto = new ChequeDto();
				dto.setChequedate(UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, list.get(i).getReceipt_date()));
				if (list.get(i).getRemarks() != null) {
					dto.setChequenum(list.get(i).getCheque_no() + "_" + list.get(i).getRemarks());
				} else {
					dto.setChequenum(list.get(i).getCheque_no());
				}
				dto.setChequeamt(UtilConverter.getFormattedNumber(list.get(i).getReceipt_amt(), "###,###,###,###"));
				dtos.add(dto);
				
				sum = sum.add(list.get(i).getReceipt_amt());
			}		
			for (int i = 0; i < chequelist.size(); i++) {
				ChequeDto dto = new ChequeDto();
				dto.setChequeamt("(" + UtilConverter.getFormattedNumber(chequelist.get(i).getCHEQUEAMT(), "###,###,###,###") + ")");
				dto.setChequedate(UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, chequelist.get(i).getCHEQUEDATE()));
				dto.setChequenum(chequelist.get(i).getCHEQUENUM() + " - Prudential hoàn trả tiền dư");
				dtos.add(dto);		
				
				sum = sum.subtract(chequelist.get(i).getCHEQUEAMT());
			}
						
			HashMap<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("P_CUSTOMERNAME", CUSTOMERNAME);
			parameterMap.put("P_ADDRL1", ADDRL1);
			parameterMap.put("P_ADDRL2", ADDRL2);
			parameterMap.put("P_ADDRL3", ADDRL3);
			parameterMap.put("P_CUSTOMERID", CUSTOMERID);
			parameterMap.put("P_PHONE", PHONE);
			parameterMap.put("P_CURR_DATE", CURR_DATE);
			parameterMap.put("P_DISBURSALDATE", DISBURSALDATE);
			parameterMap.put("P_AGREEMENTNO", AGREEMENTNO);
			parameterMap.put("P_BRANCHDESC", BRANCHDESC);
			parameterMap.put("P_PRODUCT", PRODUCT);
			parameterMap.put("P_TENURE", TENURE);
			parameterMap.put("P_REPAYMENTFIRST", REPAYMENTFIRST);
			parameterMap.put("P_REPAYMENTLAST", REPAYMENTLAST);
			parameterMap.put("P_LOANAMOUNT", LOANAMOUNT);
			parameterMap.put("P_FIRSTAMOUNT", FIRSTAMOUNT);
			parameterMap.put("P_SEMIFINALAMT", SEMIFINALAMT);
			parameterMap.put("P_FINALAMT", FINALAMT);
			parameterMap.put("P_DUEDATE", DUEDATE);
			parameterMap.put("P_STATUS", P_STATUS);
			parameterMap.put("P_CHEQUETOTAL", UtilConverter.getFormattedNumber(sum, "###,###,###,###"));
			
			ReportUtils.xuatReportWithCollectionDataSourcePDF(templatepath, parameterMap, printedplace, "pdf", 
					 UtilConverter.getHttpSessionID(), dtos);
	}

	public String exportInfoCard(String agreementno1, String agreementno2, String username, String docname,
			String customername1, String customername2, String templatepath, String printedplace, boolean isFirst) throws Exception {
			JxlUtil util = new JxlUtil();
			
			String sheetname = "InfoCard";
			WritableWorkbook workBook;
			
			Parammaster parammaster = null;
			if (null == templatepath) {
				parammaster = parammasterDAO.getParamByName("PS_TMPL_PLC");
				templatepath = parammaster.getValue();
			}
			
			if (null == printedplace) {
				parammaster = parammasterDAO.getParamByName("PS_DOC_PRT_PLC");
				printedplace = parammaster.getValue();
			}
			
			String tmpAgreementNo1 = agreementno1;
			if (StringUtils.isEmpty(agreementno1)) {
				tmpAgreementNo1 = "empty";
			}
			
			String tmpAgreementNo2 = agreementno2;
			if (StringUtils.isEmpty(agreementno2)) {
				tmpAgreementNo2 = "empty";
			}
			
			Workbook wb = Workbook.getWorkbook(new File(templatepath + "Info_Card.xls"));		
			workBook = Workbook.createWorkbook(new File(printedplace + "Info_Card_" + tmpAgreementNo1 + "_" + tmpAgreementNo2 + "_" + username + "_" + docname + ".xls"), wb);
			
			
			WritableCellFormat cf1 = util.createFormattedCell(12, WritableFont.ARIAL, true);
			WritableCellFormat cf3 = util.createFormattedCell(12, WritableFont.ARIAL, true);
			cf3.setAlignment(Alignment.LEFT);
			cf3.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			cf1.setAlignment(Alignment.CENTRE);
			cf1.setVerticalAlignment(VerticalAlignment.CENTRE);
						
			
			if (!StringUtils.isEmpty(agreementno1)) {
				util.addCellToSheet('A' - 'A', 0, customername1.trim().toUpperCase(), cf3, workBook.getSheet(sheetname));
				util.addCellToSheet('B' - 'A', 1, agreementno1, cf3, workBook.getSheet(sheetname));
			}else {
				util.addCellToSheet('A' - 'A', 0, "", cf3, workBook.getSheet(sheetname));
				util.addCellToSheet('B' - 'A', 1, "", cf3, workBook.getSheet(sheetname));
			}
			
			if (!StringUtils.isEmpty(agreementno2)) {
				util.addCellToSheet('G' - 'A', 0, customername2.trim().toUpperCase(), cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('G' - 'A', 1, agreementno2, cf1, workBook.getSheet(sheetname));
			} else {
				util.addCellToSheet('G' - 'A', 0, "", cf1, workBook.getSheet(sheetname));
				util.addCellToSheet('G' - 'A', 1, "", cf1, workBook.getSheet(sheetname));
			}
			
			
			workBook.write();
			workBook.close();
			wb.close();
			
			return printedplace + "Info_Card_" + tmpAgreementNo1 + "_" + tmpAgreementNo2 + "_" + username + "_" + docname + ".xls";
			
	}

	public Nbfc_Product_M_DAO getNbfcProductMDAO() {
		return nbfcProductMDAO;
	}
	@Autowired
	public void setNbfcProductMDAO(Nbfc_Product_M_DAO nbfcProductMDAO) {
		this.nbfcProductMDAO = nbfcProductMDAO;
	}

	public ParammasterDAO getParammasterDAO() {
		return parammasterDAO;
	}
	@Autowired
	public void setParammasterDAO(ParammasterDAO parammasterDAO) {
		this.parammasterDAO = parammasterDAO;
	}

	public Nbfc_Txn_Charge_Dtl_DAO getNbfcTxnChargeDtlDAO() {
		return nbfcTxnChargeDtlDAO;
	}
	@Autowired
	public void setNbfcTxnChargeDtlDAO(Nbfc_Txn_Charge_Dtl_DAO nbfcTxnChargeDtlDAO) {
		this.nbfcTxnChargeDtlDAO = nbfcTxnChargeDtlDAO;
	}

	public Ont_Soa_M_DAO getOntSoaMDAO() {
		return ontSoaMDAO;
	}
	@Autowired
	public void setOntSoaMDAO(Ont_Soa_M_DAO ontSoaMDAO) {
		this.ontSoaMDAO = ontSoaMDAO;
	}

	public Nbfc_Cheque_Dtl_DAO getNbfcChequeDtlDAO() {
		return nbfcChequeDtlDAO;
	}
	@Autowired
	public void setNbfcChequeDtlDAO(Nbfc_Cheque_Dtl_DAO nbfcChequeDtlDAO) {
		this.nbfcChequeDtlDAO = nbfcChequeDtlDAO;
	}

	public Lea_Ruu_Uploaded_Record_Dtl_DAO getLeaRuuUploadedRecordDtlDAO() {
		return leaRuuUploadedRecordDtlDAO;
	}
	@Autowired
	public void setLeaRuuUploadedRecordDtlDAO(
			Lea_Ruu_Uploaded_Record_Dtl_DAO leaRuuUploadedRecordDtlDAO) {
		this.leaRuuUploadedRecordDtlDAO = leaRuuUploadedRecordDtlDAO;
	}

	public Fa_Voucher_Dtl_DAO getFaVoucherDtlDAO() {
		return faVoucherDtlDAO;
	}
	@Autowired
	public void setFaVoucherDtlDAO(Fa_Voucher_Dtl_DAO faVoucherDtlDAO) {
		this.faVoucherDtlDAO = faVoucherDtlDAO;
	}

	public Lea_Agreement_Dtl_DAO getLeaAgreementDtlDAO() {
		return leaAgreementDtlDAO;
	}
	@Autowired
	public void setLeaAgreementDtlDAO(Lea_Agreement_Dtl_DAO leaAgreementDtlDAO) {
		this.leaAgreementDtlDAO = leaAgreementDtlDAO;
	}

	
	
}
