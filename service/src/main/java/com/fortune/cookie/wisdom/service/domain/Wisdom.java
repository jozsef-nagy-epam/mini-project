package com.fortune.cookie.wisdom.service.domain;

import javax.validation.constraints.NotNull;

public class Wisdom {
	private Long id;
	private String text;
	private String category;

	public Wisdom() {
	}

	public Wisdom(@NotNull final Long id, @NotNull final String text, @NotNull final String category) {
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

	public void setId(@NotNull final Long id) {
		this.id = id;
	}

	public void setText(@NotNull final String text) {
		this.text = text;
	}

	public void setCategory(@NotNull final String category) {
		this.category = category;
	}

	public static class WisdomBuilder {
		private Wisdom wisdom;

		public WisdomBuilder create() {
			this.wisdom = new Wisdom();
			return this;
		}

		public WisdomBuilder withId(@NotNull final Long id) {
			wisdom.setId(id);
			return this;
		}

		public WisdomBuilder withCategory(@NotNull final String category) {
			wisdom.setCategory(category);
			return this;
		}

		public WisdomBuilder withText(@NotNull final String text) {
			wisdom.setText(text);
			return this;
		}

		public Wisdom build() {
			Wisdom result = wisdom;
			wisdom = null;
			return result;
		}
	}
}
