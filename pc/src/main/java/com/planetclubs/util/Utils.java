package com.planetclubs.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
@Component
public class Utils {
	
	public Date getCurrentTime(){
		Date now = new Date();
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		return startCal.getTime();
	}
	
	public Date addMonths(int months){
		Date now = new Date();
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.add(Calendar.MONTH, months);
		startCal.add(Calendar.DATE, -1);
		
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		return startCal.getTime();
	}

	public Date addYears(int i) {
		Date now = new Date();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.add(Calendar.YEAR, i);
		startCal.add(Calendar.DATE, -1);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));

		return startCal.getTime();
	}

	public Date addHours(int hours) {
		Date now = new Date();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.add(Calendar.HOUR, hours);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		return startCal.getTime();
	}
	
	public Date setHoursAndMinutes(int hours,int minutes) {
		Date now = new Date();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.set(Calendar.HOUR_OF_DAY, hours);
		startCal.set(Calendar.MINUTE,minutes);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		return startCal.getTime();
	}
	
}
