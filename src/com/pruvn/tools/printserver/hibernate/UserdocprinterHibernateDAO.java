package com.pruvn.tools.printserver.hibernate;

import java.util.List;

import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.pojo.Userdocprinter;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Userdocprinters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserdocprinterHibernateDAO extends
		AbstractHibernateDAO<Userdocprinter, UserdocprinterPK> implements
		UserdocprinterDAO {

	/**
	 * Find Userdocprinter by docid
	 */
	public List<Userdocprinter> findByDocid(Integer docid) {
		return findByCriteria(Restrictions.eq("id.docid", docid));
	}
	/**
	 * Find Userdocprinter by printername
	 */
	public List<Userdocprinter> findByPrintername(String printername) {
		return findByCriteria(Restrictions.eq("printername", printername));
	}

	/**
	 * Find Userdocprinter by userid
	 */
	public List<Userdocprinter> findByUserid(Integer userid) {
		return findByCriteria(Restrictions.eq("id.userid", userid));
	}
	/**
	 * Find Userdocprinter by docmaster,usermaster
	 */
	public Userdocprinter getUserDocPrinterByDocAndUser(Docmaster docmaster,
			Usermaster usermaster) {
		Criteria criteria = getSession().createCriteria(Userdocprinter.class);
		return (Userdocprinter)criteria.add(Restrictions.eq("id.docid", docmaster.getId())).add(Restrictions.eq("id.userid", usermaster.getId())).uniqueResult();
	}

}
