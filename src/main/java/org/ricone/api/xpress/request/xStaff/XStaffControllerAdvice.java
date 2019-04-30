package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XStaffs:XStaffControllerAdvice")
public class XStaffControllerAdvice implements ResponseBodyAdvice<XStaffsResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XStaffsResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XStaffsResponse beforeBodyWrite(XStaffsResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXStaffs() != null) {
			if(CollectionUtils.isNotEmpty(body.getXStaffs().getXStaff())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXStaffs().getXStaff().size()));
			}
		}
		return body;
	}
}