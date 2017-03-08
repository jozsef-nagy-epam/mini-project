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
	public List<String> getCategories() {
		return wisdomSearchService.getCategories().stream().map(category -> category.getName())
				.collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public List<WisdomResponse> getWisdomsByCategories(@PathVariable("category") String category) {
		return wisdomSearchService.getWisdomsByCategory(category).stream().map(transformer::convert)
				.collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WisdomResponse getWisdomById(@PathVariable("category") String category, @PathVariable("id") Long id) {
		return transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id));
	}
}
