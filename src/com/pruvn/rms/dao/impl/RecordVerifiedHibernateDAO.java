package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordVerifiedDAO;
import com.pruvn.rms.domain.RecordVerified;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for RecordVerified</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordVerifiedHibernateDAO extends
		AbstractHibernateDAO<RecordVerified, Integer> implements
		RecordVerifiedDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordVerified> findAllRecordVerifieds_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<RecordVerified>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_VERIFIED_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordVerified.class);
        		List<RecordVerified> result = query.list();
        		return result;
            }
        });
	}

}
