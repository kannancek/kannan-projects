package com.ksoft.fbase.bo.admin;

import com.ksoft.core.bo.BusinessDelegate;
import com.ksoft.core.exception.InternalException;
import com.ksoft.core.to.TransferObject;

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
	public TransferObject findByKey(TransferObject to,Connection conn) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		return dao.findByKey(to,conn);
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int last,Connection conn) throws InternalException {
		UserDAO dao = new UserDAO();
		return dao.getBatch(start, last, conn);
	}

	@Override
	public int getCount(Connection conn) throws UIException,InternalException {
		UserDAO dao = new UserDAO();
		return dao.getCount(conn);
	}
}

