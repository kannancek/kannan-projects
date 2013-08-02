package com.ksoft.fbase.bo.configurations;

import java.sql.Connection;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.to.TransferObject;
import com.ksoft.fbase.bo.BusinessDelegate;
import com.ksoft.fbase.dao.configurations.ZipCodesDAO;

public class ZipCodesBO extends BusinessDelegate{

	@Override
	public void create(TransferObject to,Connection conn) throws UIException,InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		dao.create(to,conn);
	}

	@Override
	public void update(TransferObject to,Connection conn) throws UIException,InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		dao.update(to,conn);
	}

	@Override
	public void delete(TransferObject to,Connection conn) throws UIException,InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		dao.delete(to,conn);
	}

	@Override
	public TransferObject findByKey(TransferObject to) throws UIException,InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		return dao.findByKey(to);
	}

	@Override
	public ArrayList<TransferObject> getBatch(int start, int last) throws InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		return dao.getBatch(start, last);
	}

	@Override
	public int getCount() throws UIException,InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		return dao.getCount();
	}
	
	@Override
	public boolean isExisting(TransferObject to)throws UIException, InternalException {
		ZipCodesDAO dao = new ZipCodesDAO();
		return dao.isExisting(to);
	}

	@Override
	public void validate(TransferObject to)throws UIException, InternalException {
		
		
	}
}

