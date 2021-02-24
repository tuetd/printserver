package com.pruvn.printserver.dao.implement;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.printserver.dao.GeneratePasswordDAO;
import com.pruvn.printserver.entity.GeneratePassword;


public class GeneratePasswordDAOImpl extends HibernateGenericDAO<GeneratePassword, String> implements GeneratePasswordDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneratePassword> findByApplid(String string) {
		Criteria criteria=getSession().createCriteria(GeneratePassword.class);
		criteria.add(Restrictions.eq("AGREEMENTNO", string));
		return criteria.list();
	}

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int generatePasswordDefault(final String loanno){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
            	int result=0; 
            	try {
                	String sql="{call P_WEB_RESETPWD(?,?)}";
                	CallableStatement call = 	session.connection().prepareCall(sql);
                	call.setString(1, loanno);
                	call.registerOutParameter(2, OracleTypes.INTEGER);
      	            call.execute();
      	            result = call.getInt(2);	       
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

}
