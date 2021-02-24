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
 * <p>Pojo mapping TABLE RM_TB6</p>
 * <p></p>
 * 
 */
@Entity
@MappedSuperclass
@Table(name = "RM_FORECLOSURE")
public class Foreclosure implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name="ID")
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_FORECLOSURE_SEQ"))
    @GeneratedValue(generator = "generator")
    private int id           ;
	
	@Column(name="LOAN_NO")
    private String     loanNo  ;
	
	@Column(name="CUSTOMER_NAME")
    private String     customerName  ;
	
	@Column(name="REMARK_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date remarkDate     ;
	
	@Column(name="REMARK")
    private String     remark  ;
	
	@Column(name="STAGE")
    private String     stage  ;
	
	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createDate	       ;
	
	@Column(name="CREATE_BY")
    private String createBy     ;
	
	@Column(name="SEND_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date sendDate     ;
	
	@Column(name="SEND_BY")
    private String sendBy     ;
	
	@Column(name="SEND_NOTE")
    private String sendNote     ;
	
	@Column(name="COMPLETE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date completeDate     ;
	
	@Column(name="COMPLETE_BY")
    private String completeBy     ;
	
	@Column(name="COMPLETE_NOTE")
    private String completeNote     ;
	
	@Column(name="WAITING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date waitingDate     ;
	
	@Column(name="WAITING_BY")
    private String waitingBy     ;
	
	@Column(name="WAITING_NOTE")
    private String waitingNote     ;
	
	@Column(name="RETURN_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date returnDate     ;
	
	@Column(name="RETURN_BY")
    private String returnBy     ;
	
	@Column(name="RETURN_NOTE")
    private String returnNote     ;
		
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
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}



	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
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

	public Date getSendDate() {
		return sendDate;
	}



	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}



	public String getSendBy() {
		return sendBy;
	}



	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}



	public String getSendNote() {
		return sendNote;
	}



	public void setSendNote(String sendNote) {
		this.sendNote = sendNote;
	}



	/**
	 * @return the completeDate
	 */
	public Date getCompleteDate() {
		return completeDate;
	}



	/**
	 * @param completeDate the completeDate to set
	 */
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}



	/**
	 * @return the completeBy
	 */
	public String getCompleteBy() {
		return completeBy;
	}



	/**
	 * @param completeBy the completeBy to set
	 */
	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}



	/**
	 * @return the waitingDate
	 */
	public Date getWaitingDate() {
		return waitingDate;
	}



	/**
	 * @param waitingDate the waitingDate to set
	 */
	public void setWaitingDate(Date waitingDate) {
		this.waitingDate = waitingDate;
	}



	/**
	 * @return the waitingBy
	 */
	public String getWaitingBy() {
		return waitingBy;
	}


	/**
	 * @param waitingBy the waitingBy to set
	 */
	public void setWaitingBy(String waitingBy) {
		this.waitingBy = waitingBy;
	}



	/**
	 * @return the completeNote
	 */
	public String getCompleteNote() {
		return completeNote;
	}



	/**
	 * @param completeNote the completeNote to set
	 */
	public void setCompleteNote(String completeNote) {
		this.completeNote = completeNote;
	}



	/**
	 * @return the waitingNote
	 */
	public String getWaitingNote() {
		return waitingNote;
	}



	/**
	 * @param waitingNote the waitingNote to set
	 */
	public void setWaitingNote(String waitingNote) {
		this.waitingNote = waitingNote;
	}



	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}



	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}



	/**
	 * @return the returnBy
	 */
	public String getReturnBy() {
		return returnBy;
	}



	/**
	 * @param returnBy the returnBy to set
	 */
	public void setReturnBy(String returnBy) {
		this.returnBy = returnBy;
	}



	/**
	 * @return the returnNote
	 */
	public String getReturnNote() {
		return returnNote;
	}



	/**
	 * @param returnNote the returnNote to set
	 */
	public void setReturnNote(String returnNote) {
		this.returnNote = returnNote;
	}



	/**
	 * @return the remarkDate
	 */
	public Date getRemarkDate() {
		return remarkDate;
	}



	/**
	 * @param remarkDate the remarkDate to set
	 */
	public void setRemarkDate(Date remarkDate) {
		this.remarkDate = remarkDate;
	}

	
}