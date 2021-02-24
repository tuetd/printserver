package com.pruvn.rms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>Pojo mapping TABLE RecordScanCM</p>
 * <p></p>
 *
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "RM_RECORD_SCAN_CM")
public class RecordScanCM extends Record {
	@Column(name="SEND_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date     sendDate   ;

	@Column(name="SENDER")
    private String     sender        ;
    
    public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
}