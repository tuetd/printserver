package com.pruvn.rms.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pruvn.rms.dao.CSRecordDAO;
import com.pruvn.rms.dao.LogTransactionDAO;
import com.pruvn.rms.dao.RecordDAO;
import com.pruvn.rms.dao.UserLogDAO;
import com.pruvn.rms.domain.LogTransaction;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.service.BaseRecordService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;

public class BaseRecordServiceImpl implements BaseRecordService {
	
	private RecordDAO recordDAO;
	
	private LogTransactionDAO logTransactionDAO;
	
	private UserLogDAO userLogDAO;

	private CSRecordDAO csRecordDAO;
	
	public RecordDAO getRecordDAO() {
		return recordDAO;
	}

	public void setRecordDAO(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	public LogTransactionDAO getLogTransactionDAO() {
		return logTransactionDAO;
	}
	
	public void setLogTransactionDAO(LogTransactionDAO logTransactionDAO) {
		this.logTransactionDAO = logTransactionDAO;
	}
	
	public UserLogDAO getUserLogDAO() {
		return userLogDAO;
	}

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}

	/**
	 * @return the csRecordDAO
	 */
	public CSRecordDAO getCsRecordDAO() {
		return csRecordDAO;
	}

	/**
	 * @param csRecordDAO the csRecordDAO to set
	 */
	public void setCsRecordDAO(CSRecordDAO csRecordDAO) {
		this.csRecordDAO = csRecordDAO;
	}
	
	@Override
	public String callInvolve(HttpServletRequest request, String action, String username, String stage, Integer recordId, String... values){

    	
		String errorMsg =  recordDAO.callInvolve(action, username, stage , recordId, values);
		//Save user log in to table UserLog
		//Save user log 
		UserLog userlog= new UserLog();
    	userlog.setLogDate(new Date());
    	userlog.setUsername(username);    	
    	userlog.setRemoteIP(request.getRemoteAddr());
    	userlog.setSession(request.getSession().getId());    	
    	userlog.setLogType(LOG_TYPE.DISBURSAL_LOAN.toString());
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
	public List<LogTransaction> getLogTransactionsByRecordId(Integer recordId){
		return logTransactionDAO.getByRecordId(recordId);
	}
	
	@Override
	public List<LogTransaction> getLogTransactions(Integer recordId, String logType){
		return logTransactionDAO.getLogTransactions(recordId, logType);
	}
	
	@Override
	public String callAction(HttpServletRequest request, String stage, String action, String id, String... values){
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String errorMsg =  recordDAO.callAction(stage, action, userName , id, values);
		//Save user log in to table UserLog
		UserLog userlog= new UserLog();
    	userlog.setLogDate(new Date());
    	userlog.setUsername(userName);    	
    	userlog.setRemoteIP(request.getRemoteAddr());
    	userlog.setSession(request.getSession().getId());    	
    	userlog.setLogType(LOG_TYPE.TB6.toString());
    	userlog.setActivity(action);
		userlog.setInput("TB6 ID: " + id + ";Action:" + action);
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
	public void saveUserLog(UserLog userlog){
		userLogDAO.saveOrUpdate(userlog);
	}

	@Override
	public Record getRecordByLoanId(String loanId) {
		// TODO Auto-generated method stub
		return recordDAO.searchByAgreementNo(loanId);
	}
	
	
}
