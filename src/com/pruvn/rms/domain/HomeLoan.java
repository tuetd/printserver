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
@Table(name = "RM_HOME_LOAN")
public class HomeLoan implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name="ID")
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_HOME_LOAN_SEQ"))
    @GeneratedValue(generator = "generator")
    private int id           ;
	
	@Column(name="LOAN_NO")
    private String     loanNo  ;
	
	@Column(name="CUSTOMER_NAME")
    private String customerName     ;
	
	@Column(name="ORDER_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date orderDate       ;
	
	@Column(name="EXECUTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date executionDate ;
	
	@Column(name="FILE_FOLDER")
    private String fileFolder          ;
	
	@Column(name="SEAL1")
    private String     seal1       ;
	
	@Column(name="SEAL2")
    private String     seal2  ;
	
	@Column(name="SEAl3")
    private String     seal3   ;

	@Column(name="RETURN_ORIGINAL_DOCUMENT")
    private String  returnOriginalDocument ;
	
	@Column(name="REMARK")
    private String  remark ;
	
	
	
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
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the executionDate
	 */
	public Date getExecutionDate() {
		return executionDate;
	}

	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(Date executionDate) {
		executionDate = executionDate;
	}

	/**
	 * @return the fileFolder
	 */
	public String getFileFolder() {
		return fileFolder;
	}

	/**
	 * @param fileFolder the fileFolder to set
	 */
	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	/**
	 * @return the seal1
	 */
	public String getSeal1() {
		return seal1;
	}

	/**
	 * @param seal1 the seal1 to set
	 */
	public void setSeal1(String seal1) {
		this.seal1 = seal1;
	}

	/**
	 * @return the seal2
	 */
	public String getSeal2() {
		return seal2;
	}

	/**
	 * @param seal2 the seal2 to set
	 */
	public void setSeal2(String seal2) {
		this.seal2 = seal2;
	}

	/**
	 * @return the seal3
	 */
	public String getSeal3() {
		return seal3;
	}

	/**
	 * @param seal3 the seal3 to set
	 */
	public void setSeal3(String seal3) {
		this.seal3 = seal3;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/**
	 * @return the returnOriginalDocument
	 */
	public String getReturnOriginalDocument() {
		return returnOriginalDocument;
	}

	/**
	 * @param returnOriginalDocument the returnOriginalDocument to set
	 */
	public void setReturnOriginalDocument(String returnOriginalDocument) {
		this.returnOriginalDocument = returnOriginalDocument;
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
}