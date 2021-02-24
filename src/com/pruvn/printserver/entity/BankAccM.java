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
@Table(name = "BANK_ACC_M")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccM {
private Long id;
private String bank_account;
private String bank_name;
private String bank_code;
private String bank_desc;
@Basic
@Id
@GeneratedValue
@Column(name = "id",length = 11)
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@Column(name = "bank_account",length = 11)
public String getBank_account() {
	return bank_account;
}
public void setBank_account(String bank_account) {
	this.bank_account = bank_account;
}
@Column(name = "bank_name")
public String getBank_name() {
	return bank_name;
}
public void setBank_name(String bank_name) {
	this.bank_name = bank_name;
}
@Column(name = "bank_code")
public String getBank_code() {
	return bank_code;
}

public void setBank_code(String bank_code) {
	this.bank_code = bank_code;
}
@Column(name = "bank_desc")
public String getBank_desc() {
	return bank_desc;
}
public void setBank_desc(String bank_desc) {
	this.bank_desc = bank_desc;
}



}
