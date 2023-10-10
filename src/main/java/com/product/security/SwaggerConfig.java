package com.product.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public class SwaggerConfig
{
	private static final String AUTHORIZATION_HEADER= "Authorization";
//	private static final String DEFAULT_INCLUDE_PATTERN="/api/auth/*";
	
	private ApiKey apikey()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	
	private List<SecurityContext>  securityContext()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	
	private List<SecurityReference>  sf()
	{
		AuthorizationScope scope=new AuthorizationScope("global", "access Everythings");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	
	
	@Bean
    public Docket productApi() 
	{
		
	      return new Docket(DocumentationType.SWAGGER_2)
	    		       .apiInfo(getInfo())
		    		   .securityContexts(securityContext())
		    		   .securitySchemes(Arrays.asList(apikey()))
		    		   .select()
		               .apis(RequestHandlerSelectors.basePackage("com.product"))
		               .paths(PathSelectors.any())
		               .build();
	   }
	
	private ApiInfo getInfo()
	{
		
		return new ApiInfo("Backen Course", "This project is ProductSeller", "1.0", "Terms of Services", new Contact("Gaurav","https://learncodewithdurgesh","gdhumal263@gmail.com"), "License of APIS", "API license URL",Collections.emptyList());
	}
}
