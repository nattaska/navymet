package com.ss.sq.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonHttpResponder {
	public String toJson() {
		return JSONObject.fromObject(this, JsonHttpResponderConfig.getJsonConfig()).toString();
	}

	public void responseJson(HttpServletResponse response) {
		response.setContentType("application/json");
		PrintWriter writer = null;
		try {
			try {
				writer = response.getWriter();
				writer.write(toJson());
			} finally {
				if (writer != null)
					writer.close();
			}
		} catch (Exception e) {
			throw new IllegalStateException("Cannot response JSON back to the HTTP client", e);
		}
	}
}
