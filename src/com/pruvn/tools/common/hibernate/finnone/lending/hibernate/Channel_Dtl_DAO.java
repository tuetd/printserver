package com.pruvn.tools.common.hibernate.finnone.lending.hibernate;


import com.pruvn.tools.common.hibernate.finnone.lending.Channel;
import com.pruvn.tools.printserver.GenericDAO;

public interface Channel_Dtl_DAO extends GenericDAO<Channel,Integer>{
	public int check_app_id_by_channels(String channelid, String channelList);
	
	public String printAllow(String documentname, String applid);
}
