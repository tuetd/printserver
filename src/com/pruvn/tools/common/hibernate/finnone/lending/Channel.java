package com.pruvn.tools.common.hibernate.finnone.lending;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * <p>Pojo mapping TABLE PRINTSRV.PRT_REPRINT_LOG_M</p>
 *
 * <p>Generated at Tue Dec 04 16:09:38 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
public class Channel implements java.io.Serializable {

	/**
	 * Attribute username.
	 */
	private Integer channelId;
	
	/**
	 * Attribute id.
	 */
	protected Integer parentId;
	
	/**
	 * Attribute loggedin.
	 */
	private String channelName;
	
	/**
	 * Attribute roleId.
	 */
	private String vendorId;
	
	/**
	 * Attribute sessionId.
	 */
	private String systemTeamNameId;

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getSystemTeamNameId() {
		return systemTeamNameId;
	}

	public void setSystemTeamNameId(String systemTeamNameId) {
		this.systemTeamNameId = systemTeamNameId;
	}

	
	

}