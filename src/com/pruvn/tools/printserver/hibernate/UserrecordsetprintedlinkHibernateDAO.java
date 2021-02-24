package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Userrecordsetprintedlink;
import com.pruvn.tools.printserver.UserrecordsetprintedlinkDAO;
import com.pruvn.tools.printserver.pojo.Userrecordsetprintedlink.UserrecordsetprintedlinkPK;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Userrecordsetprintedlinks</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserrecordsetprintedlinkHibernateDAO extends
		AbstractHibernateDAO<Userrecordsetprintedlink, UserrecordsetprintedlinkPK> implements
		UserrecordsetprintedlinkDAO {

	/**
	 * Find Userrecordsetprintedlink by userid
	 */
	public List<Userrecordsetprintedlink> findByUserid(Integer userid) {
		return findByCriteria(Restrictions.eq("id.userid", userid));
	}
	/**
	 * Find Userrecordsetprintedlink by recordsetid
	 */
	public List<Userrecordsetprintedlink> findByRecordsetid(Integer recordsetid) {
		return findByCriteria(Restrictions.eq("id.recordsetid", recordsetid));
	}
	/**
	 * Find Userrecordsetprintedlink by datetimeprinted
	 */
	public List<Userrecordsetprintedlink> findByDatetimeprinted(Timestamp datetimeprinted) {
		return findByCriteria(Restrictions.eq("datetimeprinted", datetimeprinted));
	}


}
