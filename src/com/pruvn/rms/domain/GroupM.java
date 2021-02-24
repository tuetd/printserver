package com.pruvn.rms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * <p>Pojo mapping TABLE group_m</p>
 * <p></p>
 *
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_M")
public class GroupM implements Serializable {

	/**
	 * Attribute groupid.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_GROUP_M"))
    @GeneratedValue(generator = "generator")
	@Column(name = "GROUPID")
	private Integer groupid;
	
	/**
	 * Attribute groupcode.
	 */
	@Column(name = "GROUPCODE", nullable = true)
	private String groupcode;
	
	/**
	 * Attribute groupname.
	 */
	@Column(name = "GROUPNAME", nullable = true)
	private String groupname;
	
	/**
	 * Attribute groupdesc.
	 */
	@Column(name = "GROUPDESC", nullable = true)
	private String groupdesc;
	
	
	@Column(name = "IS_ACTIVED", nullable = true, length = 1)
	private Integer isActived;
	
	/**
	 * <p> 
	 * </p>
	 * @return groupid
	 */
		public Integer getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid new value for groupid 
	 */
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return groupcode
	 */
		public String getGroupcode() {
		return groupcode;
	}

	/**
	 * @param groupcode new value for groupcode 
	 */
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return groupname
	 */
		public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname new value for groupname 
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return groupdesc
	 */
		public String getGroupdesc() {
		return groupdesc;
	}

	/**
	 * @param groupdesc new value for groupdesc 
	 */
	public void setGroupdesc(String groupdesc) {
		this.groupdesc = groupdesc;
	}

	public Integer getIsActived() {
		return isActived;
	}

	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}
	


}