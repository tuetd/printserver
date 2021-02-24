package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * <p>
 * Pojo mapping TABLE user_m
 * </p>
 * <p>
 * </p>
 * 
 * <p>
 * Generated at Mon Jul 11 14:56:25 ICT 2011
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "AD_RECORD")
public class AutoDebit implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "AD_RECORD_SEQ"))
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Long id;

	/**
	 * Attribute username.
	 */
	@Column(name = "CUSTOMERNAME", nullable = true)
	private String customerName;
	
	@Column(name = "STATUS", nullable = true)
	private String status;
	
	@Column(name = "BANKNAME", nullable = true)
	private String bankName;
	
	@Column(name = "TYPEAUTODEBIT", nullable = true)
	private String typeAutoDebit;
	
	@Column(name = "LOANNO", nullable = true)
	private String loanNo;
	
	@Column(name = "RONAME", nullable = true)
	private String roName;

	@Column(name = "DISBURSALDATE", nullable = true)
	private Date disbursalDate;
	
	@Column(name = "FIRSTDUEDATE", nullable = true)
	private Date firstDueDate;
	
	@Column(name = "SENDDATE", nullable = true)
	private Date sendDate;
	
	@Column(name = "RECEIVEORRETURNDATE", nullable = true)
	private Date receiveOrReturnDate;
	
//	@Transient
	@Column(name = "AUTHORIZEDDATE", nullable = true)
	private Date authorizedDate;
	
	@Column(name = "REASON", nullable = true)
	private String reason;
	
	@Column(name = "BANKCODE", nullable = true)
	private String bankCode;
	
	@Column(name = "SENDERNAME", nullable = true)
	private String sendername;
	
	@Column(name = "BRANCHDESC", nullable = true)
	private String branchDesc;
	
	
	public String getBranchDesc() {
		return branchDesc;
	}

	public void setBranchDesc(String branchDesc) {
		this.branchDesc = branchDesc;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	@Transient
	private String cm;
		
	
	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTypeAutoDebit() {
		return typeAutoDebit;
	}

	public void setTypeAutoDebit(String typeAutoDebit) {
		this.typeAutoDebit = typeAutoDebit;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRoName() {
		return roName;
	}

	public void setRoName(String roName) {
		this.roName = roName;
	}

	public Date getDisbursalDate() {
		return disbursalDate;
	}

	public void setDisbursalDate(Date disbursalDate) {
		this.disbursalDate = disbursalDate;
	}

	public Date getFirstDueDate() {
		return firstDueDate;
	}

	public void setFirstDueDate(Date firstDueDate) {
		this.firstDueDate = firstDueDate;
	}

	public Date getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveOrReturnDate() {
		return receiveOrReturnDate;
	}

	public void setReceiveOrReturnDate(Date receiveOrReturnDate) {
		this.receiveOrReturnDate = receiveOrReturnDate;
	}
	
	
	
	
	
	

}