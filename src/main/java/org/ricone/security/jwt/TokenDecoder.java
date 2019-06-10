package org.ricone.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Base64;

class TokenDecoder {
    static DecodedToken decodeToken(String token) {
        ObjectMapper map = new ObjectMapper();
        String[] base64EncodedSegments = StringUtils.split(token, "\\.");

        try {
            DecodedToken dt = null;
            dt = map.readValue(base64Decode(base64EncodedSegments[1]), DecodedToken.class);
            dt.setTokenString(token);
            return dt;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String base64Decode(String input) {
        String result = null;
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        result = new String(decodedBytes);
        return result;
    }
}