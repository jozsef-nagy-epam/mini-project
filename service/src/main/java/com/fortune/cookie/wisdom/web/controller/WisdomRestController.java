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
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.domain.CategoryResponse;
import com.fortune.cookie.wisdom.web.domain.factory.ResponseLinkFactory;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class WisdomRestController extends ResponseLinkFactory {

	private final WisdomSearchService wisdomSearchService;

	private final WisdomToWisdomResponseTransformer transformer;

	private final ResponseLinkFactory linkFactory;

	@Autowired
	public WisdomRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer wisdomToWisdomResponseTransformer, ResponseLinkFactory linkFactory) {
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = wisdomToWisdomResponseTransformer;
		this.linkFactory = linkFactory;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public List<AbstractResponse> getCategories() {
		return wisdomSearchService.getCategories().stream().map(CategoryResponse::new)
				.map(categoryResponse -> linkFactory.addLinkToResponse(categoryResponse)).collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public List<AbstractResponse> getWisdomsByCategories(@PathVariable("category") String category) {
		return wisdomSearchService.getWisdomsByCategory(category).stream().map(transformer::convert)
				.map(wisdomResponse -> linkFactory.addLinkToResponse(wisdomResponse)).collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getWisdomByCategoryAndId(@PathVariable("category") String category,
			@PathVariable("id") Long id) {
		return linkFactory
				.addLinkToResponse(transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id)));
	}

}
