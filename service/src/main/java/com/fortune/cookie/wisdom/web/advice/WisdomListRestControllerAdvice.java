package com.fortune.cookie.wisdom.web.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.hateoas.Link;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fortune.cookie.wisdom.web.controller.WisdomListRestController;
import com.fortune.cookie.wisdom.web.domain.WisdomListResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.domain.factory.ResponseLinkFactory;

@ControllerAdvice(assignableTypes = WisdomListRestController.class)
public class WisdomListRestControllerAdvice implements ResponseBodyAdvice<WisdomListResponse> {

	private ResponseLinkFactory linkFactory;

	@Autowired
	public WisdomListRestControllerAdvice(ResponseLinkFactory linkFactory) {
		super();
		this.linkFactory = linkFactory;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public WisdomListResponse beforeBodyWrite(WisdomListResponse body, MethodParameter returnType,
			org.springframework.http.MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		body.getWisdoms().forEach(wisdom -> wisdom.addLink(getLinkForWisdomResponse(wisdom)));
		body.addLink(linkFactory.getLinkForWisdomList(body.getCategory(), "self"));
		body.addLink(linkFactory.getLinkForCategories("root"));
		return body;
	}

	private Link getLinkForWisdomResponse(WisdomResponse wisdom) {
		return linkFactory.getLinkForWisdom(wisdom.getCategory(), wisdom.getId(), "next");
	}
}
