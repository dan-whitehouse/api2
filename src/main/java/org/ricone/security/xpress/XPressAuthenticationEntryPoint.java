package org.ricone.security.xpress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.model.XError;
import org.ricone.api.xpress.model.XErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component("Security:XPress:AuthenticationEntryPoint")
public class XPressAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
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

            XErrorResponse errorResponse = new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 401, "Unauthorized", (String)request.getAttribute("JWTVerificationException")));
            response.getOutputStream().println(mapper.writeValueAsString(errorResponse));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}