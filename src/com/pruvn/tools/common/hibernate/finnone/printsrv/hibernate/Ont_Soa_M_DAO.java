package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import com.pruvn.tools.common.hibernate.finnone.printsrv.ONT_SOA_M;
import com.pruvn.tools.printserver.GenericDAO;

public interface Ont_Soa_M_DAO extends GenericDAO<ONT_SOA_M, Integer>{
	ONT_SOA_M getOnTSOAMByAgreementNo(String agreementno);
}
