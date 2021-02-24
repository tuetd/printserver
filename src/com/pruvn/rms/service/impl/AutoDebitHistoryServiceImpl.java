package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pruvn.rms.dao.AutoDebitHistoryDAO;
import com.pruvn.rms.domain.AutoDebitHistory;
import com.pruvn.rms.service.AutoDebitHistoryService;
import com.pruvn.rms.service.abstracts.GenericServiceImpl;


@Service("autoDebitHistoryService")
public class AutoDebitHistoryServiceImpl extends GenericServiceImpl<AutoDebitHistoryDAO, AutoDebitHistory, Integer> implements AutoDebitHistoryService {
	
	private AutoDebitHistoryDAO autoDebitHistoryDAO;


	public AutoDebitHistoryDAO getAutoDebitHistoryDAO() {
		return autoDebitHistoryDAO;
	}

	public void setAutoDebitHistoryDAO(AutoDebitHistoryDAO autoDebitHistoryDAO) {
		this.autoDebitHistoryDAO = autoDebitHistoryDAO;
	}

	@Override
	public void saveOrUpdate(AutoDebitHistory entity) {
		// TODO Auto-generated method stub
		autoDebitHistoryDAO.saveOrUpdate(entity);
	}

	@Override
	public void save(AutoDebitHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AutoDebitHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AutoDebitHistory getById(Integer id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutoDebitHistory getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutoDebitHistory loadById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebitHistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebitHistory> findByCriteria(Map criterias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebitHistory> findByExample(AutoDebitHistory exampleInstance,
			String[] excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AutoDebitHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
