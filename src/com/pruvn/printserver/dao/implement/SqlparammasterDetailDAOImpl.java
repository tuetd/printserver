package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.SqlparammasterDetailDAO;
import com.pruvn.printserver.entity.SqlparammasterDetail;

/**
 * <p>Hibernate DAO layer for Sqlparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class SqlparammasterDetailDAOImpl extends HibernateGenericDAO<SqlparammasterDetail, Long> 
implements SqlparammasterDetailDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SqlparammasterDetail> getFieldsWith_FCL_REQ_ESTIMATED(Long id,
			String sqlparammasterDetailReasonFcl) {
		Criteria criteria = getSession().createCriteria(SqlparammasterDetail.class);
		criteria.add(Restrictions.eq("docid", id));
		criteria.add(Restrictions.eq("name", sqlparammasterDetailReasonFcl));
		return criteria.list();
	}

}
