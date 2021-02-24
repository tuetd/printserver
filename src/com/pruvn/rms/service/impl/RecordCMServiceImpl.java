package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.RecordInBoxDAO;
import com.pruvn.rms.dao.RecordScanCMCheckDAO;
import com.pruvn.rms.dao.RecordScanCMDAO;
import com.pruvn.rms.domain.RecordInBox;
import com.pruvn.rms.domain.RecordScanCM;
import com.pruvn.rms.domain.RecordScanCMCheck;
import com.pruvn.rms.service.RecordCMService;

public class RecordCMServiceImpl extends BaseRecordServiceImpl implements RecordCMService {
	
	private RecordScanCMDAO recordScanCMDAO;
	
	private RecordScanCMCheckDAO recordScanCMCheckDAO;
	
	private RecordInBoxDAO recordInBoxDAO;
	

	@Override
	public RecordScanCM getRecordScanCMById(Integer id) {
		return recordScanCMDAO.getById(id);
	}
	
	@Override
	public List<RecordScanCM> getAllRecordScanCMs_ACL(String username, String stage, Map<String, Object> filters){
		return recordScanCMDAO.findAllRecordScanCM_ACL(username, stage, filters);
	}
	
	@Override
	public RecordScanCMCheck getRecordScanCMCheckById(Integer id) {
		return recordScanCMCheckDAO.getById(id);
	}
	
	@Override
	public List<RecordScanCMCheck> getAllRecordScanCMChecks_ACL(String username, String stage, Map<String, Object> filters){
		return recordScanCMCheckDAO.findAllRecordScanCMCheck_ACL(username, stage, filters);
	}
	
	@Override
	public RecordInBox getRecordInBoxById(Integer id) {
		return recordInBoxDAO.getById(id);
	}
	
	@Override
	public List<RecordInBox> getAllRecordInBoxs_ACL(String username, String stage, Map<String, Object> filters){
		return recordInBoxDAO.findAllRecordInBox_ACL(username, stage, filters);
	}

	public RecordScanCMDAO getRecordScanCMDAO() {
		return recordScanCMDAO;
	}

	public void setRecordScanCMDAO(RecordScanCMDAO recordScanCMDAO) {
		this.recordScanCMDAO = recordScanCMDAO;
	}

	public RecordScanCMCheckDAO getRecordScanCMCheckDAO() {
		return recordScanCMCheckDAO;
	}

	public void setRecordScanCMCheckDAO(RecordScanCMCheckDAO recordScanCMCheckDAO) {
		this.recordScanCMCheckDAO = recordScanCMCheckDAO;
	}

	public RecordInBoxDAO getRecordInBoxDAO() {
		return recordInBoxDAO;
	}

	public void setRecordInBoxDAO(RecordInBoxDAO recordInBoxDAO) {
		this.recordInBoxDAO = recordInBoxDAO;
	}
	
}
