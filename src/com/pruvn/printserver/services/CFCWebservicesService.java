package com.pruvn.printserver.services;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.pruvn.printserver.client.CommonGatewayStub;


public interface CFCWebservicesService {
	CommonGatewayStub getCommonGatewayStub() throws AxisFault;
	int checkConnectWebserive();
	
	//call webservice with param APPLID
	String call_webserivce_with_lstParam(String webservice, Map<String, String> lst);
	
	
}

