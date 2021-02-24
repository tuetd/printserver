package com.pruvn.tools.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.pruvn.tools.common.util.GlobalConstant;



public class FileUtil {
	public static void copy(String fromFileName, String toFileName) throws IOException {
	  File fromFile = new File(fromFileName);
	  File toFile = new File(toFileName);

	  if (!fromFile.exists())
	    throw new IOException("FileCopy: " + "no such source file: "
	        + fromFileName);
	  if (!fromFile.isFile())
	    throw new IOException("FileCopy: " + "can't copy directory: "
	        + fromFileName);
	  if (!fromFile.canRead())
	    throw new IOException("FileCopy: " + "source file is unreadable: "
	        + fromFileName);
	
	  if (toFile.isDirectory())
	    toFile = new File(toFile, fromFile.getName());
	
	  if (toFile.exists()) {
//	    if (!toFile.canWrite())
//	      throw new IOException("FileCopy: "
//	          + "destination file is unwriteable: " + toFileName);
//	    System.out.print("Overwrite existing file " + toFile.getName()
//	        + "? (Y/N): ");
//	    System.out.flush();
//	    BufferedReader in = new BufferedReader(new InputStreamReader(
//	        System.in));
//	    String response = in.readLine();
//	    if (!response.equals("Y") && !response.equals("y")) {
//	    	return;
//	    }	      
	  } else {
	    String parent = toFile.getParent();
	    if (parent == null)
	      parent = System.getProperty("user.dir");
	    File dir = new File(parent);
	    if (!dir.exists())
	      throw new IOException("FileCopy: "
	          + "destination directory doesn't exist: " + parent);
	    if (dir.isFile())
	      throw new IOException("FileCopy: "
	          + "destination is not a directory: " + parent);
	    if (!dir.canWrite())
	      throw new IOException("FileCopy: "
	          + "destination directory is unwriteable: " + parent);
	  }
	
	  FileInputStream from = null;
	  FileOutputStream to = null;
	  try {
	    from = new FileInputStream(fromFile);
	    to = new FileOutputStream(toFile);
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	
	    while ((bytesRead = from.read(buffer)) != -1)
	      to.write(buffer, 0, bytesRead); // write
	  } finally {
	    if (from != null)
	      try {
	        from.close();
	      } catch (IOException e) {
	        ;
	      }
	    if (to != null)
	      try {
	        to.close();
	      } catch (IOException e) {
	        ;
	      }
	  }
	}
	
	public static void copy(File fromFileName, String toFileName) throws IOException {
		  File fromFile = fromFileName;
		  File toFile = new File(toFileName);

		  if (!fromFile.exists())
		    throw new IOException("FileCopy: " + "no such source file: "
		        + fromFileName);
		  if (!fromFile.isFile())
		    throw new IOException("FileCopy: " + "can't copy directory: "
		        + fromFileName);
		  if (!fromFile.canRead())
		    throw new IOException("FileCopy: " + "source file is unreadable: "
		        + fromFileName);
		
		  if (toFile.isDirectory())
		    toFile = new File(toFile, fromFile.getName());
		
		  if (toFile.exists()) {
		  } else {
		    String parent = toFile.getParent();
		    if (parent == null)
		      parent = System.getProperty("user.dir");
		    File dir = new File(parent);
		    if (!dir.exists())
		      throw new IOException("FileCopy: "
		          + "destination directory doesn't exist: " + parent);
		    if (dir.isFile())
		      throw new IOException("FileCopy: "
		          + "destination is not a directory: " + parent);
		    if (!dir.canWrite())
		      throw new IOException("FileCopy: "
		          + "destination directory is unwriteable: " + parent);
		  }
		
		  FileInputStream from = null;
		  FileOutputStream to = null;
		  try {
		    from = new FileInputStream(fromFile);
		    to = new FileOutputStream(toFile);
		    byte[] buffer = new byte[4096];
		    int bytesRead;
		
		    while ((bytesRead = from.read(buffer)) != -1)
		      to.write(buffer, 0, bytesRead); // write
		  } finally {
		    if (from != null)
		      try {
		        from.close();
		      } catch (IOException e) {
		        ;
		      }
		    if (to != null)
		      try {
		        to.close();
		      } catch (IOException e) {
		        ;
		      }
		  }
		}
	
