package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.FieldtypemasterDAO;
import com.pruvn.printserver.entity.Fieldtypemaster;

/**
 * <p>Hibernate DAO layer for Fieldtypemasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FieldtypemasterDAOImpl extends
HibernateGenericDAO<Fieldtypemaster, Long> implements
		FieldtypemasterDAO {

	/**
	 * Find Fieldtypemaster by name
	 */
	@SuppressWarnings("unchecked")
	public List<Fieldtypemaster> findByNameField(String name) {
		Criteria criteria = getSession().createCriteria(Fieldtypemaster.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}
	

}
