package com.pruvn.rms.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * <p>Pojo mapping TABLE user_group_a</p>
 * <p></p>
 *
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "USER_GROUP_A")
public class UserGroupA implements Serializable {

	/**
	 * Attribute usergroupid.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_USER_GROUP_A"))
    @GeneratedValue(generator = "generator")
	@Column(name = "USERGROUPID")
	private Integer usergroupid;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.LAZY)	
	@JoinColumn(name = "USERID", nullable = false)
	private UserM user;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUPID", nullable = false)
	private GroupM group;
	
	
	public UserM getUser() {
		return user;
	}

	public void setUser(UserM user) {
		this.user = user;
	}

	public GroupM getGroup() {
		return group;
	}

	public void setGroup(GroupM group) {
		this.group = group;
	}

	/**
	 * <p> 
	 * </p>
	 * @return usergroupid
	 */
		public Integer getUsergroupid() {
		return usergroupid;
	}

	/**
	 * @param usergroupid new value for usergroupid 
	 */
	public void setUsergroupid(Integer usergroupid) {
		this.usergroupid = usergroupid;
	}
	


}