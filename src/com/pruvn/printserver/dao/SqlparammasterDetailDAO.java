package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.SqlparammasterDetail;
/**
 * <p>Generic DAO layer for Sqlparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface SqlparammasterDetailDAO extends GenericDAO<SqlparammasterDetail,Long> {

	List<SqlparammasterDetail> getFieldsWith_FCL_REQ_ESTIMATED(Long id,
			String sqlparammasterDetailReasonFcl);

}