package com.ksoft.fbase.to.family;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.ksoft.core.to.TransferObject;

public class FamilyTO extends TransferObject{

	private String familyId		=	null;
	private String familyName		=	null;
	private String addressLine1		=	null;
	private String addressLine2		=	null;
	private String addressLine3		=	null;
	private String state		=	null;
	private String district		=	null;
	private String country		=	null;
	private String zipCode		=	null;
	private String status		=	null;

	ArrayList<MemberTO> membersList = new ArrayList<MemberTO>();

	public String getFamilyId(){
		return familyId;
	}

	public void setFamilyId(String familyId){
		this.familyId = familyId;
	}

	public String getFamilyName(){
		return familyName;
	}

	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}

	public String getAddressLine1(){
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1){
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2(){
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2){
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3(){
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3){
		this.addressLine3 = addressLine3;
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

	public String getZipCode(){
		return zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<MemberTO> getMembersList() {
		return membersList;
	}

	public void setMembersList(ArrayList<MemberTO> membersList) {
		this.membersList = membersList;
	}

	public void populate(HttpServletRequest request){

		setFamilyId(request.getParameter("familyId"));
		setFamilyName(request.getParameter("familyName"));
		setAddressLine1(request.getParameter("addressLine1"));
		setAddressLine2(request.getParameter("addressLine2"));
		setAddressLine3(request.getParameter("addressLine3"));
		setState(request.getParameter("state"));
		setDistrict(request.getParameter("district"));
		setCountry(request.getParameter("country"));
		setZipCode(request.getParameter("zipCode"));
		setStatus(request.getParameter("status"));

	}

}

