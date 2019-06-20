package org.ricone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.ricone.logging.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;

public abstract class TokenDecoder {
	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	public static <T> T decodeToken(String token, Class<T> clazz) throws JWTDecodeException {
		DecodedJWT jwt = JWT.decode(token);
		try {
			ObjectMapper map = new ObjectMapper();
			return map.readValue(base64Decode(jwt.getPayload()), clazz);
		}
		catch(UnrecognizedPropertyException e) {
			throw new JWTDecodeException("Invalid Authorization Token provided");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String base64Decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		return new String(decodedBytes);
	}
}
