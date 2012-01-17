package com.dvdworld.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import com.dvdworld.model.Rental;

//
// Various business utilities we can use inside all of our Java code.
//
public class DvdWorldBusinessUtils {
	//
	// Convert a string under the form yyyy/mm/dd to a java.util.Date object.
	//
	public static Date StringToDate(String dateString) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd"); 
			Date convertedDate = dateFormat.parse(dateString);
			return convertedDate;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Long intToLong(int intValue) {
		return Integer.valueOf(intValue).longValue();
	}
	
	public static Date addDaysFromNow(int days) {
		if (days < 0)
			return null;
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, days);
		return now.getTime();
	}
	
	public static double getTotalSumForRentals(Rental []rentals) {
		if (rentals == null || rentals.length == 0)
			return 0;
		
		int sumToPay = 0;
		Date startDate = null;
		Date dueDate = null;
		
		for (int i = 0; i < rentals.length; i++) {
			startDate = rentals[i].getStartDate();
			dueDate = rentals[i].getDueDate();
			Calendar startCal = Calendar.getInstance();
			Calendar dueCal = Calendar.getInstance();
			startCal.setTime(startDate);
			dueCal.setTime(dueDate);
			long diff = dueCal.getTimeInMillis() - startCal.getTimeInMillis();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			sumToPay += rentals[i].getDvd().getPrice() * diffDays;
		}
		
		return sumToPay;
	}
}
