package com.fortune.cookie.wisdom.service.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

@Component
@PropertySource(value = "classpath:repositoryURLs.properties")
public class RepositoryData {

	@Value("${getCategories}")
	private String categories;

	@Value("${getWisdomsByCategory}")
	private String wisdomsByCategory;

	@Value("${getWisdomsByCategoryAndId}")
	private String wisdomsByCategoryAndId;

	public URI getCategoriesURI() {
		UriTemplate uri = new UriTemplate(categories);
		return uri.expand();
	}

	public URI getWisdomsByCategoryURI(String category) {
		UriTemplate uri = new UriTemplate(wisdomsByCategory);
		Map<String, String> parameters = new HashMap<>();
		parameters.put("category", category);
		return uri.expand(parameters);
	}

	public URI getWisdomByCategoryAndIdURI(String category, Long id) {
		UriTemplate uri = new UriTemplate(wisdomsByCategoryAndId);
		Map<String, String> parameters = new HashMap<>();
		parameters.put("category", category);
		parameters.put("id", id.toString());
		return uri.expand(parameters);
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
