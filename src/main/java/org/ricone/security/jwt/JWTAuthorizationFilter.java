package org.ricone.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.ConfigService;
import org.ricone.config.cache.AppCache;
import org.ricone.config.model.App;
import org.ricone.config.model.DataXML;
import org.ricone.config.model.District;
import org.ricone.init.CacheService;
import org.ricone.security.PropertiesLoader;
import org.ricone.security.acl.Environment;
import org.ricone.security.acl.PathPermission;
import org.ricone.security.acl.PathPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CacheService cacheService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, CacheService cacheService) {
        super(authManager);
        this.cacheService = cacheService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        AuthRequest authRequest = new AuthRequest(req);
        if(authRequest.isAuthEnabled()) {
            if(authRequest.isHeader() || (authRequest.isParameter() && authRequest.isAllowTokenParameter())) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(req, authRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        else {
            Application application = getDisabledSecurityAccount();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(application, null, getFakeACLs(application.getApp()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, AuthRequest authRequest) {
        if(StringUtils.isBlank(authRequest.getToken())) {
            return null;  //Token was blank... 403 Forbidden
        }

        DecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken());

        Application application = null;
        if(decodedToken != null) {
            application = new Application(decodedToken.getApplication_id(), decodedToken.getTokenString(), cacheService);
        }

        try {
            if(application != null && StringUtils.isNotBlank(application.getApp().getProviderSecret())) {
                JWT.require(Algorithm.HMAC256(application.getApp().getProviderSecret().getBytes()))
                        .withIssuer(PropertiesLoader.getInstance().getProperty("security.auth.jwt.issuer"))
                        .build().verify(authRequest.getToken());
                return new UsernamePasswordAuthenticationToken(application, decodedToken.getTokenString(), getACLs(application));
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


    private Collection<GrantedAuthority> getFakeACLs(App app) {
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

        return new Application(decodedToken.getApplication_id(), decodedToken.getTokenString(), cacheService);
    }
}