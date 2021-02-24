package com.pruvn.printserver.dao.implement;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.printserver.dao.DocmasterDAO;
import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.utils.DateUtils;


public class DocmasterDAOImpl extends HibernateGenericDAO<Docmaster, Long> implements DocmasterDAO{

	@Override
	public Docmaster getById(Long docid) {
		return findById(docid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Docmaster> findByNameDocmaster(String documentname){
		Criteria criteria = getSession().createCriteria(Docmaster.class);
		criteria.add(Restrictions.eq("name_doc", documentname));
		return criteria.list();
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int checkVersionFCL(final String agreementno,final Date startIntDate,final int instlnum,
			final Date duedate, final String check_CREDIT_SHEILD){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
            	int result=0; 
            	try {
                	String sql = "{? = call F_LOAN_FCL_V10(?,?,?,?,?)}";
                	CallableStatement call = 	session.connection().prepareCall(sql);
                	call.setString(2, agreementno);
                	call.setDate(3, (java.sql.Date) startIntDate);
                	call.setInt(4, instlnum);
                	call.setDate(5, (java.sql.Date) duedate);
                	call.setString(6, check_CREDIT_SHEILD);
                	call.registerOutParameter(1, OracleTypes.INTEGER);
      	            call.execute();
      	            result = call.getInt(1);	       
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



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String printCheckFCL(final String agreementno,final String fcldate) {
			return (String) getHibernateTemplate().execute(new HibernateCallback() {
	            public String doInHibernate(Session session) throws HibernateException, SQLException {
	            	String err_Msg = "0";
	            	try {
	            		GregorianCalendar cal = new GregorianCalendar();
	    	        	cal.setTime(DateUtils.toDate(fcldate, "ddMMyyyy"));
	            		//
	    	        	String sql = "{call ? := F_PRINTSERVER_CHECK_FCL(?,?)}";
	    	        	CallableStatement cs = session.connection().prepareCall(sql);
	    	        	cs.registerOutParameter(1, Types.VARCHAR);
	    	        	cs.setObject(2, agreementno);
	    	        	cs.setObject(3, new Date(cal.getTimeInMillis()));
	    	        	cs.execute();
	    	        	err_Msg = cs.getString(1);
	    	        	cs.close();
					} catch (Exception e) {
						logger.error(e.getMessage());
						return "0";
					} finally {
						session.connection().close();
					}
					return err_Msg;
	            }
	        });	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String printFCLPercent(final String agreementno,final String fcldate) {
		return (String) getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String err_Msg = "0";
            	GregorianCalendar cal = new GregorianCalendar();
	        	cal.setTime(DateUtils.toDate(fcldate, "ddMMyyyy"));
            	try {
            		String sql = "{call ? := F_FCL_PERCENT_V10(?,?)}";
    	        	CallableStatement cs = session.connection().prepareCall(sql);
    	        	cs.registerOutParameter(1, Types.VARCHAR);
    	        	cs.setObject(2, agreementno);
    	        	cs.setObject(3, new Date(cal.getTimeInMillis()));
    	        	cs.execute();
    	        	err_Msg = String.valueOf(cs.getInt(1));
    	        	cs.close();
				} catch (Exception e) {
					logger.error(e.getMessage());
					return "-1";
				} finally {
					session.connection().close();
				}
				return err_Msg;
            }
        });	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void saveNoSignatureWithCEO(final String loanno, final String username,
			final String docname, final String no_sign) {
		  getHibernateTemplate().execute(new HibernateCallback() {
            public Void doInHibernate(Session session) throws HibernateException, SQLException {
            	try {
            		String sql = "{call P_PRT_NO_SIGNATURE(?,?,?,?)}";
            		CallableStatement cs = session.connection().prepareCall(sql);
            		cs.setString(1, loanno);
            		cs.setString(2, username);
            		cs.setString(3, docname);
            		cs.setString(4, no_sign);
            		cs.execute();
            		cs.close();
				} catch (Exception e) {
					logger.error(e.getMessage());
				} finally {
					session.connection().close();
				}
				return null;
            }
        });	
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String printAllow(final String agreementno) {
		return (String) getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String err_Msg = "0";
            	try {
            		String sql = "{call ? := F_CHECK_PRINT_ALLOW(?)}";
    	        	CallableStatement cs = session.connection().prepareCall(sql);
    	        	cs.registerOutParameter(1, Types.VARCHAR);
    	        	cs.setObject(2, agreementno);
    	        	cs.execute();
    	        	err_Msg = String.valueOf(cs.getInt(1));
    	        	cs.close();
				} catch (Exception e) {
					logger.error(e.getMessage());
					return "-1";
				} finally {
					session.connection().close();
				}
				return err_Msg;
            }
        });	
	}
	
	
	
}
