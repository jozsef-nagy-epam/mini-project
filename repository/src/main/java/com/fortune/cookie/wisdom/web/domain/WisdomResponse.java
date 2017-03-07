package com.fortune.cookie.wisdom.web.domain;

public class WisdomResponse {
	private final Long id;
	private final String text;
	private final String category;

	public WisdomResponse(Long id, String text, String category) {
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

}
