package com.pruvn.rms.dao.abstracts;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericHibernateDAO extends HibernateDaoSupport implements GenericDAO {

	private boolean allowCreateNew = true;

    protected void initDao() throws Exception {
        super.initDao();
        getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_COMMIT);
    }

    public void setAllowCreateNew( boolean allowCreateNew ) {
        this.allowCreateNew = allowCreateNew;
    }

    public boolean isAllowCreateNew() {
        return allowCreateNew;
    }

    protected Session getCurrentSession() {
        return getSession(isAllowCreateNew());
    }

    public Serializable save( final Object modelObject ) throws DataAccessException, NonUniqueObjectException {
        return getHibernateTemplate().save(modelObject);
    }

    public void update( final Object modelObject ) throws DataAccessException {
    	getHibernateTemplate().update(modelObject);
    }

    public void merge( final Object modelObject ) throws DataAccessException {
    	getHibernateTemplate().merge(modelObject);
    }

    public void saveOrUpdate( final Object modelObject ) throws DataAccessException, NonUniqueObjectException {
    	getHibernateTemplate().saveOrUpdate(modelObject);
    }

    public Object load( final Class clazz, final Long id ) throws DataAccessException {
        return getHibernateTemplate().load(clazz, id);
    }

    public Object get( final Class clazz, final Long id ) throws DataAccessException {
        return getHibernateTemplate().get(clazz, id);
    }

    public void delete( final Object modelObject ) throws DataAccessException {
    	getHibernateTemplate().delete(modelObject);
    }

    public void flush() {
    	getHibernateTemplate().flush();
    }

    public void refresh( Object entity ) {
    	getHibernateTemplate().refresh(entity);
    }

    public void clear() {
    	getHibernateTemplate().clear();
    }

}
