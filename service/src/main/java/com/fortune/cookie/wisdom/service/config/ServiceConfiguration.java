package com.fortune.cookie.wisdom.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {

	@Bean("customRestTemplate")
	public RestTemplate createRestTemplate() {
		RestTemplate rt = new RestTemplate();
		return rt;
	}
}
