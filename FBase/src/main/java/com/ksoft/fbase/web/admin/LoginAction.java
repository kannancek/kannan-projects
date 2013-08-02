package com.ksoft.fbase.web.admin;

import com.ksoft.core.exception.UIException;
import com.ksoft.core.logger.KsoftLogger;
import com.ksoft.fbase.bo.admin.UserBO;
import com.ksoft.fbase.to.admin.UserTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction  extends ActionSupport{

	 private static final long serialVersionUID = 1L;	 
	 private String username = null;
	 private String password = null;
	 
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String execute() {
			
			UserTO to = new UserTO();
			to.setUsername(this.username);
			to.setPassword(this.password);
			
			UserBO bo  = new UserBO();
			Object[] params = new Object[1];
			params[0] = to;
			
			Boolean isLoginSuccess = false;
			
			try {
				
				isLoginSuccess = (Boolean)bo.executeMethod(bo, "checkUser", params);
				
			} catch (UIException e) {
				
				addActionError(e.getMessage());
	        	return ERROR;
			}
			
	        if(isLoginSuccess)
	        {
	            return SUCCESS;
	 
	        }else{
	        	addActionError("Login Failed. Invalid username or password");
	        	return ERROR;
	        }
	            
	    }
	 

}
