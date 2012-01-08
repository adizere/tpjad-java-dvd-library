package com.dvdworld.business;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
