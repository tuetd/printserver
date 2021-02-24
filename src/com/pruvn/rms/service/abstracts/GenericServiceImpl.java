package com.pruvn.rms.service.abstracts;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;

import com.pruvn.rms.dao.GenericDAO;


public abstract class GenericServiceImpl<TDao extends GenericDAO<T, TId>, T, TId> implements GenericService<T, TId> {
	protected TDao dao;
	protected Mapper mapper;
	
	/**
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	public GenericDAO<T, TId> getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public void setDao(GenericDAO<T, TId> dao) {
		this.dao = (TDao) dao;
	}

	@Override
	public T getById(TId id, boolean lock) {
		return dao.getById(id, lock);
	}

	@Override
	public T getById(TId id) {
		return dao.getById(id);
	}

	@Override
	public T loadById(TId id) {
		return dao.loadById(id);
	}

	@Override
	public void deleteById(TId id) {
		dao.deleteById(id);
	}

	@Override
	public void saveOrUpdate(T entity) {
		dao.saveOrUpdate(entity);
		
	}

	@Override
	public void save(T entity) {
		dao.save(entity);
		
	}

	@Override
	public void update(T entity) {
		dao.update(entity);
	}


	@Override
	public void delete(T entity) {
		dao.delete(entity);
		
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public List<T> findByCriteria(Map criterias) {
		return dao.findByCriteria(criterias);
	}

	@Override
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		return findByExample(exampleInstance, excludeProperty);
	}
}
