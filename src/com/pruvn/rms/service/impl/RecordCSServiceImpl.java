package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.RecordCSCompletedDAO;
import com.pruvn.rms.dao.RecordCSWaitingDAO;
import com.pruvn.rms.domain.RecordCSCompleted;
import com.pruvn.rms.domain.RecordCSWaiting;
import com.pruvn.rms.service.RecordCSService;

public class RecordCSServiceImpl extends BaseRecordServiceImpl implements RecordCSService {
	
	private RecordCSWaitingDAO recordCSWaitingDAO;
	
	private RecordCSCompletedDAO recordCSCompletedDAO;
	
	
	public RecordCSWaiting getRecordCSWaitingById(Integer id){
		return recordCSWaitingDAO.getById(id);
	}
	
	public List<RecordCSWaiting> getAllRecordCSWaitings_ACL(String username, String stage, Map<String, Object> filters) {
		return recordCSWaitingDAO.findAllRecordCSWaitings_ACL(username, stage, filters);
	}
	
	public RecordCSCompleted getRecordCSCompletedById(Integer id) {
		return recordCSCompletedDAO.getById(id);
	}
	
	public List<RecordCSCompleted> getAllRecordCSCompleteds_ACL(String username, String stage, Map<String, Object> filters) {
		return recordCSCompletedDAO.findAllRecordCSCompleteds_ACL(username, stage, filters);
	}

	public RecordCSWaitingDAO getRecordCSWaitingDAO() {
		return recordCSWaitingDAO;
	}

	public void setRecordCSWaitingDAO(RecordCSWaitingDAO recordCSWaitingDAO) {
		this.recordCSWaitingDAO = recordCSWaitingDAO;
	}

	public RecordCSCompletedDAO getRecordCSCompletedDAO() {
		return recordCSCompletedDAO;
	}

	public void setRecordCSCompletedDAO(RecordCSCompletedDAO recordCSCompletedDAO) {
		this.recordCSCompletedDAO = recordCSCompletedDAO;
	}
	
}

