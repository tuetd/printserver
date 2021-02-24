package com.pruvn.tools.common.hibernate.finnone.lending.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.lending.LEA_RUU_UPLOADED_RECORD_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Lea_Ruu_Uploaded_Record_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Lea_Ruu_Uploaded_Record_Dtl_DAO_Impl extends AbstractHibernateDAO<LEA_RUU_UPLOADED_RECORD_DTL, Integer> implements Lea_Ruu_Uploaded_Record_Dtl_DAO{

	@SuppressWarnings("unchecked")
	public List<LEA_RUU_UPLOADED_RECORD_DTL> getUploadRecordListWithAgreementNoAndNotEQBankAccOrderUploadDateNormal(
			String agreementno, Integer bankaccno) {
		Criteria criteria = getSession().createCriteria(LEA_RUU_UPLOADED_RECORD_DTL.class);
		return (List<LEA_RUU_UPLOADED_RECORD_DTL>)criteria.add(Restrictions.eq("agreementno", agreementno))
									   					  .add(Restrictions.ne("bankacnum", bankaccno))
									   					  .addOrder(Order.asc("upload_date")).list();
	}

}