	public static String readProperties(String fileName, String propName) {
		try {
			String result = null;		
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(fileName); 
			properties.load(fis);
			result = properties.getProperty(propName);
			fis.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	    	   
	}
	
	public static String readProperties(InputStream is, String propName) throws IOException {
		if (null == is) {
			return null;
		}
		Properties properties = new Properties();
		properties.load(is);
	    return properties.getProperty(propName); 	   
	}
	
	public static String[] readArrayProperties(InputStream is, String[] propNames) throws IOException {
		if (null == is) {
			return null;
		}
		String[] result = new String[propNames.length];		
		Properties properties = new Properties();
		properties.load(is);
		for (int i = 0; i < propNames.length; i++) {
			result[i] = properties.getProperty(propNames[i]);
		}
	    return result; 	   
	}

	public static String[][] readExcelTabDelimiterFile(String filePath) throws FileNotFoundException, IOException {		 
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		
		BufferedReader bufRdr  = new BufferedReader(fr);
		String line = null;	
		int arraySize = -1;
		
		List<String[]> result = new ArrayList<String[]>(); 
		//read each line of text file
		while((line = bufRdr.readLine()) != null) {			
			List<String> rowList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(line, String.valueOf(GlobalConstant.TAB));
			while (st.hasMoreTokens()) {				
				//get next token and store it in the array
				rowList.add(st.nextToken());				
			}
			if (arraySize < 0) {
				arraySize = rowList.size();
			}
			String[] row = new String[arraySize];
			for (int i = 0; i < rowList.size(); i++) {
				row[i] = rowList.get(i).replaceAll(String.valueOf(GlobalConstant.DOUBLEQUOTE), "");				
			}			
			result.add(row);
			
		}
		String[][] data = new String[result.size()][];
		for (int  j = 0; j < data.length; j++) {
			data[j] = result.get(j);
		}
		
		fr.close();
		bufRdr.close();
		
		return data;
	}
	
	public static String[][] readExcelTabDelimiterFile(File file) throws FileNotFoundException, IOException {		
		FileReader fr = new FileReader(file);
		
		BufferedReader bufRdr  = new BufferedReader(fr);
		String line = null;	
		int arraySize = -1;
		
		List<String[]> result = new ArrayList<String[]>(); 
		//read each line of text file
		while((line = bufRdr.readLine()) != null) {			
			List<String> rowList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(line, String.valueOf(GlobalConstant.TAB));
			while (st.hasMoreTokens()) {				
				//get next token and store it in the array
				rowList.add(st.nextToken().trim());				
			}
			if (arraySize < 0) {
				arraySize = rowList.size();
			}
			String[] row = new String[arraySize];
			for (int i = 0; i < rowList.size(); i++) {
				row[i] = rowList.get(i).replaceAll(String.valueOf(GlobalConstant.DOUBLEQUOTE), "").trim();				
			}			
			result.add(row);
			
		}
		String[][] data = new String[result.size()][];
		for (int  j = 0; j < data.length; j++) {			
			data[j] = result.get(j);			
		}
		
		fr.close();
		bufRdr.close();
		
		return data;
	}
	
	public static String[][] readExcelTabDelimiterFile(File file, String delimiter) throws FileNotFoundException, IOException {		
		FileReader fr = new FileReader(file);
		
		BufferedReader bufRdr  = new BufferedReader(fr);
		String line = null;	
		int arraySize = -1;
		
		List<String[]> result = new ArrayList<String[]>(); 
		//read each line of text file
		while((line = bufRdr.readLine()) != null) {			
			List<String> rowList = new ArrayList<String>();
//			StringTokenizer st = new StringTokenizer(line, String.valueOf(delimiter));
//			while (st.hasMoreTokens()) {				
//				//get next token and store it in the array
//				rowList.add(st.nextToken().trim());				
//			}
			String[] arr =  line.split(delimiter);
			rowList = Arrays.asList(arr);
//			if (arraySize < 0) {
				arraySize = rowList.size();
//			}
			String[] row = new String[arraySize];
			for (int i = 0; i < rowList.size(); i++) {
				row[i] = rowList.get(i).replaceAll(String.valueOf(GlobalConstant.DOUBLEQUOTE), "").trim();				
			}			
			result.add(row);
			
		}
		String[][] data = new String[result.size()][];
		for (int  j = 0; j < data.length; j++) {			
			data[j] = result.get(j);			
		}
		
		fr.close();
		bufRdr.close();
		
		return data;
	}
	
	public static String[][] readExcelTabDelimiterFileUTF8(String filePath) throws FileNotFoundException, IOException {
		BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
		String line = null;	
		int arraySize = -1;
		
		List<String[]> result = new ArrayList<String[]>(); 
		//read each line of text file
		while((line = bufRdr.readLine()) != null) {			
			List<String> rowList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(line, String.valueOf(GlobalConstant.TAB));
			while (st.hasMoreTokens()) {				
				//get next token and store it in the array
				rowList.add(st.nextToken());				
			}
			if (arraySize < 0) {
				arraySize = rowList.size();
			}
			String[] row = new String[arraySize];
			for (int i = 0; i < rowList.size(); i++) {
				row[i] = rowList.get(i).replaceAll(String.valueOf(GlobalConstant.DOUBLEQUOTE), "");				
			}			
			result.add(row);
			
		}
		String[][] data = new String[result.size()][];
		for (int  j = 0; j < data.length; j++) {
			data[j] = result.get(j);
		}
		
		return data;
	}
	
	public static String[][] readExcelTabDelimiterFileUTF8(File file) throws FileNotFoundException, IOException {
		BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String line = null;	
		int arraySize = -1;
		
		List<String[]> result = new ArrayList<String[]>(); 
		//read each line of text file
		while((line = bufRdr.readLine()) != null) {			
			List<String> rowList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(line, String.valueOf(GlobalConstant.TAB));
			while (st.hasMoreTokens()) {				
				//get next token and store it in the array
				rowList.add(st.nextToken());				
			}
			if (arraySize < 0) {
				arraySize = rowList.size();
			}
			String[] row = new String[arraySize];
			for (int i = 0; i < rowList.size(); i++) {
				row[i] = rowList.get(i).replaceAll(String.valueOf(GlobalConstant.DOUBLEQUOTE), "");				
			}			
			result.add(row);
			
		}
		String[][] data = new String[result.size()][];
		for (int  j = 0; j < data.length; j++) {
			data[j] = result.get(j);
		}
		
		return data;
	}
	
	public static void writeLog(Exception e, String filePath) {				
		try {
			FileOutputStream fos = new FileOutputStream(filePath, true);
			PrintStream ps = new PrintStream(fos);
			ps.append(new Date().toString());
			ps.append(GlobalConstant.NEWLINE);
			e.printStackTrace(ps);
			ps.close();
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	public static void writeLog(String log, String filePath) {				
		try {
			FileOutputStream fos = new FileOutputStream(filePath, true);
			PrintStream ps = new PrintStream(fos);
			ps.append(new Date().toString() + ": " + log);
			ps.close();
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	public static String[] readTextFile(String filepath) {
		try {
			FileInputStream fstream = new FileInputStream(filepath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			List<String> list = new ArrayList<String>();
			while ((strLine = br.readLine()) != null)   { 
			  list.add(strLine);
			}	    
			//Close the input stream
			br.close();
			in.close();
			fstream.close();
			
			String[] result = new String[list.size()];
			list.toArray(result);
			return result;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		} 
	    
	}
	


}
