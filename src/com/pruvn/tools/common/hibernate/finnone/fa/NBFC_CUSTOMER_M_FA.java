package com.pruvn.tools.common.hibernate.finnone.fa;

public class NBFC_CUSTOMER_M_FA {
	private Integer cust_id_n;
	private String customername;
	private String pan_no;
	private LOS_APP_APPLICATIONS_FA los_app_applications;
	
	public LOS_APP_APPLICATIONS_FA getLos_app_applications() {
		return los_app_applications;
	}
	public void setLos_app_applications(LOS_APP_APPLICATIONS_FA los_app_applications) {
		this.los_app_applications = los_app_applications;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public Integer getCust_id_n() {
		return cust_id_n;
	}
	public void setCust_id_n(Integer cust_id_n) {
		this.cust_id_n = cust_id_n;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
}
