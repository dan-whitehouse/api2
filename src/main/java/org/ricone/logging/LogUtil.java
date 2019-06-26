package org.ricone.logging;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ricone.api.oneroster.model.Error;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.security.AuthRequest;
import org.ricone.security.TokenDecoder;
import org.ricone.security.oneroster.OneRosterDecodedToken;
import org.ricone.security.xpress.XPressDecodedToken;
import org.slf4j.event.Level;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogUtil {

	public static String logStacktrace(Environment environment, HttpServletRequest request, Exception ex, Level level, int statusCode) {
		return new LogBuilder()
				.uuid(request.getAttribute("uuid").toString())
				.component("API")
				.provider(environment.getProperty("security.auth.provider.id"))
				.app(getAppId(request, environment))
				.level(level)
				.statusCode(statusCode)
				.errorMessage(ExceptionUtils.getRootCauseMessage(ex))
				.errorDescription(ExceptionUtils.getStackTrace(ex))
				.build().toLogWithStacktrace();
	}


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

	static String formatByteSize(long v) {
		if (v < 1024) return v + " B";
		int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
		return String.format("%.2f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}

	static Level getLogLevel(int statusCode) {
		if(LogUtil.isBetween(statusCode, 200, 299)) {
			return Level.INFO;
		}
		else if(LogUtil.isBetween(statusCode, 400, 499)) {
			return Level.WARN;
		}
		else if(LogUtil.isBetween(statusCode, 500, 599)) {
			return Level.ERROR;
		}
		return Level.DEBUG;
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

	static String getAppId(HttpServletRequest request, Environment environment) {
		AuthRequest authRequest = new AuthRequest(request, environment);
		if(StringUtils.isNotBlank(authRequest.getToken())) {
			try {
				if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
					XPressDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), XPressDecodedToken.class);
					if(decodedToken != null) {
						return decodedToken.getApplicationId();
					}
				}
				else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
					OneRosterDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), OneRosterDecodedToken.class);
					if(decodedToken != null) {
						return decodedToken.getAppId();
					}
				}
			}
			catch(JWTVerificationException e) {
				//Token Failed to Decode, lets try the opposite decoder. Maybe they gave us the wrong token.
				try {
					if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
						OneRosterDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), OneRosterDecodedToken.class);
						if(decodedToken != null) {
							return decodedToken.getAppId();
						}
					}
					else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
						XPressDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), XPressDecodedToken.class);
						if(decodedToken != null) {
							return decodedToken.getApplicationId();
						}
					}
				}
				catch (JWTVerificationException ignore) {
					//Exception is ignorable, because we log this someplace else higher up the chain. However, we need the catch.
				}
			}
		}
		return null;
	}

	static String[] getErrors(HttpServletRequest request, String responseBody) {
		ObjectMapper mapper = new ObjectMapper();
		if(request.getContentType() != null) {
			if(request.getContentType().equalsIgnoreCase("application/xml")) {
				mapper = new XmlMapper();
			}
		}

		try {
			if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
				XErrorResponse errorResponse = mapper.readValue(responseBody, XErrorResponse.class);
				if(errorResponse != null && errorResponse.getError() != null) {
					return new String[] {errorResponse.getError().getMessage(), errorResponse.getError().getDescription()};
				}
			}
			else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
				ErrorResponse errorResponse = mapper.readValue(responseBody, ErrorResponse.class);
				if(errorResponse != null) {
					String[] errors = new String[2];
					errors[0] = errorResponse.getErrors().stream().map(Error::getCodeMinor).map(String::valueOf).collect(Collectors.joining(","));
					errors[1] = errorResponse.getErrors().stream().map(Error::getDescription).collect(Collectors.joining(","));
					return new String[] {errors[0], errors[1]};
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return new String[]{"null", "There was an error using the object mapper to decode the error. So... we don't know"};
	}

	private static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
}
