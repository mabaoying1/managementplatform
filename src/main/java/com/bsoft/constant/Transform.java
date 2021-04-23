package com.bsoft.constant;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.bsoft.annotation.Object2MapField;

public class Transform implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6404567930294811771L;


	
	public Map<String, Object> toMap() {
		return this.toMap(this, false,false);
	}

	/**
	 *  对象转Map ，不包括超类型的字段 ，Key 键值大写
	 * @return
	 */
	public Map<String, Object> toUpperKeyMap() {
		return this.toMap(this, true,false);
	}

	/**
	 * 对象转Map ，包括超类型的字段 ，Key 键值大写
	 * @return
	 */
	public Map<String, Object> toUpperKeyForSuperFiledsMap() {
		return this.toMap(this, true,true);
	} 
	private Map<String, Object> toMap(Object o, boolean isupper,boolean isSuperFileds) {
		if(null==o)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields;
		if(!isSuperFileds)
		declaredFields = o.getClass().getDeclaredFields();
		else 
			declaredFields=o.getClass().getFields();
		for (Field field : declaredFields) {
			int fieldValue = field.getModifiers();
			if (Modifier.isStatic(fieldValue))
				continue;
			field.setAccessible(true);
			Object2MapField o2m = field.getAnnotation(Object2MapField.class);
			String key = field.getName();
			if (isupper)
				key = key.toUpperCase();
			try {
				if (null == o2m) {
					map.put(key, field.get(o));
				} else {
					map.put(key, this.toMap(field.get(o), isupper,isSuperFileds));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	
	
}
