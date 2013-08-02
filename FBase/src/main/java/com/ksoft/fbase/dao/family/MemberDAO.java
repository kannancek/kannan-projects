package com.ksoft.fbase.dao.family;

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
import com.ksoft.core.utils.DateLibrary;
import com.ksoft.fbase.dao.DataAccessObject;
import com.ksoft.fbase.to.family.MemberTO;
import com.ksoft.fbase.utils.SequeneGenerator;

public class MemberDAO extends DataAccessObject{

	@Override
	public void create(TransferObject transObj,Connection conn) throws InternalException {

		MemberTO to = (MemberTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"MEMBER_ID","FAMILY_ID","NAME","DOB","SEX","BLOOD_GROUP","MARITAL_STATUS","CONTACT_NO","EMAIL","QUALIFICATION","OCCUPATION","ANNUAL_INCOME","FATHER_NAME","MOTHER_NAME","SPOUSE_NAME","RELATION","FAMILY_HEAD_YN","DISABLED_YN","VOTER_ID","AADHAR_ID","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getInsertQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			
			to.setMemberId(getMemberyID());
			
			pStmt.setString(1, to.getMemberId());
			pStmt.setString(2, to.getFamilyId());
			pStmt.setString(3, to.getName());
			pStmt.setDate(4, DateLibrary.toSqlDate(to.getDob()));
			pStmt.setString(5, to.getSex());
			pStmt.setString(6, to.getBloodGroup());
			pStmt.setString(7, to.getMaritalStatus());
			pStmt.setString(8, to.getContactNo());
			pStmt.setString(9, to.getEmail());
			pStmt.setString(10, to.getQualification());
			pStmt.setString(11, to.getOccupation());
			pStmt.setString(12, to.getAnnualIncome());
			pStmt.setString(13, to.getFatherName());
			pStmt.setString(14, to.getMotherName());
			pStmt.setString(15, to.getSpouseName());
			pStmt.setString(16, to.getRelation());
			pStmt.setString(17, to.getFamilyHeadYn());
			pStmt.setString(18, to.getDisabledYn());
			pStmt.setString(19, to.getVoterId());
			pStmt.setString(20, to.getAadharId());
			pStmt.setString(21, "A");
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

		MemberTO to = (MemberTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"FAMILY_ID","NAME","DOB","SEX","BLOOD_GROUP","MARITAL_STATUS","CONTACT_NO","EMAIL","QUALIFICATION","OCCUPATION","ANNUAL_INCOME","FATHER_NAME","MOTHER_NAME","SPOUSE_NAME","RELATION","FAMILY_HEAD_YN","DISABLED_YN","VOTER_ID","AADHAR_ID"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND MEMBER_ID = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getFamilyId());
			pStmt.setString(2, to.getName());
			pStmt.setDate(3, DateLibrary.toSqlDate(to.getDob()));
			pStmt.setString(4, to.getSex());
			pStmt.setString(5, to.getBloodGroup());
			pStmt.setString(6, to.getMaritalStatus());
			pStmt.setString(7, to.getContactNo());
			pStmt.setString(8, to.getEmail());
			pStmt.setString(9, to.getQualification());
			pStmt.setString(10, to.getOccupation());
			pStmt.setString(11, to.getAnnualIncome());
			pStmt.setString(12, to.getFatherName());
			pStmt.setString(13, to.getMotherName());
			pStmt.setString(14, to.getSpouseName());
			pStmt.setString(15, to.getRelation());
			pStmt.setString(16, to.getFamilyHeadYn());
			pStmt.setString(17, to.getDisabledYn());
			pStmt.setString(18, to.getVoterId());
			pStmt.setString(19, to.getAadharId());
			pStmt.setString(20, to.getMemberId());
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

		MemberTO to = (MemberTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND MEMBER_ID = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, "R");
			pStmt.setString(2, to.getMemberId());
			pStmt.executeUpdate();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while updation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public TransferObject findByKey(TransferObject transObj) throws InternalException {

		MemberTO to = (MemberTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"MEMBER_ID","FAMILY_ID","NAME","DOB","SEX","BLOOD_GROUP","MARITAL_STATUS","CONTACT_NO","EMAIL","QUALIFICATION","OCCUPATION","ANNUAL_INCOME","FATHER_NAME","MOTHER_NAME","SPOUSE_NAME","RELATION","FAMILY_HEAD_YN","DISABLED_YN","VOTER_ID","AADHAR_ID","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND MEMBER_ID = ? ");
		String strQuery	=	sqlBuilder.getSelectQuery();

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getMemberId());
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to.setMemberId(rs.getString("MEMBER_ID"));
				to.setFamilyId(rs.getString("FAMILY_ID"));
				to.setName(rs.getString("NAME"));
				to.setDob(rs.getString("DOB"));
				to.setSex(rs.getString("SEX"));
				to.setBloodGroup(rs.getString("BLOOD_GROUP"));
				to.setMaritalStatus(rs.getString("MARITAL_STATUS"));
				to.setContactNo(rs.getString("CONTACT_NO"));
				to.setEmail(rs.getString("EMAIL"));
				to.setQualification(rs.getString("QUALIFICATION"));
				to.setOccupation(rs.getString("OCCUPATION"));
				to.setAnnualIncome(rs.getString("ANNUAL_INCOME"));
				to.setFatherName(rs.getString("FATHER_NAME"));
				to.setMotherName(rs.getString("MOTHER_NAME"));
				to.setSpouseName(rs.getString("SPOUSE_NAME"));
				to.setRelation(rs.getString("RELATION"));
				to.setFamilyHeadYn(rs.getString("FAMILY_HEAD_YN"));
				to.setDisabledYn(rs.getString("DISABLED_YN"));
				to.setVoterId(rs.getString("VOTER_ID"));
				to.setAadharId(rs.getString("AADHAR_ID"));
				to.setStatus(rs.getString("STATUS"));

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatment(pStmt);
			DBUtils.closeConnection(conn);
		}
		return to;
	}

