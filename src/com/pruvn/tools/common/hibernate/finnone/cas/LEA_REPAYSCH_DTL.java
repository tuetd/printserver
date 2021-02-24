package com.pruvn.tools.common.hibernate.finnone.cas;

// Generated May 31, 2011 2:37:34 PM by Hibernate Tools 3.2.4.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * LEA_REPAYSCH_DTL generated by hbm2java
 */
public class LEA_REPAYSCH_DTL implements java.io.Serializable {

	private int PROPINSTLID;
	private int PROPOSALID;
	private Integer AGREEMENTID;
	private short INSTLNUM;
	private Date DUEDATE;
	private BigDecimal INSTLAMT;
	private BigDecimal TAXAMT;
	private BigDecimal RECDAMT;
	private BigDecimal ODAMT;
	private Date ODDATE;
	private BigDecimal PRINCOMP;
	private BigDecimal INTCOMP;
	private String ADVFLAG;
	private BigDecimal SDADJAMT;
	private String BILLFLAGE;
	private BigDecimal ARREARS;
	private BigDecimal OTHERSAMT;
	private BigDecimal FLOATINT;
	private BigDecimal EFFRATE;
	private BigDecimal BALPRIN;
	private BigDecimal PREPAYAMT;
	private BigDecimal EXINTCOMP;
	private Date BILLEDDATE;
	private BigDecimal VAT_PRIN;
	private BigDecimal VAT_INT;
	private Date DATELASTUPDT;
	private BigDecimal PRINCOMP_RECD;
	private BigDecimal INTCOMP_RECD;
	private BigDecimal VAT_INSTL;
	private BigDecimal VAT_INSTL_RECD;
	private BigDecimal VAT_PRIN_RECD;
	private BigDecimal VAT_INT_RECD;
	private Integer VAT_PROCESSID;
	private BigDecimal EXINTCOMP_RECD;
	private BigDecimal EXINT_PAYABLE;
	private BigDecimal EXINT_INCOME;
	private String NPASTAGEID;
	private String CONS_BILLFLAG;
	private BigDecimal TDS;
	private BigDecimal LER;
	private BigDecimal ACC_DEPR;
	private BigDecimal SERVICETAX;
	private BigDecimal SERVICETAX_RECD;
	private Integer DAYS;

	public LEA_REPAYSCH_DTL() {
	}

	public LEA_REPAYSCH_DTL(int PROPINSTLID, int PROPOSALID, short INSTLNUM,
			Date DUEDATE, BigDecimal INSTLAMT) {
		this.PROPINSTLID = PROPINSTLID;
		this.PROPOSALID = PROPOSALID;
		this.INSTLNUM = INSTLNUM;
		this.DUEDATE = DUEDATE;
		this.INSTLAMT = INSTLAMT;
	}

