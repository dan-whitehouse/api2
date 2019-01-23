package org.ricone.api.oneroster.error;

import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.CodeMajor;
import org.ricone.api.oneroster.model.CodeMinor;
import org.ricone.api.oneroster.model.Severity;
import org.ricone.api.oneroster.model.StatusInfoSet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController("OneRosterErrorController")
public class ErrorController {
    /**** 20X ****/
    /*@ResponseBody
    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Do nothing
    }*/

    /**** 40X ****/

    /*@ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StatusInfoSet badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        //Another 400 Exception is thrown in XChangesSinceController
        return new StatusInfoSet(request.getRequestURL().toString(), 400, "Bad Request", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({AccessDeniedException.class, UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public StatusInfoSet unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new StatusInfoSet(request.getRequestURL().toString(), 401, "Unauthorized", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public StatusInfoSet forbidden(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new StatusInfoSet(request.getRequestURL().toString(), 403, "Forbidden", ex.getMessage());
    }
*/
    @ResponseBody
    @ExceptionHandler({UnknownObjectException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private ErrorResponse notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        StatusInfoSet error = new StatusInfoSet(CodeMajor.failure, Severity.error, null, null, "Not Found - there is no resource behind the URI", CodeMinor.unknown_object);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }

   /* @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    private StatusInfoSet notAcceptable(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new StatusInfoSet(request.getRequestURL().toString(), 406, "Not Acceptable", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    private StatusInfoSet conflict(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new StatusInfoSet(request.getRequestURL().toString(), 409, "Conflict", ex.getMessage());
    }

    *//**** 50X ****//*
    @ResponseBody
    @ExceptionHandler({Exception.class, MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private StatusInfoSet serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new StatusInfoSet(request.getRequestURL().toString(), 500, "Internal Server Error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    private StatusInfoSet configError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        return new StatusInfoSet(request.getRequestURL().toString(), 503, "Service Unavailable", ex.getMessage());
    }*/
}