package com.pruvn.printserver.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pruvn.printserver.dao.GenericDAO;

public abstract class HibernateGenericDAO<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID>  {

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public HibernateGenericDAO() {
	        this.persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistentClass() {
        return persistentClass;
    }

	@SuppressWarnings("unchecked")
    public T findById(ID id) {
		Object object = this.getSession().get(this.getPersistentClass(), id);
		if(object == null){
			return null;
		}
		return (T)object;
    }
	
	@SuppressWarnings("unchecked")
	public T findByName(String name) {
		Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.add(Restrictions.eq("name", name));		
		List<T> result = criteria.list();		
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}
	
	public List<T> findAll(){
		return this.getHibernateTemplate().loadAll(this.getPersistentClass());
	}
	
	public T save(T entity){
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}
	
	public void delete(T entity){
		this.getHibernateTemplate().delete(entity);
	}
	
	public void delete(ID id){
		T entity = this.findById(id);
		this.getHibernateTemplate().delete(entity);
	}
	
	public T update(T entity){
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	} 
	
	public void refresh(T entity){
		this.getHibernateTemplate().refresh(entity);		
	}
	
	public void merge(T entity){
		this.getHibernateTemplate().merge(entity);
	}
	
	public void flush(){
		this.getHibernateTemplate().flush();
	}
}