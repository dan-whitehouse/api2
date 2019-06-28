package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Demographics:DemographicController")
@Swagger.Controller.Demographic
class DemographicController extends BaseController {
	private final DemographicService service;

	public DemographicController(DemographicService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/demographics/{id}")
	@Swagger.Operation.Demographic.GetDemographic /**/ @Swagger.Response.Demographic
	DemographicResponse getDemographic(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getDemographic(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/demographics")
	@Swagger.Operation.Demographic.GetAllDemographics /**/ @Swagger.Response.Demographics
	DemographicsResponse getAllDemographics(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllDemographics(getMetaData(request, response));
	}
}
