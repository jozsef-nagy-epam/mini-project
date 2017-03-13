package com.fortune.cookie.wisdom.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.config.URLBuilder;
import com.fortune.cookie.wisdom.service.domain.CategoryList;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.domain.WisdomList;

@Service
public class RestTemplateBasedWisdomSearchService implements WisdomSearchService {

	private final URLBuilder repoData;
	private final RestTemplate restTemplate;

	@Autowired
	public RestTemplateBasedWisdomSearchService(URLBuilder repoData, RestTemplate restTemplate) {
		this.repoData = repoData;
		this.restTemplate = restTemplate;
	}

	@Override
	public Set<String> getCategories() {
		ResponseEntity<CategoryList> response = restTemplate.getForEntity(repoData.getCategoriesURI(),
				CategoryList.class);
		return response.getBody().getCategories();
	}

	@Override
	public Set<Wisdom> getWisdomsByCategory(String category) {
		ResponseEntity<WisdomList> response = restTemplate.getForEntity(repoData.getWisdomsByCategoryURI(category),
				WisdomList.class);
		return response.getBody().getWisdoms();
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		ResponseEntity<Wisdom> response = restTemplate
				.getForEntity(repoData.getWisdomByCategoryAndIdURI(category, wisdomId), Wisdom.class);
		return response.getBody();
	}

}
