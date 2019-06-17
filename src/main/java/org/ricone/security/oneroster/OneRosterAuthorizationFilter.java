package org.ricone.security.oneroster;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
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
import java.util.Objects;

public class OneRosterAuthorizationFilter extends BasicAuthenticationFilter {
    private final CacheService cacheService;
    private final Environment environment;

    public OneRosterAuthorizationFilter(AuthenticationManager authManager, CacheService cacheService, Environment environment) {
        super(authManager);
        this.cacheService = cacheService;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        AuthRequest authRequest = new AuthRequest(req);
        if(authRequest.isAuthEnabled()) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req, authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, AuthRequest authRequest) {
        try {
            if(StringUtils.isBlank(authRequest.getToken())) {
                throw new JWTVerificationException(environment.getProperty("security.auth.error.token-blank"));
            }

            DecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken());

            Application application = null;
            if(decodedToken != null) {
                if(!Objects.requireNonNull(environment.getProperty("security.auth.jwt.provider.id")).equalsIgnoreCase(decodedToken.getProviderId())) {
                    throw new JWTVerificationException(environment.getProperty("security.auth.error.wrong-provider"));
                }
                application = new Application(decodedToken.getAppId(), decodedToken.getToken(), cacheService);
            }

            if(application != null && StringUtils.isNotBlank(application.getApp().getProviderSecret())) {
                JWT.require(Algorithm.HMAC256(application.getApp().getProviderSecret().getBytes()))
                        .withIssuer(environment.getProperty("security.auth.jwt.issuer"))
                        .build().verify(authRequest.getToken());
                return new UsernamePasswordAuthenticationToken(application, decodedToken.getToken(), getACLs(application));
            }
        }
        catch(SignatureVerificationException | JWTDecodeException exception) {
            req.setAttribute("JWTVerificationException", environment.getProperty("security.auth.error.invalid"));
        }
        catch (JWTVerificationException exception) {
            //https://medium.com/fullstackblog/spring-security-jwt-token-expired-custom-response-b85437914b81
            req.setAttribute("JWTVerificationException", exception.getMessage());
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