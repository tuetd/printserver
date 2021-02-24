/*
 * Java bean class for entity table RM_MRC 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.MRCDao;
import com.pruvn.rms.domain.MRC;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.SqlConstant;


/**
 * Data Access Object implementation for table "RM_MRC"
 * 
 * @author Telosys Tools Generator
 *
 */
public class MRCHibernateDao extends AbstractHibernateDAO<MRC, Integer> implements MRCDao {

	
	public MRC findByLoanNo(String loanNo) {
		List<MRC> list =  findByCriteria(Restrictions.eq("loanNo", loanNo));		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public List<MRC> searchByLoanNo(String loanNo) {
		List<MRC> list =  findByCriteria(Restrictions.like("loanNo", loanNo,MatchMode.ANYWHERE));		
		return list;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MRC> getMRCList(final FilterMRCForm filterMRCForm){		
		return getHibernateTemplate().execute(new HibernateCallback() {
	        public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	        	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_MRC_LIST_ACL);
	
	        	if(!CommonUtils.isNullOrEmpty(filterMRCForm.getLoanNo())) {            		
	        		sqlQuery.append(" AND LOAN_NO like '%" + filterMRCForm.getLoanNo() + "%'");
	        	}
	        	
	         	if(!CommonUtils.isNullOrEmpty(filterMRCForm.getDateSend())) {            		
	        		sqlQuery.append(" AND ORG_RETURN = '"+filterMRCForm.getDateSend() + "'");
	        	}
	        	
	    		SQLQuery query = session.createSQLQuery(
	    				sqlQuery.toString());
	    		query.addEntity(MRC.class);
	    		List<MRC> result = query.list();
	    		return result;
	        }
	    });
	}
	
}