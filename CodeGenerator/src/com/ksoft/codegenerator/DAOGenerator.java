package com.ksoft.codegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;



public class DAOGenerator {

	
	public void generateTOFile(){
		
		
		
		String toName = Constants.CLASS_NAME +"TO";
		StringBuffer classString = new StringBuffer();
		ArrayList<String> colList = DBUtils.getColumnList(Constants.TABLE_NAME);
		
		classString.append("package " + Constants.ROOT_PACKAGE + ".dao." + Constants.MODULE +";\n\n");
		
		classString.append("import com.ksoft.core.exception.InternalException;\n");		
		classString.append("import com.ksoft.core.to.TransferObject;\n\n");
		classString.append("public class " + Constants.CLASS_NAME + "DAO extends DataAccessObject{\n\n");
		
		classString.append(getCreateMethod(colList, Constants.TABLE_NAME, toName));
		classString.append(getUpdateMethod(colList, Constants.TABLE_NAME, toName,Constants.KEY_FIELD));
		classString.append(getDeleteMethod(colList, Constants.TABLE_NAME, toName,Constants.KEY_FIELD));
		classString.append(getFindByKeyMethod(colList, Constants.TABLE_NAME, toName,Constants.KEY_FIELD));
		classString.append(getBatchMethod(colList, Constants.TABLE_NAME, toName,Constants.KEY_FIELD));
		classString.append(getCountMethod(Constants.TABLE_NAME));
		classString.append(getIsExistingMethod(colList, Constants.TABLE_NAME, toName,Constants.KEY_FIELD));
		classString.append("\n}");
			
		
			
		File file = new File("output/"+Constants.CLASS_NAME+"DAO.java");
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
	
	
	public String getCreateMethod(ArrayList<String> colList, String tableName, String toName){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic void create(TransferObject transObj,Connection conn) throws InternalException {\n\n");		
		methodString.append("\t\t"+ toName +" to = ("+ toName +")transObj;\n");
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tString[] arrFields = {");
		
		for(int i =0; i<colList.size();i++){
			if(i != 0)
				methodString.append(",");
			methodString.append("\"" + colList.get(i) +"\"");
		}
		
		methodString.append("};\n");		
		methodString.append("\t\tsqlBuilder.setArrFields(arrFields);\n");
		methodString.append("\t\tString strQuery	=	sqlBuilder.getInsertQuery();\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		
		for(int j =0; j<colList.size();j++){
			
			methodString.append("\t\t\tpStmt.setString("+ (j+1) +", to.get"+ TOGenerator.getPropertyName(colList.get(j),true) +"());\n");
			
		}
		
		methodString.append("\t\t\tpStmt.execute();\n\n");
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while creation.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeStatment(pStmt);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}
	
	public String getUpdateMethod(ArrayList<String> colList, String tableName, String toName, String key){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic void update(TransferObject transObj,Connection conn) throws InternalException {\n\n");		
		methodString.append("\t\t"+ toName +" to = ("+ toName +")transObj;\n");
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tString[] arrFields = {");
		
		for(int i =0; i<colList.size();i++){
			if(i != 0)
				methodString.append(",");
			methodString.append("\"" + colList.get(i) +"\"");
		}
		
		methodString.append("};\n");		
		methodString.append("\t\tsqlBuilder.setArrFields(arrFields);\n");
		methodString.append("\t\tsqlBuilder.setWhereClause(\" AND "+ key +" = ? \");\n");
		
		methodString.append("\t\tString strQuery	=	sqlBuilder.getUpdateQuery();\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		
		int j =0;
		for(j =0; j<colList.size();j++){
			
			methodString.append("\t\t\tpStmt.setString("+ (j+1) +", to.get"+ TOGenerator.getPropertyName(colList.get(j),true) +"());\n");
			
		}
		methodString.append("\t\t\tpStmt.setString("+ (j+1) +", to.get"+ TOGenerator.getPropertyName(key,true) +"());\n");
		methodString.append("\t\t\tpStmt.execute();\n\n");
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while updation.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeStatment(pStmt);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}
	
	public String getDeleteMethod(ArrayList<String> colList, String tableName, String toName, String key){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic void delete(TransferObject transObj,Connection conn) throws InternalException {\n\n");		
		methodString.append("\t\t"+ toName +" to = ("+ toName +")transObj;\n");
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tsqlBuilder.setWhereClause(\" AND "+ key +" = ? \");\n");		
		methodString.append("\t\tString strQuery	=	sqlBuilder.getDeleteQuery();\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		methodString.append("\t\t\tpStmt.setString(1, to.get"+ TOGenerator.getPropertyName(key,true) +"());\n");
		methodString.append("\t\t\tpStmt.execute();\n\n");
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while deletion.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeStatment(pStmt);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}
	
	public String getFindByKeyMethod(ArrayList<String> colList, String tableName, String toName, String key){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic TransferObject findByKey(TransferObject transObj,Connection conn) throws InternalException {\n\n");		
		methodString.append("\t\t"+ toName +" to = ("+ toName +")transObj;\n");
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tResultSet rs = null;\n");		
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tString[] arrFields = {");
		
		for(int i =0; i<colList.size();i++){
			if(i != 0)
				methodString.append(",");
			methodString.append("\"" + colList.get(i) +"\"");
		}
		
		methodString.append("};\n");		
		methodString.append("\t\tsqlBuilder.setArrFields(arrFields);\n");
		methodString.append("\t\tsqlBuilder.setWhereClause(\" AND "+ key +" = ? \");\n");
		
		methodString.append("\t\tString strQuery	=	sqlBuilder.getSelectQuery();\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		methodString.append("\t\t\tpStmt.setString(1, to.get"+ TOGenerator.getPropertyName(key,true) +"());\n");
		methodString.append("\t\t\trs		=	pStmt.executeQuery();\n");
		methodString.append("\t\t\twhile(rs.next()){\n\n");
		
		int j =0;
		for(j =0; j<colList.size();j++){
			
			methodString.append("\t\t\t\tto.set"+ TOGenerator.getPropertyName(colList.get(j),true) +"(rs.getString(\"" + colList.get(j) +"\"));\n");
			
		}
		methodString.append("\n\t\t\t}\n");
		
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while fetching data.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeResultSet(rs);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t\treturn to;\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}	
	
	
	public String getBatchMethod(ArrayList<String> colList, String tableName, String toName, String key){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic ArrayList<TransferObject> getBatch(int start, int limit,Connection conn) throws InternalException {\n\n");			
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tResultSet rs = null;\n");		
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tString[] arrFields = {");
		
		for(int i =0; i<colList.size();i++){
			if(i != 0)
				methodString.append(",");
			methodString.append("\"" + colList.get(i) +"\"");
		}
		
		methodString.append("};\n");		
		methodString.append("\t\tsqlBuilder.setArrFields(arrFields);\n");
		methodString.append("\t\tString strQuery	=	sqlBuilder.getSelectQuery();\n");
		methodString.append("\t\tArrayList<TransferObject> alist	=	new ArrayList<TransferObject>();\n");
		methodString.append("\t\t"+ toName +" to;\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		methodString.append("\t\t\trs		=	pStmt.executeQuery();\n");
		methodString.append("\t\t\twhile(rs.next()){\n\n");
		methodString.append("\t\t\t\tto = new "+ toName +"();\n\n");
		
		int j =0;
		for(j =0; j<colList.size();j++){
			
			methodString.append("\t\t\t\tto.set"+ TOGenerator.getPropertyName(colList.get(j),true) +"(rs.getString(\"" + colList.get(j) +"\"));\n");
			
		}
		methodString.append("\t\t\t\talist.add(to);\n\n");
		methodString.append("\n\t\t\t}\n");
		
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while fetching data.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeResultSet(rs);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t\treturn alist;\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}	
	
	public String getCountMethod(String tableName){
		
		StringBuffer methodString = new StringBuffer();
		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic int getCount(Connection conn) throws InternalException{ \n\n");			
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tResultSet rs = null;\n");		
		methodString.append("\t\tCRUDHelper sqlBuilder = new CRUDHelper();\n");
		methodString.append("\t\tsqlBuilder.setTableName(\""+ tableName +"\");\n");		
		methodString.append("\t\tString[] arrFields = {\"COUNT(*) AS COUNT\"};");
				
		methodString.append("\t\tsqlBuilder.setArrFields(arrFields);\n");
		methodString.append("\t\tString strQuery	=	sqlBuilder.getSelectQuery();\n");
		methodString.append("\t\tint count = 0;\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		methodString.append("\t\t\trs		=	pStmt.executeQuery();\n");
		methodString.append("\t\t\twhile(rs.next()){\n\n");
		
		methodString.append("\t\t\t\tcount = Integer.parseInt(rs.getString(\"COUNT\"));\n");
		methodString.append("\n\t\t\t}\n");
		
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while fetching data.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeResultSet(rs);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t\treturn count;\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}	
	
	public String getIsExistingMethod(ArrayList<String> colList, String tableName, String toName, String key){
		
		StringBuffer methodString = new StringBuffer();		
		methodString.append("\t@Override\n");
		methodString.append("\tpublic boolean isExisting(TransferObject transObj,Connection conn) throws InternalException {\n\n");		
		methodString.append("\t\tboolean result = false;\n");
		methodString.append("\t\t"+ toName +" to = ("+ toName +")transObj;\n");
		methodString.append("\t\tPreparedStatement pStmt	=	null;\n");
		methodString.append("\t\tResultSet rs = null;\n");		
		methodString.append("\t\tString strQuery	=	\"SELECT 1 FROM ZIPCODES WHERE "+ key +" = ? \";\n");
		methodString.append("\n\t\ttry {\n\n");
		methodString.append("\t\t\tpStmt	=	 conn.prepareStatement(strQuery);\n");
		methodString.append("\t\t\tpStmt.setString(1, to.get"+ TOGenerator.getPropertyName(key,true) +"());\n");
		methodString.append("\t\t\trs		=	pStmt.executeQuery();\n");
		methodString.append("\t\t\tif(rs.next()){\n\n");			
		methodString.append("\t\t\t\tresult = true;\n");			
		
		methodString.append("\n\t\t\t}\n");
		
		methodString.append("\t\t}catch (SQLException e) {\n\n");
		methodString.append("\t\t\tKsoftLogger.getLogger().error(e.getMessage());\n");
		methodString.append("\t\t\tthrow new InternalException (100,\"Error occurred while fetching data.\");\n\n");
		methodString.append("\t\t}finally{\n");
		methodString.append("\t\t\tDBUtils.closeResultSet(rs);\n");		
		methodString.append("\t\t}\n");
		methodString.append("\t\treturn result;\n");
		methodString.append("\t}\n\n");
		
		return methodString.toString();
		
	}	
}
