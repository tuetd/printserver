package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;
import com.pruvn.tools.printserver.RecordsetfieldlinkDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Recordsetfieldlinks</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordsetfieldlinkHibernateDAO extends
		AbstractHibernateDAO<Recordsetfieldlink, Integer> implements
		RecordsetfieldlinkDAO {

	/**
	 * Find Recordsetfieldlink by fieldid
	 */
	public List<Recordsetfieldlink> findByFieldid(Integer fieldid) {
		return findByCriteria(Restrictions.eq("fieldid", fieldid));
	}
	
	/**
	 * Find Recordsetfieldlink by recordsetid
	 */
	public List<Recordsetfieldlink> findByRecordsetid(Integer recordsetid) {
		return findByCriteria(Restrictions.eq("recordsetid", recordsetid));
	}
	
	/**
	 * Find Recordsetfieldlink by data
	 */
	public List<Recordsetfieldlink> findByData(String data) {
		return findByCriteria(Restrictions.eq("data", data));
	}
	

}
