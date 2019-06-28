package org.ricone.security;

import java.io.Serializable;

public class BaseDecodedToken implements Serializable {
    private final static long serialVersionUID = -2798265389832101234L;
    private String app;
    private String provider;

	public BaseDecodedToken() { }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}