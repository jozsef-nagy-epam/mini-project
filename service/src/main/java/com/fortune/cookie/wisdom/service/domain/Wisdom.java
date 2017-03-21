package com.fortune.cookie.wisdom.service.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Wisdom.Builder.class)
public class Wisdom {
	private final Long id;
	private final String text;
	private final String category;

	public Wisdom(Builder builder) {
		id = builder.id;
		text = builder.text;
		category = builder.category;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String text;
		private String category;

		@JsonProperty("id")
		public Builder withId(@NotNull final Long id) {
			this.id = id;
			return this;
		}

		@JsonProperty("category")
		public Builder withCategory(@NotNull final String category) {
			this.category = category;
			return this;
		}

		@JsonProperty("text")
		public Builder withText(@NotNull final String text) {
			this.text = text;
			return this;
		}

		public Wisdom build() {
			return new Wisdom(this);
		}
	}
}
