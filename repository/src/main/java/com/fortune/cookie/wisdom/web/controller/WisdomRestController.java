package com.fortune.cookie.wisdom.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.CategoryListResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomListResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class WisdomRestController {

	private final WisdomSearchService wisdomSearchService;
	private final WisdomToWisdomResponseTransformer transformer;

	@Autowired
	public WisdomRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer transformer) {
		super();
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = transformer;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public CategoryListResponse getCategories() {
		List<String> categories = wisdomSearchService.getCategories().stream().map(category -> category.getName())
				.collect(Collectors.toList());
		return new CategoryListResponse(categories);
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public WisdomListResponse getWisdomsByCategories(@PathVariable("category") String category) {
		List<WisdomResponse> wisdoms = wisdomSearchService.getWisdomsByCategory(category).stream()
				.map(transformer::convert).collect(Collectors.toList());
		return new WisdomListResponse(wisdoms);
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WisdomResponse getWisdomById(@PathVariable("category") String category, @PathVariable("id") Long id) {
		WisdomResponse response = transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id));
		return response;
	}
}
