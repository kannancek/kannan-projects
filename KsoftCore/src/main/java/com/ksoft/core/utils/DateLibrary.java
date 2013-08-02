package com.ksoft.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateLibrary {
	
	private static String SERVER_DATE_FORMAT = "dd/MM/yyyy";
	private static String CLIENT_DATE_FORMAT = "dd/MM/yyyy";
	
	
	public static java.sql.Date toSqlDate(String strDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat(CLIENT_DATE_FORMAT);		
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		try {
			utilDate = (java.util.Date)sdf.parse(strDate);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			
		}
		
	    return sqlDate;
		
	}
	
	public static String toBaseDate(java.sql.Date date){
		
		String strDate = "";
		
		if(null != date){
			
			SimpleDateFormat sdf = new SimpleDateFormat(CLIENT_DATE_FORMAT);		
			java.util.Date utilDate = null;
			
			
			utilDate = new java.util.Date(date.getTime());			
			strDate = sdf.format(utilDate);
		}
		
		
	    return strDate;
		
	}

}
