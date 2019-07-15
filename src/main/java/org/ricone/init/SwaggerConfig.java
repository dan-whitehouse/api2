package org.ricone.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
	/*
		https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui
		Multiple Specs: https://github.com/springfox/springfox/issues/748#issuecomment-104082075
		http://localhost:8080/docs/swagger.json?group=OneRoster
		http://localhost:8080/docs/swagger.json?group=XPress
	 */

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

	@Bean //Swagger Related
	public Docket xPressAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.ricone.api.xpress"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(xPressMetaData())
				.groupName("xPress")
				//.securitySchemes(Collections.singletonList(apiKey()))
				.securitySchemes(getSecuritySchemes())
				.securityContexts(getSecurityContexts())
				;
	}

	@Bean //Swagger Related
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
				// .securitySchemes(Collections.singletonList(apiKey()))
				;
	}

	@Bean //Swagger Related
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
	}

	//Swagger Related
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

	//Swagger Related
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

	//Swagger Related
	private List<ApiKey> getSecuritySchemes() {
		List<ApiKey> list = new ArrayList<>();
		list.add(new ApiKey("Bearer", "Authorization", "header"));
		//list.add(new ApiKey("Test", "Authorization", "header"));
		return list;
	}

	//Swagger Related
	private List<SecurityContext> getSecurityContexts() {
		List<SecurityContext> list = new ArrayList<>();
		list.add(SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.build());
		return list;
	}

	//Swagger Related
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		List<SecurityReference> list = new ArrayList<>();
		list.add(new SecurityReference("Bearer", authorizationScopes));
		return list;
	}
}
