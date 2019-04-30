package org.ricone.api.xpress.request.xLea;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XLeasResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XLeas:XLeaControllerAdvice")
public class XLeaControllerAdvice implements ResponseBodyAdvice<XLeasResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XLeasResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XLeasResponse beforeBodyWrite(XLeasResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXLeas() != null) {
			if(CollectionUtils.isNotEmpty(body.getXLeas().getXLeas())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXLeas().getXLeas().size()));
			}
		}
		return body;
	}
}