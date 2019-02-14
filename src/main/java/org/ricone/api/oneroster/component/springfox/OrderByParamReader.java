package org.ricone.api.oneroster.component.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.List;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1004)
public class OrderByParamReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public OrderByParamReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<OrderByParam> requestParam = methodParameter.findAnnotation(OrderByParam.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("query")
					.name("orderBy")
					.description("May be used in the request to ask for the collection to be returned in an ascending (asc) or descending (desc) order.")
					.allowableValues(new AllowableListValues(List.of("asc", "desc"), "string"))
					.required(false)
					.order(1004)
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}