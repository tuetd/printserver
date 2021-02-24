package com.pruvn.tools.printserver.hibernate;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.printserver.RecordsetmasterDAO;
import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;
import com.pruvn.tools.printserver.pojo.Recordsetmaster;

/**
 * <p>Hibernate DAO layer for Recordsetmasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordsetmasterHibernateDAO extends
		AbstractHibernateDAO<Recordsetmaster, Integer> implements
		RecordsetmasterDAO {

	/**
	 * Find Recordsetmaster by datetime
	 */
	public List<Recordsetmaster> findByDatetime(Timestamp datetime) {
		return findByCriteria(Restrictions.eq("datetime", datetime));
	}
	
	/**
	 * Find Recordsetmaster by statusid
	 */
	public List<Recordsetmaster> findByStatusid(Integer statusid) {
		return findByCriteria(Restrictions.eq("statusid", statusid));
	}
	
	/**
	 * Find Recordsetmaster by userid
	 */
	public List<Recordsetmaster> findByUserid(Integer userid) {
		return findByCriteria(Restrictions.eq("userid", userid));
	}

	public void updateRecordList(List<Recordsetmaster> list,Set<Recordsetfieldlink> listRecord) {
		for (int i = 0; i < list.size(); i++) {
			getSession().saveOrUpdate(list.get(i));
			Iterator<Recordsetfieldlink> iterator = listRecord.iterator();
			while (iterator.hasNext()) {
				Recordsetfieldlink recordsetfieldlink=iterator.next();
				recordsetfieldlink.setRecordsetid(list.get(i).getId());
				getSession().saveOrUpdate(recordsetfieldlink);
			}
			if (i % 20 == 0) {
				getSession().flush();
				getSession().clear();				
			}				
		}
		
	}
	

}
