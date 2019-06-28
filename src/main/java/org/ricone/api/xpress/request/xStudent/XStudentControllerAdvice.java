package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XStudents:XStudentControllerAdvice")
public class XStudentControllerAdvice implements ResponseBodyAdvice<XStudentsResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XStudentsResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XStudentsResponse beforeBodyWrite(XStudentsResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXStudents() != null) {
			if(CollectionUtils.isNotEmpty(body.getXStudents().getXStudent())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXStudents().getXStudent().size()));
			}
		}
		return body;
	}
}