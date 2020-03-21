package com.mkern.infobip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowCredentials(true)
//				.allowedHeaders("User", "X-Requested-With", "Content-Type", "Accept", "Authorization",
//						"Origin", "Access-Control-Request-Method", "Access-Control-Allow-Origin")
				.allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
	}
}
