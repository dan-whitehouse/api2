package org.ricone.logging;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.oneroster.model.Error;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.security.Application;
import org.ricone.security.AuthRequest;
import org.ricone.security.TokenDecoder;
import org.ricone.security.oneroster.OneRosterDecodedToken;
import org.ricone.security.xpress.XPressDecodedToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.apache.commons.io.Charsets.UTF_8;

@Component
@PropertySource("classpath:security.properties")
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger("splunk.logger");
    private final Environment environment;

    public LoggingFilter(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper); /* !Important - Needs to occur before we inspect the response body */

        String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
        logger.debug("response body: " + responseBody);

        try {
            Log log;
            if(Level.INFO.equals(getLogLevel(response.getStatus()))) {
                log = new LogBuilder()
                        .provider(environment.getProperty("security.auth.provider.id"))
                        .component("API")
                        .app(getAppId(request))
                        .request(LogUtil.getRequestUrl(request))
                        .requestHeaders(LogUtil.getHeaders(request).toString())
                        .requestDatetime("")
                        .responseDatetime(LocalDateTime.now().toString())
                        .statusCode(String.valueOf(response.getStatus()))
                        .build();
                logger.info(log.getLog());
            }
            else {
                String[] errors = getErrors(request, responseBody);
                log = new LogBuilder()
                        .provider(environment.getProperty("security.auth.provider.id"))
                        .component("API")
                        .app(getAppId(request))
                        .request(LogUtil.getRequestUrl(request))
                        .requestHeaders(LogUtil.getHeaders(request).toString())
                        .requestDatetime("")
                        .responseDatetime(LocalDateTime.now().toString())
                        .statusCode(String.valueOf(response.getStatus()))
                        .errorMessage(errors[0])
                        .errorDescription(errors[1])
                        .build();
                if(Level.WARN.equals(getLogLevel(response.getStatus()))) {
                    logger.warn(log.getLogWithErrors());
                }
                else if(Level.ERROR.equals(getLogLevel(response.getStatus()))) {
                    logger.error(log.getLogWithErrors());
                }
                else if(Level.DEBUG.equals(getLogLevel(response.getStatus()))) {
                    logger.debug(log.getLogWithErrors());
                }
            }
        }
        catch(Exception e) {
            logger.debug(e.getMessage());
        }
        responseWrapper.copyBodyToResponse(); /* !Important - Without this line, no data is returned to the user */
    }

    private Level getLogLevel(int statusCode) {
        if(LogUtil.isBetween(statusCode, 200, 299)) {
            return Level.INFO;
        }
        else if(LogUtil.isBetween(statusCode, 400, 499)) {
            return Level.WARN;
        }
        else if(LogUtil.isBetween(statusCode, 500, 599)) {
            return Level.ERROR;
        }
        return Level.DEBUG;
    }


    private String[] getErrors(HttpServletRequest request, String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        if(request.getContentType() != null) {
            if(request.getContentType().equalsIgnoreCase("application/xml")) {
                mapper = new XmlMapper();
            }
        }

        try {
            if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
                XErrorResponse errorResponse = mapper.readValue(responseBody, XErrorResponse.class);
                if(errorResponse != null && errorResponse.getError() != null) {
                    return new String[] {errorResponse.getError().getMessage(), errorResponse.getError().getDescription()};
                }
            }
            else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
                ErrorResponse errorResponse = mapper.readValue(responseBody, ErrorResponse.class);
                if(errorResponse != null) {
                    String[] errors = new String[2];
                    errors[0] = errorResponse.getErrors().stream().map(Error::getCodeMinor).map(String::valueOf).collect(Collectors.joining(","));
                    errors[1] = errorResponse.getErrors().stream().map(Error::getDescription).collect(Collectors.joining(","));
                    return new String[] {errors[0], errors[1]};
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"null", "There was an error using the object mapper to decode the error. So... we don't know"};
    }


    private String getAppId(HttpServletRequest request) {
        AuthRequest authRequest = new AuthRequest(request, environment);
        if(StringUtils.isNotBlank(authRequest.getToken())) {
            try {
                if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
                    XPressDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), XPressDecodedToken.class);
                    if(decodedToken != null) {
                        return decodedToken.getApplicationId();
                    }
                }
                else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
                    OneRosterDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), OneRosterDecodedToken.class);
                    if(decodedToken != null) {
                        return decodedToken.getAppId();
                    }
                }
            }
            catch(JWTVerificationException e) {
                //Token Failed to Decode, lets try the opposite decoder. Maybe they gave us the wrong token.
                logger.debug("Trying alternate token decoder...");
                try {
                    if(StringUtils.startsWith(request.getServletPath(), "/requests/")) {
                        OneRosterDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), OneRosterDecodedToken.class);
                        if(decodedToken != null) {
                            return decodedToken.getAppId();
                        }
                    }
                    else if(StringUtils.startsWith(request.getServletPath(), "/ims/oneroster/")) {
                        XPressDecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken(), XPressDecodedToken.class);
                        if(decodedToken != null) {
                            return decodedToken.getApplicationId();
                        }
                    }
                }
                catch (JWTVerificationException ignore) {
                    //Exception is ignorable, because we log this someplace else higher up the chain. However, we need the catch.
                }
            }
        }
        return null;
    }
}