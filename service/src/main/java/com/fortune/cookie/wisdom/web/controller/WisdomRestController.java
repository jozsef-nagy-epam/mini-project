package com.fortune.cookie.wisdom.web.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class WisdomRestController {

	private final WisdomSearchService wisdomSearchService;

	private final WisdomToWisdomResponseTransformer transformer;

	@Autowired
	public WisdomRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer wisdomToWisdomResponseTransformer) {
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = wisdomToWisdomResponseTransformer;
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
    // since addLink is a common theme for every controller method
    // and comes off a bit boilerplate-ish
    // you might want to create a @ConrtollerAdvice to add the links via some aop-magic
	public List<AbstractResponse> getCategories() {
	    // suggest formating:
		return wisdomSearchService.getCategories()
                .stream()
                .map(CategoryResponse::new)
                //.map(linkAppender::addLink)
				.map(categoryResponse -> addLinkToResponse(categoryResponse))
                .collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}")
	@ResponseStatus(HttpStatus.OK)
	public List<AbstractResponse> getWisdomsByCategories(@PathVariable("category") String category) {
		return wisdomSearchService.getWisdomsByCategory(category).stream().map(transformer::convert)
				.map(wisdomResponse -> addLinkToResponse(wisdomResponse)).collect(Collectors.toList());
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getWisdomByCategoryAndId(@PathVariable("category") String category,
			@PathVariable("id") Long id) {
		return addLinkToResponse(transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id)));
	}

	private CategoryResponse addLinkToResponse(CategoryResponse response) {
		response.addLink("wisdoms",
				linkTo(methodOn(WisdomRestController.class).getWisdomsByCategories(response.getCategory())).toUri());
		return response;
	}

	private WisdomResponse addLinkToResponse(WisdomResponse response) {
		response.addLink("self", linkTo(
				methodOn(WisdomRestController.class).getWisdomByCategoryAndId(response.getCategory(), response.getId()))
						.toUri());
		return response;
	}

}
