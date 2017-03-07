package com.fortune.cookie.wisdom.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.domain.CategoriesResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomListResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;

@RestController
@RequestMapping("/api")
public class WisdomRestController {

	private final WisdomSearchService wisdomSearchService;

	@Autowired
	public WisdomRestController(WisdomSearchService wisdomSearchService) {
		super();
		this.wisdomSearchService = wisdomSearchService;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getCategories() {
		return new CategoriesResponse(null);
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getWisdomsByCategories(@PathVariable("category") String category) {
		return new WisdomListResponse(null);
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getWisdomById(@PathVariable("category") String category, @PathVariable("id") Long id) {
		return new WisdomResponse(null, null, null);
	}
}
