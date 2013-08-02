package com.ksoft.fbase.web.configurations;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ksoft.core.to.TransferObject;
import com.ksoft.core.utils.JSONSupport;
import com.ksoft.core.utils.WebUtils;
import com.ksoft.fbase.bo.configurations.ZipCodesBO;
import com.ksoft.fbase.to.configurations.ZipCodesTO;
import com.opensymphony.xwork2.ActionSupport;

public class ZipCodesAction extends ActionSupport {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public String execute() {

		String strAction = request.getParameter("Action");
		String strMode = request.getParameter("Mode");

		if ("save".equals(strAction)) {
			
			JSONObject object = new JSONObject();
			ZipCodesTO to = new ZipCodesTO();
			to.populate(request);
			
			ZipCodesBO bo = new ZipCodesBO();
			
			try{
				if("create".equalsIgnoreCase(strMode)){
					bo.create(to);
					object.put("success", "true");
					object.put("message", "Zipcode created successfully");		
				}else if("update".equalsIgnoreCase(strMode)){
					bo.update(to);
					object.put("success", "true");
					object.put("message", "Zipcode updated successfully");		
				}					
				
			}catch(Exception ue){
				
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
			

		} else if ("delete".equals(strAction)) {
			
			JSONObject object = new JSONObject();
			ZipCodesTO to = new ZipCodesTO();
			to.setZipCode(request.getParameter("zipCodes"));
			
			ZipCodesBO bo = new ZipCodesBO();
			
			try{
				
				bo.delete(to);
				object.put("success", "true");
				object.put("message", "Records deleted successfully");
				
			}catch(Exception ue){
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());

		} else if ("getAllRecords".equals(strAction)) {

			ZipCodesBO bo = new ZipCodesBO();
			JSONObject object = new JSONObject();
			JSONArray arr = new JSONArray();
			
			ArrayList<TransferObject> aList = new ArrayList<TransferObject>();
			
			int start = new Integer(request.getParameter("start"));
			int limit = new Integer(request.getParameter("limit"));
			
			try{
				
				aList = bo.getBatch(start, limit);					
				for(TransferObject to : aList){					
					arr.add(JSONSupport.toJsonObject((ZipCodesTO)to));					
				}	
				
				object.put("count", bo.getCount());
				object.put("zipcodes", arr);
				
			}catch(Exception ue){
				object.put("message", ue.getMessage());
			}
			
			WebUtils.writeResponse(response, object.toString());
		}
		
		return SUCCESS;
	}
}
