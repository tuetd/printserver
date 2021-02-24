package com.pruvn.rms.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pruvn.rms.dao.TB6Dao;
import com.pruvn.rms.domain.TB6;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.TB6Service;
import com.pruvn.rms.service.response.UploadTB6Response;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.TB6_STAGE;


public class TB6ServiceImpl extends BaseRecordServiceImpl implements TB6Service {

	private TB6Dao tb6Dao;

	
	
	/**
	 * @return the tb6Dao
	 */
	public TB6Dao getTb6Dao() {
		return tb6Dao;
	}

	/**
	 * @param tb6Dao the tb6Dao to set
	 */
	public void setTb6Dao(TB6Dao tb6Dao) {
		this.tb6Dao = tb6Dao;
	}

	public List<TB6> getTB6List(String stage,
			FilterMRCForm filterMRCForm) {
		return tb6Dao.getTB6List(stage,filterMRCForm);	
	}
	
	
	public UploadTB6Response saveUploadTB6(List<TB6> listTB6){
		UploadTB6Response resp = new UploadTB6Response();
		for (TB6 tb6 : listTB6) {
			TB6 tb6DB = tb6Dao.findByLoanNo(tb6.getLoanNo());			
			if(tb6DB == null) {			
				tb6Dao.saveOrUpdate(tb6);
				getRecordDAO().callAction(TB6_STAGE.TB6_UPLOAD.toString() , ACTIONS.TB6_UPLOAD.toString(), SecurityContextHolder.getContext()
						.getAuthentication().getName(), tb6.getId() +"", new String[]{});
				resp.getSuccessList().add(tb6);
			} else {
				resp.getExistList().add(tb6);
			}
		}
		
		return resp;
	}
	
	/*
	public void receiveTB6(String id, String remark, String dateReturn){
		TB6 tb6 = tb6Dao.getById(Integer.parseInt(id));
		try{
			tb6.setReceivedDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(dateReturn));
		 } catch (Exception e) { 
		 }
		tb6.setReceivedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		tb6.setRemark(remark);
		tb6.setStage(TB6_STAGE.RECEIVED.toString());
		tb6Dao.saveOrUpdate(tb6);
	}
	
	
	public void markWatingTB6(String id, String remark, String dateWaiting){
		TB6 tb6 = tb6Dao.getById(Integer.parseInt(id));
		try{
			tb6.setWaitingDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(dateWaiting));
		 } catch (Exception e) {
		 }
		tb6.setWaitingBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		tb6.setRemark(remark);
		tb6.setStage(TB6_STAGE.WAITING.toString());
		tb6Dao.saveOrUpdate(tb6);
	}
	

	public void markCompleteTB6(String id, String remark, String dateComplete){
		TB6 tb6 = tb6Dao.getById(Integer.parseInt(id));
		try{
			tb6.setCompletedDate(new SimpleDateFormat(Constant.FORMAT_DATE).parse(dateComplete));
		 } catch (Exception e) {
		 }
		tb6.setCompletedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		tb6.setRemark(remark);
		tb6.setStage(TB6_STAGE.COMPLETED.toString());
		tb6Dao.saveOrUpdate(tb6);
	}
	*/
	
	
}
