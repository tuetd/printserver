package com.pruvn.rms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CommonUtils {
	public static String getOnlyNumerics(String str, String replacement) {
	    if (str == null) {
	        return null;
	    }
	
	    StringBuffer strBuff = new StringBuffer();
	    char c;
	    
	    for (int i = 0; i < str.length() ; i++) {
	        c = str.charAt(i);
	        
	        if (Character.isDigit(c)) {
	            strBuff.append(c);
	        }else {
	        	strBuff.append(replacement);
	        }
	    }
	    return strBuff.toString();
	}
	
	public static Boolean isNumeric(String str){
		char c;
		for (int i = 0; i < str.length() ; i++) {
	        c = str.charAt(i);
	        
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }
		
		return true;
	}
	
	private static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String MD5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        byte[] md5hash = md.digest();
        return byteToHex(md5hash);
    }
    public static Document loadXMLFromString(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setFeature(
				"http://xml.org/sax/features/external-general-entities", false);
		factory.setFeature(
				"http://xml.org/sax/features/external-parameter-entities",
				false);
		factory.setFeature(
				"http://apache.org/xml/features/disallow-doctype-decl", true);

		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
    public static String getTextContent(Element node, String tagName) {
		if (node.getElementsByTagName(tagName).getLength() == 0)
			return null;
		return node.getElementsByTagName(tagName).item(0).getTextContent();
	}

    public static String UnicodeToCompoundAndTrim(String src) {
		src = UnicodeToCompound(src);
		src = src.replaceAll("\\s+", " ");
		
		return src;
	}
    
    public static Properties getStreamFromFileConfig(String fileName) {
		Properties prop = new Properties();
		InputStream stream = null;
    	try {
    		stream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName);
			prop.load(stream);
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
    	return prop;
	}
	
	/**
	 * convert chuoi co dau thanh khong dau. VD: NGUYá»„N Ä?á»¨C LONG -> NGUYEN DUC LONG
	 * @param src
	 * @return
	 */	
	/*public static String UnicodeToCompound(String src) {		
		try {
			src = src.replaceAll("Ã ", "a");
			src = src.replaceAll("Ã¡", "a");
			src = src.replaceAll("áº£", "a");
			src = src.replaceAll("Ã£", "a");
			src = src.replaceAll("áº¡", "a");
			
			src = src.replaceAll("Ã?", "A");
			src = src.replaceAll("Ã€", "A");
			src = src.replaceAll("áº¢", "A");
			src = src.replaceAll("Ãƒ", "A");
			src = src.replaceAll("áº ", "A");
			
			src = src.replaceAll("Äƒ", "a");		
			src = src.replaceAll("áº¯", "a");		
			src = src.replaceAll("áº±", "a");		
			src = src.replaceAll("áº·", "a");		
			src = src.replaceAll("áº³", "a");		
			src = src.replaceAll("áºµ", "a");
			
			src = src.replaceAll("Ä‚", "A");
			src = src.replaceAll("áº®", "A");
			src = src.replaceAll("áº´", "A");
			src = src.replaceAll("áº°", "A");
			src = src.replaceAll("áº¶", "A");
			src = src.replaceAll("áº²", "A");
			
			src = src.replaceAll("Ã¢", "a");		
			src = src.replaceAll("áº¥", "a");		
			src = src.replaceAll("áº§", "a");		
			src = src.replaceAll("áº©", "a");		
			src = src.replaceAll("áº«", "a");		
			src = src.replaceAll("áº­", "a");
			
			src = src.replaceAll("áº¬", "A");
			src = src.replaceAll("Ã‚", "A");
			src = src.replaceAll("áº¤", "A");
			src = src.replaceAll("áº¦", "A");
			src = src.replaceAll("áº¨", "A");
			src = src.replaceAll("áºª", "A");
			
			src = src.replaceAll("Ã©", "e");
			src = src.replaceAll("Ã¨", "e");
			src = src.replaceAll("áº»", "e");
			src = src.replaceAll("áº½", "e");
			src = src.replaceAll("áº¹", "e");
			
			src = src.replaceAll("Ã‰", "E");
			src = src.replaceAll("Ãˆ", "E");
			src = src.replaceAll("áºº", "E");
			src = src.replaceAll("áº¼", "E");
			src = src.replaceAll("áº¸", "E");
			
			src = src.replaceAll("Ãª", "e");		
			src = src.replaceAll("áº¿", "e");		
			src = src.replaceAll("á»?", "e");		
			src = src.replaceAll("á»ƒ", "e");		
			src = src.replaceAll("á»…", "e");		
			src = src.replaceAll("á»‡", "e");
			
			src = src.replaceAll("á»†", "E");
			src = src.replaceAll("ÃŠ", "E");
			src = src.replaceAll("áº¾", "E");
			src = src.replaceAll("á»€", "E");
			src = src.replaceAll("á»‚", "E");
			src = src.replaceAll("á»„", "E");
			
			src = src.replaceAll("Ã­", "i");
			src = src.replaceAll("Ã¬", "i");
			src = src.replaceAll("á»‰", "i");
			src = src.replaceAll("Ä©", "i");
			src = src.replaceAll("á»‹", "i");
			
			src = src.replaceAll("Ã?", "I");
			src = src.replaceAll("ÃŒ", "I");
			src = src.replaceAll("á»ˆ", "I");
			src = src.replaceAll("Ä¨", "I");
			src = src.replaceAll("á»Š", "I");
			
			src = src.replaceAll("Ã³", "o");
			src = src.replaceAll("Ã²", "o");
			src = src.replaceAll("á»?", "o");
			src = src.replaceAll("Ãµ", "o");
			src = src.replaceAll("á»?", "o");
			
			src = src.replaceAll("Ã“", "O");
			src = src.replaceAll("Ã’", "O");
			src = src.replaceAll("á»Ž", "O");
			src = src.replaceAll("Ã•", "O");
			src = src.replaceAll("á»Œ", "O");
			
			src = src.replaceAll("Ã´", "o");
			src = src.replaceAll("á»‘", "o");
			src = src.replaceAll("á»“", "o");
			src = src.replaceAll("á»•", "o");
			src = src.replaceAll("á»—", "o");
			src = src.replaceAll("á»™", "o");
			
			src = src.replaceAll("Ã”", "O");
			src = src.replaceAll("á»?", "O");
			src = src.replaceAll("á»’", "O");
			src = src.replaceAll("á»”", "O");
			src = src.replaceAll("á»–", "O");
			src = src.replaceAll("á»˜", "O");
			
			src = src.replaceAll("Æ¡", "o");
			src = src.replaceAll("á»›", "o");
			src = src.replaceAll("á»?", "o");
			src = src.replaceAll("á»Ÿ", "o");
			src = src.replaceAll("á»¡", "o");
			src = src.replaceAll("á»£", "o");
			
			src = src.replaceAll("Æ ", "O");
			src = src.replaceAll("á»š", "O");
			src = src.replaceAll("á»œ", "O");
			src = src.replaceAll("á»ž", "O");
			src = src.replaceAll("á» ", "O");
			src = src.replaceAll("á»¢", "O");
			
			src = src.replaceAll("Ãº", "u");
			src = src.replaceAll("Ã¹", "u");
			src = src.replaceAll("á»§", "u");
			src = src.replaceAll("Å©", "u");
			src = src.replaceAll("á»¥", "u");
			
			src = src.replaceAll("Ãš", "U");
			src = src.replaceAll("Ã™", "U");
			src = src.replaceAll("á»¦", "U");
			src = src.replaceAll("Å¨", "U");
			src = src.replaceAll("á»¤", "U");
			
			src = src.replaceAll("Æ°", "u");
			src = src.replaceAll("á»©", "u");
			src = src.replaceAll("á»«", "u");
			src = src.replaceAll("á»­", "u");
			src = src.replaceAll("á»¯", "u");
			src = src.replaceAll("á»±", "u");
			
			src = src.replaceAll("Æ¯", "U");
			src = src.replaceAll("á»¨", "U");
			src = src.replaceAll("á»ª", "U");
			src = src.replaceAll("á»¬", "U");
			src = src.replaceAll("á»®", "U");
			src = src.replaceAll("á»°", "U");
			
			src = src.replaceAll("Ã½", "y");
			src = src.replaceAll("á»³", "y");
			src = src.replaceAll("á»·", "y");
			src = src.replaceAll("á»¹", "y");
			src = src.replaceAll("á»µ", "y");
			
			src = src.replaceAll("Ã?", "Y");
			src = src.replaceAll("á»²", "Y");
			src = src.replaceAll("á»¶", "Y");
			src = src.replaceAll("á»¸", "Y");
			src = src.replaceAll("á»´", "Y");
			
			src = src.replaceAll("Ä‘", "d");
			src = src.replaceAll("Ä?", "D");
			
			
			src = src.trim();
		} catch (RuntimeException e) {		
			return "";
		}
		
		return src;
	}*/
	
	public static String UnicodeToCompound(String src) {		
		try {
			src = src.replaceAll("à","a");
	    src = src.replaceAll("á","a");
	    src = src.replaceAll("ả","a");
	    src = src.replaceAll("ã","a");
	    src = src.replaceAll("ạ","a");
	    
	    src = src.replaceAll("Á","A");
	    src = src.replaceAll("À","A");
	    src = src.replaceAll("Ả","A");
	    src = src.replaceAll("Ã","A");
	    src = src.replaceAll("Ạ","A");
	    
	    src = src.replaceAll("ă","a");		
	    src = src.replaceAll("ắ","a");		
	    src = src.replaceAll("ằ","a");		
	    src = src.replaceAll("ặ","a");		
	    src = src.replaceAll("ẳ","a");		
	    src = src.replaceAll("ẵ","a");
	    
	    src = src.replaceAll("Ă","A");
	    src = src.replaceAll("Ắ","A");
	    src = src.replaceAll("Ẵ","A");
	    src = src.replaceAll("Ằ","A");
	    src = src.replaceAll("Ặ","A");
	    src = src.replaceAll("Ẳ","A");
	    
	    src = src.replaceAll("â","a");		
	    src = src.replaceAll("ấ","a");		
	    src = src.replaceAll("ầ","a");		
	    src = src.replaceAll("ẩ","a");		
	    src = src.replaceAll("ẫ","a");		
	    src = src.replaceAll("ậ","a");
	    
	    src = src.replaceAll("Ậ","A");
	    src = src.replaceAll("Â","A");
	    src = src.replaceAll("Ấ","A");
	    src = src.replaceAll("Ầ","A");
	    src = src.replaceAll("Ẩ","A");
	    src = src.replaceAll("Ẫ","A");
	    
	    src = src.replaceAll("é","e");
	    src = src.replaceAll("è","e");
	    src = src.replaceAll("ẻ","e");
	    src = src.replaceAll("ẽ","e");
	    src = src.replaceAll("ẹ","e");
	    
	    src = src.replaceAll("É","E");
	    src = src.replaceAll("È","E");
	    src = src.replaceAll("Ẻ","E");
	    src = src.replaceAll("Ẽ","E");
	    src = src.replaceAll("Ẹ","E");
	    
	    src = src.replaceAll("ê","e");		
	    src = src.replaceAll("ế","e");		
	    src = src.replaceAll("ề","e");		
	    src = src.replaceAll("ể","e");		
	    src = src.replaceAll("ễ","e");		
	    src = src.replaceAll("ệ","e");
	    
	    src = src.replaceAll("Ệ","E");
	    src = src.replaceAll("Ê","E");
	    src = src.replaceAll("Ế","E");
	    src = src.replaceAll("Ề","E");
	    src = src.replaceAll("Ể","E");
	    src = src.replaceAll("Ễ","E");
	    
	    src = src.replaceAll("í","i");
	    src = src.replaceAll("ì","i");
	    src = src.replaceAll("ỉ","i");
	    src = src.replaceAll("ĩ","i");
	    src = src.replaceAll("ị","i");
	    
	    src = src.replaceAll("Í","I");
	    src = src.replaceAll("Ì","I");
	    src = src.replaceAll("Ỉ","I");
	    src = src.replaceAll("Ĩ","I");
	    src = src.replaceAll("Ị","I");
	    
	    src = src.replaceAll("ó","o");
	    src = src.replaceAll("ò","o");
	    src = src.replaceAll("ỏ","o");
	    src = src.replaceAll("õ","o");
	    src = src.replaceAll("ọ","o");
	    
	    src = src.replaceAll("Ó","O");
	    src = src.replaceAll("Ò","O");
	    src = src.replaceAll("Ỏ","O");
	    src = src.replaceAll("Õ","O");
	    src = src.replaceAll("Ọ","O");
	    
	    src = src.replaceAll("ô","o");
	    src = src.replaceAll("ố","o");
	    src = src.replaceAll("ồ","o");
	    src = src.replaceAll("ổ","o");
	    src = src.replaceAll("ỗ","o");
	    src = src.replaceAll("ộ","o");
	    
	    src = src.replaceAll("Ô","O");
	    src = src.replaceAll("Ố","O");
	    src = src.replaceAll("Ồ","O");
	    src = src.replaceAll("Ổ","O");
	    src = src.replaceAll("Ỗ","O");
	    src = src.replaceAll("Ộ","O");
	    
	    src = src.replaceAll("ơ","o");
	    src = src.replaceAll("ớ","o");
	    src = src.replaceAll("ờ","o");
	    src = src.replaceAll("ở","o");
	    src = src.replaceAll("ỡ","o");
	    src = src.replaceAll("ợ","o");
	    
	    src = src.replaceAll("Ơ","O");
	    src = src.replaceAll("Ớ","O");
	    src = src.replaceAll("Ờ","O");
	    src = src.replaceAll("Ở","O");
	    src = src.replaceAll("Ỡ","O");
	    src = src.replaceAll("Ợ","O");
	    
	    src = src.replaceAll("ú","u");
	    src = src.replaceAll("ù","u");
	    src = src.replaceAll("ủ","u");
	    src = src.replaceAll("ũ","u");
	    src = src.replaceAll("ụ","u");
	    
	    src = src.replaceAll("Ú","U");
	    src = src.replaceAll("Ù","U");
	    src = src.replaceAll("Ủ","U");
	    src = src.replaceAll("Ũ","U");
	    src = src.replaceAll("Ụ","U");
	    
	    src = src.replaceAll("ư","u");
	    src = src.replaceAll("ứ","u");
	    src = src.replaceAll("ừ","u");
	    src = src.replaceAll("ử","u");
	    src = src.replaceAll("ữ","u");
	    src = src.replaceAll("ự","u");
	    
	    src = src.replaceAll("Ư","U");
	    src = src.replaceAll("Ứ","U");
	    src = src.replaceAll("Ừ","U");
	    src = src.replaceAll("Ử","U");
	    src = src.replaceAll("Ữ","U");
	    src = src.replaceAll("Ự","U");
	    
	    src = src.replaceAll("ý","y");
	    src = src.replaceAll("ỳ","y");
	    src = src.replaceAll("ỷ","y");
	    src = src.replaceAll("ỹ","y");
	    src = src.replaceAll("ỵ","y");
	    
	    src = src.replaceAll("Ý","Y");
	    src = src.replaceAll("Ỳ","Y");
	    src = src.replaceAll("Ỷ","Y");
	    src = src.replaceAll("Ỹ","Y");
	    src = src.replaceAll("Ỵ","Y");
	    
	    src = src.replaceAll("đ","d");
	    src = src.replaceAll("Đ","D");
			//src = src.replaceAll("Ä?", "D");
			
			
			src = src.trim();
		} catch (RuntimeException e) {		
			return "";
		}
		
		return src;
	}
	
	public static String nullOrTrim(String s) {
		if (null == s || 0 == s.trim().length()) {
			return null;
		}
		return s.trim();
	}
	
	public static String nullOrTrimInternalSpaces(String s) {
		if (null == s || 0 == s.trim().length()) {
			return null;
		}
		s = s.replaceAll("\\s+", " ");
		return s.trim();
	}
	public static String processString(String s) {
		if (null == s || 0 == s.trim().length()) {
			return null;
		}
		s = s.replaceAll("\\s+", " ");
		s = s.replaceAll("\"", "");
		return s.trim();
	}
	public static String nullOrTrim(Object object) {
		if (null == object || object.toString().trim().length() == 0) {
			return null;
		}
		return object.toString();
	}
	
	public static BigDecimal convertStringToBigDecimal(String s, BigDecimal returnValOnException) {		
		try {
			return new BigDecimal(s);
		} catch (RuntimeException e) {	
			if (null == returnValOnException)
				return null;
			else
				return returnValOnException;
		}
	}
	
	public static String getFormattedNumber(BigDecimal number, String pattern) {
		if (null == number) {
			return "-";
		}
		NumberFormat formatter = new DecimalFormat(pattern);
	    return formatter.format(number.doubleValue());		
	}

	public static BigDecimal convertStringToBigDecimal(String s, int scale, int roundMode) {		
		try {
			return new BigDecimal(s).setScale(scale, roundMode);
		} catch (RuntimeException e) {			
			return null;
		}
	}
	
	public static BigDecimal convertStringToBigDecimal(String s, int scale) {		
		try {
			return new BigDecimal(s).setScale(scale);
		} catch (RuntimeException e) {			
			try {
				return convertStringWithCommaToBigDecimal(s, scale);
			} catch (RuntimeException e1) {
				return null;
			}
		}
	}
	
	public static BigDecimal convertStringWithCommaToBigDecimal(String s, int scale) {		
		try {
			String number = "";
			for (int i = 0; i < s.length(); i++) {
				Character c = s.charAt(i);
				if (Character.isDigit(c) || c == '.') {
					number += c;
				} else if (c == ',') {
					continue;
				} else {
					throw new RuntimeException("Invalid number " + s);
				}
			}
			return new BigDecimal(number).setScale(scale);
		} catch (RuntimeException e) {			
			return null;
		}
	}
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equalsIgnoreCase(str);
	}
	
	public static String formatStringCompound(String sa) {
		if (StringUtils.isNotEmpty(sa)) {
        	sa = sa.replaceAll("\"", "");
//			sa = UnicodeToCompound(sa);
        	
        	return sa;
        }
		return sa;
	}
	
	public static void main(String[] args) {  
		//System.out.println(StringUtils.isNotBlank("c"));
		//System.out.println(StringUtils.isEmpty(null));
		//String str = "10511722/ LÊ VĂN HÓA - 2324324sdfds3532434";
		//System.out.println(str.matches("-?\\d+(.\\d+)?"));
		//Matcher m = Pattern.compile("\\d+").matcher(str);
		
		//StringBuffer sb = gGetCustomerName(str);
		//System.out.println(sb.toString().trim());
		
		
			
		//while (m.find()) {
		//   System.out.println(m.group(0));
		//}
		//if(m.find(0)){
		//	  ///System.out.println(m.group(0));
		//}
		System.out.println(UnicodeToCompound("VÕ THỊ PHƯƠNG THẢO"));
	}

	private static StringBuffer getCustomerName(String str) {
		Matcher m2 = Pattern.compile("[\\D]").matcher(str.trim());
		//myStr = myStr.replaceAll( "[^\\d]", "" )
		StringBuffer sb = new StringBuffer();
		while (m2.find()) {
			if(m2.group(0).equals("-")) {
				break;
			}
			if(!m2.group(0).equals("/")) {
			   System.out.println(m2.group(0));
			   sb.append(m2.group(0));
			}
			
		}
		return sb;
	}
	
	public static Connection getConnection(String connectionType, String url, String userName, String password) throws Exception {
		//url = "jdbc:oracle:thin:@localhost:1521:xe";
		//DB_URL = "jdbc:mysql://localhost/";
		//url = "jdbc:sqlserver://localhost:1433;databaseName=SVFC_MSCRM;integratedSecurity=true";
	    //URL: jdbc:sqlserver://${mssql.server}:${mssql.port};databaseName=${mssql.database};user=${mssql.user};password=${mssql.password}.
	    //Driver Class Name: com.microsoft.sqlserver.jdbc.SQLServerDriver.

		Connection conn = null;
		if("ORACLE".equals(connectionType)) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} else if("SQLSERVER".equals(connectionType)) {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} else if("MYSQL".equals(connectionType)) {
			Class.forName("com.mysql.jdbc.Driver");
		} else {
			return null;
		}
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}
}
