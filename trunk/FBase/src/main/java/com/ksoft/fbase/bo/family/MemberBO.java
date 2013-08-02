package com.ksoft.fbase.bo.family;

import java.sql.Connection;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.to.TransferObject;
import com.ksoft.fbase.bo.BusinessDelegate;
import com.ksoft.fbase.dao.family.MemberDAO;
import com.ksoft.fbase.to.family.MemberTO;

public class MemberBO extends BusinessDelegate{

	@Override
	public void create(TransferObject to,Connection conn) throws UIException,InternalException {
		MemberDAO dao = new MemberDAO();
		dao.create(to,conn);
	}

	@Override
	public void update(TransferObject to,Connection conn) throws UIException,InternalException {
		MemberDAO dao = new MemberDAO();
		dao.update(to,conn);
	}

	@Override
	public void delete(TransferObject to,Connection conn) throws UIException,InternalException {
		MemberDAO dao = new MemberDAO();
		dao.delete(to,conn);
	}

	@Override
	public TransferObject findByKey(TransferObject to) throws UIException,InternalException {
		MemberDAO dao = new MemberDAO();
		return dao.findByKey(to);
	}

	@Override
	public ArrayList<MemberTO> getBatch(int start, int limit) throws InternalException {
		MemberDAO dao = new MemberDAO();
		return dao.getBatch(start, limit);
	}

	@Override
	public int getCount() throws UIException,InternalException {
		MemberDAO dao = new MemberDAO();
		return dao.getCount();
	}
	@Override
	public boolean isExisting(TransferObject to)throws UIException, InternalException {
		MemberDAO dao = new MemberDAO();
		return dao.isExisting(to);
	}

	@Override
	public void validate(TransferObject to)throws UIException, InternalException {
		// TODO Auto-generated method stub
	}
	

}

