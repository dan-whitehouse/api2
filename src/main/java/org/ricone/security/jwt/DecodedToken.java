package org.ricone.security.jwt;

import java.util.Date;

public class DecodedToken {
    private String application_id;
    private Date iat;
    private Date exp;
    private String iss;
    private String tokenString;

    public DecodedToken() {
        super();
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = new Date(Long.parseLong(iat + "000"));
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = new Date(Long.parseLong(exp + "000"));
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }
}