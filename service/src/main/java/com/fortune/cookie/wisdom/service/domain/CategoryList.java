package com.fortune.cookie.wisdom.service.domain;

import java.util.Set;

public class CategoryList {
	private Set<String> categories;

	public CategoryList() {
	}

	public CategoryList(Set<String> categories) {
		super();
		this.categories = categories;
	}

	public Set<String> getCategories() {
		return categories;
	}

	void setCategories(Set<String> categories) {
		this.categories = categories;
	}

}
