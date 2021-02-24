package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.printsrv.AuditlogFcl;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.AuditlogFclDAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class AuditlogFclHibernateDAO
  extends AbstractHibernateDAO<AuditlogFcl, Integer>
  implements AuditlogFclDAO
{
  public List<AuditlogFcl> findByFromDate(Timestamp fromDate, Timestamp toDate)
  {
    Criteria criteria = getSession().createCriteria(AuditlogFcl.class);
    
    List<AuditlogFcl> list = criteria.add(Restrictions.gt("createdDate", fromDate))
      .add(Restrictions.lt("createdDate", toDate)).list();
    return list;
  }
}