	public LEA_REPAYSCH_DTL(int PROPINSTLID, int PROPOSALID,
			Integer AGREEMENTID, short INSTLNUM, Date DUEDATE,
			BigDecimal INSTLAMT, BigDecimal TAXAMT, BigDecimal RECDAMT,
			BigDecimal ODAMT, Date ODDATE, BigDecimal PRINCOMP,
			BigDecimal INTCOMP, String ADVFLAG, BigDecimal SDADJAMT,
			String BILLFLAGE, BigDecimal ARREARS, BigDecimal OTHERSAMT,
			BigDecimal FLOATINT, BigDecimal EFFRATE, BigDecimal BALPRIN,
			BigDecimal PREPAYAMT, BigDecimal EXINTCOMP, Date BILLEDDATE,
			BigDecimal VAT_PRIN, BigDecimal VAT_INT, Date DATELASTUPDT,
			BigDecimal PRINCOMP_RECD, BigDecimal INTCOMP_RECD,
			BigDecimal VAT_INSTL, BigDecimal VAT_INSTL_RECD,
			BigDecimal VAT_PRIN_RECD, BigDecimal VAT_INT_RECD,
			Integer VAT_PROCESSID, BigDecimal EXINTCOMP_RECD,
			BigDecimal EXINT_PAYABLE, BigDecimal EXINT_INCOME,
			String NPASTAGEID, String CONS_BILLFLAG, BigDecimal TDS,
			BigDecimal LER, BigDecimal ACC_DEPR, BigDecimal SERVICETAX,
			BigDecimal SERVICETAX_RECD, Integer DAYS) {
		this.PROPINSTLID = PROPINSTLID;
		this.PROPOSALID = PROPOSALID;
		this.AGREEMENTID = AGREEMENTID;
		this.INSTLNUM = INSTLNUM;
		this.DUEDATE = DUEDATE;
		this.INSTLAMT = INSTLAMT;
		this.TAXAMT = TAXAMT;
		this.RECDAMT = RECDAMT;
		this.ODAMT = ODAMT;
		this.ODDATE = ODDATE;
		this.PRINCOMP = PRINCOMP;
		this.INTCOMP = INTCOMP;
		this.ADVFLAG = ADVFLAG;
		this.SDADJAMT = SDADJAMT;
		this.BILLFLAGE = BILLFLAGE;
		this.ARREARS = ARREARS;
		this.OTHERSAMT = OTHERSAMT;
		this.FLOATINT = FLOATINT;
		this.EFFRATE = EFFRATE;
		this.BALPRIN = BALPRIN;
		this.PREPAYAMT = PREPAYAMT;
		this.EXINTCOMP = EXINTCOMP;
		this.BILLEDDATE = BILLEDDATE;
		this.VAT_PRIN = VAT_PRIN;
		this.VAT_INT = VAT_INT;
		this.DATELASTUPDT = DATELASTUPDT;
		this.PRINCOMP_RECD = PRINCOMP_RECD;
		this.INTCOMP_RECD = INTCOMP_RECD;
		this.VAT_INSTL = VAT_INSTL;
		this.VAT_INSTL_RECD = VAT_INSTL_RECD;
		this.VAT_PRIN_RECD = VAT_PRIN_RECD;
		this.VAT_INT_RECD = VAT_INT_RECD;
		this.VAT_PROCESSID = VAT_PROCESSID;
		this.EXINTCOMP_RECD = EXINTCOMP_RECD;
		this.EXINT_PAYABLE = EXINT_PAYABLE;
		this.EXINT_INCOME = EXINT_INCOME;
		this.NPASTAGEID = NPASTAGEID;
		this.CONS_BILLFLAG = CONS_BILLFLAG;
		this.TDS = TDS;
		this.LER = LER;
		this.ACC_DEPR = ACC_DEPR;
		this.SERVICETAX = SERVICETAX;
		this.SERVICETAX_RECD = SERVICETAX_RECD;
		this.DAYS = DAYS;
	}

	public int getPROPINSTLID() {
		return this.PROPINSTLID;
	}

	public void setPROPINSTLID(int PROPINSTLID) {
		this.PROPINSTLID = PROPINSTLID;
	}

	public int getPROPOSALID() {
		return this.PROPOSALID;
	}

	public void setPROPOSALID(int PROPOSALID) {
		this.PROPOSALID = PROPOSALID;
	}

	public Integer getAGREEMENTID() {
		return this.AGREEMENTID;
	}

	public void setAGREEMENTID(Integer AGREEMENTID) {
		this.AGREEMENTID = AGREEMENTID;
	}

	public short getINSTLNUM() {
		return this.INSTLNUM;
	}

	public void setINSTLNUM(short INSTLNUM) {
		this.INSTLNUM = INSTLNUM;
	}

	public Date getDUEDATE() {
		return this.DUEDATE;
	}

	public void setDUEDATE(Date DUEDATE) {
		this.DUEDATE = DUEDATE;
	}

	public BigDecimal getINSTLAMT() {
		return this.INSTLAMT;
	}

	public void setINSTLAMT(BigDecimal INSTLAMT) {
		this.INSTLAMT = INSTLAMT;
	}

	public BigDecimal getTAXAMT() {
		return this.TAXAMT;
	}

	public void setTAXAMT(BigDecimal TAXAMT) {
		this.TAXAMT = TAXAMT;
	}

