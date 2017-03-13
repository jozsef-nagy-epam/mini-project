package com.fortune.cookie.wisdom.web.domain;

import javax.validation.constraints.NotNull;

public class WisdomResponse extends AbstractResponse {
	private final Long id;
	private final String text;
	private final String category;

	public WisdomResponse(@NotNull final Long id, @NotNull final String text, @NotNull final String category) {
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
