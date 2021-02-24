package com.pruvn.rms.service.impl;

import java.util.List;

import com.pruvn.rms.dao.StoredLoanDao;
import com.pruvn.rms.domain.StoredLoan;
import com.pruvn.rms.model.FilterStoredLoanForm;
import com.pruvn.rms.service.StoredLoanService;
import com.pruvn.rms.service.response.UploadStoredLoanResponse;


public class StoredLoanServiceImpl extends BaseRecordServiceImpl implements StoredLoanService {

	private StoredLoanDao storedLoanDao;
	public StoredLoanDao getStoredLoanDao() {
		return storedLoanDao;
	}
	public void setStoredLoanDao(StoredLoanDao storedLoanDao) {
		this.storedLoanDao = storedLoanDao;
	}
	public List<StoredLoan> getForeclosureList(
			FilterStoredLoanForm filterMRCForm) {
		return storedLoanDao.getForeclosureList(filterMRCForm);	
	}
	public void saveUploadForeclosure(List<StoredLoan> listForeclosure){
		for (StoredLoan fore : listForeclosure) {
			storedLoanDao.saveOrUpdate(fore);
		}
	}
	@Override
	public UploadStoredLoanResponse updateStoredLoan(List<StoredLoan> listForeclosure,String username) {
		// TODO Auto-generated method stub
		UploadStoredLoanResponse resp = new UploadStoredLoanResponse();
		for (StoredLoan fore : listForeclosure) {
			StoredLoan foreDB = storedLoanDao.findByBarCodeAndNameBox(fore.getBarCode(),fore.getNameBox());			
			if(foreDB != null) {			
				storedLoanDao.updateDestroyStoredLoan(fore,username);
				resp.getSuccessList().add(fore);
			} else {
				resp.getExistList().add(fore);
			}
		}
		return resp;
	}
	@Override
	public StoredLoan getStoredLoanById(Integer id) {
		// TODO Auto-generated method stub
		return storedLoanDao.getById(id);
	}
	@Override
	public void updateStoredLoan(StoredLoan loan){
		storedLoanDao.saveOrUpdate(loan);
	}
}
