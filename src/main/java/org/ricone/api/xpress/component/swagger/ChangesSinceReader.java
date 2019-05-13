package org.ricone.api.xpress.component.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.ricone.api.xpress.util.Util;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1001)
public class ChangesSinceReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public ChangesSinceReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<SwaggerParam.ChangesSince> requestParam = methodParameter.findAnnotation(SwaggerParam.ChangesSince.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("query")
					.name("changesSinceMarker")
					.description("description here....")
					.required(false)
					.order(1009)
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}