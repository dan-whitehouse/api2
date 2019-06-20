package org.ricone.logging;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.security.Application;
import org.ricone.security.AuthRequest;
import org.ricone.security.TokenDecoder;
import org.ricone.security.oneroster.OneRosterDecodedToken;
import org.ricone.security.xpress.XPressDecodedToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static org.apache.commons.io.Charsets.UTF_8;

@Component
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

        filterChain.doFilter(requestWrapper, responseWrapper);

        /*logger.debug("-------------------------");
        logger.debug("request.getPathInfo(): " + request.getPathInfo());
        logger.debug("request.getServletPath(): " + request.getServletPath());
        logger.debug("request.getRequestURI(): " + request.getRequestURI());
        logger.debug("request.getAuthType(): " + request.getAuthType());
        logger.debug("request.getContextPath(): " + request.getContextPath());
        logger.debug("request.getMethod(): " + request.getMethod());
        logger.debug("request.getPathTranslated(): " + request.getPathTranslated());
        logger.debug("request.getUserPrincipal(): " + request.getUserPrincipal());
        logger.debug("request.getRequestURL(): " + request.getRequestURL());
        logger.debug("request.getServletContext(): " + request.getServletContext());
        logger.debug("request.getRequestedSessionId(): " + request.getRequestedSessionId());
        logger.debug("request.getQueryString(): " + request.getQueryString());
        logger.debug("request.getRemoteUser(): " + request.getRemoteUser());
        logger.debug("request.getCharacterEncoding(): " + request.getCharacterEncoding());
        logger.debug("request.getContentType(): " + request.getContentType());
        logger.debug("Authorization Header: " + request.getHeader("Authorization"));*/

        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            logger.debug("SecurityContextHolder.getContext().getAuthentication(): " + ((Application)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        }
        else {
            logger.debug("SecurityContextHolder.getContext().getAuthentication(): " + "Null... Decoded Token: " + getAppId(request));
        }

        String output = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
        logger.debug("response body: " + output);

        try {
            Log log = new LogBuilder()
                    .component("API")
                    .app(getAppId(request))
                    .request(request.getRequestURI())
                    .requestHeaders(LogUtil.getHeaders(request).toString())
                    .requestDatetime(Instant.now().toString())
                    .responseDatetime("")
                    .statusCode(String.valueOf(response.getStatus()))
                    //.errorMessage(ex != null ? ex.getMessage() : null)
                    //.errorDescription(ex != null ? ex.getMessage() : null)
                    .build();

            logger.info(log.toString());
        }
        catch(Exception e) {
            e.printStackTrace();
        }



        responseWrapper.copyBodyToResponse(); /* !Important - Without this line, no data is returned to the user */
    }


    private String getAppId(HttpServletRequest request) {
        AuthRequest authRequest = new AuthRequest(request, environment);
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
        catch(JWTDecodeException e) {
            //Token Failed to Decode, lets try the opposite decoder. Maybe they gave us the wrong token.
            logger.debug("Trying alternate token decoder...");
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
        catch(Exception e){e.printStackTrace();}
        return null;
    }
}