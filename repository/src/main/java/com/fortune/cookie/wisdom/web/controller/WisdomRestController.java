package com.fortune.cookie.wisdom.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
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
	public List<String> getCategories() {
		return Arrays.asList("general", "future", "pull");
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public List<WisdomResponse> getWisdomsByCategories(@PathVariable("category") String category) {
		return Arrays.asList(new WisdomResponse(1L, "first wisdom", "general"),
				new WisdomResponse(1L, "second wisdom", "future"));
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WisdomResponse getWisdomById(@PathVariable("category") String category, @PathVariable("id") Long id) {
		return new WisdomResponse(1L, "first wisdom", "general");
	}
}
