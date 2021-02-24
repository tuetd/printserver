package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import com.pruvn.tools.common.hibernate.finnone.printsrv.AuditlogFcl;
import com.pruvn.tools.printserver.GenericDAO;
import java.sql.Timestamp;
import java.util.List;

public abstract interface AuditlogFclDAO
  extends GenericDAO<AuditlogFcl, Integer>
{
  public abstract List<AuditlogFcl> findByFromDate(Timestamp paramTimestamp1, Timestamp paramTimestamp2);
}
