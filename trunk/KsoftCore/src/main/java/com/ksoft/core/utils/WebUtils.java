package com.ksoft.core.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ksoft.core.logger.KsoftLogger;

public class WebUtils {

	
	public static void writeResponse(HttpServletResponse response, String str){
		
		try {
			
			response.getWriter().write(str);
			response.getWriter().close();
			
		} catch (IOException e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
		}
		
	}
}
