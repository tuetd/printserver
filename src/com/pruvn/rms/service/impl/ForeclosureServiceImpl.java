package com.pruvn.rms.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pruvn.rms.dao.ForeclosureDao;
import com.pruvn.rms.domain.Foreclosure;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.ForeclosureService;
import com.pruvn.rms.service.response.UploadForeclosureResponse;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.FORECLOSURE_STAGE;


public class ForeclosureServiceImpl extends BaseRecordServiceImpl implements ForeclosureService {

	private ForeclosureDao closureDao;

	/**
	 * @return the closureDao
	 */
	public ForeclosureDao getForeclosureDao() {
		return closureDao;
	}


	/**
	 * @param closureDao the closureDao to set
	 */
	public void setForeclosureDao(ForeclosureDao closureDao) {
		this.closureDao = closureDao;
	}


	public List<Foreclosure> getForeclosureList(String stage,
			FilterMRCForm filterMRCForm) {
		return closureDao.getForeclosureList(stage,filterMRCForm);	
	}
	
	
	public UploadForeclosureResponse saveUploadForeclosure(List<Foreclosure> listForeclosure){
		UploadForeclosureResponse resp = new UploadForeclosureResponse();
		for (Foreclosure fore : listForeclosure) {
			Foreclosure foreDB = closureDao.findByLoanNo(fore.getLoanNo());			
			if(foreDB == null) {			
				closureDao.saveOrUpdate(fore);
				getRecordDAO().callAction(FORECLOSURE_STAGE.FORECLOSURE_UPLOAD.toString() , ACTIONS.FORECLOSURE_UPLOAD.toString(), SecurityContextHolder.getContext()
						.getAuthentication().getName(), fore.getId() +"", new String[]{});
				resp.getSuccessList().add(fore);
			} else {
				resp.getExistList().add(fore);
			}
		}
		
		return resp;
	}
}
