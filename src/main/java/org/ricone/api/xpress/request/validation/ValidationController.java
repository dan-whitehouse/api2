package org.ricone.api.xpress.request.validation;

import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.model.validation.Validation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

@RestController("RICOne:Validation:ValidationController")
@Swagger.Controller.ValidationController
public class ValidationController extends BaseController {
    private final ValidationService service;

    public ValidationController(ValidationService service) {this.service = service;}

    @GetMapping("/requests/validation/{refId}")
    public Validation getValidationByLeaRefId(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
        return service.findByLeaRefId(getMetaData(request, response), refId);
    }
}