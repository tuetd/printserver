/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruvn.rms.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.rms.dao.ICMDAO;
import com.pruvn.rms.domain.Icm;
import com.pruvn.rms.dto.IBMCMDto;
import com.pruvn.rms.service.ICMService;

/**
 *
 * @author Owner
 */
public class ICMServiceImpl implements ICMService {
	@Autowired
	private ICMDAO icmDao;

	@Override
	public void save(List<Icm> lst) {
		icmDao.deleteData();
		for (Icm icm : lst) {
			icmDao.save(icm);
		}
	}

	public ICMDAO getIcmDao() {
		return icmDao;
	}

	public void setIcmDao(ICMDAO icmDao) {
		this.icmDao = icmDao;
	}

	@Override
	public List<IBMCMDto> zipAgreementNo() {
		// TODO Auto-generated method stub
		return icmDao.zipAgreementNo();
	}
	
	
}
