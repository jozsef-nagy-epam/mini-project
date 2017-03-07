package com.fortune.cookie.wisdom.service.domain;

public class Wisdom {
	private final Long id;
	private final String text;
	private final Category category;

	public Wisdom(Long id, String text, Category category) {
		this.id = id;
		this.text = text;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Category getCategory() {
		return category;
	}

}
