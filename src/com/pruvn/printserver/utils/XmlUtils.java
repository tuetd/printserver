package com.pruvn.printserver.utils;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUtils {

public static String transferToXMLFileWithListParam(String refNo, String sourceChannel,String interfaceName,Map<String, String> lst) throws ParserConfigurationException, FileNotFoundException, TransformerException  {
		String root = "Message";
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
		Element rootElement = document.createElement(root);
        document.appendChild(rootElement);
        
        // Header
        Element header = document.createElement("Header");
        	//Refno
			Element ref_No = document.createElement("RefNo");
			ref_No.appendChild(document.createTextNode(refNo));
			header.appendChild(ref_No);
			//SourceChannel
			Element source_Channel = document.createElement("SourceChannel");
			source_Channel.appendChild(document.createTextNode(sourceChannel));
			header.appendChild(source_Channel);
			//TargetChannel
			Element target_Channel = document.createElement("TargetChannel");
			target_Channel.appendChild(document.createTextNode("PrintServerService"));
			header.appendChild(target_Channel);
			//Interface_Name
			Element interface_Name = document.createElement("InterfaceName");
			interface_Name.appendChild(document.createTextNode(interfaceName));
			header.appendChild(interface_Name);
			//Interface_Name
			Element req_TxnDt = document.createElement("ReqTxnDt");
			req_TxnDt.appendChild(document.createTextNode(DateUtils.getCurrentTimeSecond(new Date())));
			header.appendChild(req_TxnDt);
			//Interface_Name
			Element signature = document.createElement("Signature");
			signature.appendChild(document.createTextNode("PrintServer"));
			header.appendChild(signature);
			
		rootElement.appendChild(header)	;
			
			
		// Body
		Element body = document.createElement("Body");
		//Appid
		for (String param : lst.keySet()) {
			Element note = document.createElement(param);
			note.appendChild(document.createTextNode(lst.get(param)));
			body.appendChild(note);
		}
		
		rootElement.appendChild(body);
		
		return transformData(document);
	}

private static String transformData(Document document) throws FileNotFoundException, TransformerException {
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(document);
    StringWriter outputStream = new StringWriter();
    StreamResult result =  new StreamResult(outputStream);
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(source, result);
    return outputStream.toString();
}


// public static void main(String[] args) {
//	XmlUtils test= new XmlUtils();
//	try {
//		System.out.println(transferToXMLFileWithAppid("316298165", "ThachN", "WS_CLOSURE_LETTER", "105156"));
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (ParserConfigurationException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (TransformerException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
}
