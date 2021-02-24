package com.pruvn.rms.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pruvn.rms.dao.CSLogTransactionDAO;
import com.pruvn.rms.dao.CSRecordDAO;
import com.pruvn.rms.dao.UserLogDAO;
import com.pruvn.rms.domain.CSLogTransaction;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.service.BaseCSRecordService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;

public class BaseCSRecordServiceImpl implements BaseCSRecordService {
	private CSRecordDAO csRecordDAO;
	
	private CSLogTransactionDAO csLogTransactionDAO;
	
	private UserLogDAO userLogDAO;

	@Override
	public String callInvolve(HttpServletRequest request, String action, String username, String stage, Integer recordId, String... values){
		
		String errorMsg =  csRecordDAO.callInvolve(action, username, stage , recordId, values);
		//Save user log in to table UserLog
		//Save user log 
		UserLog userlog= new UserLog();
    	userlog.setLogDate(new Date());
    	userlog.setUsername(username);    	
    	userlog.setRemoteIP(request.getRemoteAddr());
    	userlog.setSession(request.getSession().getId());    	
    	userlog.setLogType(LOG_TYPE.CREDIT_SHIELD.toString());
    	userlog.setActivity(action);
		userlog.setInput("Record ID: " + recordId + ";Action:" + action
				+ ";Stage:" + stage);
    	userlog.setOutput(errorMsg);
    	if(CommonUtils.isNullOrEmpty(errorMsg)){
    		userlog.setStatus(LOG_STATUS.SUCCESS.toString());
    	} else {
    		userlog.setStatus(LOG_STATUS.FAILURE.toString());
    	}
    	userlog.setStatusNote(errorMsg);
    	userLogDAO.save(userlog);
		
		return errorMsg;
	}
	
	
	@Override
	public List<CSLogTransaction> getCSLogTransactionsByCSRecordId(Integer recordId){
		return csLogTransactionDAO.getByCSRecordId(recordId);
	}	
	
	public CSRecordDAO getCsRecordDAO() {
		return csRecordDAO;
	}


	public void setCsRecordDAO(CSRecordDAO csRecordDAO) {
		this.csRecordDAO = csRecordDAO;
	}


	/**
	 * @return the csLogTransactionDAO
	 */
	public CSLogTransactionDAO getCsLogTransactionDAO() {
		return csLogTransactionDAO;
	}


	/**
	 * @param csLogTransactionDAO the csLogTransactionDAO to set
	 */
	public void setCsLogTransactionDAO(CSLogTransactionDAO csLogTransactionDAO) {
		this.csLogTransactionDAO = csLogTransactionDAO;
	}

	public UserLogDAO getUserLogDAO() {
		return userLogDAO;
	}

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}
	
	
}