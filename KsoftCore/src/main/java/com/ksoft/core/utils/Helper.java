package com.ksoft.core.utils;

public class Helper {

	public static boolean isNull(String strValue) {
		
		boolean result = false;
		
		if (null == strValue || "".equalsIgnoreCase(strValue.trim())) {
			result = true;
		}
		
		return result;

	}
	
	public static String checkNull(String strValue) {
		
				
		if (null == strValue || "".equalsIgnoreCase(strValue.trim())) {
			return "";
		}else{
			return strValue;
		}
		
		

	}

}
