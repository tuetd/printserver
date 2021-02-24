package com.pruvn.rms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pruvn.rms.dao.GenericDAO;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.SqlConstant;

/**
 * Generated at Mon Jul 11 15:00:10 ICT 2011
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public abstract class AbstractHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

	public AbstractHibernateDAO()  {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void initDao() throws Exception {
		super.initDao();
        getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_COMMIT);
	}

	public Class<T> getPersistentClass() {
        return persistentClass;
    }

	public T getById(ID id) {
		return (T) getHibernateTemplate().get(getPersistentClass(), id);
	}

	public T getById(ID id, boolean lock) {
		if (lock) {
			return (T) getHibernateTemplate().get(getPersistentClass(), id, LockMode.UPGRADE);
		} else
			return getById(id);
	}

	public T loadById(ID id) {
		return (T) getHibernateTemplate().load(getPersistentClass(), id);
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void deleteById(ID id) 	{
		getHibernateTemplate().delete(loadById(id));
	}

	@SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }
	
	/**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(final Criterion... criterion) {
    	return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria crit = session.createCriteria(getPersistentClass());
            	for (Criterion c : criterion) {
                    crit.add(c);
                }
                return crit.list();
            }
        });
   }
   
   	/**
 	 * Find by criteria.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByCriteria(final Map criterias) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria criteria = session.createCriteria(getPersistentClass());
            	criteria.add(Restrictions.allEq(criterias));
                return criteria.list();
            }
        });
	}
	
	/**
	 * This method will execute an HQL query and return the number of affected entities.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> executeQuery(final String query, final String namedParams[],	final Object params[]) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
            	Query q = session.createQuery(query);
                if (namedParams != null) {
        			for (int i = 0; i < namedParams.length; i++) {
        				q.setParameter(namedParams[i], params[i]);
        			}
        		}
                return (List<T>) q.list();
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int executeUpdateQuery(final String query, final String namedParams[],	final Object params[]) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Query q = session.createQuery(query);
                if (namedParams != null) {
        			for (int i = 0; i < namedParams.length; i++) {
        				q.setParameter(namedParams[i], params[i]);
        			}
        		}
                return q.executeUpdate();
            }
        });
	}
	
	protected int executeUpdateQuery(String query) {
		return executeUpdateQuery(query, null, null);
	}
	
	/**
	 * This method will execute a Named HQL query and return the number of affected entities.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int executeNamedQuery(final String namedQuery, final String namedParams[],	final Object params[]) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Query q = session.getNamedQuery(namedQuery);
            	if (namedParams != null) {
        			for (int i = 0; i < namedParams.length; i++) {
        				q.setParameter(namedParams[i], params[i]);
        			}
        		}
            	return q.executeUpdate();
            }
        });
	}
	
	protected int executeNamedQuery(String namedQuery) {
		return executeNamedQuery(namedQuery, null, null);
	}
	
	@SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleInstance, final String[] excludeProperty) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria crit = session.createCriteria(getPersistentClass());
            	Example example =  Example.create(exampleInstance).excludeZeroes().enableLike().ignoreCase();
                for (String exclude : excludeProperty) {
                    example.excludeProperty(exclude);
                }
                crit.add(example);
                return crit.list();
            }
        });
    }
	
	protected String buildADFilterQuery(Map<String, Object> filters,
			String sqlQuery) {
		String clause ="";
		if (filters != null) {
			Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
			String key, value;
			while (im.hasNext()) {
				Entry<String, Object> me = (Entry<String, Object>) im.next();
				key = me.getKey();
				value = me.getValue().toString();
				
				if ("BankCode".equals(key)) {
					if(value!=null)
					{
						
						String valueArr[] = value.split(",");
						if(Integer.parseInt(valueArr[0])!=-1)
						{
							clause = clause + " AND UPPER(BANKCODE) = UPPER('"+ valueArr[0] + "')" ;
						}
					}
				} else if ("loanNo".equals(key)) {
					value = value.replace(";", ",");
					clause = clause + " AND LOANNO in (" + value + ")";
				} else if ("CustomerName".equals(key)) {
					clause = clause + " AND UPPER(CUSTOMERNAME) like UPPER('%" + value + "%')";
				} else if ("AuthorizedDate".equals(key)) {
					clause = clause + " AND TRUNC(AUTHORIZEDDATE) = TO_DATE('" + value + "', 'DD/MM/YYYY')";
				} else if ("DisbursalDate".equals(key)) {
					clause = clause + " AND TRUNC(DISBURSALDATE) = TO_DATE('" + value + "', 'DD/MM/YYYY')";
				} else if ("FirstDueDate".equals(key)) {
					clause = clause + " AND TRUNC(FIRSTDUEDATE) = TO_DATE('" + value + "', 'DD/MM/YYYY')";
				} else if ("SendDate".equals(key)) {
					clause = clause + " AND TRUNC(SENDDATE) = TO_DATE('" + value + "', 'DD/MM/YYYY')";
				}else if ("branchDesc".equals(key)) {
					if(!value.equals("-1"))
					{
						clause = clause + " AND UPPER(BRANCHDESC) like UPPER('%" + value + "%') ";
					}
				}else if ("sendername".equals(key)) {
					clause = clause + " AND UPPER(SENDERNAME) like UPPER('%" + value + "%') ";
				}else if ("status".equals(key)) {
					if(!value.equals("None"))
					{
						clause = clause + " AND UPPER(STATUS) like UPPER('%"+ value + "%') " ;
					}
					if(value.equals("None"))
					{
						clause = clause + " AND (STATUS is null OR ( UPPER(STATUS) != UPPER('Delete') AND UPPER(STATUS) != UPPER('Done'))) ";
					}
				}else if ("roName".equals(key)) {
					clause = clause + " AND UPPER(RONAME) like UPPER('%" + value + "%') ";				
				}  
			}
		}	
		sqlQuery = sqlQuery.replace("#Clause#", clause);
		return sqlQuery;
	}
	
	
	
	protected void buildFilterQuery(Map<String, Object> filters,
			StringBuffer sqlQuery) {
		if (filters != null) {
			Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
			String key, value;
			while (im.hasNext()) {
				Entry<String, Object> me = (Entry<String, Object>) im.next();
				key = me.getKey();
				value = me.getValue().toString();
				if ("Id".equals(key)) {
					sqlQuery.append(" AND ID like '%" + value + "%'");
				} else if ("FromDate".equals(key)) {
					sqlQuery.append(" AND DISBURSALDATE >= TO_DATE('" + value + "', 'DD/MM/YYYY')");
				} else if ("ToDate".equals(key)) {
					sqlQuery.append(" AND DISBURSALDATE <= TO_DATE('" + value + "', 'DD/MM/YYYY')");
				} else if ("AgreementNo".equals(key)) {
					sqlQuery.append(" AND AGREEMENTNO like '%" + value + "%'");
				} else if ("Branch".equals(key)) {
					sqlQuery.append(" AND BRANCHDESC like '%" + value + "%'");
				} else if ("AppFromNo".equals(key)) {
					sqlQuery.append(" AND APP_FORMNO like '%" + value + "%'");
				} else if ("SendDate".equals(key)) {
					sqlQuery.append(" AND SEND_DATE BETWEEN TO_DATE('"+ value +"','DD/MM/YYYY') AND TO_DATE('"+ value +"','DD/MM/YYYY') + 1");
				} else if ("Sender".equals(key)) {
					sqlQuery.append(" AND SENDER like '%" + value + "%'");
				}else if ("Area".equals(key)) {
					sqlQuery.append(" AND UPPER(AREA) like UPPER('%" + value + "%')");
				}
			}
		}
		sqlQuery.append(SqlConstant.ORDER_BY);
	}
	
	
	protected void buildFilterQuerySearch(Map<String, Object> filters,
			StringBuffer sqlQuery) {
		boolean isSearch = false;
		if (filters != null) {
			Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
			String key, value;
			
			while (im.hasNext()) {
				Entry<String, Object> me = (Entry<String, Object>) im.next();
				key = me.getKey();
				value = me.getValue().toString();
				if(!CommonUtils.isNullOrEmpty(value)) {
					isSearch = true;
					if ("Id".equals(key)) {
						sqlQuery.append(" AND ID like '%" + value + "%'");
					} else if ("FromDate".equals(key)) {
						sqlQuery.append(" AND DISBURSALDATE >= TO_DATE('" + value + "', 'DD/MM/YYYY')");
					} else if ("ToDate".equals(key)) {
						sqlQuery.append(" AND DISBURSALDATE <= TO_DATE('" + value + "', 'DD/MM/YYYY')");
					} else if ("AgreementNo".equals(key)) {
						sqlQuery.append(" AND AGREEMENTNO like '%" + value + "%'");
					} else if ("Branch".equals(key)) {
						sqlQuery.append(" AND BRANCHDESC like '%" + value + "%'");
					} else if ("AppFromNo".equals(key)) {
						sqlQuery.append(" AND APP_FORMNO like '%" + value + "%'");
					} else if ("SendDate".equals(key)) {
						sqlQuery.append(" AND SEND_DATE BETWEEN TO_DATE('"+ value +"','DD/MM/YYYY') AND TO_DATE('"+ value +"','DD/MM/YYYY') + 1");
					} else if ("Sender".equals(key)) {
						sqlQuery.append(" AND SENDER like '%" + value + "%'");
					}else if ("Area".equals(key)) {
						sqlQuery.append(" AND UPPER(AREA) like UPPER('%" + value + "%')");
					}
				}
			}
		}
		if(!isSearch) {
			sqlQuery.append(" AND 1 = 2 ");
		}
		sqlQuery.append(SqlConstant.ORDER_BY);
	}
	protected void buildFilterQueryCreditShield(Map<String, Object> filters,
			StringBuffer sqlQuery) {
		if (filters != null) {
			Iterator<Entry<String, Object>> im = filters.entrySet().iterator();
			String key, value;
			
			while (im.hasNext()) {
				Entry<String, Object> me = (Entry<String, Object>) im.next();
				key = me.getKey();
				value = me.getValue().toString();
				if(!CommonUtils.isNullOrEmpty(value)) {
					if ("callDateFrom".equals(key)) {
						sqlQuery.append(" AND to_char(DISBURSALDATE,'dd/MM/yyyy') = '" + value + "'");
					} else if ("callDateTo".equals(key)) {
						sqlQuery.append(" AND DISBURSALDATE <= TO_DATE('" + value + "', 'DD/MM/YYYY')");
					}
				}
			}
		}
	}
}


