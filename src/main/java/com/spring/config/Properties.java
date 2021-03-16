package com.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class Properties {
	
	private final Swagger swagger = new Swagger();
	
	@Getter
	@Setter
	public static class Swagger {
		private String title = "Application API";
		private String description = "API Documentation";
		private String version = "1.0.0";
		private String host;
		private String[] protocols = {};
		private boolean useDefaultResponseMessages = true;
		private String defaultIncludePattern = "/api/.*";
	}

}
