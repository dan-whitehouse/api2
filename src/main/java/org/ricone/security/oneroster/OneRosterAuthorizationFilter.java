package org.ricone.security.oneroster;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.cache.CacheService;
import org.ricone.security.Application;
import org.ricone.security.BaseAuthenticationFilter;
import org.ricone.security.TokenDecoder;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Objects;

public class OneRosterAuthorizationFilter extends BaseAuthenticationFilter {
    private final CacheService cacheService;
    private final Environment environment;

    public OneRosterAuthorizationFilter(AuthenticationManager authManager, CacheService cacheService, Environment environment) {
        super(authManager, environment);
        this.cacheService = cacheService;
        this.environment = environment;
    }

    @Override
    protected UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) throws JWTVerificationException {
        DecodedToken decodedToken = TokenDecoder.decodeToken(token, DecodedToken.class);

        Application application = null;
        if(decodedToken != null) {
            if(!Objects.requireNonNull(environment.getProperty("security.auth.provider.id")).equalsIgnoreCase(decodedToken.getProviderId())) {
                throw new JWTVerificationException(environment.getProperty("security.auth.error.wrong-provider"));
            }
            application = new Application(decodedToken.getAppId(), token, cacheService);
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