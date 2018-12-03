package org.ricone.security.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Base64;

public class TokenDecoder {
    public static DecodedToken decodeToken(String token) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper map = new ObjectMapper();
        String[] base64EncodedSegments = StringUtils.split(token, "\\.");
        DecodedToken dt = map.readValue(base64Decode(base64EncodedSegments[1]), DecodedToken.class);
        dt.setTokenString(token);
        return dt;
    }

    private static String base64Decode(String input) {
        String result = null;
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        result = new String(decodedBytes);
        return result;
    }
}