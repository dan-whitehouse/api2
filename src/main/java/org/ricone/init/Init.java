package org.ricone.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	public CorsFilter corsFilter() {
		//https://stackoverflow.com/questions/51720552/enabling-cors-globally-in-spring-boot
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "SchoolYear", "IdType", "NavigationPage", "NavigationPageSize"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "HEAD", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				/*.deepLinking(true)
				.displayOperationId(false)
				.defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE)
				.filter(false)
				.maxDisplayedTags(null)*/
				.operationsSorter(OperationsSorter.ALPHA)
				/*.showExtensions(false)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)*/
				.build();
	}

	@Bean
	public Docket xPressAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
				.apis(RequestHandlerSelectors.basePackage("org.ricone.api.xpress"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(xPressMetaData())
				.groupName("xPress")
				.securitySchemes(Collections.singletonList(apiKey()))
			//.securitySchemes(getSecuritySchemes())
			//.securityContexts(getSecurityContexts())
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
			//.securitySchemes(getSecuritySchemes())
			//.securityContexts(getSecurityContexts())
				.securitySchemes(Collections.singletonList(apiKey()))
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
			.description("\"A description goes here...\"")
			.version("2.0.0")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
			.contact(new Contact("RIC One", "http://www.ricone.org/", "support@ricone.org"))
			.build();
	}

	private ApiInfo oneRosterMetaData() {
		return new ApiInfoBuilder()
			.title("One Roster")
			.description("\"A description goes here...\"")
			.version("v1p1")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
			//.termsOfServiceUrl("https://www.imsglobal.org/oneroster-v11-final-specification")
			.contact(new Contact("RIC One", "http://www.ricone.org/", "support@ricone.org"))
			.build();
	}

	private ApiKey apiKey() {
		//`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
		return new ApiKey("Bearer", "Authorization", "header");
	}

	/*private List<ApiKey> getSecuritySchemes() {
		List<ApiKey> list = new ArrayList<>();
		list.add(new ApiKey("Bearer", "Authorization", "header"));
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
		list.add(new SecurityReference("Bearer", authorizationScopes));
		return list;
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId("test-app-client-id")
				.clientSecret("test-app-client-secret")
				.realm("test-app-realm")
				.appName("test-app")
				.scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false)
				.build();
	}*/
}