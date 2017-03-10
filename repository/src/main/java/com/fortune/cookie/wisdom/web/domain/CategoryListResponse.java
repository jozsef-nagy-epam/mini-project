package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryListResponse {
	@JsonProperty("categories")
	private final List<String> categories;

	public CategoryListResponse(List<String> categories) {
		super();
		this.categories = categories;
	}

}
