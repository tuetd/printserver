package com.pruvn.tools.printserver.webapp.editor;

public class SQLQueryDto implements Comparable<SQLQueryDto> {
	private String VALUE;
	private Integer TYPE;
	private Integer INDEXNUM;
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String value) {
		VALUE = value;
	}
	public Integer getTYPE() {
		return TYPE;
	}
	public void setTYPE(Integer type) {
		TYPE = type;
	}
	public Integer getINDEXNUM() {
		return INDEXNUM;
	}
	public void setINDEXNUM(Integer indexnum) {
		INDEXNUM = indexnum;
	}
	
	public int compareTo(SQLQueryDto dto) {
	    return INDEXNUM.compareTo(dto.INDEXNUM);
//	    return result == 0 ? firstName.compareTo(((Person) person).firstName) : result;
	  }
}
