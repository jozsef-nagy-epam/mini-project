package com.fortune.cookie.wisdom.web.domain.factory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.fortune.cookie.wisdom.web.controller.WisdomListRestController;
import com.fortune.cookie.wisdom.web.controller.WisdomRestController;
import com.fortune.cookie.wisdom.web.domain.CategoryResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;

@Component
public class ResponseLinkFactory {

	public CategoryResponse addLinkToResponse(CategoryResponse response) {
		response.addLink("wisdoms",
				linkTo(methodOn(WisdomListRestController.class).getWisdomsByCategories(response.getCategory()))
						.toUri());
		return response;
	}

	public WisdomResponse addLinkToResponse(WisdomResponse response) {
		response.addLink("self", linkTo(
				methodOn(WisdomRestController.class).getWisdomByCategoryAndId(response.getCategory(), response.getId()))
						.toUri());
		return response;
	}
}