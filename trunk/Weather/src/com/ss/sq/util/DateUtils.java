package com.ss.sq.util;

import java.util.Calendar;
import java.util.Date;

import com.ss.sq.util.configurer.DateUtilsConfigurer;

public class DateUtils {
	private static DateUtilsConfigurer DATE_UTILS_CONFIGURER;
	public static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;

	public static void setDateUtilsConfigurer(DateUtilsConfigurer dateUtilsConfigurer) {
		DATE_UTILS_CONFIGURER = dateUtilsConfigurer;
	}

	public static DateUtilsConfigurer getDateUtilsConfigurer() {
		return DATE_UTILS_CONFIGURER;
	}

	public static Date toStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateUtils.removeTime(calendar);
		return calendar.getTime();
	}

	public static Date toEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date parseShortDate(String shortDate) {
		try {
			return DATE_UTILS_CONFIGURER.getShortSimpleDateFormat().parse(shortDate);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot parse date string input [" + shortDate + "] by using short date format [" + DATE_UTILS_CONFIGURER.getShortDateFormat() + "]");
		}
	}

	public static String formatShortDate(Date date) {
		return DATE_UTILS_CONFIGURER.getShortSimpleDateFormat().format(date);
	}

	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}

	public static boolean before(Date date1, Date date2) {
		return date1.compareTo(date2) < 0;
	}

	public static boolean after(Date date1, Date date2) {
		return date1.compareTo(date2) > 0;
	}

	public static boolean between(Date date, Date start, Date end) {
		if (before(date, start)) {
			return false;
		} else if (after(date, end)) {
			return false;
		} else {
			return true;
		}
	}

	public static int diffDate(Date date1, Date date2) {
		long millisecond1 = date1.getTime();
		long millisecond2 = date2.getTime();
		long diffMilliseconds = millisecond2 - millisecond1;
		return (int) (diffMilliseconds / MILLISECONDS_PER_DAY);
	}

	public static String getShortSystemEndDate() {
		return DATE_UTILS_CONFIGURER.getShortSystemEndDate();
	}

	public static Date getSystemEndDate() {
		return DATE_UTILS_CONFIGURER.getSystemEndDate();
	}

	public static Date getTodayDate() {
		Calendar calendar = Calendar.getInstance();
		DateUtils.removeTime(calendar);
		return calendar.getTime();
	}

	public static Date getTodayDateTime() {
		return Calendar.getInstance().getTime();
	}

	public static String getShortTodayDate() {
		return DateUtils.formatShortDate(DateUtils.getTodayDate());
	}

	public static Date getTomorrowDate() {
		Calendar calendar = Calendar.getInstance();
		DateUtils.removeTime(calendar);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static String getShortTomorrowDate() {
		return DateUtils.formatShortDate(DateUtils.getTomorrowDate());
	}

	public static Date getYesterdayDate() {
		Calendar calendar = Calendar.getInstance();
		DateUtils.removeTime(calendar);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static String getShortYesterdayDate() {
		return DateUtils.formatShortDate(DateUtils.getYesterdayDate());
	}

	private static void removeTime(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
}
