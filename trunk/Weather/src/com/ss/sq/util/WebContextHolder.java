package com.ss.sq.util;

import javax.servlet.ServletContext;

public class WebContextHolder {

	private static ServletContext servletContext;

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		WebContextHolder.servletContext = servletContext;
	}
	
}
