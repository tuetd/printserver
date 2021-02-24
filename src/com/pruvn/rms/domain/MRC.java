package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * <p>Pojo mapping TABLE RM_HOME_LOAN</p>
 * <p></p>
 * 
 */
@Entity
@MappedSuperclass
@Table(name = "RM_MRC")
public class MRC implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name="ID")
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_MRC_SEQ"))
    @GeneratedValue(generator = "generator")
    private int id           ;
	
	
	@Column(name="BLOCK")
    private String     block  ;
	
	@Column(name="NO")
    private int     no  ;
	
	
	@Column(name="LOAN_NO")
    private String     loanNo  ;
	
	@Column(name="DISBURSAL_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date disbursalDate	       ;
	
	@Column(name="CUSTOMER_NAME")
    private String customerName     ;
	
	@Column(name="DEALER")
    private String dealer     ;
	
	@Column(name="CERT_NO")
    private String certNo     ;
	
	@Column(name="RCVD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date rcvdDate       ;
	
	@Column(name="COPY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date copyDate ;
	
	@Column(name="TENURE")
    private int tenure          ;
	
	@Column(name="MATURITY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date     maturityDate       ;
	
	@Column(name="ORG_RETURN")
    private String     orgReturn  ;
	
	@Column(name="IS_BILL")
    private String     isBill  ;
	
	@Column(name="STATUS")
    private String     status  ;
	
	@Column(name="REMARK")
    private String     remark  ;
	
	@Column(name = "UPLOAD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@Column(name = "UPLOAD_USER")
	private String uploadUser;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}



	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}



	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}



	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}



	/**
	 * @return the loanNo
	 */
	public String getLoanNo() {
		return loanNo;
	}



	/**
	 * @param loanNo the loanNo to set
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}



	/**
	 * @return the disbusalDate
	 */
	public Date getDisbursalDate() {
		return disbursalDate;
	}



	/**
	 * @param disbusalDate the disbusalDate to set
	 */
	public void setDisbursalDate(Date disbusalDate) {
		this.disbursalDate = disbusalDate;
	}



	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}



	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	/**
	 * @return the dealer
	 */
	public String getDealer() {
		return dealer;
	}



	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}



	/**
	 * @return the certNo
	 */
	public String getCertNo() {
		return certNo;
	}



	/**
	 * @param certNo the certNo to set
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}



	/**
	 * @return the rcvdDate
	 */
	public Date getRcvdDate() {
		return rcvdDate;
	}



	/**
	 * @param rcvdDate the rcvdDate to set
	 */
	public void setRcvdDate(Date rcvdDate) {
		this.rcvdDate = rcvdDate;
	}



	/**
	 * @return the copyDate
	 */
	public Date getCopyDate() {
		return copyDate;
	}



	/**
	 * @param copyDate the copyDate to set
	 */
	public void setCopyDate(Date copyDate) {
		this.copyDate = copyDate;
	}



	/**
	 * @return the tenure
	 */
	public int getTenure() {
		return tenure;
	}



	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}



	/**
	 * @return the maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}



	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}



	/**
	 * @return the orgReturn
	 */
	public String getOrgReturn() {
		return orgReturn;
	}



	/**
	 * @param orgReturn the orgReturn to set
	 */
	public void setOrgReturn(String orgReturn) {
		this.orgReturn = orgReturn;
	}



	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * @return the isBill
	 */
	public String getIsBill() {
		return isBill;
	}



	/**
	 * @param isBill the isBill to set
	 */
	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}



	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}



	/**
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}



	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}



	/**
	 * @return the uploadUser
	 */
	public String getUploadUser() {
		return uploadUser;
	}



	/**
	 * @param uploadUser the uploadUser to set
	 */
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
}