	public BigDecimal getRECDAMT() {
		return this.RECDAMT;
	}

	public void setRECDAMT(BigDecimal RECDAMT) {
		this.RECDAMT = RECDAMT;
	}

	public BigDecimal getODAMT() {
		return this.ODAMT;
	}

	public void setODAMT(BigDecimal ODAMT) {
		this.ODAMT = ODAMT;
	}

	public Date getODDATE() {
		return this.ODDATE;
	}

	public void setODDATE(Date ODDATE) {
		this.ODDATE = ODDATE;
	}

	public BigDecimal getPRINCOMP() {
		return this.PRINCOMP;
	}

	public void setPRINCOMP(BigDecimal PRINCOMP) {
		this.PRINCOMP = PRINCOMP;
	}

	public BigDecimal getINTCOMP() {
		return this.INTCOMP;
	}

	public void setINTCOMP(BigDecimal INTCOMP) {
		this.INTCOMP = INTCOMP;
	}

	public String getADVFLAG() {
		return this.ADVFLAG;
	}

	public void setADVFLAG(String ADVFLAG) {
		this.ADVFLAG = ADVFLAG;
	}

	public BigDecimal getSDADJAMT() {
		return this.SDADJAMT;
	}

	public void setSDADJAMT(BigDecimal SDADJAMT) {
		this.SDADJAMT = SDADJAMT;
	}

	public String getBILLFLAGE() {
		return this.BILLFLAGE;
	}

	public void setBILLFLAGE(String BILLFLAGE) {
		this.BILLFLAGE = BILLFLAGE;
	}

	public BigDecimal getARREARS() {
		return this.ARREARS;
	}

	public void setARREARS(BigDecimal ARREARS) {
		this.ARREARS = ARREARS;
	}

	public BigDecimal getOTHERSAMT() {
		return this.OTHERSAMT;
	}

	public void setOTHERSAMT(BigDecimal OTHERSAMT) {
		this.OTHERSAMT = OTHERSAMT;
	}

	public BigDecimal getFLOATINT() {
		return this.FLOATINT;
	}

	public void setFLOATINT(BigDecimal FLOATINT) {
		this.FLOATINT = FLOATINT;
	}

	public BigDecimal getEFFRATE() {
		return this.EFFRATE;
	}

	public void setEFFRATE(BigDecimal EFFRATE) {
		this.EFFRATE = EFFRATE;
	}

	public BigDecimal getBALPRIN() {
		return this.BALPRIN;
	}

	public void setBALPRIN(BigDecimal BALPRIN) {
		this.BALPRIN = BALPRIN;
	}

	public BigDecimal getPREPAYAMT() {
		return this.PREPAYAMT;
	}

	public void setPREPAYAMT(BigDecimal PREPAYAMT) {
		this.PREPAYAMT = PREPAYAMT;
	}

	public BigDecimal getEXINTCOMP() {
		return this.EXINTCOMP;
	}

	public void setEXINTCOMP(BigDecimal EXINTCOMP) {
		this.EXINTCOMP = EXINTCOMP;
	}

	public Date getBILLEDDATE() {
		return this.BILLEDDATE;
	}

	public void setBILLEDDATE(Date BILLEDDATE) {
		this.BILLEDDATE = BILLEDDATE;
	}

	public BigDecimal getVAT_PRIN() {
		return this.VAT_PRIN;
	}

	public void setVAT_PRIN(BigDecimal VAT_PRIN) {
		this.VAT_PRIN = VAT_PRIN;
	}

	public BigDecimal getVAT_INT() {
		return this.VAT_INT;
	}

	public void setVAT_INT(BigDecimal VAT_INT) {
		this.VAT_INT = VAT_INT;
	}

	public Date getDATELASTUPDT() {
		return this.DATELASTUPDT;
	}

	public void setDATELASTUPDT(Date DATELASTUPDT) {
		this.DATELASTUPDT = DATELASTUPDT;
	}

	public BigDecimal getPRINCOMP_RECD() {
		return this.PRINCOMP_RECD;
	}

	public void setPRINCOMP_RECD(BigDecimal PRINCOMP_RECD) {
		this.PRINCOMP_RECD = PRINCOMP_RECD;
	}

