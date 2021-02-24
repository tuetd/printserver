package com.pruvn.rms.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity
@Table(name = "USER_LOG")
public class UserLog implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_USER_LOG"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;

	/**
	 * Attribute name.
	 */
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	@Column(name = "REMOTE_IP", nullable = true)
	private String remoteIP;
	
	@Column(name = "SESSIONUSERNAME", nullable = true)
	private String session;
	
	@Column(name = "LOG_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date logDate;
	
	@Column(name = "NOIDUNG", nullable = true)
	private String noidung;
	
	@Column(name = "NOIDUNG1", nullable = true)
	private String noidung1;
	
	@Column(name = "NOIDUNG2", nullable = true)
	private String noidung2;
	
	@Column(name = "NOIDUNG3", nullable = true)
	private String noidung3;
	
	@Column(name = "NOIDUNG4", nullable = true)
	private String noidung4;
	
	
	@Column(name = "LOG_TYPE", nullable = true)
	private String logType;
	
	@Column(name = "ACTIVITY", nullable = true)
	private String activity;
	
	@Column(name = "INPUT", nullable = true)
	private String input;
	
	@Column(name = "OUTPUT", nullable = true)
	private String output;
	
	@Column(name = "STATUS", nullable = true)
	private String status;
	
	@Column(name = "STATUS_NOTE", nullable = true)
	private String statusNote;
	

	/**
	 * <p>
	 * </p>
	 * 
	 * @return id
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            new value for id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getNoidung1() {
		return noidung1;
	}

	public void setNoidung1(String noidung1) {
		this.noidung1 = noidung1;
	}

	public String getNoidung2() {
		return noidung2;
	}

	public void setNoidung2(String noidung2) {
		this.noidung2 = noidung2;
	}

	public String getNoidung3() {
		return noidung3;
	}

	public void setNoidung3(String noidung3) {
		this.noidung3 = noidung3;
	}

	public String getNoidung4() {
		return noidung4;
	}

	public void setNoidung4(String noidung4) {
		this.noidung4 = noidung4;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}
	
	

	/**
	 * <p>
	 * </p>
	 * 
	 * @return name
	 */
	
}
