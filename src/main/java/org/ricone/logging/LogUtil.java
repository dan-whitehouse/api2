package org.ricone.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LogUtil {

	static Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	static Map<String, String> getHeaders(HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		Collection<String> headerNames = response.getHeaderNames();
		headerNames.forEach(headerName -> {
			map.put(headerName, response.getHeader(headerName));
		});
		return map;
	}

	static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

	static String getRequestUrl(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();

		if (queryString == null) {
			return request.getMethod() + " " + requestURL.toString();
		} else {
			return request.getMethod() + " " + requestURL.append('?').append(queryString).toString();
		}
	}
}
