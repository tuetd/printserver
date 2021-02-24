package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.pojo.Userdocprinter;
import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import com.pruvn.tools.printserver.pojo.Usermaster;
/**
 * <p>Generic DAO layer for Userdocprinters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UserdocprinterDAO extends GenericDAO<Userdocprinter,UserdocprinterPK> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUserdocprinterDAO()
	 */
	  	 
	/**
	 * Find Userdocprinter by docid
	 */
	public List<Userdocprinter> findByDocid(Integer docid);

	/**
	 * Find Userdocprinter by printername
	 */
	public List<Userdocprinter> findByPrintername(String printername);

	/**
	 * Find Userdocprinter by userid
	 */
	public List<Userdocprinter> findByUserid(Integer userid);
	/**
	 * Find Userdocprinter by DOCMASTER and USERMASTER 
	 */
	 public Userdocprinter getUserDocPrinterByDocAndUser(Docmaster docmaster,Usermaster usermaster);


}