package com.fortune.cookie.wisdom.service.domain;

import java.util.List;

public class CategoryList {
	private List<String> categories;

	public CategoryList() {
	}

	public CategoryList(List<String> categories) {
		super();
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

	void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
