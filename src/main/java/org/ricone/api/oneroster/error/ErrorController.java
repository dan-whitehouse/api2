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
        StatusInfoSet error = new StatusInfoSet(CodeMajor.failure, Severity.error, null, null, "Not Found - there is no resource behind the URI", CodeMinor.unknown_object);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({BlankFieldSelectionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ErrorResponse blankFieldSelectionException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        StatusInfoSet error = new StatusInfoSet(CodeMajor.failure, Severity.error, null, null, "The fields parameter can not be blank", CodeMinor.invalid_blank_selection_field);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler({InvalidFilterFieldException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ErrorResponse invalidFilterFieldException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        StatusInfoSet error = new StatusInfoSet(CodeMajor.failure, Severity.error, null, null, "The filter parameter has non-existent fields", CodeMinor.invalid_blank_selection_field);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getStatusInfoSet().add(error);
        return errorResponse;
    }
}