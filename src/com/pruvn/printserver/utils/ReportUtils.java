package com.pruvn.printserver.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 public static void xuatReport(String inputfiles,Map paramMaps,String outfiles,String loaiFiles,String namefiles) {
	        try {
	        	rootpath = outfiles;
	        	inputfile=inputfiles;
				typeFile = loaiFiles;
				nameFile = namefiles;
				paramMap = paramMaps;
	        	JasperDesign jasperDesign = JRXmlLoader.load(rootpath+inputfile);
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
	            System.err.print("Loi " + e.getMessage());
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
	                outFile = outPath+"templates"+File.separator+"word"+File.separator+namefile+ ".doc";
	                logger.info("outFile"+outFile);
	                jrexporter = new JRRtfExporter();
	                break;
	            case EXCEL:
	                outFile = outPath+File.separator+"excel"+File.separator+namefile+ ".xls";
	                logger.info("outFile"+outFile);
	                jrexporter = new JRXlsExporter();
	                break;
	            case PDF:
	                outFile = outPath+"templates"+File.separator+"pdf"+File.separator+namefile+ ".pdf";
	                logger.info("outFile"+outFile);
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

	  public static void checkExist(String folder) throws IOException {
			File file = new File(folder);
			if(!file.exists()){
				file.mkdirs();
			}
		}
	  public static void copyFileBackup(String pathfile, String folderBackup2,
				String namefile) {
			InputStream inStream = null;
			OutputStream outStream = null;
			try {
				File afile = new File(pathfile);
				File bfile = new File(folderBackup2 + File.separator + namefile);
				inStream = new FileInputStream(afile);
				outStream = new FileOutputStream(bfile);
				byte[] buffer = new byte[1024];
				int length;
				// copy the file content in bytes
				while ((length = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
				inStream.close();
				outStream.close();
				logger.info("File is copied successful!");
			} catch (IOException e) {
				logger.error(e);
			}
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
