package com.pruvn.tools.common.hibernate.finnone.fa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class FA_VOUCHER_DTL implements Serializable {
	private String voucherid;	
	private String moduleid;
	private String branchid;
	private String sequencenum;
	private Integer chequeid;
	private Date voucherdate;
	private BigDecimal dramt;
	private BigDecimal cramt;
	public BigDecimal getDramt() {
		return dramt;
	}
	public void setDramt(BigDecimal dramt) {
		this.dramt = dramt;
	}
	public BigDecimal getCramt() {
		return cramt;
	}
	public void setCramt(BigDecimal cramt) {
		this.cramt = cramt;
	}
	public Date getVoucherdate() {
		return voucherdate;
	}
	public void setVoucherdate(Date voucherdate) {
		this.voucherdate = voucherdate;
	}
	public Integer getChequeid() {
		return chequeid;
	}
	public void setChequeid(Integer chequeid) {
		this.chequeid = chequeid;
	}
	public String getVoucherid() {
		return voucherid;
	}
	public void setVoucherid(String voucherid) {
		this.voucherid = voucherid;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getSequencenum() {
		return sequencenum;
	}
	public void setSequencenum(String sequencenum) {
		this.sequencenum = sequencenum;
	}	
}
