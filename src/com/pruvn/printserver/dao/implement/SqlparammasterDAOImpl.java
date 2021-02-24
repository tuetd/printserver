package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.SqlparammasterDAO;
import com.pruvn.printserver.entity.Sqlparammaster;

/**
 * <p>Hibernate DAO layer for Sqlparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class SqlparammasterDAOImpl extends HibernateGenericDAO<Sqlparammaster, Long> 
implements SqlparammasterDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sqlparammaster> findByNameSqlPara(String name) {
		Criteria criteria = getSession().createCriteria(Sqlparammaster.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Sqlparammaster> findByFriendlyname(String friendlyname) {
		Criteria criteria = getSession().createCriteria(Sqlparammaster.class);
		criteria.add(Restrictions.eq("friendlyname", friendlyname));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Sqlparammaster> findByTypeid(Long typeid) {
		Criteria criteria = getSession().createCriteria(Sqlparammaster.class);
		criteria.add(Restrictions.eq("typeid", typeid));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Sqlparammaster> findByDocid(Long datasourceid) {
		Criteria criteria = getSession().createCriteria(Sqlparammaster.class);
		criteria.add(Restrictions.eq("docid", datasourceid));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Sqlparammaster> findByFieldtype(String fieldtype) {
		Criteria criteria = getSession().createCriteria(Sqlparammaster.class);
		criteria.add(Restrictions.eq("fieldtype", fieldtype));
		return criteria.list();
	}

	/**
	 * Find Sqlparammaster by name
	 */
	

}
