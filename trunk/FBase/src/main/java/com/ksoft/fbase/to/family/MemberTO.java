package com.ksoft.fbase.to.family;

import javax.servlet.http.HttpServletRequest;
import com.ksoft.core.to.TransferObject;

public class MemberTO extends TransferObject{

	private String memberId		=	null;
	private String familyId		=	null;
	private String name		=	null;
	private String dob		=	null;
	private String sex		=	null;
	private String bloodGroup		=	null;
	private String maritalStatus		=	null;
	private String contactNo		=	null;
	private String email		=	null;
	private String qualification		=	null;
	private String occupation		=	null;
	private String annualIncome		=	null;
	private String fatherName		=	null;
	private String motherName		=	null;
	private String spouseName		=	null;
	private String relation		=	null;
	private String familyHeadYn		=	"N";
	private String disabledYn		=	null;
	private String voterId		=	null;
	private String aadharId		=	null;
	private String status		=	null;

	public String getMemberId(){
		return memberId;
	}

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getFamilyId(){
		return familyId;
	}

	public void setFamilyId(String familyId){
		this.familyId = familyId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDob(){
		return dob;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getBloodGroup(){
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus(){
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}

	public String getContactNo(){
		return contactNo;
	}

	public void setContactNo(String contactNo){
		this.contactNo = contactNo;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getQualification(){
		return qualification;
	}

	public void setQualification(String qualification){
		this.qualification = qualification;
	}

	public String getOccupation(){
		return occupation;
	}

	public void setOccupation(String occupation){
		this.occupation = occupation;
	}

	public String getAnnualIncome(){
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome){
		this.annualIncome = annualIncome;
	}

	public String getFatherName(){
		return fatherName;
	}

	public void setFatherName(String fatherName){
		this.fatherName = fatherName;
	}

	public String getMotherName(){
		return motherName;
	}

	public void setMotherName(String motherName){
		this.motherName = motherName;
	}

	public String getSpouseName(){
		return spouseName;
	}

	public void setSpouseName(String spouseName){
		this.spouseName = spouseName;
	}

	public String getRelation(){
		return relation;
	}

	public void setRelation(String relation){
		this.relation = relation;
	}

	public String getFamilyHeadYn(){
		return familyHeadYn;
	}

	public void setFamilyHeadYn(String familyHeadYn){
		this.familyHeadYn = familyHeadYn;
	}

	public String getDisabledYn(){
		return disabledYn;
	}

	public void setDisabledYn(String disabledYn){
		this.disabledYn = disabledYn;
	}

	public String getVoterId(){
		return voterId;
	}

	public void setVoterId(String voterId){
		this.voterId = voterId;
	}

	public String getAadharId(){
		return aadharId;
	}

	public void setAadharId(String aadharId){
		this.aadharId = aadharId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void populate(HttpServletRequest request){

		setMemberId(request.getParameter("memberId"));
		setFamilyId(request.getParameter("familyId"));
		setName(request.getParameter("name"));
		setDob(request.getParameter("dob"));
		setSex(request.getParameter("sex"));
		setBloodGroup(request.getParameter("bloodGroup"));
		setMaritalStatus(request.getParameter("maritalStatus"));
		setContactNo(request.getParameter("contactNo"));
		setEmail(request.getParameter("email"));
		setQualification(request.getParameter("qualification"));
		setOccupation(request.getParameter("occupation"));
		setAnnualIncome(request.getParameter("annualIncome"));
		setFatherName(request.getParameter("fatherName"));
		setMotherName(request.getParameter("motherName"));
		setSpouseName(request.getParameter("spouseName"));
		setRelation(request.getParameter("relation"));
		setFamilyHeadYn(request.getParameter("familyHeadYn"));
		setDisabledYn(request.getParameter("disabledYn"));
		setVoterId(request.getParameter("voterId"));
		setAadharId(request.getParameter("aadharId"));
		setStatus(request.getParameter("status"));

	}

}

