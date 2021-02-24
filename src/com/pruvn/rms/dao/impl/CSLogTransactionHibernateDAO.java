package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.CSLogTransactionDAO;
import com.pruvn.rms.domain.CSLogTransaction;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class CSLogTransactionHibernateDAO extends
		AbstractHibernateDAO<CSLogTransaction, Integer> implements
		CSLogTransactionDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CSLogTransaction> getByCSRecordId(final Integer recordId) {
		return (List<CSLogTransaction>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("FROM CSLogTransaction WHERE recordId = :recordId");
        		query.setParameter("recordId", recordId);
        		return query.list();
            }
        });
	}

}
