package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pruvn.rms.dao.AutoDebitDAO;
import com.pruvn.rms.domain.AutoDebit;
import com.pruvn.rms.domain.DocumentMaintenance;
import com.pruvn.rms.service.AutoDebitService;
import com.pruvn.rms.service.abstracts.GenericServiceImpl;


@Service("autoDebitService")
public class AutoDebitServiceImpl extends GenericServiceImpl<AutoDebitDAO, AutoDebit, Integer> implements AutoDebitService {
	
	private AutoDebitDAO autoDebitDAO;
	public AutoDebitDAO getAutoDebitDAO() {
		return autoDebitDAO;
	}

	public void setAutoDebitDAO(AutoDebitDAO autoDebitDAO) {
		this.autoDebitDAO = autoDebitDAO;
	}

	@Override
	public AutoDebit findById(Long id) {
		return autoDebitDAO.findByID(id);
	}
	
	
	@Override
	public List<AutoDebit> getAllRecords_AD(String username, String stage, Map<String, Object> filters){
		return autoDebitDAO.findAll_AD(username, stage,filters);
	}
	@Override
	public List<DocumentMaintenance> getAllDocument(String username, String stage, Map<String, Object> filters){
		return autoDebitDAO.getAllDocument(username, stage,filters);
	}
	@Override
	public List<AutoDebit> getlistBank(){
		return autoDebitDAO.getlistBank();
	}
	
	@Override
	public List<AutoDebit> getlistBranch(){
		return autoDebitDAO.getlistBranch();
	}
	
	@Override
	public int countAllRecord(String username, String stage, Map<String, Object> filters){
		return autoDebitDAO.countAll_AD(username, stage, filters);
	}
	

	@Override
	public void saveOrUpdate(AutoDebit entity) {
		// TODO Auto-generated method stub
		autoDebitDAO.saveOrUpdate(entity);
	}

	@Override
	public void save(AutoDebit entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AutoDebit entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AutoDebit getById(Integer id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutoDebit getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutoDebit loadById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebit> findByCriteria(Map criterias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutoDebit> findByExample(AutoDebit exampleInstance,
			String[] excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AutoDebit entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
