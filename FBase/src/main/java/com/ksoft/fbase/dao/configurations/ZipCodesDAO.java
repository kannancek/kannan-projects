package com.ksoft.fbase.dao.configurations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.CRUDHelper;
import com.ksoft.core.utils.DBUtils;
import com.ksoft.fbase.dao.DataAccessObject;
import com.ksoft.fbase.to.configurations.ZipCodesTO;

public class ZipCodesDAO extends DataAccessObject{

	@Override
	public void create(TransferObject transObj,Connection conn) throws InternalException {

		ZipCodesTO to = (ZipCodesTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		String[] arrFields = {"ZIP_CODE","STATE","DISTRICT","COUNTRY","POST"};
		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getInsertQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getZipCode());
			pStmt.setString(2, to.getState());
			pStmt.setString(3, to.getDistrict());
			pStmt.setString(4, to.getCountry());
			pStmt.setString(5, to.getPost());
			pStmt.execute();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while creation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public void update(TransferObject transObj,Connection conn) throws InternalException {

		ZipCodesTO to = (ZipCodesTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		String[] arrFields = {"ZIP_CODE","STATE","DISTRICT","COUNTRY","POST"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND ZIP_CODE = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getZipCode());
			pStmt.setString(2, to.getState());
			pStmt.setString(3, to.getDistrict());
			pStmt.setString(4, to.getCountry());
			pStmt.setString(5, to.getPost());
			pStmt.setString(6, to.getZipCode());
			pStmt.execute();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while updation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public void delete(TransferObject transObj,Connection conn) throws InternalException {

		ZipCodesTO to = (ZipCodesTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		sqlBuilder.setWhereClause(" AND ZIP_CODE = ? ");
		String strQuery	=	sqlBuilder.getDeleteQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getZipCode());
			pStmt.execute();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while deletion.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public TransferObject findByKey(TransferObject transObj) throws InternalException {

		ZipCodesTO to = (ZipCodesTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		String[] arrFields = {"ZIP_CODE","STATE","DISTRICT","COUNTRY","POST"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND ZIP_CODE = ? ");
		String strQuery	=	sqlBuilder.getSelectQuery();
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getZipCode());
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to.setZipCode(rs.getString("ZIP_CODE"));
				to.setState(rs.getString("STATE"));
				to.setDistrict(rs.getString("DISTRICT"));
				to.setCountry(rs.getString("COUNTRY"));
				to.setPost(rs.getString("POST"));

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeConnection(conn);
		}
		return to;
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int last) throws InternalException {

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		String[] arrFields = {"ZIP_CODE","STATE","DISTRICT","COUNTRY","POST"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setLimit(start, last);
		String strQuery	=	sqlBuilder.getSelectQuery();
		ArrayList<TransferObject> alist	=	new ArrayList<TransferObject>();
		ZipCodesTO to;

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to = new ZipCodesTO();

				to.setZipCode(rs.getString("ZIP_CODE"));
				to.setState(rs.getString("STATE"));
				to.setDistrict(rs.getString("DISTRICT"));
				to.setCountry(rs.getString("COUNTRY"));
				to.setPost(rs.getString("POST"));
				alist.add(to);


			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeConnection(conn);
		}
		return alist;
	}

	@Override
	public int getCount() throws InternalException{ 

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("ZIPCODES");
		String[] arrFields = {"COUNT(*) AS COUNT"};		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getSelectQuery();
		int count = 0;

		Connection conn = null;
		try {
			
			conn = DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				count = Integer.parseInt(rs.getString("COUNT"));

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeConnection(conn);
		}
		return count;
	}
	
	
	@Override
	public boolean isExisting(TransferObject transObj) throws InternalException {

		boolean result = false;
		ZipCodesTO to = (ZipCodesTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		String strQuery	=	"SELECT 1 FROM ZIPCODES WHERE ZIP_CODE = ? ";

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			
			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getZipCode());
			rs		=	pStmt.executeQuery();
			if(rs.next()){

				result = true;

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeConnection(conn);
		}
		return result;
	}


}