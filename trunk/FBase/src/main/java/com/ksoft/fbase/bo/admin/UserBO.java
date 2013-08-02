package com.ksoft.fbase.bo.admin;

import java.sql.Connection;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.to.TransferObject;
import com.ksoft.fbase.bo.BusinessDelegate;
import com.ksoft.fbase.dao.admin.UserDAO;
import com.ksoft.fbase.to.admin.UserTO;

public class UserBO extends BusinessDelegate{

	@Override
	public void create(TransferObject to,Connection conn) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		dao.create(to,conn);
	}

	@Override
	public void update(TransferObject to,Connection conn) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		dao.update(to,conn);
	}

	@Override
	public void delete(TransferObject to,Connection conn) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		dao.delete(to,conn);
	}

	@Override
	public TransferObject findByKey(TransferObject to) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		return dao.findByKey(to);
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int last) throws InternalException {
		UserDAO dao = new UserDAO();
		return dao.getBatch(start, last);
	}

	@Override
	public int getCount() throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		return dao.getCount();
	}
	
	public boolean checkUser(UserTO to, Connection conn){
		
		
		return true;
		
	}

	@Override
	public boolean isExisting(TransferObject to)throws UIException, InternalException {
		UserDAO dao = new UserDAO();
		return dao.isExisting(to);
	}

	@Override
	public void validate(TransferObject to)throws UIException, InternalException {
		
		
	}
}

