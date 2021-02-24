package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.CSRecordFL;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordCompleted;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordRMT;
import com.pruvn.rms.domain.CSRecordRMTReceive;
import com.pruvn.rms.domain.CSRecordWaiting;

public interface CreditShieldService extends BaseCSRecordService {
	
	CSRecordFL getCSRecordFLById(Integer id);
	
	List<CSRecordFL> getAllCSRecordFL_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordRMT getCSRecordRMTById(Integer id);
	
	List<CSRecordRMT> getAllCSRecordRMT_ACL(String username, String stage, Map<String, Object> filters);
	
	
	CSRecordRMTReceive getCSRecordRMTReceiveById(Integer id);
	
	List<CSRecordRMTReceive> getAllRecordRMTReceived_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordWaiting getCSRecordWaitingById(Integer id);
	
	List<CSRecordWaiting> getAllCSRecordWaiting_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordPending getCSRecordPendingById(Integer id);
	
	List<CSRecordPending> getAllCSRecordPending_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordFailed getCSRecordFailedById(Integer id);
	
	List<CSRecordFailed> getAllCSRecordFailed_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordCompleted getCSRecordPassById(Integer id);
	
	List<CSRecordCompleted> getAllCSRecordCompleted_ACL(String username, String stage, Map<String, Object> filters);
	
	
	//List<FollowUp> getFollowUpsByRecordId(Integer recordId);
	
	//FollowUp getFollowUpById(Integer id);
	
	//void saveFollowUp(FollowUp followUp);

	

	
	

}
