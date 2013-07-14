package com.ksoft.codegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;



public class TOGenerator {

	
	public void generateTOFile(){
		
		System.out.println("File generation started");
		
		System.out.println("Class\t\t: " + Constants.CLASS_NAME);
		System.out.println("Table Name\t: " + Constants.TABLE_NAME);
		
		StringBuffer classString = new StringBuffer();
		classString.append("package " + Constants.ROOT_PACKAGE + ".to." + Constants.MODULE +";\n\n");
		
		classString.append("import javax.servlet.http.HttpServletRequest;\n");
		classString.append("import com.ksoft.core.to.TransferObject;\n\n");
		classString.append("public class " + Constants.CLASS_NAME + "TO extends TransferObject{\n\n");
		classString.append("");
		
		ArrayList<String> colList = DBUtils.getColumnList(Constants.TABLE_NAME);
		System.out.println("Table Columns\t: " + colList);
		
		for(String colName :  colList){			
			classString.append("\tprivate String "+ getPropertyName(colName,false) +"\t\t=\tnull;\n");
		}
		
		classString.append("\n\n");
		
		for(String colName :  colList){		
			
			String propertyName = getPropertyName(colName,false);
			String methodName = getPropertyName(colName,true);
			
			classString.append("\tpublic String get"+ methodName + "(){\n");
			classString.append("\t\treturn "+ propertyName + ";\n");
			classString.append("\t}\n\n");
			
			classString.append("\tpublic void set"+ methodName + "(String "+ propertyName + "){\n");
			classString.append("\t\tthis."+ propertyName + " = "+ propertyName +";\n");
			classString.append("\t}\n\n");
		}
		
		classString.append("\tpublic void populate(HttpServletRequest request){\n\n");
		for(String colName :  colList){	
			String propertyName = getPropertyName(colName,false);
			String methodName = getPropertyName(colName,true);
			classString.append("\t\tset"+ methodName  +"(request.getParameter(\""+ propertyName +"\"));\n");
		}		
		classString.append("\n\t}\n\n");		
		classString.append("}\n\n");
		
		File file = new File("output/"+Constants.CLASS_NAME+"TO.java");
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
