package org.ricone.api.xpress.request.xCourse;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XCourses:XCourseControllerAdvice")
public class XCourseControllerAdvice implements ResponseBodyAdvice<XCoursesResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XCoursesResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XCoursesResponse beforeBodyWrite(XCoursesResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXCourses() != null) {
			if(CollectionUtils.isNotEmpty(body.getXCourses().getXCourse())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXCourses().getXCourse().size()));
			}
		}
		return body;
	}
}