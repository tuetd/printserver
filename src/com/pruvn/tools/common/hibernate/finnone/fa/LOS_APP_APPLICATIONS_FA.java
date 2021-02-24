package com.pruvn.tools.common.hibernate.finnone.fa;

public class LOS_APP_APPLICATIONS_FA {
	private String app_id_c;
	private String laa_app_formno;
	private Integer laa_app_loandtl_term_n;
	private NBFC_PRODUCT_M_FA nbfc_product_m;
	private NBFC_CUSTOMER_M_FA nbfc_customer_m;	
	
	public Integer getLaa_app_loandtl_term_n() {
		return laa_app_loandtl_term_n;
	}
	public void setLaa_app_loandtl_term_n(Integer laa_app_loandtl_term_n) {
		this.laa_app_loandtl_term_n = laa_app_loandtl_term_n;
	}
	public NBFC_CUSTOMER_M_FA getNbfc_customer_m() {
		return nbfc_customer_m;
	}
	public void setNbfc_customer_m(NBFC_CUSTOMER_M_FA nbfc_customer_m) {
		this.nbfc_customer_m = nbfc_customer_m;
	}
	public NBFC_PRODUCT_M_FA getNbfc_product_m() {
		return nbfc_product_m;
	}
	public void setNbfc_product_m(NBFC_PRODUCT_M_FA nbfc_product_m) {
		this.nbfc_product_m = nbfc_product_m;
	}
	public String getApp_id_c() {
		return app_id_c;
	}
	public void setApp_id_c(String app_id_c) {
		this.app_id_c = app_id_c;
	}
	public String getLaa_app_formno() {
		return laa_app_formno;
	}
	public void setLaa_app_formno(String laa_app_formno) {
		this.laa_app_formno = laa_app_formno;
	}
}
