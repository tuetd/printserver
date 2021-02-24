package com.pruvn.tools.common.hibernate.finnone.printsrv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class AuditlogFcl
  implements Serializable
{
  private static final long serialVersionUID = 2595742366537099571L;
  private BigDecimal auditLogId;
  private BigDecimal appId;
  private String printedUser;
  private String department;
  private String userPlace;
  private String status;
  private Timestamp createdDate;
  
  private String reasonFcl;
  private String estClosedDate;
  //private String approach;
  
  
  public BigDecimal getAuditLogId()
  {
    return this.auditLogId;
  }
  
  public void setAuditLogId(BigDecimal auditLogId)
  {
    this.auditLogId = auditLogId;
  }
  
  public BigDecimal getAppId()
  {
    return this.appId;
  }
  
  public void setAppId(BigDecimal appId)
  {
    this.appId = appId;
  }
  
  public String getPrintedUser()
  {
    return this.printedUser;
  }
  
  public void setPrintedUser(String printedUser)
  {
    this.printedUser = printedUser;
  }
  
  public String getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(String department)
  {
    this.department = department;
  }
  
  public String getUserPlace()
  {
    return this.userPlace;
  }
  
  public void setUserPlace(String userPlace)
  {
    this.userPlace = userPlace;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Timestamp getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Timestamp createdDate)
  {
    this.createdDate = createdDate;
  }

public String getReasonFcl() {
	return reasonFcl;
}

public void setReasonFcl(String reasonFcl) {
	this.reasonFcl = reasonFcl;
}

public String getEstClosedDate() {
	return estClosedDate;
}

public void setEstClosedDate(String estClosedDate) {
	this.estClosedDate = estClosedDate;
}

/*public String getApproach() {
	return approach;
}

public void setApproach(String approach) {
	this.approach = approach;
}*/
  
  
  
}
