package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

public class CategoriesResponse extends AbstractResponse {
	private final List<String> categories;

	public CategoriesResponse(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

}
