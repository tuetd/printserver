package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.PrtReprintLogMDAO;
import com.pruvn.printserver.dao.PrtReprintMDAO;
import com.pruvn.printserver.entity.PrtReprintLogM;
import com.pruvn.printserver.entity.PrtReprintM;
import com.pruvn.printserver.services.PrintServerReprintService;

public class PrintServerReprintServiceImpl 
implements PrintServerReprintService {
	@Autowired
	private PrtReprintMDAO prtReprintMDAO;
	@Autowired
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
		prtReprintMDAO.save(entity);
	}
	public PrtReprintM findPrtReprintById(Integer id) {
		return prtReprintMDAO.findById(id);
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
