package com.pruvn.tools.common.hibernate.finnone.lending.hibernate;

import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.lending.LEA_RUU_UPLOADED_RECORD_DTL;
import com.pruvn.tools.printserver.GenericDAO;

public interface Lea_Ruu_Uploaded_Record_Dtl_DAO extends GenericDAO<LEA_RUU_UPLOADED_RECORD_DTL, Integer>{
	List<LEA_RUU_UPLOADED_RECORD_DTL> getUploadRecordListWithAgreementNoAndNotEQBankAccOrderUploadDateNormal(String agreementno, Integer bankaccno);
}
