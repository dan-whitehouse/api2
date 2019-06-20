package org.ricone.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LogUtil {

	public static Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	public static Map<String, String> getHeaders(HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		Collection<String> headerNames = response.getHeaderNames();
		headerNames.forEach(headerName -> {
			map.put(headerName, response.getHeader(headerName));
		});
		return map;
	}
}
