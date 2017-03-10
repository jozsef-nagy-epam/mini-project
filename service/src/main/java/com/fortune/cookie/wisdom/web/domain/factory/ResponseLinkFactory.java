package com.fortune.cookie.wisdom.web.domain.factory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.fortune.cookie.wisdom.web.controller.CategoriesRestController;
import com.fortune.cookie.wisdom.web.controller.WisdomListRestController;
import com.fortune.cookie.wisdom.web.controller.WisdomRestController;

@Component
public class ResponseLinkFactory {

	public Link getLinkForCategories(String rel) {
		return linkTo(methodOn(CategoriesRestController.class).getCategories()).withRel(rel);
	}

	public Link getLinkForWisdomList(String category, String rel) {
		return linkTo(methodOn(WisdomListRestController.class).getWisdomsByCategories(category)).withRel(rel);
	}

	public Link getLinkForWisdom(String category, Long id, String rel) {
		return linkTo(methodOn(WisdomRestController.class).getWisdomByCategoryAndId(category, id)).withRel(rel);
	}

}