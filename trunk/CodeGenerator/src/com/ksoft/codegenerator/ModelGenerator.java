package com.ksoft.codegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;



public class ModelGenerator {

	
	public void generateTOFile(){
		
		System.out.println("File generation started");
		
		System.out.println("Class\t\t: " + Constants.CLASS_NAME);
		
		StringBuffer classString = new StringBuffer();
		
		classString.append("Ext.define('Ext.model." + Constants.CLASS_NAME + "', {\n\n");
		classString.append("\textend: 'Ext.data.Model',\n");
		classString.append("\tfields: [{\n");
		
		ArrayList<String> colList = DBUtils.getColumnList(Constants.TABLE_NAME);
		
		for(int i =0;i<colList.size();i++){
			
			if(i >0){
				classString.append("\t},{\n");
			}
			classString.append("\t\tname : '"+ getPropertyName(colList.get(i),false) +"',\n");
			classString.append("\t\ttype : 'string'\n");
		}
			
		
		
		classString.append("\t}]\n\n");
		
				
		classString.append("});\n\n");
		
		File file = new File("output/"+Constants.CLASS_NAME+".js");
		FileOutputStream out;
		
		try {
			
			out = new FileOutputStream(file);
			out.write(classString.toString().getBytes());
			out.close();
			
			System.out.println("File generated successfully. File Path : " + file.getAbsolutePath());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static String getPropertyName(String colName,boolean isMethod){		
			
		StringBuffer strPropertyName = new StringBuffer();
		
		for(int i=0; i<colName.length(); i++){			
			
			if(i==0 && isMethod){
				strPropertyName.append((colName.charAt(i)+"").toUpperCase());	
			}else{				
				if("_".equals(colName.charAt(i)+"")){
					i++;
					strPropertyName.append((colName.charAt(i)+"").toUpperCase());	
				}else{
					strPropertyName.append((colName.charAt(i)+"").toLowerCase());	
				}					
			}
		}		
		return strPropertyName.toString();		
	}
	
}
