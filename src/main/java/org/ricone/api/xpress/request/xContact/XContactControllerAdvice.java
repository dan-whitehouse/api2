package org.ricone.api.xpress.request.xContact;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XContactsResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XContacts:XContactControllerAdvice")
public class XContactControllerAdvice implements ResponseBodyAdvice<XContactsResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XContactsResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XContactsResponse beforeBodyWrite(XContactsResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXContacts() != null) {
			if(CollectionUtils.isNotEmpty(body.getXContacts().getXContact())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXContacts().getXContact().size()));
			}
		}
		return body;
	}
}