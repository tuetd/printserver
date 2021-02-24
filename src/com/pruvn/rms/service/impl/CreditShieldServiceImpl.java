package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.CSRecordFLDAO;
import com.pruvn.rms.dao.CSRecordFailedDAO;
import com.pruvn.rms.dao.CSRecordCompletedDAO;
import com.pruvn.rms.dao.CSRecordPendingDAO;
import com.pruvn.rms.dao.CSRecordRMTDAO;
import com.pruvn.rms.dao.CSRecordRMTReceiveDAO;
import com.pruvn.rms.dao.CSRecordWaitingDAO;
import com.pruvn.rms.dao.FollowUpDAO;
import com.pruvn.rms.domain.CSLogTransaction;
import com.pruvn.rms.domain.CSRecordFL;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordCompleted;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordRMT;
import com.pruvn.rms.domain.CSRecordRMTReceive;
import com.pruvn.rms.domain.CSRecordWaiting;
import com.pruvn.rms.service.CreditShieldService;

public class CreditShieldServiceImpl extends BaseCSRecordServiceImpl implements CreditShieldService {
	
	private CSRecordFLDAO csRecordFLDAO;
	
	private FollowUpDAO followUpDAO;
	
	private CSRecordRMTDAO csRecordRMTDAO;
	
	private CSRecordRMTReceiveDAO csRecordRMTReceiveDAO;
	
	private CSRecordWaitingDAO csRecordWaitingDAO;
	
	private CSRecordPendingDAO csRecordPendingDAO;
	
	private CSRecordFailedDAO csRecordFailedDAO;
	
	private CSRecordCompletedDAO csRecordCompletedDAO;
	
	@Override
	public CSRecordFL getCSRecordFLById(Integer id) {
		return csRecordFLDAO.getById(id);
	}
	
	@Override
	public List<CSRecordFL> getAllCSRecordFL_ACL(String username, String stage, Map<String, Object> filters){
		return csRecordFLDAO.findAll_ACL(username, stage, filters);
	}
	
	public CSRecordRMT getCSRecordRMTById(Integer id) {
		return csRecordRMTDAO.getById(id);
	}
	
	public List<CSRecordRMT> getAllCSRecordRMT_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordRMTDAO.findAll_ACL(username, stage, filters);
	}
	
	
	public CSRecordRMTReceive getCSRecordRMTReceiveById(Integer id){
		return csRecordRMTReceiveDAO.getById(id);
	}
	
	public List<CSRecordRMTReceive> getAllRecordRMTReceived_ACL(String username, String stage, Map<String, Object> filters){
		return csRecordRMTReceiveDAO.findAll_ACL(username, stage, filters);
	}
	
	
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
	
	public CSRecordCompleted getCSRecordPassById(Integer id) {
		return csRecordCompletedDAO.getById(id);
	}
	
	public List<CSRecordCompleted> getAllCSRecordCompleted_ACL(String username, String stage, Map<String, Object> filters) {
		return csRecordCompletedDAO.findAll_ACL(username, stage, filters);
	}
//	@Override
//	public List<FollowUp> getFollowUpsByRecordId(Integer recordId) {
//		return followUpDAO.findAllByRecordId(recordId);
//	}
//	
//	@Override
//	public FollowUp getFollowUpById(Integer id){
//		return followUpDAO.getById(id);
//	}
//	
//	@Override
//	public void saveFollowUp(FollowUp followUp){
//		followUpDAO.saveOrUpdate(followUp);
//	}
	
	public CSRecordWaitingDAO getCsRecordWaitingDAO() {
		return csRecordWaitingDAO;
	}

	public void setCsRecordWaitingDAO(CSRecordWaitingDAO csRecordWaitingDAO) {
		this.csRecordWaitingDAO = csRecordWaitingDAO;
	}

	public CSRecordRMTDAO getCsRecordRMTDAO() {
		return csRecordRMTDAO;
	}

	public void setCsRecordRMTDAO(CSRecordRMTDAO csRecordRMTDAO) {
		this.csRecordRMTDAO = csRecordRMTDAO;
	}

	public CSRecordFLDAO getCsRecordFLDAO() {
		return csRecordFLDAO;
	}

	public void setCsRecordFLDAO(CSRecordFLDAO csRecordFLDAO) {
		this.csRecordFLDAO = csRecordFLDAO;
	}

	public CSRecordRMTReceiveDAO getCsRecordRMTReceiveDAO() {
		return csRecordRMTReceiveDAO;
	}

	public void setCsRecordRMTReceiveDAO(CSRecordRMTReceiveDAO csRecordRMTReceiveDAO) {
		this.csRecordRMTReceiveDAO = csRecordRMTReceiveDAO;
	}

	public FollowUpDAO getFollowUpDAO() {
		return followUpDAO;
	}
	public void setFollowUpDAO(FollowUpDAO followUpDAO) {
		this.followUpDAO = followUpDAO;
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
	
}
