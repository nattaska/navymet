package com.ss.sq.util;

public class StringUtils {
	
	public static String toString(Object obj) {
		if(BeanUtils.isNull(obj)) {
			return "";
		}
		return obj.toString();
	}
	
}
