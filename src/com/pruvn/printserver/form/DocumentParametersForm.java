package com.pruvn.printserver.form;

import java.util.ArrayList;
import java.util.List;
public class DocumentParametersForm  {
	private List<DocumentParameterDto> list;
	private String documentname;
	private List<String> paramnamelist;
	private List<String> paramtypelist;
	
	public DocumentParametersForm(){
	}
	
	public List<String> getParamtypelist() {
		return paramtypelist;
	}

	public void setParamtypelist(List<String> paramtypelist) {
		this.paramtypelist = paramtypelist;
	}

	public List<String> getParamnamelist() {
		return paramnamelist;
	}

	public void setParamnamelist(List<String> paramnamelist) {
		this.paramnamelist = paramnamelist;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public List<DocumentParameterDto> getList() {
		return list;
	}

	public void setList(List<DocumentParameterDto> list) {
		this.list = list;
	}
	
}
