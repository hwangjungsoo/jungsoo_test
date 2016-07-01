package org.zerock.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateTest {

	public static void main(String[] args) throws Exception {
		
		Map<String, String> paramMap = new HashMap<>(); 
		paramMap.put("teamname","team");
		paramMap.put("member","hi");
		String str="org.zerock.controller.TeamVO";
		Class clz= Class.forName(str);
		//MemberVO vo = (MemberVO)clz.newInstance();
		Object vo = clz.newInstance();
		System.out.println(vo);
		
		BeanInfo info = Introspector.getBeanInfo(clz); //Java Bean 규칙에 맞체 호출할 수 있도록 바뀜
		PropertyDescriptor[] props = info.getPropertyDescriptors();
		System.out.println(Arrays.toString(props));
		
		for(PropertyDescriptor prop:props){
			
			String propName = prop.getName();
			System.out.println("propName: "+propName);
			
			String paramValue = paramMap.get(propName);

			
			Method setMethod = prop.getWriteMethod();
			
			if(setMethod == null || paramValue == null){
				continue;
			}
			setMethod.invoke(vo, paramValue);
			
			
			setMethod.invoke(vo,"AAAAA");
		}
		
		System.out.println(vo.toString());
	}

}
