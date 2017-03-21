package com.fortune.cookie.wisdom.service.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

@Component
public class URLBuilder {

	@Value("${getCategories}")
	private String categories;
	@Value("${getWisdomsByCategory}")
	private String wisdomsByCategory;
	@Value("${getWisdomsByCategoryAndId}")
	private String wisdomsByCategoryAndId;

	public URLBuilder() {
		super();
	}

	public URI getCategoriesURI() {
		return new UrlPreparer(categories).build();
	}

	public URI getWisdomsByCategoryURI(String category) {
		return new UrlPreparer(wisdomsByCategory).addParam("category", category).build();
	}

	public URI getWisdomByCategoryAndIdURI(String category, Long id) {
		return new UrlPreparer(wisdomsByCategoryAndId).addParam("category", category).addParam("id", id.toString())
				.build();
	}

	String getCategories() {
		return categories;
	}

	void setCategories(String categories) {
		this.categories = categories;
	}

	String getWisdomsByCategory() {
		return wisdomsByCategory;
	}

	void setWisdomsByCategory(String wisdomsByCategory) {
		this.wisdomsByCategory = wisdomsByCategory;
	}

	String getWisdomsByCategoryAndId() {
		return wisdomsByCategoryAndId;
	}

	void setWisdomsByCategoryAndId(String wisdomsByCategoryAndId) {
		this.wisdomsByCategoryAndId = wisdomsByCategoryAndId;
	}

	private class UrlPreparer {
		private final UriTemplate template;
		private final Map<String, String> params;

		public UrlPreparer(String url) {
			template = new UriTemplate(url);
			params = new HashMap<>();
		}

		public UrlPreparer addParam(String key, String value) {
			params.put(key, value);
			return this;
		}

		public URI build() {
			return template.expand(params);
		}

	}

}
