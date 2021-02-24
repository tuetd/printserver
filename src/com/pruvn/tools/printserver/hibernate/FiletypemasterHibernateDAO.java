package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Filetypemaster;
import com.pruvn.tools.printserver.FiletypemasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Filetypemasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FiletypemasterHibernateDAO extends
		AbstractHibernateDAO<Filetypemaster, Integer> implements
		FiletypemasterDAO {

	/**
	 * Find Filetypemaster by name
	 */
	public List<Filetypemaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	

}
