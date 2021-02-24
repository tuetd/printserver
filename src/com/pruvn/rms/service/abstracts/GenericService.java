package com.pruvn.rms.service.abstracts;

import java.util.List;
import java.util.Map;

public interface GenericService<T, TId> {
	void saveOrUpdate(T entity);
	void save(T entity);
	
	void delete(T entity);
	
	T getById(TId id, boolean lock);

	T getById(TId id);
	
	T loadById(TId id);

	List<T> findAll();
	
	List<T> findByCriteria(Map criterias);
	
	public List<T> findByExample(T exampleInstance, String[] excludeProperty);

	void update(T entity);

	void deleteById(TId id);
}
