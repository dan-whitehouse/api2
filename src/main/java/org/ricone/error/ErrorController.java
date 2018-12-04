package org.ricone.error;

import org.ricone.error.Error;
import org.ricone.error.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class ErrorController {
    /**** 20X ****/
    @ResponseBody
    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Do nothing
    }

    /**** 40X ****/

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Another 400 Exception is thrown in XChangesSinceController
        return new Error(request.getRequestURL().toString(), 400, "Bad Request", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({AccessDeniedException.class, UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Error unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 401, "Unauthorized", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Error forbidden(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 403, "Forbidden", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private Error notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 404, "Not Found", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    private Error notAcceptable(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 406, "Not Acceptable", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    private Error conflict(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 409, "Conflict", ex.getMessage());
    }

    /**** 50X ****/
    @ResponseBody
    @ExceptionHandler({Exception.class, MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private Error serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Error(request.getRequestURL().toString(), 500, "Internal Server Error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    private Error configError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        return new Error(request.getRequestURL().toString(), 503, "Service Unavailable", ex.getMessage());
    }
}