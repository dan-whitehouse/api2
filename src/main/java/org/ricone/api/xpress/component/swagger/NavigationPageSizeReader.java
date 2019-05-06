package org.ricone.api.xpress.component.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1003)
public class NavigationPageSizeReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public NavigationPageSizeReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<SwaggerParam.NavigationPageSize> requestParam = methodParameter.findAnnotation(SwaggerParam.NavigationPageSize.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("header")
					.name("NavigationPageSize")
					.description("The navigationPageSize header allows you determine the number of results requested per page")
					.required(false)
					.order(1003)
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}