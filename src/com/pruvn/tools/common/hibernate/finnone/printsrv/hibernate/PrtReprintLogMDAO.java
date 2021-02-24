package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import java.sql.Timestamp;
import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintLogM;
import com.pruvn.tools.printserver.GenericDAO;

/**
 * <p>Generic DAO layer for PrtReprintLogMs</p>
 * <p>Generated at Tue Dec 04 16:09:38 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface PrtReprintLogMDAO extends GenericDAO<PrtReprintLogM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildPrtReprintLogMDAO()
	 */
	  	 
	/**
	 * Find PrtReprintLogM by applid
	 */
	public List<PrtReprintLogM> findByApplid(String applid);

	/**
	 * Find PrtReprintLogM by fromDate
	 */
	public List<PrtReprintLogM> findByFromDate(Timestamp fromDate);

	/**
	 * Find PrtReprintLogM by stopInMinutes
	 */
	public List<PrtReprintLogM> findByStopInMinutes(Long stopInMinutes);

	/**
	 * Find PrtReprintLogM by createdBy
	 */
	public List<PrtReprintLogM> findByCreatedBy(String createdBy);

	/**
	 * Find PrtReprintLogM by createdDate
	 */
	public List<PrtReprintLogM> findByCreatedDate(Timestamp createdDate);

	/**
	 * Find PrtReprintLogM by logDate
	 */
	public List<PrtReprintLogM> findByLogDate(Timestamp logDate);

	/**
	 * Find PrtReprintLogM by notes
	 */
	public List<PrtReprintLogM> findByNotes(String notes);

}