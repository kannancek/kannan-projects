package com.ksoft.fbase.bo.family;

import java.sql.Connection;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.to.TransferObject;
import com.ksoft.fbase.bo.BusinessDelegate;
import com.ksoft.fbase.dao.family.FamilyDAO;
import com.ksoft.fbase.to.family.FamilyTO;
import com.ksoft.fbase.to.family.MemberTO;

public class FamilyBO extends BusinessDelegate{

	static int i = 0;
	
	@Override
	public void create(TransferObject transObj,Connection conn) throws UIException,InternalException {
		FamilyDAO dao = new FamilyDAO();
		FamilyTO to = (FamilyTO)transObj;
		dao.create(to,conn);
	}

	@Override
	public void update(TransferObject to,Connection conn) throws UIException,InternalException {
		FamilyDAO dao = new FamilyDAO();
		dao.update(to,conn);
	}

	@Override
	public void delete(TransferObject to,Connection conn) throws UIException,InternalException {
		FamilyDAO dao = new FamilyDAO();
		dao.delete(to,conn);
	}

	@Override
	public TransferObject findByKey(TransferObject to) throws UIException,InternalException {
		FamilyDAO dao = new FamilyDAO();
		return dao.findByKey(to);
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int limit) throws InternalException {
		FamilyDAO dao = new FamilyDAO();
		return dao.getBatch(start, limit);
	}

	@Override
	public int getCount() throws UIException,InternalException {
		FamilyDAO dao = new FamilyDAO();
		return dao.getCount();
	}
	@Override
	public boolean isExisting(TransferObject to)throws UIException, InternalException {
		FamilyDAO dao = new FamilyDAO();
		return dao.isExisting(to);
	}

	@Override
	public void validate(TransferObject to)throws UIException, InternalException {
		// TODO Auto-generated method stub
	}

}

