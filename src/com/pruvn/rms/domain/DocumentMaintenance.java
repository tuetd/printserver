package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "DOCUMENT_MAINTENANCE")
public class DocumentMaintenance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CIF")
	private String cif;

	@Column(name = "LOANID")
	private String loanId;
	
	@Column(name = "DOCID")
	private String docId;
	
	@Column(name = "DOCDESC")
	private String docDesc;

	@Column(name = "SCANNEDDATE")
	private Date scanDate;
	
	@Column(name = "UPLOADCHANNEL")
	private String uploadChannel;
	
	@Id
	@Column(name = "DOCREFID")
	private String docRefId;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public Date getScanDate() {
		return scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	public String getUploadChannel() {
		return uploadChannel;
	}

	public void setUploadChannel(String uploadChannel) {
		this.uploadChannel = uploadChannel;
	}

	public String getDocRefId() {
		return docRefId;
	}

	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}
	

}