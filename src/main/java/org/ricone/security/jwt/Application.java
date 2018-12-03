package org.ricone.security.jwt;

import org.ricone.config.model.App;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Application implements UserDetails {
    private App app;
    private DecodedToken decodedToken;

    public Application(App app, DecodedToken decodedToken) {
        this.app = app;
        this.decodedToken = decodedToken;
    }

    public App getApp() {
        return app;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return decodedToken.getTokenString();
    }

    @Override
    public String getUsername() {
        return app.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return app.getPublic();
    }
}
