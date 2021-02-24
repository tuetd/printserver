package com.pruvn.rms.model;

import javax.validation.constraints.NotNull;

public class CatagoriesForm {
private Integer id;	
private String name;
@NotNull
private String code;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

}
