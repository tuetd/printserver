package com.pruvn.rms.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.pruvn.rms.dao.HomeLoanDao;
import com.pruvn.rms.dao.InsuranceDao;
import com.pruvn.rms.dao.MRCDao;
import com.pruvn.rms.dao.ScreenDao;
import com.pruvn.rms.domain.HomeLoan;
import com.pruvn.rms.domain.Insurance;
import com.pruvn.rms.domain.MRC;
import com.pruvn.rms.domain.TB6;
import com.pruvn.rms.model.FilterHomeLoanForm;
import com.pruvn.rms.model.FilterInsuranceForm;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.UploadService;
import com.pruvn.rms.service.response.UploadInsuranceResponse;
import com.pruvn.rms.service.response.UploadTB6Response;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.SqlConstant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.TB6_STAGE;


public class UploadServiceImpl extends BaseRecordServiceImpl implements UploadService {

	private MRCDao mrcDao;
	
	private HomeLoanDao homeLoanDao;
	
	private ScreenDao screenDao;
	
	private InsuranceDao insuranceDao;
	
		
	public ScreenDao getScreenDao() {
		return screenDao;
	}

	public void setScreenDao(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	/**
	 * @return the mrcDao
	 */
	public MRCDao getMrcDao() {
		return mrcDao;
	}

	/**
	 * @param mrcDao the mrcDao to set
	 */
	public void setMrcDao(MRCDao mrcDao) {
		this.mrcDao = mrcDao;
	}

	/**
	 * @return the homeLoanDao
	 */
	public HomeLoanDao getHomeLoanDao() {
		return homeLoanDao;
	}

	/**
	 * @param homeLoanDao the homeLoanDao to set
	 */
	public void setHomeLoanDao(HomeLoanDao homeLoanDao) {
		this.homeLoanDao = homeLoanDao;
	}

	/**
	 * @return the insuranceDao
	 */
	public InsuranceDao getInsuranceDao() {
		return insuranceDao;
	}

	/**
	 * @param insuranceDao the insuranceDao to set
	 */
	public void setInsuranceDao(InsuranceDao insuranceDao) {
		this.insuranceDao = insuranceDao;
	}

	@Override
	public List<MRC> getMRCList(FilterMRCForm filterMRCForm){		
		return mrcDao.getMRCList(filterMRCForm);	
	}
	
	@Override
	public List<HomeLoan> getHomeLoanList(FilterHomeLoanForm filterHomeLoanForm) {
		if(com.pruvn.rms.utils.CommonUtils.isNullOrEmpty(filterHomeLoanForm.getLoanNo())) 
			return homeLoanDao.findAll();
		else return homeLoanDao.searchByLoanNo(filterHomeLoanForm.getLoanNo());
		
	}
	
	@Override
	@Transactional
	public void saveUploadMRC(List<MRC> listMRC){
		for (MRC mrc : listMRC) {
			MRC mrcDB = mrcDao.findByLoanNo(mrc.getLoanNo());			
			if(mrcDB == null) {			
				mrcDao.saveOrUpdate(mrc);	
			}
		}	
	}
	
	@Override
	@Transactional
	public void saveUploadHomeLoan(List<HomeLoan> listHomeLoan){
		for (HomeLoan hl : listHomeLoan) {
			HomeLoan homeLoanDB = homeLoanDao.findByLoanNo(hl.getLoanNo());			
			if(homeLoanDB == null) {			 
				homeLoanDao.saveOrUpdate(hl);	
			}
		}	
	}
	
	@Override
	@Transactional
	public void remarkMRC(String id, String remark, String dateReturn){
		MRC mrc = mrcDao.getById(Integer.parseInt(id));
		mrc.setOrgReturn(dateReturn);
		mrc.setRemark(remark);
		mrcDao.saveOrUpdate(mrc);
	}
	
	@Override
	@Transactional
	public void remarkHomeLoan(String id, String remark, String dateReturn){
		HomeLoan homeLoan = homeLoanDao.getById(Integer.parseInt(id));
		homeLoan.setReturnOriginalDocument(dateReturn);
		homeLoan.setRemark(remark);
		homeLoanDao.saveOrUpdate(homeLoan);
	}
	
	@Override
	public List<Insurance> getInsuranceList(FilterInsuranceForm filterInsuranceForm){
		if(com.pruvn.rms.utils.CommonUtils.isNullOrEmpty(filterInsuranceForm.getLoanNo())) 
			return insuranceDao.findAll();
		else return insuranceDao.searchByLoanNo(filterInsuranceForm.getLoanNo());
	}

	@Override
	@Transactional
	public void remarkInsurance(String id, String remark, String dateReceive){
		Insurance ins = insuranceDao.getById(Integer.parseInt(id));
		try {
			ins.setReceiveDate(DateUtils.stringToDate(dateReceive, "dd/MM/yyyy"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ins.setRemark(remark);
		ins.setReceiveBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		insuranceDao.saveOrUpdate(ins);
	}

	@Override
	@Transactional
	public UploadInsuranceResponse saveUploadInsurance(List<Insurance> listInsurance){
		UploadInsuranceResponse resp = new UploadInsuranceResponse();
		for (Insurance ins : listInsurance) {
			Insurance tb6DB = insuranceDao.findByLoanNo(ins.getLoanNo());			
			if(tb6DB == null) {			
				insuranceDao.saveOrUpdate(ins);
				getRecordDAO().callAction(TB6_STAGE.TB6_UPLOAD.toString() , ACTIONS.INSURANCE_UPLOAD.toString(), SecurityContextHolder.getContext()
						.getAuthentication().getName(), ins.getId() +"", new String[]{});
				resp.getSuccessList().add(ins);
			} else {
				resp.getExistList().add(ins);
			}
		}
		
		return resp;
	}
}
