package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

/**
 * Generated at Mon Jul 11 15:00:10 ICT 2011
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface GenericDAO<T, TId> {

	T getById(TId id, boolean lock);

	T getById(TId id);
	
	T loadById(TId id);

	List<T> findAll();
	
	List<T> findByCriteria(Map criterias);
	
	public List<T> findByExample(T exampleInstance, String[] excludeProperty);

	void save(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);
	
	void deleteById(TId id);
	
}