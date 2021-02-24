package com.pruvn.tools.printserver.webapp.editor;

public class DocumentParameterDto implements Comparable<DocumentParameterDto> {
	
	private String paramFriendlyName;
	private String paramName;
	private String paramType;
	private String fieldType;
	private Integer ID;	
	
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
	public Integer getID() {
		return ID;
	}
	public void setID(Integer id) {
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
}
