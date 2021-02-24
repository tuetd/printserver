/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruvn.tools.utils;

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

    public static final String ddMMyyyy = "ddMMyyyy";
    public static final String ddFMMFyyyy = "dd/MM/yyyy";
    public static final String HH = "HH";
    
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
//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
//	  }
//		
		 
		
	
}
