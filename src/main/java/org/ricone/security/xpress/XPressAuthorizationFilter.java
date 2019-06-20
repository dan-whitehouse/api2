package org.ricone.security.xpress;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.cache.AppService;
import org.ricone.security.Application;
import org.ricone.security.BaseAuthenticationFilter;
import org.ricone.security.BaseDecodedToken;
import org.ricone.security.TokenDecoder;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class XPressAuthorizationFilter extends BaseAuthenticationFilter {
    private final AppService cacheService;
    private final Environment environment;

    public XPressAuthorizationFilter(AuthenticationManager authManager, AppService cacheService, Environment environment) {
        super(authManager, environment);
        this.cacheService = cacheService;
        this.environment = environment;
    }

    @Override
    protected UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) throws JWTVerificationException {
        XPressDecodedToken decodedToken = TokenDecoder.decodeToken(token, XPressDecodedToken.class);

        Application application = null;
        if(decodedToken != null) {
            application = new Application(decodedToken.getApplicationId(), token, cacheService);
        }

        if(application != null && StringUtils.isNotBlank(application.getApp().getProviderSecret())) {
            JWT.require(Algorithm.HMAC256(application.getApp().getProviderSecret().getBytes()))
                    .withIssuer(environment.getProperty("security.auth.jwt.issuer"))
                    .build().verify(token);
            return new UsernamePasswordAuthenticationToken(application, token, super.getACLs(application));
        }
        return null;
    }
}