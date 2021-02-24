package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fileparamfilelink;
import com.pruvn.tools.printserver.FileparamfilelinkDAO;
import com.pruvn.tools.printserver.pojo.Fileparamfilelink.FileparamfilelinkPK;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Fileparamfilelinks</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FileparamfilelinkHibernateDAO extends
		AbstractHibernateDAO<Fileparamfilelink, FileparamfilelinkPK> implements
		FileparamfilelinkDAO {

	/**
	 * Find Fileparamfilelink by fileid
	 */
	public List<Fileparamfilelink> findByFileid(Integer fileid) {
		return findByCriteria(Restrictions.eq("id.fileid", fileid));
	}
	/**
	 * Find Fileparamfilelink by fileparamid
	 */
	public List<Fileparamfilelink> findByFileparamid(Integer fileparamid) {
		return findByCriteria(Restrictions.eq("id.fileparamid", fileparamid));
	}
	/**
	 * Find Fileparamfilelink by value
	 */
	public List<Fileparamfilelink> findByValue(String value) {
		return findByCriteria(Restrictions.eq("value", value));
	}


}
