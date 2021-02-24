package com.pruvn.tools.printserver.pojo;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <p>Pojo mapping TABLE userrecordsetprintedlink</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "userrecordsetprintedlink", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Userrecordsetprintedlink implements Serializable {

	/**
	 * Primary key
	 */
	private UserrecordsetprintedlinkPK userrecordsetprintedlinkPK;

	/**
	 * Attribute datetimeprinted.
	 */
	private Timestamp datetimeprinted;
	
	
	/**
	 * Get the primary key
	 */
	@Basic
	@Id
	public UserrecordsetprintedlinkPK getUserrecordsetprintedlinkPK() {
		return this.userrecordsetprintedlinkPK;
	}
	
	/**
	 * set the primary key
	 */
	public void setUserrecordsetprintedlinkPK(UserrecordsetprintedlinkPK userrecordsetprintedlinkPK) {
		this.userrecordsetprintedlinkPK = userrecordsetprintedlinkPK;
	}


		

		
	/**
	 * <p> 
	 * </p>
	 * @return datetimeprinted
	 */
	@Basic
	@Column(name = "DATETIMEPRINTED")
		public Timestamp getDatetimeprinted() {
		return datetimeprinted;
	}

	/**
	 * @param datetimeprinted new value for datetimeprinted 
	 */
	public void setDatetimeprinted(Timestamp datetimeprinted) {
		this.datetimeprinted = datetimeprinted;
	}
	

	/**
		  * <p>Composite primary key for table userrecordsetprintedlink</p>
 	  *
 	  * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 	  * @author Salto-db Generator v1.0.16 / EJB3
 	  */
	@SuppressWarnings("serial")
	@Embeddable
	public static class UserrecordsetprintedlinkPK implements Serializable {

		/**
		 * Attribute userid
		 */
		private UserrecordsetprintedlinkPK userid;

		/**
		 * Attribute recordsetid
		 */
		private int recordsetid;

		/**
		 * Return userid
		 */
		@Column(name = "USERID")
		public UserrecordsetprintedlinkPK getUserid() {
			return userid;
		}

		/**
		 * @param userid new value for userid 
		 */
		public void setUserid(UserrecordsetprintedlinkPK userid) {
			this.userid = userid;
		}
		/**
		 * Return recordsetid
		 */
		@Column(name = "RECORDSETID")
		public int getRecordsetid() {
			return recordsetid;
		}

		/**
		 * @param recordsetid new value for recordsetid 
		 */
		public void setRecordsetid(int recordsetid) {
			this.recordsetid = recordsetid;
		}

		/**
		 * calculate hashcode
		 */
		@Override
		public int hashCode()
		{
			//TODO : implement this method
			return super.hashCode();
		}

		/**
		 * equals method
		 */
		@Override
		public boolean equals(Object object)
		{
			//TODO : implement this method
			return super.equals(object);
		}

	}

}