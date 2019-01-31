package org.ricone.api.oneroster.request.classes;

import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice
public class ClassControllerAdvice implements ResponseBodyAdvice<ClassesResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return ClassesResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public ClassesResponse beforeBodyWrite(ClassesResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getData() != null) {
			response.getHeaders().add("X-Total-Count", String.valueOf(body.getData().size()));
		}
		return body;
	}
}