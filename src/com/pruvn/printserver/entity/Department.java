package com.pruvn.printserver.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "DEPARTMENT")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department {
private Long id;
private String name;
private String namecompany;
private String namecode;
private Date createdate;
private Date modifieddate;
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
@Basic
@Column(name = "name", length = 100)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Basic
@Column(name = "namecompany", length = 100)
public String getNamecompany() {
	return namecompany;
}
public void setNamecompany(String namecompany) {
	this.namecompany = namecompany;
}
@Basic
@Column(name = "namecode", length = 100)
public String getNamecode() {
	return namecode;
}
public void setNamecode(String namecode) {
	this.namecode = namecode;
}
@Basic
@Column(name = "createdate")
public Date getCreatedate() {
	return createdate;
}
public void setCreatedate(Date createdate) {
	this.createdate = createdate;
}
@Basic
@Column(name = "modifieddate")
public Date getModifieddate() {
	return modifieddate;
}
public void setModifieddate(Date modifieddate) {
	this.modifieddate = modifieddate;
}


}
