package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fileparammaster;
import com.pruvn.tools.printserver.FileparammasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Fileparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FileparammasterHibernateDAO extends
		AbstractHibernateDAO<Fileparammaster, Integer> implements
		FileparammasterDAO {

	/**
	 * Find Fileparammaster by name
	 */
	public List<Fileparammaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Fileparammaster by filetypeid
	 */
	public List<Fileparammaster> findByFiletypeid(Integer filetypeid) {
		return findByCriteria(Restrictions.eq("filetypeid", filetypeid));
	}
	

}
