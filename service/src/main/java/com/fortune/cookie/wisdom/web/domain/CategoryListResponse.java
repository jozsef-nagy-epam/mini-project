package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "categories", "_links" })
public class CategoryListResponse extends AbstractResponse {
	@JsonProperty("categories")
	private final List<CategoryResponse> categories;

	public CategoryListResponse(List<CategoryResponse> categories) {
		super();
		this.categories = categories;
	}

	public List<CategoryResponse> getCategories() {
		return categories;
	}

}
