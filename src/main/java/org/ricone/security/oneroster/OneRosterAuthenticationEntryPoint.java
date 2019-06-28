package org.ricone.security.oneroster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.oneroster.model.CodeMajor;
import org.ricone.api.oneroster.model.CodeMinor;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.ricone.api.oneroster.model.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("Security:OneRoster:AuthenticationEntryPoint")
public class OneRosterAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType(request.getContentType());
            if(StringUtils.isBlank(request.getContentType())) {
                response.setContentType("application/json");
            }

            ObjectMapper mapper = new ObjectMapper();
            if(request.getContentType() != null) {
                if(request.getContentType().equalsIgnoreCase("application/xml")) {
                    mapper = new XmlMapper();
                }
            }

            //https://medium.com/fullstackblog/spring-security-jwt-token-expired-custom-response-b85437914b81
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.getErrors().add(new org.ricone.api.oneroster.model.Error(Severity.error, CodeMajor.failure, CodeMinor.unauthorized, (String)request.getAttribute("JWTVerificationException")));
            response.getOutputStream().println(mapper.writeValueAsString(errorResponse));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}