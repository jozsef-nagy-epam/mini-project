package com.fortune.cookie.wisdom.service.domain;

public class Wisdom {
	private Long id;
	private String text;
	private String category;

	public Wisdom() {
	}

	public Wisdom(Long id, String text, String category) {
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

	public String getCategory() {
		return category;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
