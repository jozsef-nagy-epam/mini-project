package com.fortune.cookie.wisdom.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.web.domain.exception.HandlerNotFoundException;

@RestController
public class NoMappingRestController {

	@RequestMapping(value = "/**")
	public void noHandlerMappingFound() throws HandlerNotFoundException {
		throw new HandlerNotFoundException("The requested url does not exist!");
	}
}
