package com.pruvn.cfcwebsite.dao.implement;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.cfcwebsite.dao.CfcCustomerMDAO;
import com.pruvn.cfcwebsite.entity.CfcCustomerM;
import com.pruvn.printserver.entity.GeneratePassword;
import com.pruvn.printserver.form.FCLForm;


public class CfcCustomerMDAOImpl extends HibernateGenericDAO<CfcCustomerM, String> implements CfcCustomerMDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CfcCustomerM> findByApplid(String string) {
		Criteria criteria=getSession().createCriteria(GeneratePassword.class);
		criteria.add(Restrictions.eq("PAN_NO", string));
		return criteria.list();
	}

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int generatePasswordDefault(final String loanno,final String app_id,final String password_encode,final String pass_text){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
            	int result=0; 
            	try {
                	String sql="{call P_WEB_RESETPWD_WEB(?,?,?,?,?)}";
                	CallableStatement call = 	session.connection().prepareCall(sql);
                	call.setString(1, loanno);
                	call.setString(2, app_id);
                	call.setString(3, password_encode);
                	call.setString(4, pass_text);
                	call.registerOutParameter(5, OracleTypes.INTEGER);
      	            call.execute();
      	            result = call.getInt(5);	       
				} catch (Exception e) {
					logger.error(e.getMessage());
					return 0;
				} finally {
					session.connection().close();
				}
				return result;
            }
        });	
	}


	@SuppressWarnings("unchecked")
	@Override
	public FCLForm getInstartDate(String loanno) {
			StringBuffer strQuery = new StringBuffer();
			strQuery.append(" select  Distinct b.LAA_INTSTART_DATE_REGULAR as startIntDate,a.agreementno,b.LAA_APP_REQ_TERM_N as instlnum,c.duedate ");
			strQuery.append(" from Lea_Agreement_Dtl a, Los_App_Applications b, lea_repaysch_dtl c ");
			strQuery.append(" where a.APPLID=b.App_Id_C ");
			strQuery.append(" and a.agreementno = c.AGREEMENTID ");
			strQuery.append(" and c.Instlnum =1 ");
			strQuery.append(" and a.agreementno = :loanno  ");
			SQLQuery query =getSession().createSQLQuery(strQuery.toString());
			query.addScalar("startIntDate", Hibernate.DATE);
			query.addScalar("agreementno", Hibernate.STRING);
			query.addScalar("instlnum", Hibernate.INTEGER);
			query.addScalar("duedate", Hibernate.DATE);
			query.setParameter("loanno", loanno);
			query.setResultTransformer(Transformers.aliasToBean(FCLForm.class));
			return (FCLForm) query.uniqueResult();
		}
		
	}
	

