package com.ksoft.fbase.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.utils.CRUDHelper;
import com.ksoft.core.utils.DBUtils;

public class SequeneGenerator {
	
	
	public static String getSequence(String sequenceName) throws InternalException{
		
		StringBuffer sequence = new StringBuffer();
		
		PreparedStatement pStmt	=	null;
		PreparedStatement pStmtUpdate	=	null;
		
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("SEQUENCE_TABLE");

		String[] arrFields = {"ALPHA_NUM_YN","ALPHA_PART","NUMERIC_PART"};	
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND SEQ_NAME = ?");
		String strQuery	=	sqlBuilder.getSelectQuery();
		String strUpdateQuery	=	"UPDATE SEQUENCE_TABLE SET NUMERIC_PART = ? WHERE SEQ_NAME = ? ";
		
		String strALPHA_NUM_YN = null;
		String strALPHA_PART = null;
		String strNUMERIC_PART = null;
		
		int numericPart = 0;
		
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmtUpdate = conn.prepareStatement(strUpdateQuery);
			
			pStmt.setString(1, sequenceName);
			
			rs		=	pStmt.executeQuery();
			if(rs.next()){

				strALPHA_NUM_YN = rs.getString("ALPHA_NUM_YN");
				strALPHA_PART = rs.getString("ALPHA_PART");
				strNUMERIC_PART = rs.getString("NUMERIC_PART");
				
				if("Y".equalsIgnoreCase(strALPHA_NUM_YN)){
					sequence.append(strALPHA_PART);
				}
				sequence.append(strNUMERIC_PART);
				
				numericPart =  Integer.parseInt(strNUMERIC_PART);
				numericPart++;
				
				pStmtUpdate.setInt(1, numericPart);
				pStmtUpdate.setString(2, sequenceName);
				pStmtUpdate.execute();
				

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeStatment(pStmtUpdate);
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatment(pStmt);
			DBUtils.closeConnection(conn);
		}
		
		return sequence.toString();
		
	}
	
	
	

}
