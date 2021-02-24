package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.FollowUpDAO;
import com.pruvn.rms.dao.RecordFL1DAO;
import com.pruvn.rms.dao.RecordFL2DAO;
import com.pruvn.rms.dao.RecordFL3DAO;
import com.pruvn.rms.dao.RecordFLDAO;
import com.pruvn.rms.dao.RecordSuspenseDAO;
import com.pruvn.rms.dao.RecordWaitDAO;
import com.pruvn.rms.domain.FollowUp;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordFL;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.domain.RecordFL2;
import com.pruvn.rms.domain.RecordFL3;
import com.pruvn.rms.domain.RecordSuspense;
import com.pruvn.rms.domain.RecordWait;
import com.pruvn.rms.service.LoanService;

public class LoanServiceImpl extends BaseRecordServiceImpl implements LoanService {
	
	private RecordFLDAO recordFLDAO;
	
	private RecordFL1DAO recordFL1DAO;
	
	private RecordFL2DAO recordFL2DAO;
	
	private RecordFL3DAO recordFL3DAO;
	
	private RecordWaitDAO recordWaitDAO;
	
	private FollowUpDAO followUpDAO;
	
	private RecordSuspenseDAO recordSuspenseDAO;
	
	@Override
	public RecordFL getRecordFLById(Integer id) {
		return recordFLDAO.getById(id);
	}
	
	@Override
	public List<RecordFL> getAllRecords_ACL(String username, String stage, Map<String, Object> filters){
		return recordFLDAO.findAll_ACL(username, stage, filters);
	}
	
	@Override
	public RecordFL1 getRecordRMTById(Integer id) {
		return recordFL1DAO.getById(id);
	}
	@Override
	public RecordFL1 getRecordFL1ByLoanId(String loanId) {
		return recordFL1DAO.getRecordFL1ByLoanId(loanId);
	}
	@Override
	public RecordFL2 getRecordFL2ByLoanId(String loanId) {
		return recordFL2DAO.getRecordFL2ByLoanId(loanId);
	}
	
	@Override
	public List<RecordFL1> getAllRecordsToRMT_ACL(String username, String stage, Map<String, Object> filters){
		return recordFL1DAO.findAllToRMT_ACL(username, stage, filters);
	}
	
	@Override
	public RecordFL2 getRecordRMTRecieveById(Integer id) {
		return recordFL2DAO.getById(id);
	}
	
	@Override
	public List<RecordFL2> getAllRecordsReceivedRMT_ACL(String username, String stage, Map<String, Object> filters){
		return recordFL2DAO.findAllReceivedRMT_ACL(username, stage, filters);
	}
	
	//@Override
	//public List<RecordFL3> getAllRecordsReturnRMT_ACL(String username, String stage, Map<String, Object> filters){
	//	return recordFL3DAO.findAllReturnRMT_ACL(username, stage, filters);
	//}
	
	@Override
	public RecordFL3 getRecordRMTReturnById(Integer id) {
		return recordFL3DAO.getById(id);
	}
	
	@Override
	public RecordWait getRecordWaitById(Integer id) {
		return recordWaitDAO.getById(id);
	}
	@Override
	public RecordWait getRecordWaitByLoanId(String loanId){
		return recordWaitDAO.getRecordWaitByLoanId(loanId);
	}
	@Override
	public List<RecordWait> getAllRecordWait_ACL(String username, String stage, Map<String, Object> filters){
		return recordWaitDAO.findAll_ACL(username, stage, filters);
	}
	
	@Override
	public List<FollowUp> getFollowUpsByRecordId(Integer recordId) {
		return followUpDAO.findAllByRecordId(recordId);
	}
	
	@Override
	public FollowUp getFollowUpById(Integer id){
		return followUpDAO.getById(id);
	}
	
	@Override
	public void saveFollowUp(FollowUp followUp){
		followUpDAO.saveOrUpdate(followUp);
	}
	
	
	public List<RecordSuspense> getAllRecordSuspense_ACL(String username, String stage,
			Map<String, Object> filters){
		return recordSuspenseDAO.findAll_ACL(username, stage, filters);
	}

	public RecordSuspense getRecordSuspenseById(Integer id) {
		return recordSuspenseDAO.getById(id);
	}
	
	
	public RecordFLDAO getRecordFLDAO() {
		return recordFLDAO;
	}
	
	public void setRecordFLDAO(RecordFLDAO recordFLDAO) {
		this.recordFLDAO = recordFLDAO;
	}
	
	public RecordFL1DAO getRecordFL1DAO() {
		return recordFL1DAO;
	}
	
	public void setRecordFL1DAO(RecordFL1DAO recordFL1DAO) {
		this.recordFL1DAO = recordFL1DAO;
	}
	
	public RecordFL2DAO getRecordFL2DAO() {
		return recordFL2DAO;
	}
	
	public void setRecordFL2DAO(RecordFL2DAO recordFL2DAO) {
		this.recordFL2DAO = recordFL2DAO;
	}
	
	public RecordFL3DAO getRecordFL3DAO() {
		return recordFL3DAO;
	}
	
	public void setRecordFL3DAO(RecordFL3DAO recordFL3DAO) {
		this.recordFL3DAO = recordFL3DAO;
	}

	public RecordWaitDAO getRecordWaitDAO() {
		return recordWaitDAO;
	}

	public void setRecordWaitDAO(RecordWaitDAO recordWaitDAO) {
		this.recordWaitDAO = recordWaitDAO;
	}

	public FollowUpDAO getFollowUpDAO() {
		return followUpDAO;
	}

	public void setFollowUpDAO(FollowUpDAO followUpDAO) {
		this.followUpDAO = followUpDAO;
	}

	public RecordSuspenseDAO getRecordSuspenseDAO() {
		return recordSuspenseDAO;
	}

	public void setRecordSuspenseDAO(RecordSuspenseDAO recordSuspenseDAO) {
		this.recordSuspenseDAO = recordSuspenseDAO;
	}
}
