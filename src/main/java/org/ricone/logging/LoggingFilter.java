package org.ricone.logging;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

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

        /* !Important - Needs to occur before we inspect the response body - DO NOT REMOVE */
        filterChain.doFilter(requestWrapper, responseWrapper);

        //Convert the InputStream into our response body
        String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);

        try {
            Log log;
            LocalDateTime before = (LocalDateTime)request.getAttribute("requestDatetime");
            LocalDateTime now = LocalDateTime.now();
            Level level = LogUtil.getLogLevel(response.getStatus());

            if(Level.INFO.equals(level)) {
                log = new LogBuilder()
                        .uuid(request.getAttribute("uuid").toString())
                        .level(LogUtil.getLogLevel(response.getStatus()))
                        .provider(environment.getProperty("security.auth.provider.id"))
                        .component("API")
                        .app(LogUtil.getAppId(request, environment))
                        .request(LogUtil.getRequestUrl(request))
                        .requestHeaders(LogUtil.getHeaders(request).toString())
                        .requestDatetime(request.getAttribute("requestDatetime").toString())
                        .responseDatetime(LocalDateTime.now().toString())
                        .duration(Duration.between(before, now).toMillis()+"ms")
                        .statusCode(response.getStatus())
                        .build();
                logger.info("LoggingFilter: " + log.getLog());
            }
            else {
                String[] errors = LogUtil.getErrors(request, responseBody);
                log = new LogBuilder()
                        .uuid(request.getAttribute("uuid").toString())
                        .level(LogUtil.getLogLevel(response.getStatus()))
                        .provider(environment.getProperty("security.auth.provider.id"))
                        .component("API")
                        .app(LogUtil.getAppId(request, environment))
                        .request(LogUtil.getRequestUrl(request))
                        .requestHeaders(LogUtil.getHeaders(request).toString())
                        .requestDatetime(request.getAttribute("requestDatetime").toString())
                        .responseDatetime(LocalDateTime.now().toString())
                        .duration(Duration.between(before, now).toMillis()+"ms")
                        .statusCode(response.getStatus())
                        .errorMessage(errors[0])
                        .errorDescription(errors[1])
                        .build();
                if(Level.WARN.equals(level)) {
                    logger.warn("LoggingFilter: " + log.getLogWithErrors());
                }
                else if(Level.ERROR.equals(level)) {
                    logger.error("LoggingFilter: " + log.getLogWithErrors());
                }
                else if(Level.DEBUG.equals(level)) {
                    logger.debug("LoggingFilter: " + log.getLogWithErrors());
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            logger.debug("LoggingFilter Catch: " + e.getMessage());
        }

        /* !Important - Without this line, no data is returned to the user */
        responseWrapper.copyBodyToResponse();
    }

}