package com.mkern.infobip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Infobip Redis Service V" + getApiVersion())
				.apiInfo(getApiInfo()).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(Predicates.not(PathSelectors.regex("/error"))).build();
	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("Matej Kern", "", "matej.kern@gmail.com");
		return new ApiInfoBuilder().title("Infobip Redis Service REST api documentation")
				.description("Documentation of rest api for infobip-redis-service by Matej Kern ")
				.version(getApiVersion()).license("Apache 2.0 \n")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").contact(contact).build();
	}

	private String getApiVersion() {
		return "1.0.0";
	}

}
