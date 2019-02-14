package org.ricone.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"org.ricone"})
@EntityScan( basePackages = {"org.ricone.api.core.model"} )
public class Init {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Init.class, args);
	}

	//https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui

	/*
		Multiple Specs: https://github.com/springfox/springfox/issues/748#issuecomment-104082075
		http://localhost:8080/docs/swagger.json?group=OneRoster
		http://localhost:8080/docs/swagger.json?group=XPress
	 */

	@Bean
	public Docket xPressAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
				.apis(RequestHandlerSelectors.basePackage("org.ricone.api.xpress"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(xPressMetaData())
				.groupName("xPress")
			.securitySchemes(getSecuritySchemes())
			.securityContexts(getSecurityContexts())
		;
	}

	@Bean
	public Docket oneRosterAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
				.apis(RequestHandlerSelectors.basePackage("org.ricone.api.oneroster"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(oneRosterMetaData())
				.groupName("OneRoster")
			.securitySchemes(getSecuritySchemes())
			.securityContexts(getSecurityContexts())
			/*.globalOperationParameters(
				newArrayList(new ParameterBuilder()
						.name("sort")
						.description("Description of someGlobalParameter")
						.modelRef(new ModelRef("string"))
						.parameterType("query")
						.required(false)
						.build()))
			*/
			;
	}

	private ApiInfo xPressMetaData() {
		return new ApiInfoBuilder()
			.title("xPress")
			.description("\"Spring Boot REST API for Online Store\"")
			.version("2.0.0")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
			.contact(new Contact("Dan Whitehouse", "https://springframework.guru/about/", "support@ricone.org"))
			.build();
	}

	private ApiInfo oneRosterMetaData() {
		return new ApiInfoBuilder()
			.title("One Roster")
			.description("\"Spring Boot REST API for Online Store\"")
			.version("v1p1")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
			.termsOfServiceUrl("https://www.imsglobal.org/oneroster-v11-final-specification")
			.contact(new Contact("Dan Whitehouse", "https://springframework.guru/about/", "support@ricone.org"))
			.build();
	}

	private List<ApiKey> getSecuritySchemes() {
		List<ApiKey> list = new ArrayList<>();
		list.add(new ApiKey("mykey", "api_key", "header"));
		return list;
	}

	private List<SecurityContext> getSecurityContexts() {
		List<SecurityContext> list = new ArrayList<>();
		list.add(SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.build());
		return list;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		List<SecurityReference> list = new ArrayList<>();
		list.add(new SecurityReference("mykey", authorizationScopes));
		return list;
	}
}