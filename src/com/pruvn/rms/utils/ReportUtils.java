package com.pruvn.rms.utils;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportUtils {
	 private static Log logger=LogFactory.getLog(ReportUtils.class);
	 public static String nameFile;
	 public static String typeFile;
	 public static String rootpath;
	 public static String inputfile;
	 @SuppressWarnings("rawtypes")
	 public static  Map paramMap;
	 @SuppressWarnings("rawtypes")
	 public static Collection collection;
	 
	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static void xuatReport(InputStream iss,Map paramMaps,String outfiles,String loaiFiles,String namefiles) {
	        try {
	        	rootpath = outfiles;
				typeFile = loaiFiles;
				nameFile = namefiles;
				paramMap = paramMaps;
	        	JasperDesign jasperDesign = JRXmlLoader.load(iss);
			    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 
				JasperPrint jprint = JasperFillManager.fillReport(jasperReport,paramMap,new JREmptyDataSource());
				
	            if (typeFile.equalsIgnoreCase("pdf")) {
	            	exportReport(jprint,ExportFileType.PDF,rootpath,nameFile);
	            } else if (typeFile.equalsIgnoreCase("doc")) {
	            	exportReport(jprint,ExportFileType.DOC,rootpath,nameFile);
	            } else if (typeFile.equalsIgnoreCase("xls")) {
	            	exportReport(jprint,ExportFileType.EXCEL,rootpath,nameFile);
	            }
	            return;
	        } catch (Exception e) {
	            System.out.print("Loi " + e.getMessage());
	            return;
	        }
	    }
	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public  static void xuatReportWithCollectionDataSource(String inputfiles, Map paramMaps,String rootpaths,String loaiFiles,String namefiles,Collection collections) {
	        try {
	        	rootpath = rootpaths;
	        	logger.info("rootpath"+rootpath);
	        	inputfile=inputfiles;
				typeFile = loaiFiles;
				nameFile = namefiles;
				paramMap = paramMaps;
				collection = collections;
	        	JasperDesign jasperDesign = JRXmlLoader.load(rootpath+inputfile);
			    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 
				JasperPrint jprint = JasperFillManager.fillReport(jasperReport,paramMap, new JRBeanCollectionDataSource(collection));
				if (typeFile.equalsIgnoreCase("pdf")) {
	            	exportReport(jprint,ExportFileType.PDF,rootpath,nameFile);
	            } else if (typeFile.equalsIgnoreCase("doc")) {
	            	exportReport(jprint,ExportFileType.DOC,rootpath,nameFile);
	            } else if (typeFile.equalsIgnoreCase("xls")) {
	            	exportReport(jprint,ExportFileType.EXCEL,rootpath,nameFile);
	            }
	            return;

	        } catch (Exception e) {
	            System.out.print("Loi " + e.getMessage());
	            return;
	        }
	    }
	 
		public static void downloadReportWithCollectionDataSource(String loaiFiles) {
			typeFile = loaiFiles;
			xuatReportWithCollectionDataSource(inputfile, paramMap, rootpath, typeFile, nameFile, collection);
		    }
	 
	 
	 public static String exportReport(JasperPrint jprint,ExportFileType expType, String outPath,String namefile) throws JRException {
	        JRExporter jrexporter = null;
	        String outFile = "";
	        switch (expType) {
	            case DOC:
	                outFile = outPath+"reports/word/"+namefile+ ".doc";
	                jrexporter = new JRRtfExporter();
	                break;
	            case EXCEL:
	                outFile = outPath+"reports/excel/"+namefile+ ".xls";
	                jrexporter = new JRXlsExporter();
	                break;
	            case PDF:
	                outFile = outPath +"reports/pdf/"+namefile+ ".pdf";
	                jrexporter = new JRPdfExporter();
	                break;
	        }
	        if (jrexporter != null) {
	            jrexporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
	            jrexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
	            jrexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFile);
	            jrexporter.exportReport();
	        }
	        return outPath;
	    }  
	public String getNameFile() {
		return nameFile;
	}
	public String getTypeFile() {
		return typeFile;
	}
	  public static String getRootpath() {
		return rootpath;
	}

	@SuppressWarnings("rawtypes")
	public Collection getCollection() {
		return collection;
	}


	@SuppressWarnings("rawtypes")
	public Map getParamMap() {
		return paramMap;
	}


	public static enum ExportFileType {
	        DOC, DOCX,EXCEL, CSV, PDF
	    }
}
