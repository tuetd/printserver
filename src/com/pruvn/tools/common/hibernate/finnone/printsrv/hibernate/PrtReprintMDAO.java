package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import java.sql.Timestamp;
import java.util.List;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintM;
import com.pruvn.tools.printserver.GenericDAO;

/**
 * <p>Generic DAO layer for PrtReprintMs</p>
 * <p>Generated at Tue Dec 04 16:09:39 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface PrtReprintMDAO extends GenericDAO<PrtReprintM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildPrtReprintMDAO()
	 */
	  	 
	/**
	 * Find PrtReprintM by applid
	 */
	public List<PrtReprintM> findByApplid(String applid);

	/**
	 * Find PrtReprintM by fromDate
	 */
	public List<PrtReprintM> findByFromDate(Timestamp fromDate);

	/**
	 * Find PrtReprintM by stopInMinutes
	 */
	public List<PrtReprintM> findByStopInMinutes(Long stopInMinutes);

	/**
	 * Find PrtReprintM by createdBy
	 */
	public List<PrtReprintM> findByCreatedBy(String createdBy);

	/**
	 * Find PrtReprintM by createdDate
	 */
	public List<PrtReprintM> findByCreatedDate(Timestamp createdDate);

	/**
	 * Find PrtReprintM by notes
	 */
	public List<PrtReprintM> findByNotes(String notes);

}