package org.ricone.api.oneroster.error;

import org.ricone.api.oneroster.error.exception.BlankFieldSelectionException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;
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

    @ResponseBody
    @ExceptionHandler({UnknownObjectException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private ErrorResponse unknownObjectException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If a read request (from the service consumer) is issued on a 'sourcedId' before it has been assigned in the service provider then a
            failure/unknown error will occur. Once the 'sourcedId' has been assigned (for example using the OneRoster CSV Bulk Import) then a read
            request should be successful and the request object returned in the service payload.
         */
        StatusInfoSet error = new StatusInfoSet(Severity.error, CodeMajor.failure, CodeMinor.unknown_object, "Not Found - there is no resource behind the URI");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({BlankFieldSelectionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ErrorResponse blankFieldSelectionException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        /*
            If the consumer requests that data be selected using a blank field the request will be treated as an invalid request.
			The server must provide the associated transaction status code information of:
				•  CodeMajor value is 'failure';
				•  Severity value is 'error';
				•  CodeMinor value is 'invalid_blank_selection_field';
				•  StatusCode value is the corresponding HTTP response code.
        */
        StatusInfoSet error = new StatusInfoSet(Severity.error, CodeMajor.failure, CodeMinor.invalid_blank_selection_field, "The fields parameter can not be blank");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({InvalidFilterFieldException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
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
        StatusInfoSet error = new StatusInfoSet(Severity.error, CodeMajor.failure, CodeMinor.invalid_filter_field, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }
}