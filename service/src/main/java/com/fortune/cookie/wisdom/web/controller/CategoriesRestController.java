package com.fortune.cookie.wisdom.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.domain.CategoryResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class CategoriesRestController {

	private final WisdomSearchService wisdomSearchService;

	private final WisdomToWisdomResponseTransformer transformer;

	@Autowired
	public CategoriesRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer transformer) {
		super();
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = transformer;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public List<AbstractResponse> getCategories() {
		return wisdomSearchService.getCategories().stream().map(CategoryResponse::new).collect(Collectors.toList());
	}
}
