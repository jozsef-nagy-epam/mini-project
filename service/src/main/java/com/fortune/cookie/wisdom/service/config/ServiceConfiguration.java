package com.fortune.cookie.wisdom.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
