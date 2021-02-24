package com.pruvn.tools.utils;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFMergerUtility;

public class PDFUtil {
	public static void mergePDF(List<String> fileList, String destination) throws COSVisitorException, IOException {		
		PDFMergerUtility mergerUtility = new PDFMergerUtility();
		for (int i = 0; i < fileList.size(); i++) {
			mergerUtility.addSource(new File(fileList.get(i)));
		}		
		mergerUtility.setDestinationFileName(destination);
		mergerUtility.mergeDocuments();
		
	}
	
	public static void printPDF(String filepath, String printername) throws IOException, PrinterException {
		PDDocument doc = PDDocument.load(filepath);
		PrintService ps = null;
        PrintService[] service = PrintServiceLookup.lookupPrintServices(null,null);        
		for (int j = 0; j < service.length; j++) {
			if (printername.equals(service[j].getName())) {
				ps = service[j];
				break;
			}
		}        
    	PrinterJob pj = PrinterJob.getPrinterJob();        	
    	pj.setPrintService(ps);        	
    	pj.setPageable(doc);       	
    	pj.print();
		doc.close();	
	}
}
