package com.pruvn.printserver.form;

import java.util.List;

public class DocumentParameterDto implements Comparable<DocumentParameterDto> {
	private List<String> fields ;
	private String paramFriendlyName;
	private String paramName;
	private String paramType;
	private String fieldType;
	private Long ID;	
	
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getParamFriendlyName() {
		return paramFriendlyName;
	}
	public void setParamFriendlyName(String paramFriendlyName) {
		this.paramFriendlyName = paramFriendlyName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long id) {
		ID = id;
	}
	
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
	public int compareTo(DocumentParameterDto dto) {
		return ID.compareTo(dto.ID);
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
}
