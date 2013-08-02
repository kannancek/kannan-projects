package com.ksoft.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONSupport {
	
	public static JSONObject toJsonObject(Object obj) throws IllegalArgumentException, IllegalAccessException{
		
		JSONObject jsonObject = null;
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		if(null != fields){
			
			jsonObject = new JSONObject();
			
			for(Field field : fields){
				
				field.setAccessible(true);
				
				if(field.getType() == ArrayList.class){
					
					JSONArray jsonArray = new JSONArray();
					ArrayList<Object> list = (ArrayList<Object>)field.get(obj);
					
					for(Object prop: list){
						
						JSONObject o1 = toJsonObject(prop);
						jsonArray.add(o1);
						
					}
					jsonObject.put(field.getName(), jsonArray);
					
				}else{
					
					jsonObject.put(field.getName(), field.get(obj));
					
				}
			}
			
		}
		
		return jsonObject;
		
		
	}

}
