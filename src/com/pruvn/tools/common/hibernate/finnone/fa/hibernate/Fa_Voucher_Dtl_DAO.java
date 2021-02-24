package com.pruvn.tools.common.hibernate.finnone.fa.hibernate;

import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.fa.FA_VOUCHER_DTL;
import com.pruvn.tools.printserver.GenericDAO;

public interface Fa_Voucher_Dtl_DAO extends GenericDAO<FA_VOUCHER_DTL, Integer>{
	List<FA_VOUCHER_DTL> getVoucherDtlList(Integer chequeid);
}