	public BigDecimal getINTCOMP_RECD() {
		return this.INTCOMP_RECD;
	}

	public void setINTCOMP_RECD(BigDecimal INTCOMP_RECD) {
		this.INTCOMP_RECD = INTCOMP_RECD;
	}

	public BigDecimal getVAT_INSTL() {
		return this.VAT_INSTL;
	}

	public void setVAT_INSTL(BigDecimal VAT_INSTL) {
		this.VAT_INSTL = VAT_INSTL;
	}

	public BigDecimal getVAT_INSTL_RECD() {
		return this.VAT_INSTL_RECD;
	}

	public void setVAT_INSTL_RECD(BigDecimal VAT_INSTL_RECD) {
		this.VAT_INSTL_RECD = VAT_INSTL_RECD;
	}

	public BigDecimal getVAT_PRIN_RECD() {
		return this.VAT_PRIN_RECD;
	}

	public void setVAT_PRIN_RECD(BigDecimal VAT_PRIN_RECD) {
		this.VAT_PRIN_RECD = VAT_PRIN_RECD;
	}

	public BigDecimal getVAT_INT_RECD() {
		return this.VAT_INT_RECD;
	}

	public void setVAT_INT_RECD(BigDecimal VAT_INT_RECD) {
		this.VAT_INT_RECD = VAT_INT_RECD;
	}

	public Integer getVAT_PROCESSID() {
		return this.VAT_PROCESSID;
	}

	public void setVAT_PROCESSID(Integer VAT_PROCESSID) {
		this.VAT_PROCESSID = VAT_PROCESSID;
	}

	public BigDecimal getEXINTCOMP_RECD() {
		return this.EXINTCOMP_RECD;
	}

	public void setEXINTCOMP_RECD(BigDecimal EXINTCOMP_RECD) {
		this.EXINTCOMP_RECD = EXINTCOMP_RECD;
	}

	public BigDecimal getEXINT_PAYABLE() {
		return this.EXINT_PAYABLE;
	}

	public void setEXINT_PAYABLE(BigDecimal EXINT_PAYABLE) {
		this.EXINT_PAYABLE = EXINT_PAYABLE;
	}

	public BigDecimal getEXINT_INCOME() {
		return this.EXINT_INCOME;
	}

	public void setEXINT_INCOME(BigDecimal EXINT_INCOME) {
		this.EXINT_INCOME = EXINT_INCOME;
	}

	public String getNPASTAGEID() {
		return this.NPASTAGEID;
	}

	public void setNPASTAGEID(String NPASTAGEID) {
		this.NPASTAGEID = NPASTAGEID;
	}

	public String getCONS_BILLFLAG() {
		return this.CONS_BILLFLAG;
	}

	public void setCONS_BILLFLAG(String CONS_BILLFLAG) {
		this.CONS_BILLFLAG = CONS_BILLFLAG;
	}

	public BigDecimal getTDS() {
		return this.TDS;
	}

	public void setTDS(BigDecimal TDS) {
		this.TDS = TDS;
	}

	public BigDecimal getLER() {
		return this.LER;
	}

	public void setLER(BigDecimal LER) {
		this.LER = LER;
	}

	public BigDecimal getACC_DEPR() {
		return this.ACC_DEPR;
	}

	public void setACC_DEPR(BigDecimal ACC_DEPR) {
		this.ACC_DEPR = ACC_DEPR;
	}

	public BigDecimal getSERVICETAX() {
		return this.SERVICETAX;
	}

	public void setSERVICETAX(BigDecimal SERVICETAX) {
		this.SERVICETAX = SERVICETAX;
	}

	public BigDecimal getSERVICETAX_RECD() {
		return this.SERVICETAX_RECD;
	}

	public void setSERVICETAX_RECD(BigDecimal SERVICETAX_RECD) {
		this.SERVICETAX_RECD = SERVICETAX_RECD;
	}

	public Integer getDAYS() {
		return this.DAYS;
	}

	public void setDAYS(Integer DAYS) {
		this.DAYS = DAYS;
	}

}
