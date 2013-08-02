package com.ksoft.fbase.bo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.reflect.MethodUtils;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.DBUtils;

public abstract class BusinessDelegate {
	
	public  void create(TransferObject to) throws UIException {
		
		Connection conn = null;
		
		try{			
				
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			if(isExisting(to)){
				throw new UIException("Record already exists");
			}
			create(to,conn);	
			conn.commit();			
			
		}catch (InternalException ie) {
			
			try {
				conn.rollback();
			} catch (SQLException e) {
				KsoftLogger.getLogger().error(e.getMessage());
			}
			throw new UIException("Application error occured. Please contact system administrator. Error Code " + ie.getErrorCode());
			
		} catch (SQLException e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured");
			
		}finally{
			
			DBUtils.closeConnection(conn);
			
		}
	}
	
	public  void update(TransferObject to) throws UIException {
		
		Connection conn = null;
		
		try{			
				
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			update(to,conn);	
			conn.commit();			
			
		}catch (InternalException ie) {
			
			try {
				conn.rollback();
			} catch (SQLException e) {
				KsoftLogger.getLogger().error(e.getMessage());
			}
			throw new UIException("Application error occured. Please contact system administrator. Error Code " + ie.getErrorCode());
			
		} catch (SQLException e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured");
			
		}finally{
			
			DBUtils.closeConnection(conn);
			
		}
	}
	
	public  void delete(TransferObject to) throws UIException {
		
		Connection conn = null;
		
		try{			
				
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			delete(to,conn);	
			conn.commit();			
			
		}catch (InternalException ie) {
			
			try {
				conn.rollback();
			} catch (SQLException e) {
				KsoftLogger.getLogger().error(e.getMessage());
			}
			throw new UIException("Application error occured. Please contact system administrator. Error Code " + ie.getErrorCode());
			
		} catch (SQLException e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured");
			
		}finally{
			
			DBUtils.closeConnection(conn);
			
		}
	}
	

	
	public  Object executeMethod(Object obj, String methodName,  Object[] params) throws UIException {
		
		Connection conn = null;
		Object resObj =null;
		Object[] newParams = null;
		try{			
				
			conn = DBUtils.getConnection();
			
			if(null != params){
				
				if(params.length > 0){
					
					newParams = new Object[params.length + 1];
					
					for(int i=0; i<params.length; i++){
						
						newParams[i] = params[i];
						
					}
					
					newParams[params.length] = conn;
					
				}else{
					newParams = new Object[1];
					newParams[0] = conn;
				}
				
			}else{
				newParams = new Object[1];
				newParams[0] = conn;
			}
			
			
			resObj = (Object)MethodUtils.invokeMethod(obj, methodName, newParams);
			
		}catch (InternalException ie) {			
			
			throw new UIException("Application error occured. Please contact system administrator. Error Code " + ie.getErrorCode());
			
		} catch (NoSuchMethodException e) {
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured. Please contact system administrator.");
		} catch (IllegalAccessException e) {
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured. Please contact system administrator.");
		} catch (InvocationTargetException e) {
			KsoftLogger.getLogger().error(e.getMessage());
			throw new UIException("Application error occured. Please contact system administrator.");
		}finally{			
			DBUtils.closeConnection(conn);			
		}
		
		return resObj;
	}
	

	public abstract void create(TransferObject to,Connection conn) throws UIException,InternalException;
	
	public abstract void update(TransferObject to, Connection conn) throws UIException,InternalException ;
	
	public abstract void delete(TransferObject to, Connection conn) throws UIException,InternalException ;
	
	public abstract TransferObject findByKey(TransferObject to) throws UIException,InternalException ;
	
	public abstract ArrayList<? extends TransferObject> getBatch(int start, int last) throws InternalException;
	
	public abstract int getCount() throws UIException,InternalException ;
	
	public abstract boolean isExisting(TransferObject to ) throws UIException,InternalException ;
	
	public abstract void validate(TransferObject to) throws UIException,InternalException;
	

}
