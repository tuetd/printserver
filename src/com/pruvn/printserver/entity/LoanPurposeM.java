package com.pruvn.printserver.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "LOAN_PURPOSE_PRINT")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoanPurposeM {
private String code;
private String system_core;
private String vn;
private String en;
private String code_contract;

@Basic
@Id
@GeneratedValue
@Column(name = "code")
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
@Column(name = "system_core")
public String getSystem_core() {
	return system_core;
}
public void setSystem_core(String system_core) {
	this.system_core = system_core;
}
@Column(name = "vn")
public String getVn() {
	return vn;
}
public void setVn(String vn) {
	this.vn = vn;
}
@Column(name = "en")
public String getEn() {
	return en;
}
public void setEn(String en) {
	this.en = en;
}
@Column(name = "code_contract")
public String getCode_contract() {
	return code_contract;
}
public void setCode_contract(String code_contract) {
	this.code_contract = code_contract;
}






}
