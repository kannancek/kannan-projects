package com.ksoft.core.to;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.logger.KsoftLogger;

public abstract class TransferObject {
			
	public JSONObject toJsonObject() throws InternalException {
		
		JSONObject jsonObject = null;
		
		try{
			
			Field[] fields = this.getClass().getDeclaredFields();
			
			if(null != fields){
				
				jsonObject = new JSONObject();
				
				for(Field field : fields){
					
					field.setAccessible(true);
					
					if(field.getType() == ArrayList.class){
						
						JSONArray jsonArray = new JSONArray();
						ArrayList<Object> list = (ArrayList<Object>)field.get(this);
						
						for(Object prop: list){
							
							JSONObject o1 = ((TransferObject)prop).toJsonObject();
							jsonArray.add(o1);
							
						}
						jsonObject.put(field.getName(), jsonArray);
						
					}else{
						
						jsonObject.put(field.getName(), field.get(this));
						
					}
				}
				
			}
			
		}catch (IllegalArgumentException e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException(201, e.getMessage());
			
		} catch (IllegalAccessException e) {
			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException(202, e.getMessage());
		}
		
		
		return jsonObject;
		
		
	}
	
	public String toJsonString() throws InternalException {
		
		return toJsonObject().toString();
	}


}
