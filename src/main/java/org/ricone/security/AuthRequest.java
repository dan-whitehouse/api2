package org.ricone.security;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AuthRequest {
    private boolean allowTokenParameter;
    private boolean isParameter;
    private boolean isHeader;
    private String token;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    AuthRequest(HttpServletRequest request) {
        allowTokenParameter = allowTokenParams();
        isHeader = StringUtils.isNotBlank(request.getHeader("Authorization"));
        isParameter = StringUtils.isNotBlank(request.getParameter("access_token"));

        try {
            if(isHeader) {
                //Strip away the key if Bearer, otherwise it will keep it's key and fail
                token = StringUtils.replace(request.getHeader("Authorization"), "Bearer", "");
                if(StringUtils.containsWhitespace(token)) {
                    token = StringUtils.deleteWhitespace(token);
                }
            }
            else if(isParameter) { //Parameter tokens are allowed, and parameter is set
                token = request.getParameter("access_token");
            }
            else {
                token = null;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private boolean allowTokenParams() {
        return BooleanUtils.toBoolean(PropertiesLoader.getInstance().getProperty("security.auth.jwt.allowTokenParameter"));
    }

    boolean isAuthEnabled() {
        return BooleanUtils.toBoolean(PropertiesLoader.getInstance().getProperty("security.auth.enabled"));
    }

    boolean isAllowTokenParameter() {
        return allowTokenParameter;
    }

    boolean isParameter() {
        return isParameter;
    }

    boolean isHeader() {
        return isHeader;
    }

    public String getToken() {
        return token;
    }


    @Override
    public String toString() {
        return "AuthRequest{" + "allowTokenParameter=" + allowTokenParameter + ", isParameter=" + isParameter + ", isHeader=" + isHeader + ", token='" + token + '\'' + '}';
    }
}