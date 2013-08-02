package com.ksoft.fbase.web.family;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ksoft.core.exception.UIException;
import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.WebUtils;
import com.ksoft.fbase.Constants;
import com.ksoft.fbase.bo.family.FamilyBO;
import com.ksoft.fbase.bo.family.MemberBO;
import com.ksoft.fbase.to.family.FamilyTO;
import com.ksoft.fbase.to.family.MemberTO;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyAction extends ActionSupport {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	ArrayList<MemberTO> membersList = null;
	int i = 0;
	
	
	
	public String execute() {

		String strMode = request.getParameter("Mode");
		String strAction = request.getParameter("Action");
		
		if ("init".equals(strAction)) {
			
			request.getSession().setAttribute("Mode", strMode);		
			
			if(Constants.ACTION_UPDATE.equals(strMode)){
				
				String familyId = request.getParameter("familyId");
				
				FamilyBO bo = new FamilyBO();
				FamilyTO to = new FamilyTO();
				to.setFamilyId(familyId);
				
				JSONObject object = new JSONObject();
				
				try{
					
					to = (FamilyTO) bo.findByKey(to);
					membersList = to.getMembersList();
					
					request.getSession().setAttribute("membersList",membersList);
					request.setAttribute("familyData", to.toJsonString());
					
					
				}catch(Exception ue){
					object.put("message", ue.getMessage());
				}
				
				//WebUtils.writeResponse(response, object.toString());
				
			}else{
				membersList = new ArrayList<MemberTO>();
				request.getSession().setAttribute("membersList",membersList);
			}
			
		}else if ("save".equals(strAction)) {
			
			JSONObject object = new JSONObject();
			FamilyTO to = new FamilyTO();
			to.populate(request);
			membersList = (ArrayList<MemberTO>) request.getSession().getAttribute("membersList");
			to.setMembersList(membersList);
			FamilyBO bo = new FamilyBO();
			
			try{
				
				if(Constants.ACTION_CREATE.equals(strMode)){
					
					bo.create(to);
					object.put("success", "true");
					object.put("message", "Family created successfully");
					
				}else if(Constants.ACTION_UPDATE.equals(strMode)){
					
					bo.update(to);
					object.put("success", "true");
					object.put("message", "Family updated successfully");
					
				}
				
				
			}catch(Exception ue){
				object.put("failure", "true");
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
			

		}else if ("addMember".equals(strAction)) {
			
			
			membersList = (ArrayList<MemberTO>) request.getSession().getAttribute("membersList");
			MemberTO memberTO = new MemberTO();
			memberTO.populate(request);
			membersList.add(memberTO);
			
			JSONObject object = new JSONObject();
			object.put("success", "true");
			object.put("message", "member added");
			
			WebUtils.writeResponse(response, object.toString());
			
		}else if ("updateMember".equals(strAction)) {
			
			
			membersList = (ArrayList<MemberTO>) request.getSession().getAttribute("membersList");
			String strRowIndex = request.getParameter("RowIndex");
			int iRowIndex = Integer.parseInt(strRowIndex);
			
			MemberTO memberTO = new MemberTO();
			memberTO.populate(request);	
			
			membersList.set(iRowIndex, memberTO);
			
			JSONObject object = new JSONObject();
			object.put("success", "true");
			object.put("message", "member added");

			WebUtils.writeResponse(response, object.toString());
			
		}else if ("deleteMember".equals(strAction)) {
			
			membersList = (ArrayList<MemberTO>) request.getSession().getAttribute("membersList");
			String strRowIndex = request.getParameter("RowIndex");
			int iRowIndex = Integer.parseInt(strRowIndex);	
			JSONObject object = new JSONObject();
			
			try {
				
				MemberTO memberTO = membersList.get(iRowIndex);
				MemberBO bo = new MemberBO();	
				bo.delete(memberTO);
				
				membersList.remove(iRowIndex);
				
				object.put("success", "true");
				object.put("message", "Member removed successfully");
				
			} catch (UIException ue) {
				object.put("failure", "true");
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
			
		}else if ("getMembers".equals(strAction)) {

			membersList = (ArrayList<MemberTO>) request.getSession().getAttribute("membersList");
			JSONObject object = new JSONObject();
			JSONArray arr = new JSONArray();
			
			try{
				
				for(TransferObject to : membersList){					
					arr.add(to.toJsonObject());					
				}	
				
				object.put("members", arr);
				
			}catch(Exception ue){
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
			
		}
		
		return SUCCESS;
	}
	
	
	
}
