package com.fortune.cookie.wisdom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.config.exceptionhandling.RepositoryResponseErrorHandler;

@Configuration
public class ServiceConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		rt.setErrorHandler(new RepositoryResponseErrorHandler());
		return rt;
	}
}
