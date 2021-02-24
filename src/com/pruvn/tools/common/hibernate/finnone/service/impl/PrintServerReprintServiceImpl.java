package com.pruvn.tools.common.hibernate.finnone.service.impl;

import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintLogM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.PrtReprintLogMDAO;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.PrtReprintMDAO;
import com.pruvn.tools.common.hibernate.finnone.service.PrintServerReprintService;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class PrintServerReprintServiceImpl extends AbstractHibernateDAO<Object, Integer>
implements PrintServerReprintService {
	private PrtReprintMDAO prtReprintMDAO;
	private PrtReprintLogMDAO prtReprintLogMDAO;
	/**
	 * @return the prtReprintMDAO
	 */
	public PrtReprintMDAO getPrtReprintMDAO() {
		return prtReprintMDAO;
	}
	/**
	 * @param prtReprintMDAO the prtReprintMDAO to set
	 */
	public void setPrtReprintMDAO(PrtReprintMDAO prtReprintMDAO) {
		this.prtReprintMDAO = prtReprintMDAO;
	}
	/**
	 * @return the prtReprintLogMDAO
	 */
	public PrtReprintLogMDAO getPrtReprintLogMDAO() {
		return prtReprintLogMDAO;
	}
	/**
	 * @param prtReprintLogMDAO the prtReprintLogMDAO to set
	 */
	public void setPrtReprintLogMDAO(PrtReprintLogMDAO prtReprintLogMDAO) {
		this.prtReprintLogMDAO = prtReprintLogMDAO;
	}
	public List<PrtReprintM> listAllReprint() {
		return prtReprintMDAO.findAll();
	}
	public void saveOrUpdate(PrtReprintM entity) {
		prtReprintMDAO.saveOrUpdate(entity);
	}
	public PrtReprintM findPrtReprintById(Integer id) {
		return prtReprintMDAO.getById(id);
	}
	public List<PrtReprintLogM> searchReprintLogByApplid(String applid) {
		return prtReprintLogMDAO.findByApplid(applid);
	}
	public List<PrtReprintLogM> searchReprintLogByNotes(String notes) {
		return prtReprintLogMDAO.findByNotes(notes);
	}
	public List<PrtReprintLogM> listAllReprintLog() {
		return prtReprintLogMDAO.findAll();
	}
	public void deleteReprint(PrtReprintM prtReprint) {
		prtReprintMDAO.delete(prtReprint);
		
	}
	public List<PrtReprintM> findPrtReprintByApplid(String applid) {
		return prtReprintMDAO.findByApplid(applid);
	}
	
	

}
