/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruvn.printserver.services.impl;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pruvn.printserver.client.CommonGatewayStub;
import com.pruvn.printserver.client.CommonGatewayStub.Process;
import com.pruvn.printserver.client.CommonGatewayStub.ProcessE;
import com.pruvn.printserver.client.CommonGatewayStub.ProcessResponseE;
import com.pruvn.printserver.services.CFCWebservicesService;
import com.pruvn.printserver.utils.DateUtils;
import com.pruvn.printserver.utils.XmlUtils;


/**
 *
 * @author Owner
 */
public class CFCWebservicesServiceImpl implements CFCWebservicesService {
	private static Log logger = LogFactory.getLog(CFCWebservicesServiceImpl.class);
    private String url;
    private CommonGatewayStub wsEngineStub;

    public CFCWebservicesServiceImpl() {
        if (url == null) {
		    url = "http://10.148.50.5:8093/api";
		}
    }
    

	/**
	 * @return the wsEngineStub
     * @throws AxisFault 
	 */
	public CommonGatewayStub getCommonGatewayStub() throws AxisFault {
		wsEngineStub = new CommonGatewayStub(getUrl());
		return wsEngineStub;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
    @Override
	public String call_webserivce_with_lstParam(String webservice, Map<String, String> lst) {
    	String result="";
		try {
			ProcessE pro_request=(ProcessE) getADBObject(ProcessE.class);
			Process clo_request=(Process) getADBObject(Process.class);
			//get template of XLM send to Webservice
			String  request=XmlUtils.transferToXMLFileWithListParam(DateUtils.getCurrentTimeMillis(), "PrintServer", webservice,lst);
			System.out.println("Request=======>>>>>>>>"+request);
			clo_request.setArg0(request);
			pro_request.setProcess(clo_request);
			ProcessResponseE pro_reponser=getCommonGatewayStub().process(pro_request);
			result=pro_reponser.getProcessResponse().get_return();
			System.out.println("Response=======>>>>>>>>"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
    
    
 
	
	
	
	//Create an ADBBean and provide it as the test object
 	 public org.apache.axis2.databinding.ADBBean getADBObject(java.lang.Class type) throws java.lang.Exception{
 		 return (org.apache.axis2.databinding.ADBBean) type.newInstance();
 	 }


	@Override
	public int checkConnectWebserive()  {
		  try {
		 		URL u = new URL(url+"?wsdl"); 
//			    URLConnection urlConnection = u.openConnection();
			    HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
			    return  con.getResponseCode();
		} catch (MalformedURLException ex) {
			       return  0;
		} catch (IOException ex) {
			       System.out.println("Failed opening connection.");
			       return  0;
		} 
		 	
		 	
	}

//public static void main(String[] args) {
//	CFCWebservicesServiceImpl ss=new CFCWebservicesServiceImpl();
//	System.out.println(ss.checkConnectWebserive());
//}
}
