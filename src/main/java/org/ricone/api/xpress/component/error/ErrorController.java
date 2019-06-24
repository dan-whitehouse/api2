package org.ricone.api.xpress.component.error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ricone.api.xpress.component.error.exception.*;
import org.ricone.api.xpress.model.XError;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.error.NoContentException;
import org.ricone.logging.Log;
import org.ricone.logging.LogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController("xPressErrorController")
@ControllerAdvice(basePackages = "org.ricone.api.xpress")
@PropertySource("classpath:exception.properties")
public class ErrorController {
    private final Environment environment;
    private static final Logger logger = LoggerFactory.getLogger("splunk.logger");

    public ErrorController(Environment environment) {
        this.environment = environment;
    }

    /* 20X */
    @ResponseBody
    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT) //204
    public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Do nothing
    }

    /* 40X */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public XErrorResponse badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Another 400 Exception is thrown in XChangesSinceController
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 400, "Bad Request", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({AccessDeniedException.class, UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public XErrorResponse unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 401, "Unauthorized", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public XErrorResponse forbidden(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 403, "Forbidden", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private XErrorResponse notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 404, "Not Found", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    private XErrorResponse notAcceptable(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 406, "Not Acceptable", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    private XErrorResponse conflict(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 409, "Conflict", ex.getMessage()));
    }

    /* 50X */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private XErrorResponse serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 500, "Internal Server Error", ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private XErrorResponse mappingError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 500, "Internal Server Error", environment.getProperty("exception.mapping")));
    }

    @ResponseBody
    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    private XErrorResponse configError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 503, "Service Unavailable", ex.getMessage()));
    }
}