/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 * @author ThinkGem
 * @version 2014-6-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 注解到对象复制，只复制能匹配上的方法。
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object){
		if (annotation != null){
			Class<?> annotationClass = annotation.getClass();
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()){
				if (StringUtils.startsWith(m.getName(), "set")){
					try {
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString())){
							if (object == null){
								object = objectClass.newInstance();
							}
							m.invoke(object, obj);
						}
					} catch (Exception e) {
						// 忽略所有设置失败方法
					}
				}
			}
		}
	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断是否为空,只判断如下几种情况
	 * 1. Null
	 * 2. CharSequence or StringBuilder or StringBuffer = ""
	 * 3. Map.size = 0
	 * 4. Array.size = 0
	 * 5. Collection.size = 0
	 * 
	 * @param target 被判断的Object
	 * @return true 如果空
	 */
	public static final boolean isEmpty(final Object target) {
		if (target == null)
			return true;

		final Class<? extends Object> clazz = target.getClass();

		// 判断是不是CharSequence
		if (CharSequence.class.isAssignableFrom(clazz)) {
			return ((CharSequence)target).length() == 0;
		}
		
		// 判断是不是StringBuilder
		if (target instanceof StringBuilder)
			return ((StringBuilder) target).length()==0;
		
		// 判断是不是StringBuffer
		if (target instanceof StringBuffer)
			return ((StringBuffer) target).length()==0;

		// 判断是不是array
		if (clazz.isArray()) {
			if (Array.getLength(target)==0)
				return true;
			
			return false;
		}
		
		// 判断是不是Collection
		if (Collection.class.isAssignableFrom(clazz)) {
			if (((Collection<?>) target).size()==0)
				return true;
			
			return false;
		}
		
		// 判断是不是Map
		if (Map.class.isAssignableFrom(clazz)) {
			if (((Map<?,?>) target).size()==0)
				return true;
			
			return false;
		}
		
		return false;
	}
	
}
