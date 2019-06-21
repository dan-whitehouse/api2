package org.ricone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.logging.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public abstract class TokenDecoder {
	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	public static <T> T decodeToken(String token, Class<T> clazz) throws JWTVerificationException {
		try {
			DecodedJWT jwt = JWT.decode(token);
			ObjectMapper map = new ObjectMapper();
			return map.readValue(base64Decode(jwt.getPayload()), clazz);
		}
		catch (Exception e) {
			throw new JWTVerificationException("Invalid Authorization Token provided");
		}
	}

	private static String base64Decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		return new String(decodedBytes);
	}
}
