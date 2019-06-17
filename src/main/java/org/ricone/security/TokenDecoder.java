package org.ricone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;

public abstract class TokenDecoder {

	public static <T> T decodeToken(String token, Class<T> clazz) throws JWTDecodeException {
		DecodedJWT jwt = JWT.decode(token);
		try {
			ObjectMapper map = new ObjectMapper();
			return map.readValue(base64Decode(jwt.getPayload()), clazz);
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
