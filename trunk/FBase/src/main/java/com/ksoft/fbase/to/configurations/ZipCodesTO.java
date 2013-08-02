package com.ksoft.fbase.to.configurations;

import javax.servlet.http.HttpServletRequest;
import com.ksoft.core.to.TransferObject;

public class ZipCodesTO extends TransferObject{

	private String zipCode		=	null;
	private String state		=	null;
	private String district		=	null;
	private String country		=	null;
	private String post		=	null;


	public String getZipCode(){
		return zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getDistrict(){
		return district;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getPost(){
		return post;
	}

	public void setPost(String post){
		this.post = post;
	}

	public void populate(HttpServletRequest request){

		setZipCode(request.getParameter("zipCode"));
		setState(request.getParameter("state"));
		setDistrict(request.getParameter("district"));
		setCountry(request.getParameter("country"));
		setPost(request.getParameter("post"));

	}

}

