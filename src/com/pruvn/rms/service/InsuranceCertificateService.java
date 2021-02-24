package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordCompleted;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordSent;
import com.pruvn.rms.domain.CSRecordWaiting;
import com.pruvn.rms.domain.CSUploadData;

public interface InsuranceCertificateService extends BaseCSRecordService {

	CSRecordWaiting getCSRecordWaitingById(Integer id);
	
	List<CSRecordWaiting> getAllCSRecordWaiting_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordPending getCSRecordPendingById(Integer id);
	
	List<CSRecordPending> getAllCSRecordPending_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordFailed getCSRecordFailedById(Integer id);
	
	List<CSRecordFailed> getAllCSRecordFailed_ACL(String username, String stage, Map<String, Object> filters);
	
	CSRecordCompleted getCSRecordCompletedById(Integer id);
	
	List<CSRecordCompleted> getAllCSRecordCompleted_ACL(String username, String stage, Map<String, Object> filters);

	CSRecordSent getCSRecordSentById(Integer id);
	
	List<CSRecordSent> getAllCSRecordSent_ACL(String username, String name,
			Map<String, Object> filters);
	

	void saveUploadData(CSUploadData ud);
	
	CSRecord getCSRecorByIdAndCusName(String stage, String loanID,
			String customerName);
	
}
