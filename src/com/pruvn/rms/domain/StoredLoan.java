package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * <p>
 * Pojo mapping TABLE RM_TB6
 * </p>
 * <p>
 * </p>
 * 
 */
@Entity
@MappedSuperclass
@Table(name = "RM_STOREDLOAN")
public class StoredLoan implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "ID")
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_STOREDLOAN_SEQ"))
	@GeneratedValue(generator = "generator")
	private int id;
	@Column(name = "LOAN_NO")
	private String loanNo;
	@Column(name = "BLOCK")
	private String block;
	@Column(name = "NO")
	private String no;
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	@Column(name = "DISBURSAL_DATE")
	private Date disbursalDate;
	@Column(name = "BARCODE")
	private String barCode;
	@Column(name = "NAME_BOX")
	private String nameBox;
	@Column(name = "DATE_SENT")
	private String dateSent;
	@Column(name = "DESTROY_DATE")
	private String destroyDate;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "CREATE_BY")
	private String createBy;

	@Column(name = "UPDATED_DATE")
	private Date updateDate;
	@Column(name = "UPDATED_BY")
	private String updateBy;
    
	public StoredLoan(){}
	public StoredLoan(String block,String no,Date disbursalDate,String loanNo,String customerName,
		String barCode,String nameBox,String dateSent){
		this.block=block;
		this.no=no;
		this.disbursalDate=disbursalDate;
		this.loanNo=loanNo;
		this.customerName=customerName;
		this.barCode=barCode;
		this.nameBox=nameBox;
		this.dateSent=dateSent;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDisbursalDate() {
		return disbursalDate;
	}

	public void setDisbursalDate(Date disbursalDate) {
		this.disbursalDate = disbursalDate;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getNameBox() {
		return nameBox;
	}

	public void setNameBox(String nameBox) {
		this.nameBox = nameBox;
	}

	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	public String getDestroyDate() {
		return destroyDate;
	}

	public void setDestroyDate(String destroyDate) {
		this.destroyDate = destroyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}