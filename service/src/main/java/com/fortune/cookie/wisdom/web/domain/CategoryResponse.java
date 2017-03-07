package com.fortune.cookie.wisdom.web.domain;

public class CategoryResponse extends AbstractResponse {
	private final String category;

	public CategoryResponse(String category) {
		super();
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

}
