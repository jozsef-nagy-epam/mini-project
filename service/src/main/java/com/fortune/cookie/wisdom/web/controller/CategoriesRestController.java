package com.fortune.cookie.wisdom.web.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.CategoryListResponse;
import com.fortune.cookie.wisdom.web.domain.CategoryResponse;

@RestController
@RequestMapping("/api")
public class CategoriesRestController {

	private final WisdomSearchService wisdomSearchService;

	@Autowired
	public CategoriesRestController(WisdomSearchService wisdomSearchService) {
		super();
		this.wisdomSearchService = wisdomSearchService;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public CategoryListResponse getCategories() {
		CategoryListResponse categories = new CategoryListResponse(
				wisdomSearchService.getCategories().stream().map(CategoryResponse::new).collect(Collectors.toSet()));
		return categories;
	}
}
