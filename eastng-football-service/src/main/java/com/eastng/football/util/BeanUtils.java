package com.eastng.football.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils extends org.springframework.beans.BeanUtils {

	public static Map<String, Object> bean2Map(Object obj){
		if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
	}
	
	/**
	 * 复制List
	 * @param fromObjList
	 * @param toObjClazz
	 * @return
	 */
	public static <T> List<T> copyList(List<?> fromObjList,Class<T> toObjClazz){
		List<T> toObjList = new ArrayList<T>(fromObjList.size());
		
		for(int i = 0 ; i < fromObjList.size() ; i++ ){
			try {
				T toObj = toObjClazz.newInstance();
				copyProperties(fromObjList.get(i), toObj);
				toObjList.add(toObj);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toObjList;
	}
	
}
