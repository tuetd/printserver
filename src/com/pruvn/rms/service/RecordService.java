package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordNotSend;
import com.pruvn.rms.domain.RecordPO;
import com.pruvn.rms.domain.RecordPOReturn;
import com.pruvn.rms.domain.RecordPending;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.domain.RecordPrepared;
import com.pruvn.rms.domain.RecordVerified;
import com.pruvn.rms.domain.UploadData;

public interface RecordService extends BaseRecordService{
	
	RecordPrepared getRecordPreparedById(Integer id);
	
	List<RecordPrepared> getAllRecordPrepareds_ACL(String username, String state, Map<String, Object> filters);
	
	RecordReady getRecordReadyById(Integer id);
	
	Record getRecordByLoanId(String loanId);
	
    RecordReady getRecordRDByLoanId(String loanId);
	RecordPending getRecordPendingByLoanId(String loanId);
	
	List<RecordReady> getAllRecordReadys_ACL(String username, String state, Map<String, Object> filters);
	
	RecordPO getRecordPOById(Integer id);
	
	List<RecordPO> getAllRecordPOs_ACL(String username, String state, Map<String, Object> filters);
	
	RecordPOReturn getRecordPOReturnById(Integer id);
	
	List<RecordPOReturn> getAllRecordPOReturns_ACL(String username, String state, Map<String, Object> filters);
	
	RecordPO getRecordPOByLoanIdAndName(String loanId,String customerName);

	List<RecordVerified> getAllRecordVerifieds_ACL(String username, String state, Map<String, Object> filters);
	
	RecordVerified getRecordVerifiedById(Integer id);

	List<RecordNotSend> getAllRecordNotSends_ACL(String username,String state, Map<String, Object> filters);

	RecordNotSend getRecordNotSendById(Integer id);

	List<RecordPending> getAllRecordPendings_ACL(String username,String state, Map<String, Object> filters);

	RecordPending getRecordPendingById(Integer id);

	void saveUploadData(UploadData ud);
}
