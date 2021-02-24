package com.pruvn.rms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



public class DateUtils {
	private static final Logger logger = Logger.getLogger(DateUtils.class);
	
	public static final String mmddyyyy = "MM/dd/yyyy";
	public static final String ddmmyyyy = "dd/MM/yyyy";
	public static final String ddmmyy = "dd-MM-yy";
	
	public static final String DATETIMEPATTERN_1 = "dd/MM/yyyy HH:mm:ss";

	public static final String yyyymmdd = "yyyyMMdd hh:mm:ss";
	public static final String yyyymmdd_1 = "yyyyMMddhhmmss";
	
	public static final String yyyyMMddhhmmss = "yyyyMMddhhmmss";
	
	
	private static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
	
	public static Date stringToDate(String strDate, String format) throws ParseException {
		String defaultFormat = "dd/MM/yyyy";
		if (StringUtils.isEmpty(format)) {
			format = defaultFormat;
		}
		DateFormat df = new SimpleDateFormat(format);
		 
        try {
        	if (StringUtils.isEmpty(strDate)) {
        		return Calendar.getInstance().getTime();
        	}


        	return df.parse(strDate);            
            
        } catch (ParseException e) {
        	logger.error("Parse exception : " + e.getStackTrace() + "; strDate:" + strDate + ";format:" + format);
            throw new ParseException("Parse exception", 0);
        }
	}
	
	public static Date stringToDateFormat(String strDate, String format){
		String defaultFormat = "dd/MM/yyyy";
		if (StringUtils.isEmpty(format)) {
			format = defaultFormat;
		}
		DateFormat df = new SimpleDateFormat(format);
        try {
        	if (StringUtils.isEmpty(strDate)) {
        		return null;
        	}
        	return df.parse(strDate);            
        }catch (ParseException e) {
        	logger.error("Parse exception : " + e.getStackTrace() + "; strDate:" + strDate + ";format:" + format);
           return null;
        }
	}
	
	public static final String getCurrentTimeSecond(Date date) {
		DateFormat df = new SimpleDateFormat(yyyyMMddhhmmss);
		return df.format(date);
	}
	
	public static String dateToString(Date date, String format) throws ParseException {
		String defaultFormat = "dd/MM/yyyy";
		if (StringUtils.isEmpty(format) || format == null) {
			format = defaultFormat;
		}
		DateFormat df = new SimpleDateFormat(format);
		 
        if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		return df.format(date);
	}
	
	/**
     * Calculates the number of days between start and end dates, taking
     * into consideration leap years, year boundaries etc.
     *
     * @param start the start date
     * @param end the end date, must be later than the start date
     * @return the number of days between the start and end dates
     */
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
    public static long caculatorDateMinutes(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/(60 * 1000);
	}
}
