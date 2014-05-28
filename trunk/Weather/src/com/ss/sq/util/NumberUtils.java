package com.ss.sq.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.ss.sq.util.configurer.NumberUtilsConfigurer;

public class NumberUtils {
	private static NumberUtilsConfigurer NUMBER_UTILS_CONFIGURER;

	public static String toString(Number number) {
		String result = null;
		if (!BeanUtils.isNull(number)) {
			DecimalFormat df = new DecimalFormat();
			df.setGroupingSize(0);
			df.setMaximumFractionDigits(0);
			result = df.format(number);
		}
		return result;
	}

	public static String toString(Number number, int fraction) {
		String result = null;
		if (!BeanUtils.isNull(number)) {
			DecimalFormat df = new DecimalFormat();
			df.setGroupingSize(0);
			df.setMaximumFractionDigits(fraction);
			df.setMinimumFractionDigits(fraction);
			result = df.format(number);
		}
		return result;
	}

	public static Boolean toBoolean(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return Boolean.parseBoolean(obj.toString());
	}

	public static Integer toInteger(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return toNumber(obj).intValue();
	}

	public static Long toLong(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return toNumber(obj).longValue();
	}

	public static Double toDouble(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return toNumber(obj).doubleValue();
	}

	public static Float toFloat(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return toNumber(obj).floatValue();
	}

	public static BigDecimal toBigDecimal(Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return new BigDecimal(toNumber(obj).toString());
	}

	private static Number toNumber(Object obj) {
		try {
			return NUMBER_UTILS_CONFIGURER.getDefaultNumberFormat().parse(obj.toString());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse text [" + obj.toString() + "] to number", e);
		}
	}

	public static void setNumberUtilsConfigurer(NumberUtilsConfigurer numberUtilsConfigurer) {
		NUMBER_UTILS_CONFIGURER = numberUtilsConfigurer;
	}
	
	public static NumberUtilsConfigurer getNumberUtilsConfigurer() {
		return NUMBER_UTILS_CONFIGURER;
	}
}
