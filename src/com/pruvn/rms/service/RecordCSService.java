package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.RecordCSCompleted;
import com.pruvn.rms.domain.RecordCSWaiting;

public interface RecordCSService extends BaseRecordService{
	
	RecordCSWaiting getRecordCSWaitingById(Integer id);
	
	List<RecordCSWaiting> getAllRecordCSWaitings_ACL(String username, String state, Map<String, Object> filters);
	
	RecordCSCompleted getRecordCSCompletedById(Integer id);
	
	List<RecordCSCompleted> getAllRecordCSCompleteds_ACL(String username, String state, Map<String, Object> filters);
	
}
