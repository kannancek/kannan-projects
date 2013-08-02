package com.ksoft.fbase.web.admin;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;

import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.utils.DBUtils;

public class Initializer extends HttpServlet{

	private static final long serialVersionUID = 1L;	

	public void init(ServletConfig config) throws ServletException {
		
		initialiazeLogger(config);
		initialiazeDatasource(config);
		setTheme(config);
		super.init(config);
	}
	
	

	private void initialiazeLogger(ServletConfig config) throws ServletException {
		
		System.out.println("Initializing logger . . . ");
		
		String log4jLocation = config.getInitParameter("log4j-xml-path");

		ServletContext sc = config.getServletContext();
		if (log4jLocation == null) {
			System.err.println("No log4j-xml-path init param, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String webAppPath = sc.getRealPath("");
			String log4jProp = webAppPath + log4jLocation;			
			KsoftLogger.initialize(log4jProp);
			System.out.println("Initialized logger. ");
		
		}
		
	}
	
	private void initialiazeDatasource(ServletConfig config) throws ServletException {
		
		System.out.println("Initializing datasource . . .");
		
		String dataSourcePath = config.getInitParameter("datasource-path");

		ServletContext sc = config.getServletContext();

		if (dataSourcePath == null) {
			System.err.println("No datasource-path init param.");
			throw new ServletException("*** Error occured while initializing datasource");
			
		} else {
			String webAppPath = sc.getRealPath("");
			String dataSourceFile = webAppPath + dataSourcePath;	
			try{
				DBUtils.initialize(dataSourceFile);
			}catch (Exception e) {
				throw new ServletException("Error occured while initializing datasource");
			}
			
			System.out.println("Initialized datasource.");
		
		}
		
		
	}
	
	private void setTheme(ServletConfig config) throws ServletException {
		
		ServletContext sc = config.getServletContext();				
		String theme = config.getInitParameter("theme");
		sc.setAttribute("css", theme);	
		
	}

}
