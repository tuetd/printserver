package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.RecordBranch;
import com.pruvn.rms.domain.RecordBranchDeliver;
import com.pruvn.rms.domain.RecordBranchReceive;
import com.pruvn.rms.domain.RecordBranchReturn;

public interface RecordBranchService extends BaseRecordService{
	RecordBranch getRecordBranchById(Integer id);
	
	List<RecordBranch> getAllRecordBranchs_ACL(String username, String state, Map<String, Object> filters);
	
	RecordBranchReceive getRecordBranchReceiveById(Integer id);
	
	List<RecordBranchReceive> getAllRecordBranchReceives_ACL(String username, String state, Map<String, Object> filters);
	
	RecordBranchReturn getRecordBranchReturnById(Integer id);
	
	List<RecordBranchReturn> getAllRecordBranchReturns_ACL(String username, String state, Map<String, Object> filters);
	
	RecordBranchDeliver getRecordBranchDeliverById(Integer id);
	
	List<RecordBranchDeliver> getAllRecordBranchDelivers_ACL(String username, String state, Map<String, Object> filters);
	
	

}
