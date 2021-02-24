package com.pruvn.tools.printserver.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.pojo.Parammaster;

/**
 * <p>Hibernate DAO layer for Parammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class ParammasterHibernateDAO extends
		AbstractHibernateDAO<Parammaster, Integer> implements
		ParammasterDAO {

	/**
	 * Find Parammaster by name
	 */
	public List<Parammaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Parammaster by value
	 */
	public List<Parammaster> findByValue(String value) {
		return findByCriteria(Restrictions.eq("value", value));
	}
	
	/**
	 * Find Parammaster by description
	 */
	public List<Parammaster> findByDescription(String description) {
		return findByCriteria(Restrictions.eq("description", description));
	}

	public Parammaster getParamByName(String NAME) {
		Criteria criteria = getSession().createCriteria(Parammaster.class);
		return (Parammaster)criteria.add(Restrictions.eq("name", NAME)).uniqueResult();
	}
	

}
