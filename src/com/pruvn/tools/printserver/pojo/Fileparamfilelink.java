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
 * <p>Pojo mapping TABLE fileparamfilelink</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "fileparamfilelink", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Fileparamfilelink implements Serializable {

	/**
	 * Primary key
	 */
	private FileparamfilelinkPK fileparamfilelinkPK;

	/**
	 * Attribute value.
	 */
	private String value;
	
	
	/**
	 * Get the primary key
	 */
	@Basic
	@Id
	public FileparamfilelinkPK getFileparamfilelinkPK() {
		return this.fileparamfilelinkPK;
	}
	
	/**
	 * set the primary key
	 */
	public void setFileparamfilelinkPK(FileparamfilelinkPK fileparamfilelinkPK) {
		this.fileparamfilelinkPK = fileparamfilelinkPK;
	}


		

		
	/**
	 * <p> 
	 * </p>
	 * @return value
	 */
	@Basic
	@Column(name = "VALUE", length = 100)
		public String getValue() {
		return value;
	}

	/**
	 * @param value new value for value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	

	/**
		  * <p>Composite primary key for table fileparamfilelink</p>
 	  *
 	  * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 	  * @author Salto-db Generator v1.0.16 / EJB3
 	  */
	@SuppressWarnings("serial")
	@Embeddable
	public static class FileparamfilelinkPK implements Serializable {

		/**
		 * Attribute fileid
		 */
		private int fileid;

		/**
		 * Attribute fileparamid
		 */
		private FileparamfilelinkPK fileparamid;

		/**
		 * Return fileid
		 */
		@Column(name = "FILEID")
		public int getFileid() {
			return fileid;
		}

		/**
		 * @param fileid new value for fileid 
		 */
		public void setFileid(int fileid) {
			this.fileid = fileid;
		}
		/**
		 * Return fileparamid
		 */
		@Column(name = "FILEPARAMID")
		public FileparamfilelinkPK getFileparamid() {
			return fileparamid;
		}

		/**
		 * @param fileparamid new value for fileparamid 
		 */
		public void setFileparamid(FileparamfilelinkPK fileparamid) {
			this.fileparamid = fileparamid;
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