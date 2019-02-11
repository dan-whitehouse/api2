package org.ricone.api.oneroster.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.error.exception.*;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(basePackages = "org.ricone.api.oneroster.request")
@RestController("OneRosterErrorController")
public class ErrorController {
    private Logger logger = LogManager.getLogger(this.getClass());
    /*
        20X
        204 - Handled In GlobalController
    */

    /* 40X */
    @ResponseBody
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
        Error error = new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_blank_selection_field, "The fields parameter can not be blank");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }

    @ResponseBody
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
        Error error = new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_filter_field, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({InvalidPagingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse pagingException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            I don't think IMS mentions this error, however infinite campus includes it.
        */
        Error error = new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_data, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({InvalidSortingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //400
    private ErrorResponse invalidSortingException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            I don't think IMS mentions this error, however infinite campus includes it.
        */
        Error error = new Error(Severity.error, CodeMajor.failure, CodeMinor.invalid_data, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }

    /*
        401 - Handled In Security Authentication
        403 - Handled In Security Authentication
    */

    @ResponseBody
    @ExceptionHandler({UnknownObjectException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) //404
    private ErrorResponse unknownObjectException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If a read request (from the service consumer) is issued on a 'sourcedId' before it has been assigned in the service provider then a
            failure/unknown error will occur. Once the 'sourcedId' has been assigned (for example using the OneRoster CSV Bulk Import) then a read
            request should be successful and the request object returned in the service payload.
         */
        Error error = new Error(Severity.error, CodeMajor.failure, CodeMinor.unknown_object, "Not Found - there is no resource behind the URI");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }

    /* 50X */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponse serverError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            Avoid - Use only if there is a catastrophic error and there is not a more appropriate code.
        */
        Error error = new Error(Severity.error, CodeMajor.failure, null, "Internal Server Error");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(error);
        return errorResponse;
    }
}