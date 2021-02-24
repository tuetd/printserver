package com.pruvn.cfcwebsite.entity;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <p>Pojo mapping TABLE sqlparammaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "CFC_WEBSITE_CUSTOMER")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class CfcCustomerM implements Serializable {

	private String PAN_NO;
	private String PASSWORD;
	private String FIRST_TIME_LOGIN;
	private Date LAST_LOGGED_DATE;
	private String TOKEN;
	private String INITIAL_PASSWORD;
	private String CLIENT_IP;
	private String REMARKS;
	private BigDecimal CUSTOMERID;
	

	@Id
	@Column(name="PAN_NO")
	public String getPAN_NO() {
		return PAN_NO;
	}
	public void setPAN_NO(String pAN_NO) {
		PAN_NO = pAN_NO;
	}
	
	
	@Column(name="PASSWORD")
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	@Column(name="FIRST_TIME_LOGIN")
	public String getFIRST_TIME_LOGIN() {
		return FIRST_TIME_LOGIN;
	}
	public void setFIRST_TIME_LOGIN(String fIRST_TIME_LOGIN) {
		FIRST_TIME_LOGIN = fIRST_TIME_LOGIN;
	}
	@Column(name="LAST_LOGGED_DATE")
	public Date getLAST_LOGGED_DATE() {
		return LAST_LOGGED_DATE;
	}
	public void setLAST_LOGGED_DATE(Date lAST_LOGGED_DATE) {
		LAST_LOGGED_DATE = lAST_LOGGED_DATE;
	}
	@Column(name="TOKEN")
	public String getTOKEN() {
		return TOKEN;
	}
	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}
	@Column(name="INITIAL_PASSWORD")
	public String getINITIAL_PASSWORD() {
		return INITIAL_PASSWORD;
	}
	public void setINITIAL_PASSWORD(String iNITIAL_PASSWORD) {
		INITIAL_PASSWORD = iNITIAL_PASSWORD;
	}
	@Column(name="CLIENT_IP")
	public String getCLIENT_IP() {
		return CLIENT_IP;
	}
	public void setCLIENT_IP(String cLIENT_IP) {
		CLIENT_IP = cLIENT_IP;
	}
	@Column(name="REMARKS")
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	@Column(name="CUSTOMERID")
	public BigDecimal getCUSTOMERID() {
		return CUSTOMERID;
	}
	public void setCUSTOMERID(BigDecimal cUSTOMERID) {
		CUSTOMERID = cUSTOMERID;
	}
	
	
	
	
	


}