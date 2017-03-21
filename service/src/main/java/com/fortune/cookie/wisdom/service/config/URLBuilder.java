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
	private Map<String, String> parameters;

	public URLBuilder() {
		super();
		parameters = new HashMap<>();
	}

	public URI getCategoriesURI() {
		return new UriTemplate(categories).expand();
	}

	public URI getWisdomsByCategoryURI(String category) {
		UriTemplate uri = new UriTemplate(wisdomsByCategory);
		addParam("category", category);
		return expandURI(uri);
	}

	public URI getWisdomByCategoryAndIdURI(String category, Long id) {
		UriTemplate uri = new UriTemplate(wisdomsByCategoryAndId);
		addParam("category", category);
		addParam("id", id.toString());
		return expandURI(uri);
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

	private void addParam(String key, String value) {
		parameters.put(key, value);
	}

	private URI expandURI(UriTemplate template) {
		URI result = template.expand(parameters);
		parameters.clear();
		return result;
	}
}
