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
 * <p>Pojo mapping TABLE RM_INSURANCE</p>
 * <p></p>
 * 
 */
@Entity
@MappedSuperclass
@Table(name = "RM_INSURANCE")
public class Insurance implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name="ID")
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_INSURANCE_SEQ"))
    @GeneratedValue(generator = "generator")
    private int id           ;
	
	@Column(name="LOAN_NO")
    private String     loanNo  ;
	
	@Column(name="CUSTOMER_NAME")
    private String     customerName  ;
	
	
	@Column(name="REMARK")
    private String     remark  ;
	
	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createDate	       ;
	
	@Column(name="CREATE_BY")
    private String createBy     ;
	
	@Column(name="RECEIVE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate     ;
	
	@Column(name="RECEIVE_BY")
    private String receiveBy     ;
	
		
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}



	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}



	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}



	/**
	 * @return the receiveDate
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}



	/**
	 * @param receiveDate the receiveDate to set
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}



	/**
	 * @return the receiveBy
	 */
	public String getReceiveBy() {
		return receiveBy;
	}



	/**
	 * @param receiveBy the receiveBy to set
	 */
	public void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}
}