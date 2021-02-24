package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.Cm_doc_printed_trialDAO;
import com.pruvn.rms.domain.finnbank.Cm_doc_printed_trial;

/**
 * <p>Hibernate DAO layer for DeptMs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class Cm_doc_printed_trialHibernateDAO extends
		AbstractHibernateDAO<Cm_doc_printed_trial, Integer> implements
		Cm_doc_printed_trialDAO {
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Boolean findByLoanNo(String loanNo) {
//		List<cm_doc_printed_trial> list= findAll();
//		if(list.size()>0)
//			return true;
//		else
//			return false;
//	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Boolean findByLoanNo(final String loanNo) {
//		return getHibernateTemplate().execute(new HibernateCallback() {
//            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
//            	
//            	
//            	Query query = session.createQuery("SELECT c.app_formno FROM Cm_doc_printed_trial c where c.app_formno=:loanNo ");
//            	query.setParameter("loanNo", loanNo);
//            	List<Cm_doc_printed_trial> result = query.list();
//            	if(result.size()>0)
//            		return true;
//            	else
//            		return false;               
//            }
//        });
//	}
	public Boolean findByLoanNo(String loanNo) {
		List<Cm_doc_printed_trial> list= findByCriteria(Restrictions.eq("app_formno", loanNo));
		if(list.size()>0)
			return true;
		else
			return false;
	}
}
