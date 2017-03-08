package com.fortune.cookie.wisdom.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.service.domain.exception.CategoryDoesNotExistException;
import com.fortune.cookie.wisdom.service.domain.exception.WisdomDoesNotExistException;
import com.fortune.cookie.wisdom.web.domain.Response;
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
	public Response getCategories() {
		List<String> categories = wisdomSearchService.getCategories().stream().map(category -> category.getName())
				.collect(Collectors.toList());
		return categories.isEmpty() ? new Response(HttpStatus.NOT_FOUND, "There is no category")
				: new Response(categories);
	}

	@GetMapping("/categories/{category}")
	public Response getWisdomsByCategories(@PathVariable("category") String category) {
		Response response = null;
		try {
			response = new Response(wisdomSearchService.getWisdomsByCategory(category).stream()
					.map(transformer::convert).collect(Collectors.toList()));
		} catch (CategoryDoesNotExistException e) {
			response = new Response(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return response;
	}

	@GetMapping("/categories/{category}/{id}")
	public Response getWisdomById(@PathVariable("category") String category, @PathVariable("id") Long id) {
		Response response = null;
		try {
			response = new Response(transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id)));
		} catch (CategoryDoesNotExistException | WisdomDoesNotExistException e) {
			response = new Response(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return response;
	}
}
