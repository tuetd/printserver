package com.pruvn.rms.dao;

import com.pruvn.rms.domain.PasswordHistory;
/**
 * <p>Generic DAO layer for PasswordHistory</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface PasswordHistoryDAO extends GenericDAO<PasswordHistory,Integer> {

	boolean isExitInHistory(String userName, String newPwd);

	 void storeHistory(PasswordHistory passwordHistory);

}