package org.ricone.api.xpress.component.swagger;

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
public class IdTypeReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public IdTypeReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<SwaggerParam.IdType> requestParam = methodParameter.findAnnotation(SwaggerParam.IdType.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("header")
					.name("IdType")
					.description("Can be used to override the default refId value")
					.allowableValues(new AllowableListValues(List.of(requestParam.get().values()), "string"))
					.required(requestParam.get().required())
					.order(1004)
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}

