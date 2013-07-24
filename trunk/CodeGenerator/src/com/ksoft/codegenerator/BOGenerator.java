package com.ksoft.codegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class BOGenerator {

	
	public void generateTOFile(){
		
		StringBuffer classString = new StringBuffer();
		String daoName = Constants.CLASS_NAME+"DAO";
		
		classString.append("package " + Constants.ROOT_PACKAGE + ".bo." + Constants.MODULE +";\n\n");
		
		classString.append("import com.ksoft.core.bo.BusinessDelegate;\n");
		classString.append("import com.ksoft.core.exception.InternalException;\n");		
		classString.append("import com.ksoft.core.to.TransferObject;\n\n");
		classString.append("public class " + Constants.CLASS_NAME + "BO extends BusinessDelegate{\n\n");
		
		//Create method
		classString.append("\t@Override\n");
		classString.append("\tpublic void create(TransferObject to,Connection conn) throws UIException,InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\tdao.create(to,conn);\n");
		classString.append("\t}\n\n");
		
		//Update method
		classString.append("\t@Override\n");
		classString.append("\tpublic void update(TransferObject to,Connection conn) throws UIException,InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\tdao.update(to,conn);\n");
		classString.append("\t}\n\n");
		
		//Delete method
		classString.append("\t@Override\n");
		classString.append("\tpublic void delete(TransferObject to,Connection conn) throws UIException,InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\tdao.delete(to,conn);\n");
		classString.append("\t}\n\n");
		
		//FindByKey method
		classString.append("\t@Override\n");
		classString.append("\tpublic TransferObject findByKey(TransferObject to,Connection conn) throws UIException,InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\treturn dao.findByKey(to,conn);\n");
		classString.append("\t}\n\n");
		
		//GetBatch method
		classString.append("\t@Override\n");
		classString.append("\tpublic ArrayList<TransferObject> getBatch(int start, int limit,Connection conn) throws InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\treturn dao.getBatch(start, last, conn);\n");
		classString.append("\t}\n\n");
		
		//GetCount method
		classString.append("\t@Override\n");
		classString.append("\tpublic int getCount(Connection conn) throws UIException,InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\treturn dao.getCount(conn);\n");
		classString.append("\t}\n");
		
		//IsExisting method
		classString.append("\t@Override\n");
		classString.append("\tpublic boolean isExisting(TransferObject to, Connection conn)throws UIException, InternalException {\n");		
		classString.append("\t\t"+daoName+" dao = new "+ daoName +"();\n");
		classString.append("\t\treturn dao.isExisting(to,conn);\n");
		classString.append("\t}\n\n");
		
		//IsExisting method
		classString.append("\t@Override\n");
		classString.append("\tpublic void validate(TransferObject to, Connection conn)throws UIException, InternalException {\n");		
		classString.append("\t\t// TODO Auto-generated method stub\n");
		classString.append("\t}\n\n");
		
		classString.append("}\n\n");
		
		
			
		File file = new File("output/"+Constants.CLASS_NAME+"BO.java");
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
	
}
