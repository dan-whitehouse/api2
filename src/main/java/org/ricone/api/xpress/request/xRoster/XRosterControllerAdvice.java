package org.ricone.api.xpress.request.xRoster;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XRostersResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XRosters:XRosterControllerAdvice")
public class XRosterControllerAdvice implements ResponseBodyAdvice<XRostersResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XRostersResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XRostersResponse beforeBodyWrite(XRostersResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXRosters() != null) {
			if(CollectionUtils.isNotEmpty(body.getXRosters().getXRoster())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXRosters().getXRoster().size()));
			}
		}
		return body;
	}
}