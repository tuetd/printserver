package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.RecordNotSendDAO;
import com.pruvn.rms.dao.RecordPODAO;
import com.pruvn.rms.dao.RecordPOReturnDAO;
import com.pruvn.rms.dao.RecordPendingDAO;
import com.pruvn.rms.dao.RecordRDDAO;
import com.pruvn.rms.dao.RecordRRDAO;
import com.pruvn.rms.dao.RecordVerifiedDAO;
import com.pruvn.rms.dao.UploadDataDao;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordNotSend;
import com.pruvn.rms.domain.RecordPO;
import com.pruvn.rms.domain.RecordPOReturn;
import com.pruvn.rms.domain.RecordPending;
import com.pruvn.rms.domain.RecordPrepared;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.domain.RecordVerified;
import com.pruvn.rms.domain.UploadData;
import com.pruvn.rms.service.RecordService;

public class RecordServiceImpl extends BaseRecordServiceImpl implements RecordService {
	
	private RecordRRDAO recordRRDAO;
	
	private RecordRDDAO recordRDDAO;
	
	private RecordPODAO recordPODAO;
	
	private RecordPOReturnDAO recordPOReturnDAO;
	
	private RecordVerifiedDAO recordVerifiedDAO;
	
	private RecordNotSendDAO recordNotSendDAO;
	
	private RecordPendingDAO recordPendingDAO;
	
	private UploadDataDao uploadDataDao;

	@Override
	public RecordPrepared getRecordPreparedById(Integer id) {
		return recordRRDAO.getById(id);
	}
	
	@Override
	public List<RecordPrepared> getAllRecordPrepareds_ACL(String username, String stage, Map<String, Object> filters){
		return recordRRDAO.findAllRecordRRs_ACL(username, stage, filters);
	}
	
	@Override
	public RecordReady getRecordReadyById(Integer id) {
		return recordRDDAO.getById(id);
	}
	
	@Override
	public List<RecordReady> getAllRecordReadys_ACL(String username, String stage, Map<String, Object> filters){
		return recordRDDAO.findAllRecordRDs_ACL(username, stage, filters);
	}
	
	@Override
	public RecordPO getRecordPOById(Integer id) {
		return recordPODAO.getById(id);
	}
	
	@Override
	public List<RecordPO> getAllRecordPOs_ACL(String username, String stage, Map<String, Object> filters){
		return recordPODAO.findAllRecordPOs_ACL(username, stage, filters);
	}
	
	@Override
	public RecordPOReturn getRecordPOReturnById(Integer id) {
		return recordPOReturnDAO.getById(id);
	}
	
	@Override
	public List<RecordPOReturn> getAllRecordPOReturns_ACL(String username, String stage, Map<String, Object> filters){
		return recordPOReturnDAO.findAllRecordPOReturns_ACL(username, stage, filters);
	}
	
	@Override
	public RecordPO getRecordPOByLoanIdAndName(String loanId,String customerName){
		return recordPODAO.getRecordPOByLoanIdAndName(loanId, customerName);
	}
	
	@Override
	public RecordNotSend getRecordNotSendById(Integer id) {
		return recordNotSendDAO.getById(id);
	}
	
	@Override
	public List<RecordNotSend> getAllRecordNotSends_ACL(String username, String stage, Map<String, Object> filters){
		return recordNotSendDAO.findAllRecordNotSends_ACL(username, stage, filters);
	}
	
	@Override
	public RecordPending getRecordPendingById(Integer id) {
		return recordPendingDAO.getById(id);
	}
	
	@Override
	public List<RecordPending> getAllRecordPendings_ACL(String username, String stage, Map<String, Object> filters){
		return recordPendingDAO.findAllRecordPendings_ACL(username, stage, filters);
	}
	
	@Override
	public RecordVerified getRecordVerifiedById(Integer id) {
		return recordVerifiedDAO.getById(id);
	}
	
	@Override
	public List<RecordVerified> getAllRecordVerifieds_ACL(String username, String stage, Map<String, Object> filters){
		return recordVerifiedDAO.findAllRecordVerifieds_ACL(username, stage, filters);
	}
	
	@Override
	public void saveUploadData(UploadData ud) {
		uploadDataDao.saveOrUpdate(ud);
	}
	
	
	
	public RecordRRDAO getRecordRRDAO() {
		return recordRRDAO;
	}

	public void setRecordRRDAO(RecordRRDAO recordRRDAO) {
		this.recordRRDAO = recordRRDAO;
	}

	public RecordRDDAO getRecordRDDAO() {
		return recordRDDAO;
	}

	public void setRecordRDDAO(RecordRDDAO recordRDDAO) {
		this.recordRDDAO = recordRDDAO;
	}

	public RecordPODAO getRecordPODAO() {
		return recordPODAO;
	}

	public void setRecordPODAO(RecordPODAO recordPODAO) {
		this.recordPODAO = recordPODAO;
	}

	public RecordPOReturnDAO getRecordPOReturnDAO() {
		return recordPOReturnDAO;
	}

	public void setRecordPOReturnDAO(RecordPOReturnDAO recordPOReturnDAO) {
		this.recordPOReturnDAO = recordPOReturnDAO;
	}

	public RecordVerifiedDAO getRecordVerifiedDAO() {
		return recordVerifiedDAO;
	}

	public void setRecordVerifiedDAO(RecordVerifiedDAO recordVerifiedDAO) {
		this.recordVerifiedDAO = recordVerifiedDAO;
	}

	public RecordNotSendDAO getRecordNotSendDAO() {
		return recordNotSendDAO;
	}

	public void setRecordNotSendDAO(RecordNotSendDAO recordNotSendDAO) {
		this.recordNotSendDAO = recordNotSendDAO;
	}

	public RecordPendingDAO getRecordPendingDAO() {
		return recordPendingDAO;
	}

	public void setRecordPendingDAO(RecordPendingDAO recordPendingDAO) {
		this.recordPendingDAO = recordPendingDAO;
	}

	public UploadDataDao getUploadDataDao() {
		return uploadDataDao;
	}

	public void setUploadDataDao(UploadDataDao uploadDataDao) {
		this.uploadDataDao = uploadDataDao;
	}
	@Override
	public RecordReady getRecordRDByLoanId(String loanId) {
		return recordRDDAO.getRecordRDByLoanId(loanId);
	}
	
	@Override
	public RecordPending getRecordPendingByLoanId(String loanId) {
		return recordRDDAO.getRecordPendingByLoanId(loanId);
	}

	@Override
	public Record getRecordByLoanId(String loanId) {
		// TODO Auto-generated method stub
		return recordPODAO.searchByAgreementNo(loanId);
	}

}

