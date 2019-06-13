package org.ricone.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.cache.CacheService;
import org.ricone.security.Application;
import org.springframework.core.env.Environment;
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


public class XPressAuthorizationFilter extends BasicAuthenticationFilter {
    private final CacheService cacheService;
    private final Environment environment;

    public XPressAuthorizationFilter(AuthenticationManager authManager, CacheService cacheService, Environment environment) {
        super(authManager);
        this.cacheService = cacheService;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        AuthRequest authRequest = new AuthRequest(req);
        if(authRequest.isAuthEnabled()) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req, authRequest.getToken());
            if(authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, String token) {
        try {
            if(StringUtils.isBlank(token)) {
                throw new JWTVerificationException("token was empty....");
            }

            DecodedToken decodedToken = TokenDecoder.decodeToken(token);

            Application application = null;
            if(decodedToken != null) {
                application = new Application(decodedToken.getApplicationId(), token, cacheService);
            }

            if(application != null && application.getApp().hasProviderSecret()) {
                JWT.require(Algorithm.HMAC256(application.getApp().getProviderSecret().getBytes()))
                        .withIssuer(environment.getProperty("security.auth.jwt.issuer"))
                        .build().verify(token);
                return new UsernamePasswordAuthenticationToken(application, token, getACLs(application));
            }
        }
        catch (JWTVerificationException exception) {
            //https://medium.com/fullstackblog/spring-security-jwt-token-expired-custom-response-b85437914b81
            req.setAttribute("JWTVerificationException", exception.getMessage());
            return null;
        }
        return null; //DecodedToken or Application was null... 403 Forbidden
    }


    private Collection<GrantedAuthority> getACLs(Application application) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        application.getPermissions().forEach(pathPermission -> {
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
        });
        return grantedAuthorities;
    }
}