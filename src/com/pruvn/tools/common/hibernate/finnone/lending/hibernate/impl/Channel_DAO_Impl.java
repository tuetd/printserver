package com.pruvn.tools.common.hibernate.finnone.lending.hibernate.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import com.pruvn.tools.common.hibernate.finnone.lending.Channel;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Channel_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Channel_DAO_Impl extends AbstractHibernateDAO<Channel, Integer> implements Channel_Dtl_DAO{
	public int check_app_id_by_channels(String appid, String channelList){
		int checkExistChannel = 0;
		try{
			Connection conn = this.getSession().connection();
			CallableStatement cstmt = conn.prepareCall("{? = call sqa.check_app_id_by_channels(?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, appid);
			cstmt.setString(3, channelList);
			cstmt.execute();
			checkExistChannel = cstmt.getInt(1);
		}catch(Exception e){			
			checkExistChannel = 0;
		}
		
		return checkExistChannel;
	}
	
	public String printAllow(String documentname, String applid){
		String err_Msg = "0";
		try{
			Connection conn = this.getSession().connection();
			CallableStatement cstmt = conn.prepareCall("{? = call F_PRINTSERVER_ALLOW(?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setObject(2, new String(documentname));
			cstmt.setObject(3, new Integer(applid));	   
			cstmt.execute();
			err_Msg = cstmt.getString(1);
		}catch(Exception e){			
			err_Msg = "0";
		}
		
		return err_Msg;
	}
}
