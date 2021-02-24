package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordPODAO;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordPO;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for RecordRD1</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordPOHibernateDAO extends
		AbstractHibernateDAO<RecordPO, Integer> implements
		RecordPODAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordPO> findAllRecordPOs_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<RecordPO>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_POST_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordPO.class);
        		//query.setFirstResult(10);
        		//query.setMaxResults(20);
        		List<RecordPO> result = query.list();
        		return result;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RecordPO getRecordPOByLoanIdAndName(final String loanId,final String customerName){
		return (RecordPO) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD_PO po WHERE po.AGREEMENTNO like :loanId and upper(po.CUSTOMERNAME) = upper(:customerName)");
             	query.setParameter("loanId", "%" + loanId);
             	query.setParameter("customerName", customerName);
            	query.addEntity(RecordPO.class);
            	List<RecordPO> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Record searchByAgreementNo(final String agreementNo) {
		return (Record) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD po WHERE po.AGREEMENTNO  =:loanId");
             	query.setParameter("loanId", agreementNo);
            	query.addEntity(Record.class);
            	List<Record> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
	}
}
