package com.ss.sq.common;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JsonHttpResponderConfig {
	private static final JsonConfig JSON_CONFIG = new JsonConfig();

	static {
		PropertyFilter propertyFilter = new PropertyFilter() {
			@Override
			public boolean apply(Object source, String key, Object value) {
				boolean result = false;
				if (value == null)
					result = true;
				return result;
			}
		};
		JSON_CONFIG.setJsonPropertyFilter(propertyFilter);
	}

	public static JsonConfig getJsonConfig() {
		return JSON_CONFIG;
	}
}
