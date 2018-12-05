package org.ricone.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.cache.AppCache;
import org.ricone.config.model.App;
import org.ricone.config.model.District;
import org.ricone.security.PropertiesLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        AuthRequest authRequest = new AuthRequest(req);
        if(authRequest.isAuthEnabled()) {
            if(authRequest.isHeader() || (authRequest.isParameter() && authRequest.isAllowTokenParameter())) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        else {
            Application application = getDisabledSecurityAccount();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(application, null, getACLs(application.getApp()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(AuthRequest authRequest) {
        if(StringUtils.isBlank(authRequest.getToken())) {
            return null;  //Token was blank... 403 Forbidden
        }

        DecodedToken decodedToken = null;
        try {
            decodedToken = TokenDecoder.decodeToken(authRequest.getToken());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Application application = null;
        if(decodedToken != null) {
            App app = AppCache.getInstance().get(decodedToken.getApplication_id());
            if(app != null) {
                application = new Application(app, decodedToken);
            }
        }

        try {
            if(application != null && StringUtils.isNotBlank(application.getApp().getProviderSecret())) {
                JWT.require(Algorithm.HMAC256(application.getApp().getProviderSecret().getBytes()))
                        .withIssuer(PropertiesLoader.getInstance().getProperty("security.auth.jwt.issuer"))
                        .build().verify(authRequest.getToken());
                return new UsernamePasswordAuthenticationToken(application, decodedToken.getTokenString(), getACLs(application.getApp()));
            }
        }
        catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage()); //Failed to verify the token... 403 Forbidden
            return null;
        }
        return null; //DecodedToken or Application was null... 403 Forbidden
    }

    private Collection<GrantedAuthority> getACLs(App app) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        FakePermissionsLoader.getPathPermissions().forEach(pathPermission -> {
            if(pathPermission.getGet()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("get:" + pathPermission.getPath()));
            }
            if(pathPermission.getPost()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("post:" + pathPermission.getPath()));
            }
            if(pathPermission.getPut()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("put:" + pathPermission.getPath()));
            }
            if(pathPermission.getDelete()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("delete:" + pathPermission.getPath()));
            }
            if(pathPermission.getHead()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("head:" + pathPermission.getPath()));
            }
        });
        return grantedAuthorities;
    }

    private Application getDisabledSecurityAccount() {
        District district = new District();
        district.setId("530501");

        App app = new App();
        app.setId("NoSecurityAccount");
        app.setPublic(true);
        app.getDistricts().add(district);

        DecodedToken decodedToken = new DecodedToken();
        decodedToken.setTokenString("password");

        return new Application(app, decodedToken);
    }
}