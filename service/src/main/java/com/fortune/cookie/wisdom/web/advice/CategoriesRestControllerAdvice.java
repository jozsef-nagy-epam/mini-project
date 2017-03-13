package com.fortune.cookie.wisdom.web.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fortune.cookie.wisdom.web.controller.CategoriesRestController;
import com.fortune.cookie.wisdom.web.domain.CategoryListResponse;
import com.fortune.cookie.wisdom.web.factory.ResponseLinkFactory;

@ControllerAdvice(assignableTypes = CategoriesRestController.class)
public class CategoriesRestControllerAdvice implements ResponseBodyAdvice<CategoryListResponse> {

	private ResponseLinkFactory linkFactory;

	@Autowired
	public CategoriesRestControllerAdvice(ResponseLinkFactory linkFactory) {
		super();
		this.linkFactory = linkFactory;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public CategoryListResponse beforeBodyWrite(CategoryListResponse body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		body.getCategories().forEach(
				category -> category.addLink(linkFactory.getLinkForWisdomList(category.getCategory(), "wisdoms")));
		body.addLink(linkFactory.getLinkForCategories("self"));
		return body;
	}

}
