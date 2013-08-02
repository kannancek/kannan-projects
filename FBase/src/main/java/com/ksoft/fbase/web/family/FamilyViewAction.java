package com.ksoft.fbase.web.family;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.WebUtils;
import com.ksoft.fbase.Constants;
import com.ksoft.fbase.bo.family.FamilyBO;
import com.ksoft.fbase.to.family.FamilyTO;
import com.ksoft.fbase.to.family.MemberTO;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyViewAction extends ActionSupport {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	ArrayList<MemberTO> membersList = null;
	int i = 0;
	public String execute() {

		
		String strAction = request.getParameter("Action");
		String strMode = request.getParameter("Mode");
		request.getSession().setAttribute("Mode", strMode);
		
		if ("init".equals(strAction)) {
			
					
			
			
		}else if ("getAllRecords".equals(strAction)) {

			FamilyBO bo = new FamilyBO();
			JSONObject object = new JSONObject();
			JSONArray arr = new JSONArray();
			
			ArrayList<TransferObject> aList = new ArrayList<TransferObject>();
			
			int start = new Integer(request.getParameter("start"));
			int limit = new Integer(request.getParameter("limit"));
			
			try{
				
				aList = bo.getBatch(start, limit);					
				for(TransferObject to : aList){					
					arr.add(to.toJsonObject());					
				}	
				
				object.put("count", bo.getCount());
				object.put("family", arr);
				
			}catch(Exception ue){
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
		}else if ("delete".equals(strAction)) {

			String familyId = request.getParameter("familyId");
			
			JSONObject object = new JSONObject();
			FamilyTO to = new FamilyTO();
			to.setFamilyId(familyId);
			
			FamilyBO bo = new FamilyBO();
			
			try{
				
				bo.delete(to);
				object.put("success", "true");
				object.put("message", "Family removed successfully");
				
			}catch(Exception ue){
				object.put("failure", "true");
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
		}
		
		return SUCCESS;
	}
	
	
	
}