	public ArrayList<MemberTO> getBatch(int start, int limit) throws InternalException {

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"MEMBER_ID","FAMILY_ID","NAME","DOB","SEX","BLOOD_GROUP","MARITAL_STATUS","CONTACT_NO","EMAIL","QUALIFICATION","OCCUPATION","ANNUAL_INCOME","FATHER_NAME","MOTHER_NAME","SPOUSE_NAME","RELATION","FAMILY_HEAD_YN","DISABLED_YN","VOTER_ID","AADHAR_ID","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND STATUS = 'A' ");
		String strQuery	=	sqlBuilder.getSelectQuery();
		ArrayList<MemberTO> alist	=	new ArrayList<MemberTO>();
		MemberTO to;
		Connection conn = null;
		try {
			conn =  DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to = new MemberTO();

				to.setMemberId(rs.getString("MEMBER_ID"));
				to.setFamilyId(rs.getString("FAMILY_ID"));
				to.setName(rs.getString("NAME"));
				to.setDob(DateLibrary.toBaseDate(rs.getDate("DOB")));
				to.setSex(rs.getString("SEX"));
				to.setBloodGroup(rs.getString("BLOOD_GROUP"));
				to.setMaritalStatus(rs.getString("MARITAL_STATUS"));
				to.setContactNo(rs.getString("CONTACT_NO"));
				to.setEmail(rs.getString("EMAIL"));
				to.setQualification(rs.getString("QUALIFICATION"));
				to.setOccupation(rs.getString("OCCUPATION"));
				to.setAnnualIncome(rs.getString("ANNUAL_INCOME"));
				to.setFatherName(rs.getString("FATHER_NAME"));
				to.setMotherName(rs.getString("MOTHER_NAME"));
				to.setSpouseName(rs.getString("SPOUSE_NAME"));
				to.setRelation(rs.getString("RELATION"));
				to.setFamilyHeadYn(rs.getString("FAMILY_HEAD_YN"));
				to.setDisabledYn(rs.getString("DISABLED_YN"));
				to.setVoterId(rs.getString("VOTER_ID"));
				to.setAadharId(rs.getString("AADHAR_ID"));
				to.setStatus(rs.getString("STATUS"));
				alist.add(to);


			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatment(pStmt);
			DBUtils.closeConnection(conn);
		}
		return alist;
	}

	@Override
	public int getCount() throws InternalException{ 

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
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
			DBUtils.closeStatment(pStmt);
			DBUtils.closeConnection(conn);
		}
		return count;
	}

	@Override
	public boolean isExisting(TransferObject transObj) throws InternalException {

		boolean result = false;
		/*MemberTO to = (MemberTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		String strQuery	=	"SELECT 1 FROM ZIPCODES WHERE MEMBER_ID = ? ";

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getMemberId());
			rs		=	pStmt.executeQuery();
			if(rs.next()){

				result = true;

			}
		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while fetching data.");

		}finally{
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatment(pStmt);
			DBUtils.closeConnection(conn);
		}*/
		return result;
	}


	public String getMemberyID() throws InternalException{
		
		String strId = SequeneGenerator.getSequence("MEMBER_ID");
		
		return strId;
	}
}