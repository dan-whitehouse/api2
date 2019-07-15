package org.ricone.api.xpress.component.error;

import org.ricone.api.xpress.component.error.exception.*;
import org.ricone.api.xpress.model.XError;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.error.NoContentException;
import org.ricone.logging.LogUtil;
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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("xPressErrorController")
@RestControllerAdvice(basePackages = "org.ricone.api.xpress")
@PropertySource("classpath:exception.properties")
public class ErrorController {
    private final Environment environment;
    private static final Logger logger = LoggerFactory.getLogger("splunk.logger");

    public ErrorController(Environment environment) {
        this.environment = environment;
    }

    /* 20X */
    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT) //204
    public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Do nothing
    }

    /* 40X */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public XErrorResponse badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Another 400 Exception is thrown in XChangesSinceController
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 400, "Bad Request", ex.getMessage()));
    }

    @ExceptionHandler({AccessDeniedException.class, UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public XErrorResponse unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 401));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 401, "Unauthorized", ex.getMessage()));
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public XErrorResponse forbidden(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 403));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 403, "Forbidden", ex.getMessage()));
    }

    @ExceptionHandler({NotFoundException.class, NoHandlerFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private XErrorResponse notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 404));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 404, "Not Found", ex.getMessage()));
    }

    @GetMapping(value = "/requests/**")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private XErrorResponse noHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            This method is special in that it isn't an actual Exception handler.
            For whatever reason, 404's are not caught when the path is not real. Ie: NoHandlerFoundException is thrown.
                ie: /xSchools = good || /school = bad
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 404));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 404, "Not Found", ex.getMessage()));
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    private XErrorResponse notAcceptable(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 406));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 406, "Not Acceptable", ex.getMessage()));
    }

    @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    private XErrorResponse conflict(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 409));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 409, "Conflict", ex.getMessage()));
    }

    /* 50X */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private XErrorResponse serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(LogUtil.logStacktrace(environment, request, ex, Level.ERROR, 500));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 500, "Internal Server Error", ex.getMessage()));
    }

    @ExceptionHandler({MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private XErrorResponse mappingError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(LogUtil.logStacktrace(environment, request, ex, Level.ERROR, 500));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 500, "Internal Server Error", environment.getProperty("exception.mapping")));
    }

    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    private XErrorResponse configError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(LogUtil.logStacktrace(environment, request, ex, Level.ERROR, 503));
        return new XErrorResponse(new XError(request.getAttribute("uuid").toString(), 503, "Service Unavailable", ex.getMessage()));
    }
}