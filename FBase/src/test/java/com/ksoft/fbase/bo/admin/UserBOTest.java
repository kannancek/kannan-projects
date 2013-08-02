package com.ksoft.fbase.bo.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.exception.UIException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.CRUDHelper;
import com.ksoft.core.utils.DBUtils;
import com.ksoft.fbase.to.admin.UserTO;

public class UserBOTest{
	
	@BeforeClass
	public static void init() throws InternalException{
		
		KsoftLogger.initialize("D:\\Projects\\WorkSpace\\FBase\\src\\main\\webapp\\WEB-INF\\config\\log4j.xml");
		DBUtils.initialize("D:\\Projects\\WorkSpace\\FBase\\src\\main\\webapp\\WEB-INF\\config\\datasource.properties");
		
	}
	
	/*@Test
	public void create() throws UIException{	
		
		UserTO to = new UserTO();
		to.setName("Kannan R");
		to.setUsername("ajit");
		to.setPassword("password");
		to.setRole("");
		to.setStatus("A");
		to.setCreationDate("01/02/2000");
		
		UserBO bo = new UserBO();
		bo.create(to);
		
	}*/
	
	/*@Test
	public void update() throws UIException{	
		
		UserTO to = new UserTO();
		to.setName("Kannan R");
		to.setUsername("kannan");
		to.setPassword("password123");
		to.setRole("");
		to.setStatus("B");
		to.setCreationDate("01/01/2000");
		
		UserBO bo = new UserBO();
		bo.update(to);
		
	}
	
	@Test
	public void delete() throws UIException{	
		
		UserTO to = new UserTO();
		to.setUsername("kannan");
		UserBO bo = new UserBO();
		bo.delete(to);
		
	}
	
	@Test
	public void findByKey() throws UIException{	
		
		UserTO to = new UserTO();
		to.setUsername("kannan");
		UserBO bo = new UserBO();
		to = (UserTO) bo.findByKey(to);
		
	}*/
	
	@Test
	public void getBatch() throws UIException, InternalException{	
		
		UserBO bo = new UserBO();
		ArrayList<TransferObject> alist = bo.getBatch(CRUDHelper.NOLIMIT,CRUDHelper.NOLIMIT);
		System.out.println(alist.toString());
		
	}
	
	@Test
	public void getCount() throws UIException, InternalException{	
		
		UserBO bo = new UserBO();
		int count =  bo.getCount();
		System.out.println("Count = " + count);
		
	}
	
	
}
