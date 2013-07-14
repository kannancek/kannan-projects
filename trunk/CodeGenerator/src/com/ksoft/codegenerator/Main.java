package com.ksoft.codegenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Properties config = new Properties();
		
		try {
			
			config.load(new FileInputStream("./config.properties"));	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Constants.CLASS_NAME = config.getProperty("className");
		Constants.TABLE_NAME = config.getProperty("tableName");
		Constants.KEY_FIELD = config.getProperty("keyField");
		Constants.ROOT_PACKAGE = config.getProperty("rootPackage");
		Constants.MODULE = config.getProperty("module");
		
		
		new BOGenerator().generateTOFile();
		
		new TOGenerator().generateTOFile();		
				
		new DAOGenerator().generateTOFile();
		
		new ModelGenerator().generateTOFile();
		
	}

}
