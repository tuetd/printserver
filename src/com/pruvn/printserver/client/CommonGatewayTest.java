

/**
 * CommonGatewayTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.5  Built on : May 28, 2011 (08:30:56 CEST)
 */
    package com.pruvn.printserver.client;



    /*
     *  CommonGatewayTest Junit test case
    */

    public class CommonGatewayTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testprocess() throws java.lang.Exception{

        com.pruvn.printserver.client.CommonGatewayStub stub =
                    new com.pruvn.printserver.client.CommonGatewayStub();//the default implementation should point to the right endpoint

           com.pruvn.printserver.client.CommonGatewayStub.ProcessE process4=
                                                        (com.pruvn.printserver.client.CommonGatewayStub.ProcessE)getTestObject(com.pruvn.printserver.client.CommonGatewayStub.ProcessE.class);
                    // TODO : Fill in the process4 here
           com.pruvn.printserver.client.CommonGatewayStub.Process process=(com.pruvn.printserver.client.CommonGatewayStub.Process) getTestObject(com.pruvn.printserver.client.CommonGatewayStub.Process.class);
//           String closure_letter="WS_CLOSURE_LETTER";
           process.setArg0("<Message><Header><RefNo>A0000000001</RefNo><SourceChannel>MSales</SourceChannel><TargetChannel>CMService</TargetChannel><InterfaceName>download_document</InterfaceName><Req_Interface_id>IBMCM_VIEW_DOC</Req_Interface_id><ReqTxnDt>20170719100117</ReqTxnDt><Signature>123</Signature></Header><Body><Doc_Ref_ID>129C2203BCF742299D08C49371169F1C</Doc_Ref_ID></Body></Message>");
           process4.setProcess(process);
           com.pruvn.printserver.client.CommonGatewayStub.ProcessResponseE  reponse=stub.process(process4);
           System.out.println(reponse.getProcessResponse().local_return);
//           System.out.println(reponse.getProcessResponse());
//                        assertNotNull(stub.process(
//                        process4));
                  
        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        public static void main(String[] args) throws Exception {
        	CommonGatewayTest ws = new CommonGatewayTest();
    		ws.testprocess();
		}
        

    }
    