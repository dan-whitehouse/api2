package org.ricone.security.oneroster;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.ricone.security.BaseTokenDecoder;

import java.io.IOException;

class TokenDecoder extends BaseTokenDecoder {
    static DecodedToken decodeToken(String token) throws JWTDecodeException {
        DecodedJWT jwt = JWT.decode(token);
        try {
            ObjectMapper map = new ObjectMapper();
            DecodedToken dt = map.readValue(base64Decode(jwt.getPayload()), DecodedToken.class);
            dt.setToken(token);
            return dt;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}