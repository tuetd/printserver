/**
 * CommonGateway.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pru.common.gateway.ws;

public interface CommonGateway extends javax.xml.rpc.Service {
    public java.lang.String getCommonGatewayPortAddress();

    public com.pru.common.gateway.ws.CommonGatewayPortType getCommonGatewayPort() throws javax.xml.rpc.ServiceException;

    public com.pru.common.gateway.ws.CommonGatewayPortType getCommonGatewayPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
