package com.pruvn.tools.common.hibernate.finnone.cas.hibernate;

import com.pruvn.tools.common.hibernate.finnone.cas.NBFC_PRODUCT_M;
import com.pruvn.tools.printserver.GenericDAO;

public interface Nbfc_Product_M_DAO extends GenericDAO<NBFC_PRODUCT_M,Integer>{
	NBFC_PRODUCT_M getProductByCode(String CODE);
}
