package com.fortune.cookie.wisdom.web.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.WisdomListResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class WisdomListRestController {
	private final WisdomSearchService wisdomSearchService;

	private final WisdomToWisdomResponseTransformer transformer;

	@Autowired
	public WisdomListRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer transformer) {
		super();
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = transformer;
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public WisdomListResponse getWisdomsByCategories(@PathVariable("category") String category) {
		return new WisdomListResponse(category, wisdomSearchService.getWisdomsByCategory(category).stream()
				.map(transformer::convert).collect(Collectors.toSet()));
	}
}
