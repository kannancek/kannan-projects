package com.ksoft.fbase.to.admin;

import javax.servlet.http.HttpServletRequest;
import com.ksoft.core.to.TransferObject;

public class UserTO extends TransferObject{

	private String username		=	null;
	private String password		=	null;
	private String name		=	null;
	private String role		=	null;
	private String status		=	null;
	private String creationDate		=	null;


	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getRole(){
		return role;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getCreationDate(){
		return creationDate;
	}

	public void setCreationDate(String creationDate){
		this.creationDate = creationDate;
	}

	public void populate(HttpServletRequest request){

		setUsername(request.getParameter("username"));
		setPassword(request.getParameter("password"));
		setName(request.getParameter("name"));
		setRole(request.getParameter("role"));
		setStatus(request.getParameter("status"));
		setCreationDate(request.getParameter("creationDate"));

	}

}

