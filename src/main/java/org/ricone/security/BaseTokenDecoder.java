package org.ricone.security;

import java.util.Base64;

public abstract class BaseTokenDecoder {
	protected static String base64Decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		return new String(decodedBytes);
	}
}
