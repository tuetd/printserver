package com.pruvn.tools.printserver;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;
import com.pruvn.tools.printserver.pojo.Recordsetmaster;
/**
 * <p>Generic DAO layer for Recordsetmasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface RecordsetmasterDAO extends GenericDAO<Recordsetmaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildRecordsetmasterDAO()
	 */
	  	 
	/**
	 * Find Recordsetmaster by datetime
	 */
	public List<Recordsetmaster> findByDatetime(Timestamp datetime);

	/**
	 * Find Recordsetmaster by statusid
	 */
	public List<Recordsetmaster> findByStatusid(Integer statusid);

	/**
	 * Find Recordsetmaster by userid
	 */
	public List<Recordsetmaster> findByUserid(Integer userid);
	void updateRecordList(List<Recordsetmaster> list,Set<Recordsetfieldlink> recordsetfieldlink);

}