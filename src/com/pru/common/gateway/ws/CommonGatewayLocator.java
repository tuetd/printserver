/**
 * CommonGatewayLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pru.common.gateway.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonGatewayLocator extends org.apache.axis.client.Service implements com.pru.common.gateway.ws.CommonGateway {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonGatewayLocator() {
    }


    public CommonGatewayLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CommonGatewayLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CommonGatewayPort
    private java.lang.String CommonGatewayPort_address = "http://10.148.84.66:443/api";

    public java.lang.String getCommonGatewayPortAddress() {
    	InputStream stream = null;
    	try {
    		stream = getClass().getClassLoader().getResourceAsStream(
					"config.properties");
        	Properties prop = new Properties();
			prop.load(stream);
			CommonGatewayPort_address = prop.getProperty("CommonGatewayPort_address");
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            if(stream!=null) {
            	try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return CommonGatewayPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CommonGatewayPortWSDDServiceName = "CommonGatewayPort";

    public java.lang.String getCommonGatewayPortWSDDServiceName() {
        return CommonGatewayPortWSDDServiceName;
    }

    public void setCommonGatewayPortWSDDServiceName(java.lang.String name) {
        CommonGatewayPortWSDDServiceName = name;
    }

    public com.pru.common.gateway.ws.CommonGatewayPortType getCommonGatewayPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
        	InputStream stream = null;
        	try {
        		stream = getClass().getClassLoader().getResourceAsStream(
						"config.properties");
	        	Properties prop = new Properties();
				prop.load(stream);
				CommonGatewayPort_address = prop.getProperty("CommonGatewayPort_address");
	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
	            if(stream!=null) {
	            	try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
            endpoint = new java.net.URL(CommonGatewayPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCommonGatewayPort(endpoint);
    }

    public com.pru.common.gateway.ws.CommonGatewayPortType getCommonGatewayPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.pru.common.gateway.ws.CommonGatewaySoapBindingStub _stub = new com.pru.common.gateway.ws.CommonGatewaySoapBindingStub(portAddress, this);
            _stub.setPortName(getCommonGatewayPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCommonGatewayPortEndpointAddress(java.lang.String address) {
        CommonGatewayPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.pru.common.gateway.ws.CommonGatewayPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.pru.common.gateway.ws.CommonGatewaySoapBindingStub _stub = new com.pru.common.gateway.ws.CommonGatewaySoapBindingStub(new java.net.URL(CommonGatewayPort_address), this);
                _stub.setPortName(getCommonGatewayPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CommonGatewayPort".equals(inputPortName)) {
            return getCommonGatewayPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.gateway.common.pru.com/", "CommonGateway");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.gateway.common.pru.com/", "CommonGatewayPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CommonGatewayPort".equals(portName)) {
            setCommonGatewayPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
