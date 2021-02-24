/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruvn.printserver.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class DateUtils {

    public static final String ddMMMyy = "dd-MMM-yy";
    public static final String ddMMyyyy = "ddMMyyyy";
    public static final String ddMMyy = "ddMMyy";
    public static final String yyMMdd = "yyMMdd";
    public static final String ddFMMFyyyy = "dd/MM/yyyy";
    public static final String ddMonyy = "dd-MMM-yyyy";
    public static final String HH = "HH";
    public static final String DATE_TIME_MILLIS_FORMAT = "yyMMddHHmmssSSS";
    public static final String YYYYMMDDHHMMSS = "yyyMMddHHmmss";
    public static final String dd = "dd";
    private static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
    public static String toPattern(String dateString, String fromPattern, String toPattern) throws ParseException {
            SimpleDateFormat simpleDate = new SimpleDateFormat();

            simpleDate.applyPattern(fromPattern);
            Date date = simpleDate.parse(dateString);
            simpleDate.applyPattern(toPattern);
            String result = simpleDate.format(date);
            return result;
    }
    
    public static String getCurrentStringDate(String format)
    {
        Calendar rightNow = Calendar.getInstance();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format); 
        return simpleDateFormat.format(new Date(rightNow.getTimeInMillis()));
    }
    
    public static Date getCurrentDate()
    {
        Calendar rightNow = Calendar.getInstance();         
        return new Date(rightNow.getTimeInMillis());
    }
    
    public static Date getPreviousDate()
    {
        Calendar rightNow = Calendar.getInstance();       
        rightNow.add(Calendar.DAY_OF_MONTH, -1);
        return new Date(rightNow.getTimeInMillis());
    }
    
    public static String toDateString(Date date, String format)
    {          
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format); 
        return simpleDateFormat.format(date);
    }
    
    public static Date toDate(String date, String pattern)
    {
        try {
            SimpleDateFormat simpleDate = new SimpleDateFormat();

            simpleDate.applyPattern(pattern);
            return simpleDate.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

	public static Integer getCurrentTimeInHour() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HH); 
        return Integer.parseInt(simpleDateFormat.format(Calendar.getInstance().getTime()));
	}
	
	public static Date getNextBusinessDay(Date day) {
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(day);  
  
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);  
  
        if (dayOfWeek == Calendar.FRIDAY) {  
            calendar.add(Calendar.DATE, 3);  
        } else if (dayOfWeek == Calendar.SATURDAY) {  
            calendar.add(Calendar.DATE, 2);  
        } else {  
            calendar.add(Calendar.DATE, 1);  
        }  
  
        Date nextBusinessDay = calendar.getTime();  
        	
        return nextBusinessDay;
	}
	public static long caculatorDateDay(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/(1000*60*60*24);
	}
	public static long caculatorDateHours(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/(1000*60*60);
	}
	public static long caculatorDateMinutes(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/(60 * 1000);
	}
	public static long caculatorDateSeconds(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/(1000);
	}
	
	public static final String getCurrentTimeMillis() {
		long currentTimeMillis = System.currentTimeMillis();
		return getCurrentTimeMillis(currentTimeMillis);
	}

	public static final String getCurrentTimeMillis(long currentTimeMillis) {
		Date currentDate = new Date(currentTimeMillis);
		DateFormat df = new SimpleDateFormat(DATE_TIME_MILLIS_FORMAT);
		return "P"+df.format(currentDate);
	}
	public static final String getCurrentTimeSecond(Date date) {
		DateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return df.format(date);
	}
	
	 public static long countDaysBetween(Date start, Date end) {
	        if (end.before(start)) {
	            throw new IllegalArgumentException("The end date must be later than the start date");
	        }

	        //reset all hours mins and secs to zero on start date
	        Calendar startCal = GregorianCalendar.getInstance();
	        startCal.setTime(start);
	        startCal.set(Calendar.HOUR_OF_DAY, 0);
	        startCal.set(Calendar.MINUTE, 0);
	        startCal.set(Calendar.SECOND, 0);
	        long startTime = startCal.getTimeInMillis();

	        //reset all hours mins and secs to zero on end date
	        Calendar endCal = GregorianCalendar.getInstance();
	        endCal.setTime(end);
	        endCal.set(Calendar.HOUR_OF_DAY, 0);
	        endCal.set(Calendar.MINUTE, 0);
	        endCal.set(Calendar.SECOND, 0);
	        long endTime = endCal.getTimeInMillis();

	        return (endTime - startTime) / MILLISECONDS_IN_DAY;
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
		    
		    	text = text.replaceAll("MỘT MƯƠI", "MƯỜI");
		    	text = text.replaceAll("MƯỜI MỐT", "MƯỜI MỘT");
		    
		        
		    return text.toLowerCase();    
		}

		private static String fSoThanhChuMini(long number) {
			String text = "";
			
		    String[] NumberText = new String[10];
		    NumberText[0] = "KHÔNG";
		    NumberText[1] = "MỘT";
		    NumberText[2] = "HAI";
		    NumberText[3] = "BA";
		    NumberText[4] = "BỐN";
		    NumberText[5]= "NĂM";
		    NumberText[6] = "SÁU";
		    NumberText[7] = "BẢY";
		    NumberText[8] = "TÁM";
		    NumberText[9] = "CHÍN";
		    
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
						text = text + "MỐT ";
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
	 
		
		public static String getFormattedNumber(String number, String pattern) {
			double paser=Double.parseDouble(number);
			if (null == number) {
				return "-";
			}
			NumberFormat formatter = new DecimalFormat(pattern);
		    return formatter.format(paser);		
		}
	
	public static void main(String[] args) throws ParseException {
		
		DateUtils datess=new DateUtils();
		System.out.println(datess.getFormattedNumber("100000000000000","#,###"));
		
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = sdf.parse("2012-11-01");
//	
//		Calendar calendar1 = Calendar.getInstance();
//	    Calendar calendar2 = Calendar.getInstance();
//	    calendar1.setTime(d);
//	    calendar2.setTime(new Date());
//	    long milliseconds1 = calendar1.getTimeInMillis();
//	    long milliseconds2 = calendar2.getTimeInMillis();
//	    long diff = milliseconds2 - milliseconds1;
//	    long diffSeconds = diff / 1000;
//	    long diffMinutes = diff / (60 * 1000);
//	    long diffHours = diff / (60 * 60 * 1000);
//	    long diffDays = diff / (24 * 60 * 60 * 1000);
//	    System.out.println("\nThe Date Different Example");
//	    System.out.println("Time in milliseconds: " + diff
//	 + " milliseconds.");
//	    System.out.println("Time in seconds: " + diffSeconds
//	 + " seconds.");
//	    System.out.println("Time in minutes: " + diffMinutes 
//	+ " minutes.");
//	    System.out.println("Time in hours: " + diffHours 
//	+ " hours.");
//	    System.out.println("Time in days: " + diffDays 
//	+ " days.");
		//DateUtils test=new DateUtils();
		//System.out.println(test.getCurrentTimeSecond(new Date()));
		
	  }
//		
		 
		
	
}
