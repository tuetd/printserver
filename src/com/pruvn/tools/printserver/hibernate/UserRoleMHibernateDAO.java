package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.UserRoleM;
import com.pruvn.tools.printserver.UserRoleMDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for UserRoleMs</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserRoleMHibernateDAO extends
		AbstractHibernateDAO<UserRoleM, Integer> implements
		UserRoleMDAO {

	/**
	 * Find UserRoleM by roleName
	 */
	public List<UserRoleM> findByRoleName(String roleName) {
		return findByCriteria(Restrictions.eq("roleName", roleName));
	}
	

}
