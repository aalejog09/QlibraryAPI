package com.api.qlibrary.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Clase de configuracion para exponer la documentacion del API basado en Swagger.
 * 
 * @author AAlejo
 * */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	 Docket api() {
	  return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
			  .build().apiInfo(apiInfoMetaData()).securityContexts(Arrays.asList(securityContext()))
             .securitySchemes(Arrays.asList(apiKey()));
	    }

	    private ApiInfo apiInfoMetaData() {

	        return new ApiInfoBuilder().title("Qlibrary - API Documentation")
	                .description("Dar a conocer los distintos servicios ofrecidos por el API del <Strong>Qlibrary</Strong>")
	                .contact(new Contact("Ashley Alejo", "https://github.com/aalejog09", "aalejog09@gmail.com"))
	                .license("Apache 2.0")
	                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                .version("1.1.1")
	                .build();
	    }
	    
	    private ApiKey apiKey() {
	        return new ApiKey("Bearer ","Authorization","header");
	    }
	    private SecurityContext securityContext() {
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }
	    private List<SecurityReference> defaultAuth(){
	        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("Bearer ", authorizationScopes));
	    }
	 
}
