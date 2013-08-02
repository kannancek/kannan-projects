package com.ksoft.fbase.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.to.TransferObject;

public abstract class DataAccessObject {

	public abstract void create(TransferObject to,Connection conn) throws InternalException ;
	
	public abstract void update(TransferObject to,Connection conn) throws InternalException ;
	
	public abstract void delete(TransferObject to,Connection conn) throws InternalException ;
	
	public abstract ArrayList<? extends TransferObject> getBatch(int start, int last) throws InternalException;
	
	public abstract TransferObject findByKey(TransferObject to) throws InternalException ;
	
	public abstract int getCount() throws InternalException ;
	
	public abstract boolean isExisting(TransferObject to) throws InternalException ;
}
