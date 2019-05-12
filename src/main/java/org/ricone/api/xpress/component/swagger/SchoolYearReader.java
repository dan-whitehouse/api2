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
public class SchoolYearReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public SchoolYearReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<SwaggerParam.SchoolYear> requestParam = methodParameter.findAnnotation(SwaggerParam.SchoolYear.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("header")
					.name("SchoolYear")
					.description("The current school year of the response data")
					.required(false)
					.order(1001)
					.defaultValue(Integer.toString(Util.getCurrentSchoolYear()))
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}