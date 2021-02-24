package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.Icm;
import com.pruvn.rms.dto.IBMCMDto;
/**
 * <p>Generic DAO layer for GroupMs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface ICMDAO extends GenericDAO<Icm,String> {
	public boolean saveCall(Icm data);
	public boolean deleteData(); 
	public List<IBMCMDto> zipAgreementNo();
}