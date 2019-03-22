package org.ricone.api.oneroster.request.demographics;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster2:Demographics:DemographicController")
@Api(value = "Demographic", description = "One Roster - Demographics", tags = {"Demographic"})
class DemographicController extends BaseController {
	@Autowired
	private DemographicService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/demographics/{id}")
	@ApiOperation(value = "getDemographic", tags = {"Demographic"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = DemographicResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	DemographicResponse getDemographic(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getDemographic(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster2/v1p1/demographics")
	@ApiOperation(value = "getAllDemographics", tags = {"Demographic"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = DemographicsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	DemographicsResponse getAllDemographics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllDemographics(getMetaData(request, response));
	}
}
