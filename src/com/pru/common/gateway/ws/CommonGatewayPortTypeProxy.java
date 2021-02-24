package com.pru.common.gateway.ws;

public class CommonGatewayPortTypeProxy implements com.pru.common.gateway.ws.CommonGatewayPortType {
  private String _endpoint = null;
  private com.pru.common.gateway.ws.CommonGatewayPortType commonGatewayPortType = null;
  
  public CommonGatewayPortTypeProxy() {
    _initCommonGatewayPortTypeProxy();
  }
  
  public CommonGatewayPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCommonGatewayPortTypeProxy();
  }
  
  private void _initCommonGatewayPortTypeProxy() {
    try {
      commonGatewayPortType = (new com.pru.common.gateway.ws.CommonGatewayLocator()).getCommonGatewayPort();
      if (commonGatewayPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)commonGatewayPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)commonGatewayPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (commonGatewayPortType != null)
      ((javax.xml.rpc.Stub)commonGatewayPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.pru.common.gateway.ws.CommonGatewayPortType getCommonGatewayPortType() {
    if (commonGatewayPortType == null)
      _initCommonGatewayPortTypeProxy();
    return commonGatewayPortType;
  }
  
  public java.lang.String process(java.lang.String arg0) throws java.rmi.RemoteException{
    if (commonGatewayPortType == null)
      _initCommonGatewayPortTypeProxy();
    return commonGatewayPortType.process(arg0);
  }
  
  
}