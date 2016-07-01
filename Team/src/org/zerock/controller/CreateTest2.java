package org.zerock.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateTest2 {
	public static void main(String[] args) throws Exception {
		
		Map<String, String[]> paramMap = new HashMap<>(); 
		paramMap.put("bno",new String[]{"123"});
		paramMap.put("title",new String[]{"TITLE"});
		paramMap.put("content",new String[]{"CONTENT"});
		paramMap.put("hobbies", new String[]{"aaa","bbb","ccc"});
		//배열: {"aa"} 정적, new String[1] 동적, new String[]{"aa"} 동적 차이가 뭐냐
	
		
		String str="org.zerock.controller.BoardVO";
		Class clz= Class.forName(str);
		Object vo = clz.newInstance();
		System.out.println(vo);
		
		BeanInfo info = Introspector.getBeanInfo(clz); //Java Bean 규칙에 맞체 호출할 수 있도록 바뀜
		PropertyDescriptor[] props = info.getPropertyDescriptors();

		
		
		for (PropertyDescriptor prop : props) {
			String propName = prop.getName();
			
			Class propType = prop.getPropertyType(); //type
			Method setMethod = prop.getWriteMethod();
			
			String[] paramValue = paramMap.get(propName);
			if(setMethod== null || paramValue == null){
			continue;	
			}
			
			if(propType == int.class){
				System.out.println(propName+": int");
				setMethod.invoke(vo, Integer.parseInt(paramValue[0]));
			}else if(propType == String.class){
				System.out.println(propName+": String");
				setMethod.invoke(vo, paramValue[0]);
			}else if(propType.isArray()){ //or propType.isArray() or propType == String[].class
				//Java
				String[] arr = paramValue;
				setMethod.invoke(vo, new Object[]{arr});
			}
		}

		System.out.println(vo.toString());
	}
}
