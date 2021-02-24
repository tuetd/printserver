package com.pruvn.rms.service.impl;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.dao.ScreenDao;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.Screen;
import com.pruvn.rms.service.UtilitiesService;


public class UtilitiesServiceImpl extends BaseRecordServiceImpl implements UtilitiesService {

	private ScreenDao screenDao;
	
	public ScreenDao getScreenDao() {
		return screenDao;
	}

	public void setScreenDao(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	@Override
	public String synchronizeData() {
		return getRecordDAO().synchronizeData();
	}
	@Override
	public Record searchRecordByAgreementNo(String agreementNo){
		return getRecordDAO().searchByAgreementNo(agreementNo);
	}
	
	@Override
	public List<Screen> getListScreen(String stage, String system){
		return screenDao.getListScreen(stage, system);
	}
	
	@Override
	public List<Screen> getListScreen2(String stage, String system){
		return screenDao.getListScreen2(stage, system);
	}
	
	@Override
	public Screen getScreen(String stage){
		List<Screen> list =  screenDao.findByStage(stage);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public 	List<Record> getAllRecords(Map<String, Object> filters){
		//return getRecordDAO().findAll(username, filters);
		return getRecordDAO().getAllRecords(filters);
	}
	@Override
	public 	List<Record> getAllRecordsCreditShield(Map<String, Object> filters){
		//return getRecordDAO().findAll(username, filters);
		return getRecordDAO().getAllRecordsCreditShield(filters);
	}
	
	@Override
	public Record getRecordById(Integer id) {
		return getRecordDAO().getById(id);
	}
	
	@Override
	public CSRecord searchCSByAgreementNo(String agreementNo){
		return getCsRecordDAO().searchByAgreementNo(agreementNo);
	}

	@Override
	public String getRefNoSeq() {
		// TODO Auto-generated method stub
		return getRecordDAO().getRefNoSeq();
	}	
}
