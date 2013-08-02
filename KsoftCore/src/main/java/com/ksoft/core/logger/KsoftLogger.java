package com.ksoft.core.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class KsoftLogger {

	//private static KsoftLogger me = null;
	private static Logger logger = null;

	private KsoftLogger() {

	}
/*
	public static KsoftLogger getInstance() {
		
		if (null == me) {
			me = new KsoftLogger();
		}
		return me;
	}*/
	
	public static void initialize(String strConfigFile){
		DOMConfigurator.configure(strConfigFile);	
		logger = Logger.getLogger(KsoftLogger.class);
	}

	public static Logger getLogger() {
		
		return logger;
	}
}
