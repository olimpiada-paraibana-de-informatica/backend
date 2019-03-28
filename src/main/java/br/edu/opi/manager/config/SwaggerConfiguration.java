package br.edu.opi.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

//	public static final Contact DEFAULT_CONTACT = new Contact(
//			"Responsável",
//			"https://responsavel.com",
//			"responsavel@email.com.br" );

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
			.title("OPI Manager API")
			.description("Manager to OPI")
			.version("0.0.1")
//			.termsOfServiceUrl("http://www.termosdeservico.com")
//			.license("License")
//			.licenseUrl("http://www.license.com")
			.build();

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));

	private static final ApiKey apiKey() {
		return new ApiKey("apiKey", "Authorization", "header");
	}

	private static final List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
	}

	private static final SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/api.*"))
				.build();
	}

	@Bean
	public Docket api() {
		final String REJECT_SLASH_AT_END = "/api.*[^//]$";
		return new Docket(DocumentationType.SWAGGER_2)
				.ignoredParameterTypes(ApiIgnore.class)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex(REJECT_SLASH_AT_END))
				.build()
				.pathMapping("/")
				.securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Arrays.asList(securityContext()))
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.apiInfo(DEFAULT_API_INFO);
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
//				.clientId(clientId)
//				.clientSecret(clientSecret)
				.realm("realm")
				.appName("opi-manager")
				.scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false)
				.build();
	}

}
