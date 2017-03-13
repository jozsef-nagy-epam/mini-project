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

	public static class WisdomBuilder {
		private Wisdom wisdom;

		public WisdomBuilder() {
			wisdom = new Wisdom();
		}

		public WisdomBuilder withId(Long id) {
			wisdom.setId(id);
			return this;
		}

		public WisdomBuilder withCategory(String category) {
			wisdom.setCategory(category);
			return this;
		}

		public WisdomBuilder withText(String text) {
			wisdom.setText(text);
			return this;
		}

		public Wisdom build() {
			Wisdom result = wisdom;
			wisdom = new Wisdom();
			return result;
		}
	}
}
