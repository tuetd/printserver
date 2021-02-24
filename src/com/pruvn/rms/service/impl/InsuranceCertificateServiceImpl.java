package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.CSRecordCompletedDAO;
import com.pruvn.rms.dao.CSRecordFailedDAO;
import com.pruvn.rms.dao.CSRecordPendingDAO;
import com.pruvn.rms.dao.CSRecordSentDAO;
import com.pruvn.rms.dao.CSRecordWaitingDAO;
import com.pruvn.rms.dao.CSUploadDataDao;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordCompleted;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordSent;
import com.pruvn.rms.domain.CSRecordWaiting;
import com.pruvn.rms.domain.CSUploadData;
import com.pruvn.rms.service.InsuranceCertificateService;
import com.pruvn.rms.utils.Constant.CS_STAGE;

public class InsuranceCertificateServiceImpl extends BaseCSRecordServiceImpl implements InsuranceCertificateService {
	
	private CSRecordWaitingDAO csRecordWaitingDAO;
	
	private CSRecordPendingDAO csRecordPendingDAO;
	
	private CSRecordFailedDAO csRecordFailedDAO;
	
	private CSRecordCompletedDAO csRecordCompletedDAO;
	
	private CSUploadDataDao csUploadDataDao;
	
	private CSRecordSentDAO csRecordSentDAO;
	

	public CSRecordWaiting getCSRecordWaitingById(Integer id) {
		return csRecordWaitingDAO.getById(id);
	}
	
	public List<CSRecordWaiting> getAllCSRecordWaiting_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordWaitingDAO.findAll_ACL(username, stage, filters);
	}
	
	public CSRecordPending getCSRecordPendingById(Integer id) {
		return csRecordPendingDAO.getById(id);
	}
	
	public List<CSRecordPending> getAllCSRecordPending_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordPendingDAO.findAll_ACL(username, stage, filters);
	}
	
	public CSRecordFailed getCSRecordFailedById(Integer id) {
		return csRecordFailedDAO.getById(id);
	}
	
	public List<CSRecordFailed> getAllCSRecordFailed_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordFailedDAO.findAll_ACL(username, stage, filters);
	}
	
	public CSRecordCompleted getCSRecordCompletedById(Integer id) {
		return csRecordCompletedDAO.getById(id);
	}
	
	public List<CSRecordCompleted> getAllCSRecordCompleted_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordCompletedDAO.findAll_ACL(username, stage, filters);
	}
	
	public CSRecordSent getCSRecordSentById(Integer id) {
		return csRecordSentDAO.getById(id);
	}
	
	public List<CSRecordSent> getAllCSRecordSent_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordSentDAO.findAll_ACL(username, stage, filters);
	}
	
	@Override
	public CSRecord getCSRecorByIdAndCusName(String stage, String loanID,
			String customerName) {
		
		if(CS_STAGE.INSURANCE_CERTIFICATE_WAITING_LIST.getName().equals(stage)) {
			return csRecordWaitingDAO.getCSRecordWaitingByIdAndCusName(loanID, customerName);
		} else if(CS_STAGE.INSURANCE_CERTIFICATE_PENDING_LIST.getName().equals(stage)) {
			return csRecordPendingDAO.getCSRecordPendingByIdAndCusName(loanID, customerName);
		} else if(CS_STAGE.INSURANCE_CERTIFICATE_FAILED_LIST.getName().equals(stage)) {
			return csRecordFailedDAO.getCSRecordFailedByIdAndCusName(loanID, customerName);
		} else if(CS_STAGE.INSURANCE_CERTIFICATE_COMPLETED_LIST.getName().equals(stage)) {
			return csRecordCompletedDAO.getCSRecordCompletedByIdAndCusName(loanID, customerName);
		}
		return null;
	}


	public void saveUploadData(CSUploadData ud) {
		csUploadDataDao.saveOrUpdate(ud);
	}
	
	
	

	public CSRecordWaitingDAO getCsRecordWaitingDAO() {
		return csRecordWaitingDAO;
	}

	public void setCsRecordWaitingDAO(CSRecordWaitingDAO csRecordWaitingDAO) {
		this.csRecordWaitingDAO = csRecordWaitingDAO;
	}

	public CSRecordPendingDAO getCsRecordPendingDAO() {
		return csRecordPendingDAO;
	}

	public void setCsRecordPendingDAO(CSRecordPendingDAO csRecordPendingDAO) {
		this.csRecordPendingDAO = csRecordPendingDAO;
	}

	public CSRecordFailedDAO getCsRecordFailedDAO() {
		return csRecordFailedDAO;
	}

	public void setCsRecordFailedDAO(CSRecordFailedDAO csRecordFailedDAO) {
		this.csRecordFailedDAO = csRecordFailedDAO;
	}

	public CSRecordCompletedDAO getCsRecordCompletedDAO() {
		return csRecordCompletedDAO;
	}

	public void setCsRecordCompletedDAO(CSRecordCompletedDAO csRecordCompletedDAO) {
		this.csRecordCompletedDAO = csRecordCompletedDAO;
	}

	public CSUploadDataDao getCsUploadDataDao() {
		return csUploadDataDao;
	}

	public void setCsUploadDataDao(CSUploadDataDao csUploadDataDao) {
		this.csUploadDataDao = csUploadDataDao;
	}

	public CSRecordSentDAO getCsRecordSentDAO() {
		return csRecordSentDAO;
	}

	public void setCsRecordSentDAO(CSRecordSentDAO csRecordSentDAO) {
		this.csRecordSentDAO = csRecordSentDAO;
	}	
	
}
