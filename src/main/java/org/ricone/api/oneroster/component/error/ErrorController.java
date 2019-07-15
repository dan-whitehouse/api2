package org.ricone.api.oneroster.component.error;

import org.ricone.api.oneroster.component.error.exception.*;
import org.ricone.api.oneroster.model.Error;
import org.ricone.api.oneroster.model.*;
import org.ricone.error.NoContentException;
import org.ricone.logging.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice(basePackages = "org.ricone.api.oneroster")
@RestController("OneRosterErrorController")
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
    @ExceptionHandler({BlankFieldSelectionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse blankFieldSelectionException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If the consumer requests that data be selected using a blank field the request will be treated as an invalid request.
			The server must provide the associated transaction status code information of:
				•  CodeMajor value is 'failure';
				•  Severity value is 'error';
				•  CodeMinor value is 'invalid_blank_selection_field';
				•  StatusCode value is the corresponding HTTP response code.
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_blank_selection_field, "The fields parameter can not be blank")));
    }

    @ExceptionHandler({InvalidFilterFieldException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse invalidFilterFieldException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If the consumer requests that data be filtered by a non-existent field, NO data is returned and the server must
            provide the associated transaction status code information of:
                •  CodeMajor value is 'failure';
                •  Severity value is 'error';
                •  CodeMinor value is 'invalid_filter_field';
                •  StatusCode value is the corresponding HTTP response code;
                •  Description should contain the supplied unknown field.
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_filter_field, ex.getMessage())));
    }

    @ExceptionHandler({InvalidDataException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse invalidDataException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            //TODO: Not sure what the rules of using this exception are, but it seems fitting when you can't filter on a specific field.
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_data, ex.getMessage())));
    }

    @ExceptionHandler({InvalidPagingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse pagingException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            I don't think IMS mentions this error, however infinite campus includes it.
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_data, ex.getMessage())));
    }

    @ExceptionHandler({InvalidSortingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse invalidSortingException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            I don't think IMS mentions this error, however infinite campus includes it.
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 400));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_data, ex.getMessage())));
    }

    /*
        401 - Handled In Security Authentication
        403 - Handled In Security Authentication
    */
    @ExceptionHandler({UnknownObjectException.class, NoHandlerFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) //404
    private ErrorResponse unknownObjectException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If a read request (from the service consumer) is issued on a 'sourcedId' before it has been assigned in the service provider then a
            failure/unknown error will occur. Once the 'sourcedId' has been assigned (for example using the OneRoster CSV Bulk Import) then a read
            request should be successful and the request object returned in the service payload.
         */
        //logger.warn(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 404));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.unknown_object, "Not Found - there is no resource behind the URI")));
    }

    @ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/**")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    ErrorResponse noHandlerFoundException(HttpServletRequest request, HttpServletResponse response) {
        /*
            This method is special in that it isn't an actual ExceptionHandler.
            For whatever reason, 404's are not caught when the path is not real. Ie: NoHandlerFoundException is thrown.
                ie: /orgs = good || /org = bad
        */
        //logger.warn(LogUtil.logStacktrace(environment, request, new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), null), Level.WARN, 404));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, CodeMinor.unknown_object, "Not Found - there is no resource behind the URI")));
    }


    /* 50X */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponse serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            Avoid - Use only if there is a catastrophic error and there is not a more appropriate code.
        */
        logger.error(LogUtil.logStacktrace(environment, request, ex, Level.WARN, 500));
        return new ErrorResponse(List.of(new Error(Severity.error, CodeMajor.failure, null, "Internal Server Error")));
    }
}