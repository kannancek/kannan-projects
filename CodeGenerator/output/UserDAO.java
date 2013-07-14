package com.ksoft.fbase.dao.admin;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.to.TransferObject;

public class UserDAO extends DataAccessObject{

	@Override
	public void create(TransferObject transObj,Connection conn) throws InternalException {

		UserTO to = (UserTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		String[] arrFields = {"USERNAME","PASSWORD","NAME","ROLE","STATUS","CREATION_DATE"};
		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getInsertQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getUsername());
			pStmt.setString(2, to.getPassword());
			pStmt.setString(3, to.getName());
			pStmt.setString(4, to.getRole());
			pStmt.setString(5, to.getStatus());
			pStmt.setString(6, to.getCreationDate());
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

		UserTO to = (UserTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		String[] arrFields = {"USERNAME","PASSWORD","NAME","ROLE","STATUS","CREATION_DATE"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND USERNAME = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getUsername());
			pStmt.setString(2, to.getPassword());
			pStmt.setString(3, to.getName());
			pStmt.setString(4, to.getRole());
			pStmt.setString(5, to.getStatus());
			pStmt.setString(6, to.getCreationDate());
			pStmt.setString(7, to.getUsername());
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

		UserTO to = (UserTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		sqlBuilder.setWhereClause(" AND USERNAME = ? ");
		String strQuery	=	sqlBuilder.getDeleteQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getUsername());
			pStmt.execute();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while deletion.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public TransferObject findByKey(TransferObject transObj,Connection conn) throws InternalException {

		UserTO to = (UserTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		String[] arrFields = {"USERNAME","PASSWORD","NAME","ROLE","STATUS","CREATION_DATE"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND USERNAME = ? ");
		String strQuery	=	sqlBuilder.getSelectQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getUsername());
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to.setUsername(rs.getString("USERNAME"));
				to.setPassword(rs.getString("PASSWORD"));
				to.setName(rs.getString("NAME"));
				to.setRole(rs.getString("ROLE"));
				to.setStatus(rs.getString("STATUS"));
				to.setCreationDate(rs.getString("CREATION_DATE"));

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
		}
		return to;
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int last,Connection conn) throws InternalException {

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		String[] arrFields = {"USERNAME","PASSWORD","NAME","ROLE","STATUS","CREATION_DATE"};
		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getSelectQuery();
		ArrayList<TransferObject> alist	=	new ArrayList<TransferObject>();
		UserTO to;

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to = new UserTO();

				to.setUsername(rs.getString("USERNAME"));
				to.setPassword(rs.getString("PASSWORD"));
				to.setName(rs.getString("NAME"));
				to.setRole(rs.getString("ROLE"));
				to.setStatus(rs.getString("STATUS"));
				to.setCreationDate(rs.getString("CREATION_DATE"));
				alist.add(to);


			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
		}
		return alist;
	}

	@Override
	public int getCount(Connection conn) throws InternalException{ 

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("USER_DETAILS");
		String[] arrFields = {"COUNT(*) AS COUNT"};		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getSelectQuery();
		int count = 0;

		try {

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
		}
		return count;
	}


}