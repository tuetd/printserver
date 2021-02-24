package com.pruvn.tools.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.web.context.request.RequestContextHolder;
import jsystem.utils.StringUtils;

public class UtilConverter {
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
	
	public static String toUpper(String s) {
		try {
			return s.toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Object getObjectAtIndex(List<Object> list, int index) {
		try {
			if (null == list || list.isEmpty() || index >= list.size()) {
				return null;
			}		
			return list.get(index);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String wrapString(String s, int length) {
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				if (Character.SPACE_SEPARATOR == s.charAt(i) && i >= length) {
					sb.append('\n');
				}
				sb.append(s.charAt(i));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String addRightPadding(String s, int length) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < length - s.length(); i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public static String addRightPadding(String s, int length, char c) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < length - s.length(); i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static String addLeftPadding(String s, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - s.length(); i++) {
			sb.append(' ');
		}
		sb.append(s);
		return sb.toString();
	}
	
	public static String addLeftPadding(String s, int length, char c) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - s.length(); i++) {
			sb.append(c);
		}
		sb.append(s);
		return sb.toString();
	}
	
	public static String nullOrTrim(String s) {
		if (null == s || 0 == s.trim().length()) {
			return null;
		}
		return s.trim();
	}
	
	public static String nullOrTrim(Object object) {
		if (null == object || object.toString().trim().length() == 0) {
			return null;
		}
		return object.toString();
	}
	
	public static String convertToString(Object object) {
		if (null == object) {
			return "";
		}
		return object.toString().trim();
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
	
	public static Number convertStringToNumber(String string, String pattern, boolean comma) {
		try {
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
			df.applyPattern(pattern);
			if (!comma) {
				string = string.replace('.', ',');
				StringBuilder b = new StringBuilder(string);			
				b.replace(string.lastIndexOf(","), string.lastIndexOf(",") + 1, "." );
				string = b.toString();
			}
			return df.parse(string, new ParsePosition(0));
			
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer convertStringToInt(String s) {		
		try {
			return new Integer(s);
		} catch (RuntimeException e) {			
			return null;
		}
	}
	
	public static Long convertStringToLong(String s) {		
		try {
			String s1 = s;
			if (s1.indexOf("(") >= 0) 
				s1 = s1.replace("(", "");
			if (s1.indexOf(")") >= 0) 
				s1 = s1.replace(")", "");
			return (new Double(s1)).longValue();
		} catch (RuntimeException e) {			
			return null;
		}
	}

	public static String fSoThanhChu(long number) {
		if (0 == number) {
			return "KHÔNG";
		}
		String text = "";
	
	    if (number / 1000000000 > 0) {
	        text = fSoThanhChuMini(number / 1000000000) + " TỈ ";    
	    }
	    
	    number = number % 1000000000;
	    if (number / 1000000 > 0) {
	        text = text + fSoThanhChuMini(number / 1000000) + " TRIỆU ";
	    }	    
	
	    number = number % 1000000;
	    if (number / 1000 > 0) {
	        text = text + fSoThanhChuMini(number / 1000) + " NGÀN ";
	    }
	    text = text + fSoThanhChuMini(number);
	    
//	    if (text.length() >= 8 && text.substring(0, 8).equals("MỘT MƯƠI")) {
	    	text = text.replaceAll("MỘT MƯƠI", "MƯỜI");
	    	text = text.replaceAll("MƯỜI M�?T", "MƯỜI MỘT");
//	    }
	    
	        
	    return text.toUpperCase();    
	}

	private static String fSoThanhChuMini(long number) {
		String text = "";
		
	    String[] NumberText = new String[10];
	    NumberText[0] = "KHÔNG";
	    NumberText[1] = "MỘT";
	    NumberText[2] = "HAI";
	    NumberText[3] = "BA";
	    NumberText[4] = "B�?N";
	    NumberText[5]= "NĂM";
	    NumberText[6] = "S�?U";
	    NumberText[7] = "BẢY";
	    NumberText[8] = "T�?M";
	    NumberText[9] = "CH�?N";
	    
	    boolean isLang = false;	    
	     	    
	    number = number % 1000;    
	        
	    if (number > 99) {	    	
	        text = text + NumberText[(int)(number / 100)] + " TRĂM ";
	    }    
	    
	    number = number % 100;
	    if (number > 9) {
	        text = text + NumberText[(int)(number / 10)] + " MƯƠI ";        
	        isLang = true;
	    }
	    else if (number % 10 > 0) {
	        if (text.length() > 0) {
	            text = text + "LẺ ";
	        }
	    }
	
	    number = number % 10;
	    if (number > 0) {
	        if (isLang) {
	        	switch ((int)(number % 10)) {
				case 1:
					text = text + "M�?T ";
					break;
				case 5:
					text = text + "LĂM ";
					break;
				default:
					text = text + NumberText[(int)(number % 10)];
					break;
				}
	        }	            
	        else {
	            text = text + NumberText[(int)(number % 10)];
	        }
	    }	    
	    
	    return text.trim();	    
	}

	public static String getFormattedDate(String format, Date date) {
		Format formatter = new SimpleDateFormat(format);
		try {
			return formatter.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getFormattedDate(String format, Calendar calendar) {
		Format formatter = new SimpleDateFormat(format);
		try {
			return formatter.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	public static String getFormattedDateFromPatternString(String format, String pattern, String date) {		
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);		
		try {			
			return getFormattedDate(format, formatter.parse(date));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date getDateFromPattern(String pattern, String date) {		
		try {
			DateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.parse(date);
		} catch (Exception e) {		
			return null;
		}
	}
	
	public static Calendar getDateFromPatternCalendar(String pattern, String date) {		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date ret = formatter.parse(date);
			Calendar cld = Calendar.getInstance();
			cld.setTime(ret);
			return cld;
		} catch (Exception e) {		
			return null;
		}
	}

	public static int getActualMaximumDay(int year, int month) {
		GregorianCalendar cal = new GregorianCalendar(year, month - 1, 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static String UnicodeToCompoundAndTrim(String src) {
		src = UnicodeToCompound(src);
		src = src.replaceAll("\\s+", " ");
		
		return src;
	}
	   public static String getHttpSessionID() {
	    	 try {
				 return RequestContextHolder.getRequestAttributes().getSessionId();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	     }
	/**
	 * convert chuoi co dau thanh khong dau. VD: NGUYỄN �?ỨC LONG -> NGUYEN DUC LONG
	 * @param src
	 * @return
	 */	
	public static String UnicodeToCompound(String src) {		
		try {
			src = src.replaceAll("à", "a");
			src = src.replaceAll("á", "a");
			src = src.replaceAll("ả", "a");
			src = src.replaceAll("ã", "a");
			src = src.replaceAll("ạ", "a");
			
			src = src.replaceAll("Á", "A");
			src = src.replaceAll("À", "A");
			src = src.replaceAll("Ả", "A");
			src = src.replaceAll("Ã", "A");
			src = src.replaceAll("Ạ", "A");
			
			src = src.replaceAll("ă", "a");		
			src = src.replaceAll("ắ", "a");		
			src = src.replaceAll("ằ", "a");		
			src = src.replaceAll("ặ", "a");		
			src = src.replaceAll("ẳ", "a");		
			src = src.replaceAll("ẵ", "a");
			
			src = src.replaceAll("Ă", "A");
			src = src.replaceAll("Ắ", "A");
			src = src.replaceAll("Ẵ", "A");
			src = src.replaceAll("Ằ", "A");
			src = src.replaceAll("Ặ", "A");
			src = src.replaceAll("Ẳ", "A");
			
			src = src.replaceAll("â", "a");		
			src = src.replaceAll("ấ", "a");		
			src = src.replaceAll("ầ", "a");		
			src = src.replaceAll("ẩ", "a");		
			src = src.replaceAll("ẫ", "a");		
			src = src.replaceAll("ậ", "a");
			
			src = src.replaceAll("Ậ", "A");
			src = src.replaceAll("Â", "A");
			src = src.replaceAll("Ấ", "A");
			src = src.replaceAll("Ầ", "A");
			src = src.replaceAll("Ẩ", "A");
			src = src.replaceAll("Ẫ", "A");
			
			src = src.replaceAll("é", "e");
			src = src.replaceAll("è", "e");
			src = src.replaceAll("ẻ", "e");
			src = src.replaceAll("ẽ", "e");
			src = src.replaceAll("ẹ", "e");
			
			src = src.replaceAll("É", "E");
			src = src.replaceAll("È", "E");
			src = src.replaceAll("Ẻ", "E");
			src = src.replaceAll("Ẽ", "E");
			src = src.replaceAll("Ẹ", "E");
			
			src = src.replaceAll("ê", "e");		
			src = src.replaceAll("ế", "e");		
			src = src.replaceAll("ề", "e");		
			src = src.replaceAll("ể", "e");		
			src = src.replaceAll("ễ", "e");		
			src = src.replaceAll("ệ", "e");
			
			src = src.replaceAll("Ệ", "E");
			src = src.replaceAll("Ê", "E");
			src = src.replaceAll("Ế", "E");
			src = src.replaceAll("Ề", "E");
			src = src.replaceAll("Ể", "E");
			src = src.replaceAll("Ễ", "E");
			
			src = src.replaceAll("í", "i");
			src = src.replaceAll("ì", "i");
			src = src.replaceAll("ỉ", "i");
			src = src.replaceAll("ĩ", "i");
			src = src.replaceAll("ị", "i");
			
			src = src.replaceAll("Í", "I");
			src = src.replaceAll("Ì", "I");
			src = src.replaceAll("Ỉ", "I");
			src = src.replaceAll("Ĩ", "I");
			src = src.replaceAll("Ị", "I");
			
			src = src.replaceAll("ó", "o");
			src = src.replaceAll("ò", "o");
			src = src.replaceAll("ỏ", "o");
			src = src.replaceAll("õ", "o");
			src = src.replaceAll("ọ", "o");
			
			src = src.replaceAll("Ó", "O");
			src = src.replaceAll("Ò", "O");
			src = src.replaceAll("Ỏ", "O");
			src = src.replaceAll("Õ", "O");
			src = src.replaceAll("Ọ", "O");
			
			src = src.replaceAll("ô", "o");
			src = src.replaceAll("ố", "o");
			src = src.replaceAll("ồ", "o");
			src = src.replaceAll("ổ", "o");
			src = src.replaceAll("ỗ", "o");
			src = src.replaceAll("ộ", "o");
			
			src = src.replaceAll("Ô", "O");
			src = src.replaceAll("Ố", "O");
			src = src.replaceAll("Ồ", "O");
			src = src.replaceAll("Ổ", "O");
			src = src.replaceAll("Ỗ", "O");
			src = src.replaceAll("Ộ", "O");
			
			src = src.replaceAll("ơ", "o");
			src = src.replaceAll("ớ", "o");
			src = src.replaceAll("ờ", "o");
			src = src.replaceAll("ở", "o");
			src = src.replaceAll("ỡ", "o");
			src = src.replaceAll("ợ", "o");
			
			src = src.replaceAll("Ơ", "O");
			src = src.replaceAll("Ớ", "O");
			src = src.replaceAll("Ờ", "O");
			src = src.replaceAll("Ở", "O");
			src = src.replaceAll("Ỡ", "O");
			src = src.replaceAll("Ợ", "O");
			
			src = src.replaceAll("ú", "u");
			src = src.replaceAll("ù", "u");
			src = src.replaceAll("ủ", "u");
			src = src.replaceAll("ũ", "u");
			src = src.replaceAll("ụ", "u");
			
			src = src.replaceAll("Ú", "U");
			src = src.replaceAll("Ù", "U");
			src = src.replaceAll("Ủ", "U");
			src = src.replaceAll("Ũ", "U");
			src = src.replaceAll("Ụ", "U");
			
			src = src.replaceAll("ư", "u");
			src = src.replaceAll("ứ", "u");
			src = src.replaceAll("ừ", "u");
			src = src.replaceAll("ử", "u");
			src = src.replaceAll("ữ", "u");
			src = src.replaceAll("ự", "u");
			
			src = src.replaceAll("Ư", "U");
			src = src.replaceAll("Ứ", "U");
			src = src.replaceAll("Ừ", "U");
			src = src.replaceAll("Ử", "U");
			src = src.replaceAll("Ữ", "U");
			src = src.replaceAll("Ự", "U");
			
			src = src.replaceAll("ý", "y");
			src = src.replaceAll("ỳ", "y");
			src = src.replaceAll("ỷ", "y");
			src = src.replaceAll("ỹ", "y");
			src = src.replaceAll("ỵ", "y");
			
			src = src.replaceAll("Ý", "Y");
			src = src.replaceAll("Ỳ", "Y");
			src = src.replaceAll("Ỷ", "Y");
			src = src.replaceAll("Ỹ", "Y");
			src = src.replaceAll("Ỵ", "Y");
			
			src = src.replaceAll("đ", "d");
			src = src.replaceAll("Đ", "D");
			
			
			src = src.trim();
		} catch (RuntimeException e) {		
			return "";
		}
		
		return src;
	}
	
	public static Date convertStringToDate(String s, String pattern) {		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String encodeString(String s) {
		try {
			return URLEncoder.encode(s, "UTF8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * added 11-9-2008
	 * @param s
	 * @param c
	 * @return
	 */
	public static String removeChar(String s, char c) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (c != s.charAt(i)) {
				result += s.charAt(i);
			}
		}
		return result;
	}
	
	public static void sendSMS(String mobile, String script) {		
		try {
			script = script.replaceAll(GlobalConstant.SINGLEQUOTE, "");
			Connection conn = DBProcessor.ORACLEConnect("128.235.106.183", "1521", "DWH02", "longnd", "nsn132027");
			CallableStatement cs = conn.prepareCall("{CALL sms_owner.cfc_send_sms(?,?)}");			
			cs.setObject(1, script);
			cs.setObject(2, mobile);
			cs.execute();
			cs.close();
			DBProcessor.closeConnection(conn);			
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();			
		}		
	}
	
	public static void sendSMS(String mobile, String script, String dept) {		
		Connection conn = null;
		try {
			script = script.replaceAll(GlobalConstant.SINGLEQUOTE, "");
			conn = DBProcessor.ORACLEConnect("128.235.106.183", "1521", "DWH02", "longnd", "nsn132027");
			CallableStatement cs = conn.prepareCall("{CALL sms_owner.CFC_SEND_SMS_15062010(?,?,?)}");			
			cs.setObject(1, script);
			cs.setObject(2, mobile);
			cs.setObject(3, dept);
			cs.execute();
			cs.close();						
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			DBProcessor.closeConnection(conn);
		}
	}
	
	public static void sendSMSWithDept(String mobile, String script, String dept, Connection conn) {		
		try {
			script = script.replaceAll(GlobalConstant.SINGLEQUOTE, "");			
			CallableStatement cs = conn.prepareCall("{CALL sms_owner.CFC_SEND_SMS_15062010(?,?,?)}");			
			cs.setObject(1, script);
			cs.setObject(2, mobile);
			cs.setObject(3, dept);
			cs.execute();
			cs.close();	
		} catch (SQLException e) {
			e.printStackTrace();			
		}		
	}
	
	public static String composeExceptionMessage(Exception e) {
		String message = new Date().toString() + GlobalConstant.NEWLINE;
		message += e.toString() + GlobalConstant.NEWLINE;
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			message += ste[i].toString() + GlobalConstant.NEWLINE;
		}
		return message;
	}
	
	// Deletes all files and subdirectories under dir.
     // Returns true if all deletions were successful.
     // If a deletion fails, the method stops attempting to delete and returns false.
     public static boolean deleteDir(File dir) {
         if (dir.isDirectory()) {
             String[] children = dir.list();
             for (int i=0; i<children.length; i++) {
                 boolean success = deleteDir(new File(dir, children[i]));
                 if (!success) {
                     return false;
                 }
             }
         }
     
         // The directory is now empty so delete it
         return dir.delete();
     } 
     
     public static int[] getCalendarDiff(String sdate1, String sdate2, String fmt, TimeZone tz)
     {
         SimpleDateFormat df = new SimpleDateFormat(fmt);

         Date date1  = null;
         Date date2  = null;
         
         try 
         {
             date1 = df.parse(sdate1); 
             date2 = df.parse(sdate2); 
         }
         catch (ParseException pe)
         {
             pe.printStackTrace();
         }

         Calendar cal1 = null; 
         Calendar cal2 = null;
         
         if (tz == null)
         {
           cal1=Calendar.getInstance(); 
           cal2=Calendar.getInstance(); 
         }
         else
         {
           cal1=Calendar.getInstance(tz); 
           cal2=Calendar.getInstance(tz); 
         }
           
         
         // different date might have different offset
         cal1.setTime(date1);          
         long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
         
         cal2.setTime(date2);
         long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
         
         // Use integer calculation, truncate the decimals
         int hr1   = (int)(ldate1/3600000); //60*60*1000
         int hr2   = (int)(ldate2/3600000);

         int days1 = (int)hr1/24;
         int days2 = (int)hr2/24;

         
         int dateDiff  = days2 - days1;
         int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))<0 ? 1 : 0;
         int weekDiff  = dateDiff/7 + weekOffset; 
         int yearDiff  = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
         int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

         
//         System.out.println();  
//         System.out.println("DateTime 1: " + sdate1);
//         System.out.println("DateTime 2: " + sdate2);
//         
//         System.out.println("Date difference : " + dateDiff);
//         System.out.println("Week difference : " + weekDiff);
//         System.out.println("Month difference: " + monthDiff);
//         System.out.println("Year difference : " + yearDiff);
         
         return new int[]{dateDiff, weekDiff, monthDiff, yearDiff};
     }
     
     public static int[] getCalendarDiff(Date date1, Date date2, TimeZone tz)
     {
         Calendar cal1 = null; 
         Calendar cal2 = null;
         
         if (tz == null)
         {
           cal1=Calendar.getInstance(); 
           cal2=Calendar.getInstance(); 
         }
         else
         {
           cal1=Calendar.getInstance(tz); 
           cal2=Calendar.getInstance(tz); 
         }
           
         
         // different date might have different offset
         cal1.setTime(date1);          
         long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
         
         cal2.setTime(date2);
         long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
         
         // Use integer calculation, truncate the decimals
         int hr1   = (int)(ldate1/3600000); //60*60*1000
         int hr2   = (int)(ldate2/3600000);

         int days1 = (int)hr1/24;
         int days2 = (int)hr2/24;

         
         int dateDiff  = days2 - days1;
         int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))<0 ? 1 : 0;
         int weekDiff  = dateDiff/7 + weekOffset; 
         int yearDiff  = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
         int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

         
//         System.out.println();  
//         System.out.println("DateTime 1: " + sdate1);
//         System.out.println("DateTime 2: " + sdate2);
//         
//         System.out.println("Date difference : " + dateDiff);
//         System.out.println("Week difference : " + weekDiff);
//         System.out.println("Month difference: " + monthDiff);
//         System.out.println("Year difference : " + yearDiff);
         
         return new int[]{dateDiff, weekDiff, monthDiff, yearDiff};
     }
     
     @SuppressWarnings("unchecked")
	public static void copyData(Object from, Object to) {
    	 try {
			Class fromClass = from.getClass();
			Class toClass = to.getClass();
			 Method[] fromMethods = fromClass.getMethods();
			 Method[] toMethods = toClass.getMethods();
			 
			 for (int i = 0; i < fromMethods.length; i++) {
				 if (fromMethods[i].getName().startsWith("get") && !"getClass".equals(fromMethods[i].getName())) {					 
					 String setMethod = "set" + fromMethods[i].getName().substring(3, fromMethods[i].getName().length());
					 if (checkMethodExist(setMethod, toMethods)) {
						 executeMethod(to, setMethod, new Class[]{fromMethods[i].getReturnType()}, 
								 new Object[]{executeMethod(from, fromMethods[i].getName(), new Class[]{}, new Object[]{})});
					 }
				 }
			 }
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}         
     }
     
     public static Integer tryParseInteger(String agreementno) {
    	 if (StringUtils.isEmpty(agreementno)) {
			return -1;
    	 }
		 if (!agreementno.startsWith("1")) {
			return -1;
		 }
		 
		 if (agreementno.length() != 8) {
				return -1;
		}
		 
		 String strAgreementid = agreementno.substring(1);
		Integer agreementid = -1;
		try {
			agreementid = new Integer(strAgreementid);
		} catch (NumberFormatException e) {
			agreementid = -1;
		}
  		
  		return agreementid;
  	}
     
    private static boolean checkMethodExist(String method, Method[] methods) {
    	for (int i = 0; i < methods.length; i++) {
    		if (method.equals(methods[i].getName())) {
    			return true;
    		}
    	}
    	return false;
    }
     
     @SuppressWarnings("unchecked")
	private static Object executeMethod(Object object, String method, Class[] params, Object[] args) 
     	throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {    	 
         Class c = Class.forName(object.getClass().getName());         
         Method executeMethod = c.getMethod(method, params);         
     	 return executeMethod.invoke(object, args);         
     }
     
     public static void main(String[] args) {
    	 String text = "NGUYỄN   VĂN    PHƯỚC";
    	 System.out.println(text);
    	 System.out.println(UnicodeToCompound(text));
		System.out.println("NGUYEN VAN PHUOC".equals(UnicodeToCompound(text)));
//		System.out.println(Calendar.getInstance().getTime());
//		System.out.println(getDateFromPatternCalendar(GlobalConstant.DATEPATTERN_1, "29/08/2011").getTime());
//		System.out.println(convertStringToBigDecimal("123,345,556.54", 2));
		
		
	}

}
