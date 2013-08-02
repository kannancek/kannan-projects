package com.ksoft.fbase.dao.family;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.CRUDHelper;
import com.ksoft.core.utils.DBUtils;
import com.ksoft.core.utils.DateLibrary;
import com.ksoft.core.utils.Helper;
import com.ksoft.fbase.bo.family.MemberBO;
import com.ksoft.fbase.dao.DataAccessObject;
import com.ksoft.fbase.to.family.FamilyTO;
import com.ksoft.fbase.to.family.MemberTO;
import com.ksoft.fbase.utils.SequeneGenerator;

public class FamilyDAO extends DataAccessObject{

	@Override
	public void create(TransferObject transObj,Connection conn) throws InternalException {

		FamilyTO to = (FamilyTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY");
		String[] arrFields = {"FAMILY_ID","FAMILY_NAME","ADDRESS_LINE1","ADDRESS_LINE2","ADDRESS_LINE3","STATE","DISTRICT","COUNTRY","ZIP_CODE","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		String strQuery	=	sqlBuilder.getInsertQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			
			to.setFamilyId(getFamilyID());
			
			pStmt.setString(1, to.getFamilyId());
			pStmt.setString(2, to.getFamilyName());
			pStmt.setString(3, to.getAddressLine1());
			pStmt.setString(4, to.getAddressLine2());
			pStmt.setString(5, to.getAddressLine3());
			pStmt.setString(6, to.getState());
			pStmt.setString(7, to.getDistrict());
			pStmt.setString(8, to.getCountry());
			pStmt.setString(9, to.getZipCode());
			pStmt.setString(10, "A");
			pStmt.execute();
			

			ArrayList<MemberTO> membersList = to.getMembersList();
			for(MemberTO memberTO : membersList){
				memberTO.setFamilyId(to.getFamilyId());
				createMembers(memberTO,conn);
			}

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while creation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}
	
		@Override
	public void update(TransferObject transObj,Connection conn) throws InternalException {

		FamilyTO to = (FamilyTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY");
		String[] arrFields = {"FAMILY_NAME","ADDRESS_LINE1","ADDRESS_LINE2","ADDRESS_LINE3","STATE","DISTRICT","COUNTRY","ZIP_CODE"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND FAMILY_ID = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		int result = 0;
		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getFamilyName());
			pStmt.setString(2, to.getAddressLine1());
			pStmt.setString(3, to.getAddressLine2());
			pStmt.setString(4, to.getAddressLine3());
			pStmt.setString(5, to.getState());
			pStmt.setString(6, to.getDistrict());
			pStmt.setString(7, to.getCountry());
			pStmt.setString(8, to.getZipCode());
			pStmt.setString(9, to.getFamilyId());
			
			result = pStmt.executeUpdate();
			
			if(result > 0){
				ArrayList<MemberTO> membersList = to.getMembersList();
				
				for(MemberTO memberTO : membersList){
					
					if(Helper.isNull(memberTO.getMemberId())){
						
						memberTO.setFamilyId(to.getFamilyId());
						createMembers(memberTO,conn);
						
					}else{
						updateMembers(memberTO,conn);
					}
					
				}
				
			}

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while updation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public void delete(TransferObject transObj,Connection conn) throws InternalException {

		FamilyTO to = (FamilyTO)transObj;
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY");
		String[] arrFields = {"STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND FAMILY_ID = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, "R");
			pStmt.setString(2, to.getFamilyId());
			pStmt.executeUpdate();
			
			deleteMembers(to.getFamilyId(), conn);

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while updation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}

	@Override
	public TransferObject findByKey(TransferObject transObj) throws InternalException {

		FamilyTO to = (FamilyTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY");
		String[] arrFields = {"FAMILY_ID","FAMILY_NAME","ADDRESS_LINE1","ADDRESS_LINE2","ADDRESS_LINE3","STATE","DISTRICT","COUNTRY","ZIP_CODE","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND FAMILY_ID = ? ");
		String strQuery	=	sqlBuilder.getSelectQuery();

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getFamilyId());
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to.setFamilyId(rs.getString("FAMILY_ID"));
				to.setFamilyName(rs.getString("FAMILY_NAME"));
				to.setAddressLine1(rs.getString("ADDRESS_LINE1"));
				to.setAddressLine2(rs.getString("ADDRESS_LINE2"));
				to.setAddressLine3(rs.getString("ADDRESS_LINE3"));
				to.setState(rs.getString("STATE"));
				to.setDistrict(rs.getString("DISTRICT"));
				to.setCountry(rs.getString("COUNTRY"));
				to.setZipCode(rs.getString("ZIP_CODE"));
				to.setStatus(rs.getString("STATUS"));
				
				to.setMembersList(getMembersList(to.getFamilyId()));

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

	public ArrayList<MemberTO> getMembersList(String familyId) throws InternalException {
		
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"MEMBER_ID","FAMILY_ID","NAME","DOB","SEX","BLOOD_GROUP","MARITAL_STATUS","CONTACT_NO","EMAIL","QUALIFICATION","OCCUPATION","ANNUAL_INCOME","FATHER_NAME","MOTHER_NAME","SPOUSE_NAME","RELATION","FAMILY_HEAD_YN","DISABLED_YN","VOTER_ID","AADHAR_ID","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND FAMILY_ID = ? AND STATUS = 'A'");
		String strQuery	=	sqlBuilder.getSelectQuery();
		ArrayList<MemberTO> alist	=	new ArrayList<MemberTO>();
		MemberTO to;
		Connection conn = null;
		try {
			conn =  DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, familyId);
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
			DBUtils.closeConnection(conn);
		}
		return alist;
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int limit) throws InternalException {

		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY");
		String[] arrFields = {"FAMILY_ID","FAMILY_NAME","ADDRESS_LINE1","ADDRESS_LINE2","ADDRESS_LINE3","STATE","DISTRICT","COUNTRY","ZIP_CODE","STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND STATUS = 'A' ");
		String strQuery	=	sqlBuilder.getSelectQuery();
		ArrayList<TransferObject> alist	=	new ArrayList<TransferObject>();
		FamilyTO to;
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			pStmt	=	 conn.prepareStatement(strQuery);
			rs		=	pStmt.executeQuery();
			while(rs.next()){

				to = new FamilyTO();

				to.setFamilyId(rs.getString("FAMILY_ID"));
				to.setFamilyName(rs.getString("FAMILY_NAME"));
				to.setAddressLine1(rs.getString("ADDRESS_LINE1"));
				to.setAddressLine2(rs.getString("ADDRESS_LINE2"));
				to.setAddressLine3(rs.getString("ADDRESS_LINE3"));
				to.setState(rs.getString("STATE"));
				to.setDistrict(rs.getString("DISTRICT"));
				to.setCountry(rs.getString("COUNTRY"));
				to.setZipCode(rs.getString("ZIP_CODE"));
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
		sqlBuilder.setTableName("FAMILY");
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
		/*FamilyTO to = (FamilyTO)transObj;
		PreparedStatement pStmt	=	null;
		ResultSet rs = null;
		String strQuery	=	"SELECT 1 FROM FAMILY WHERE FAMILY_ID = ? ";

		Connection conn = null;
		try {
			conn = DBUtils.getConnection();

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, to.getFamilyId());
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

	public void updateMembers(MemberTO memberTO, Connection conn) throws InternalException{
		
		MemberBO bo = new MemberBO();
		try {
			
			bo.update(memberTO, conn);
			
		} catch (UIException e) {
			throw new InternalException(e.getMessage());
		}
	}
	
	public void createMembers(MemberTO memberTO, Connection conn) throws InternalException{
		
		MemberBO bo = new MemberBO();
		try {
			
			bo.create(memberTO, conn);
			
		} catch (UIException e) {
			throw new InternalException(e.getMessage());
		}
	}
	
	public void deleteMembers(String familyId, Connection conn) throws InternalException{
		
		PreparedStatement pStmt	=	null;
		CRUDHelper sqlBuilder = new CRUDHelper();
		sqlBuilder.setTableName("FAMILY_MEMBERS");
		String[] arrFields = {"STATUS"};
		sqlBuilder.setArrFields(arrFields);
		sqlBuilder.setWhereClause(" AND FAMILY_ID = ? ");
		String strQuery	=	sqlBuilder.getUpdateQuery();

		try {

			pStmt	=	 conn.prepareStatement(strQuery);
			pStmt.setString(1, "R");
			pStmt.setString(2, familyId);
			pStmt.executeUpdate();

		}catch (SQLException e) {

			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException (100,"Error occurred while updation.");

		}finally{
			DBUtils.closeStatment(pStmt);
		}
	}
	
	
	public String getFamilyID() throws InternalException{
		
		String strId = SequeneGenerator.getSequence("FAMILY_ID");
		
		return strId;
	}

}