package com.pruvn.tools.printserver.pojo;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
 * <p>
 * Pojo mapping TABLE userdocprinter
 * </p>
 * <p>
 * </p>
 * 
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "userdocprinter", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Userdocprinter implements Serializable {

	/**
	 * Primary key
	 */
	
	private UserdocprinterPK userdocprinterPK;
	/**
	 * Attribute printername.
	 */
	private String printername;


	/**
	 * Get the primary key
	 */
	@Id
	public UserdocprinterPK getUserdocprinterPK() {
		return this.userdocprinterPK;
	}

	/**
	 * set the primary key
	 */
	
	public void setUserdocprinterPK(UserdocprinterPK userdocprinterPK) {
		this.userdocprinterPK = userdocprinterPK;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return printername
	 */
	@Basic
	@Column(name = "PRINTERNAME", length = 100)
	public String getPrintername() {
		return printername;
	}

	/**
	 * @param printername
	 *            new value for printername
	 */
	public void setPrintername(String printername) {
		this.printername = printername;
	}

	/**
	 * <p>
	 * Composite primary key for table userdocprinter
	 * </p>
	 * 
	 * <p>
	 * Generated at Thu Jul 19 10:40:45 ICT 2012
	 * </p>
	 * 
	 * @author Salto-db Generator v1.0.16 / EJB3
	 */
	@SuppressWarnings("serial")
	@Embeddable
	public static class UserdocprinterPK implements Serializable {

		/**
		 * Attribute docid
		 */
		
		private int docid;

		/**
		 * Attribute userid
		 */
		
		private int userid;

		/**
		 * Return docid
		 */
		public int getDocid() {
			return docid;
		}

		/**
		 * @param docid
		 *            new value for docid
		 */
		public void setDocid(int docid) {
			this.docid = docid;
		}

		/**
		 * Return userid
		 */
		public int getUserid() {
			return userid;
		}

		/**
		 * @param userid
		 *            new value for userid
		 */
		public void setUserid(int userid) {
			this.userid = userid;
		}

	}

}