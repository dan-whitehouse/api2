package org.ricone.security;

import java.util.Base64;

public abstract class BaseTokenDecoder {
	protected static String base64Decode(String input) {
		String result = null;
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		result = new String(decodedBytes);
		return result;
	}
}
