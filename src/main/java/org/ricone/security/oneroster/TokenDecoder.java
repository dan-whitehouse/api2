package org.ricone.security.oneroster;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.ricone.security.BaseTokenDecoder;

import java.io.IOException;

class TokenDecoder extends BaseTokenDecoder {
    static DecodedToken decodeToken(String token) {
        ObjectMapper map = new ObjectMapper();
        String[] base64EncodedSegments = StringUtils.split(token, "\\.");

        try {
            DecodedToken dt = null;
            dt = map.readValue(base64Decode(base64EncodedSegments[1]), DecodedToken.class);
            dt.setToken(token);
            return dt;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}