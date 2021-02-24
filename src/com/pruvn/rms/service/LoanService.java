package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.FollowUp;
import com.pruvn.rms.domain.RecordFL;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.domain.RecordFL2;
import com.pruvn.rms.domain.RecordFL3;
import com.pruvn.rms.domain.RecordSuspense;
import com.pruvn.rms.domain.RecordWait;

public interface LoanService extends BaseRecordService {
	
	RecordFL getRecordFLById(Integer id);
	
	RecordFL1 getRecordRMTById(Integer id);
	//change upload 
	RecordFL1  getRecordFL1ByLoanId(String loanId); 
	RecordFL2  getRecordFL2ByLoanId(String loanId);
	RecordFL2 getRecordRMTRecieveById(Integer id);
	
	RecordFL3 getRecordRMTReturnById(Integer id);
	
	RecordWait getRecordWaitById(Integer id);
	RecordWait getRecordWaitByLoanId(String loanId);
	
	List<RecordFL> getAllRecords_ACL(String username, String state, Map<String, Object> filters);
	
	List<RecordFL1> getAllRecordsToRMT_ACL(String username, String state, Map<String, Object> filters);
	
	List<RecordFL2> getAllRecordsReceivedRMT_ACL(String username, String state, Map<String, Object> filters);
	
	//List<RecordFL3> getAllRecordsReturnRMT_ACL(String username, String state, Map<String, Object> filters);
	
	List<RecordWait> getAllRecordWait_ACL(String username, String state, Map<String, Object> filters);
	
	List<FollowUp> getFollowUpsByRecordId(Integer recordId);
	
	FollowUp getFollowUpById(Integer id);
	
	void saveFollowUp(FollowUp followUp);

	List<RecordSuspense> getAllRecordSuspense_ACL(String username, String stage,
			Map<String, Object> filters);

	RecordSuspense getRecordSuspenseById(Integer id);

	

	
	

}
