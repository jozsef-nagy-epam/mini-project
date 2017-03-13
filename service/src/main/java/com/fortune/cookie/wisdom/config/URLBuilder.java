package com.fortune.cookie.wisdom.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

@Component
public class URLBuilder {

	@Value("${rootUrl}")
	private String rootUrl;
	@Value("${getCategories}")
	private String categories;
	@Value("${getWisdomsByCategory}")
	private String wisdomsByCategory;
	@Value("${getWisdomsByCategoryAndId}")
	private String wisdomsByCategoryAndId;

	public URI getCategoriesURI() {
		UriTemplate uri = new UriTemplate(rootUrl + categories);
		return uri.expand();
	}

	public URI getWisdomsByCategoryURI(String category) {
		UriTemplate uri = new UriTemplate(rootUrl + wisdomsByCategory);
		Map<String, String> parameters = new HashMap<>();
		parameters.put("category", category);
		return uri.expand(parameters);
	}

	public URI getWisdomByCategoryAndIdURI(String category, Long id) {
		UriTemplate uri = new UriTemplate(rootUrl + wisdomsByCategoryAndId);
		Map<String, String> parameters = new HashMap<>();
		parameters.put("category", category);
		parameters.put("id", id.toString());
		return uri.expand(parameters);
	}

	String getRootUrl() {
		return rootUrl;
	}

	void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
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

}
