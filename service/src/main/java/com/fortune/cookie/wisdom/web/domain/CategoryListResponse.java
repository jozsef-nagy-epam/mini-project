package com.fortune.cookie.wisdom.web.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "categories", "_links" })
public class CategoryListResponse extends AbstractResponse {
	@JsonProperty("categories")
	private final Set<CategoryResponse> categories;

	public CategoryListResponse(Set<CategoryResponse> categories) {
		super();
		this.categories = categories;
	}

	public Set<CategoryResponse> getCategories() {
		return categories;
	}

}
