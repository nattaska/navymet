package com.ss.sq.util.configurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DateUtilsConfigurer implements InitializingBean {
	private Locale defaultLocale;
	private String shortDateFormat;
	private SimpleDateFormat shortSimpleDateFormat;
	private String shortSystemEndDate;
	private Date systemEndDate;

	public DateUtilsConfigurer() throws ParseException {
		defaultLocale = Locale.US;
		shortDateFormat = "dd/MM/yyyy";
		shortSystemEndDate = "31/12/2049";
		shortSimpleDateFormat = new SimpleDateFormat(shortDateFormat, defaultLocale);
		systemEndDate = shortSimpleDateFormat.parse(shortSystemEndDate);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		shortSimpleDateFormat = new SimpleDateFormat(shortDateFormat, defaultLocale);
		systemEndDate = shortSimpleDateFormat.parse(shortSystemEndDate);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setShortDateFormat(String shortDateFormat) {
		this.shortDateFormat = shortDateFormat;
	}

	public String getShortDateFormat() {
		return shortDateFormat;
	}

	public SimpleDateFormat getShortSimpleDateFormat() {
		return shortSimpleDateFormat;
	}

	public String getShortSystemEndDate() {
		return shortSystemEndDate;
	}

	public void setShortSystemEndDate(String shortSystemEndDate) {
		this.shortSystemEndDate = shortSystemEndDate;
	}

	public Date getSystemEndDate() {
		return systemEndDate;
	}
}
