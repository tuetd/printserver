package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.RecordBranchDAO;
import com.pruvn.rms.dao.RecordBranchDeliverDAO;
import com.pruvn.rms.dao.RecordBranchReceiveDAO;
import com.pruvn.rms.dao.RecordBranchReturnDAO;
import com.pruvn.rms.domain.RecordBranch;
import com.pruvn.rms.domain.RecordBranchDeliver;
import com.pruvn.rms.domain.RecordBranchReceive;
import com.pruvn.rms.domain.RecordBranchReturn;
import com.pruvn.rms.service.RecordBranchService;

public class RecordBranchServiceImpl extends BaseRecordServiceImpl implements RecordBranchService {
	
	private RecordBranchDAO recordBranchDAO;
	
	private RecordBranchReceiveDAO recordBranchReceiveDAO;
	
	private RecordBranchReturnDAO recordBranchReturnDAO;
	
	private RecordBranchDeliverDAO recordBranchDeliverDAO;
	

	
	@Override
	public RecordBranch getRecordBranchById(Integer id) {
		return recordBranchDAO.getById(id);
	}
	
	@Override
	public List<RecordBranch> getAllRecordBranchs_ACL(String username, String stage, Map<String, Object> filters){
		return recordBranchDAO.findAllRecordBranch_ACL(username, stage, filters);
	}
	
	@Override
	public RecordBranchReceive getRecordBranchReceiveById(Integer id) {
		return recordBranchReceiveDAO.getById(id);
	}
	
	@Override
	public List<RecordBranchReceive> getAllRecordBranchReceives_ACL(String username, String stage, Map<String, Object> filters){
		return recordBranchReceiveDAO.findAllRecordBranchReceive_ACL(username, stage, filters);
	}
	
	@Override
	public RecordBranchReturn getRecordBranchReturnById(Integer id) {
		return recordBranchReturnDAO.getById(id);
	}
	
	@Override
	public List<RecordBranchReturn> getAllRecordBranchReturns_ACL(String username, String stage, Map<String, Object> filters){
		return recordBranchReturnDAO.findAllRecordBranchReturn_ACL(username, stage, filters);
	}
	
	@Override
	public RecordBranchDeliver getRecordBranchDeliverById(Integer id) {
		return recordBranchDeliverDAO.getById(id);
	}
	
	@Override
	public List<RecordBranchDeliver> getAllRecordBranchDelivers_ACL(String username, String stage, Map<String, Object> filters){
		return recordBranchDeliverDAO.findAllRecordBranchDeliver_ACL(username, stage, filters);
	}
	
	public RecordBranchDAO getRecordBranchDAO() {
		return recordBranchDAO;
	}

	public void setRecordBranchDAO(RecordBranchDAO recordBranchDAO) {
		this.recordBranchDAO = recordBranchDAO;
	}
	
	public RecordBranchReceiveDAO getRecordBranchReceiveDAO() {
		return recordBranchReceiveDAO;
	}

	public void setRecordBranchReceiveDAO(
			RecordBranchReceiveDAO recordBranchReceiveDAO) {
		this.recordBranchReceiveDAO = recordBranchReceiveDAO;
	}

	public RecordBranchReturnDAO getRecordBranchReturnDAO() {
		return recordBranchReturnDAO;
	}

	public void setRecordBranchReturnDAO(RecordBranchReturnDAO recordBranchReturnDAO) {
		this.recordBranchReturnDAO = recordBranchReturnDAO;
	}

	public RecordBranchDeliverDAO getRecordBranchDeliverDAO() {
		return recordBranchDeliverDAO;
	}

	public void setRecordBranchDeliverDAO(
			RecordBranchDeliverDAO recordBranchDeliverDAO) {
		this.recordBranchDeliverDAO = recordBranchDeliverDAO;
	}
	
}
