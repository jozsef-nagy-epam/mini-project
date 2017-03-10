package com.fortune.cookie.wisdom.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {

	@Bean
	// please preserve the prefix get / set for getters, setters
    // I'd suggest create or configure instead
    // NB! with the @Bean annotation the name of the bean will be the same as the name of the method
    // please override the default bean name with a meaningful one via @Bean("beanName")
    // If there are some headers set for every rest call (e.g.: content-type, x-clientId, ...)
    // you could instead create a rest template instance that does exactly that automatically
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
