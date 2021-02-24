package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import com.pruvn.tools.printserver.pojo.Statusmaster;
import com.pruvn.tools.printserver.StatusmasterDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Statusmasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class StatusmasterHibernateDAO extends
		AbstractHibernateDAO<Statusmaster, Integer> implements
		StatusmasterDAO {

	/**
	 * Find Statusmaster by name
	 */
	public List<Statusmaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}

	public Statusmaster getStatus(String name) {
		Criteria criteria = getSession().createCriteria(Statusmaster.class);
		return (Statusmaster)criteria.add(Restrictions.eq("name", name)).uniqueResult();
	}
	

}
