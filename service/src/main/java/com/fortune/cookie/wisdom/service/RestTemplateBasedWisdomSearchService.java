package com.fortune.cookie.wisdom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.config.RepositoryData;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.transformer.ResponseToPojoTransformer;

@Service
public class RestTemplateBasedWisdomSearchService implements WisdomSearchService {

	private RepositoryData repoData;

	private RestTemplate restTemplate;

	private ResponseToPojoTransformer responseTransformer;

	@Autowired
	public RestTemplateBasedWisdomSearchService(RepositoryData repoData, RestTemplate restTemplate,
			ResponseToPojoTransformer responseTransformer) {
		this.repoData = repoData;
		this.restTemplate = restTemplate;
		this.responseTransformer = responseTransformer;
	}

	@Override
	public List<String> getCategories() {
		ResponseEntity<String> response = restTemplate.getForEntity(repoData.getCategoriesURI(), String.class);
		return responseTransformer.convertResponseToList(response.getBody(), String.class);
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(String category) {
		ResponseEntity<String> response = restTemplate.getForEntity(repoData.getWisdomsByCategoryURI(category),
				String.class);
		return responseTransformer.convertResponseToList(response.getBody(), Wisdom.class);
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		ResponseEntity<Wisdom> response = restTemplate
				.getForEntity(repoData.getWisdomByCategoryAndIdURI(category, wisdomId), Wisdom.class);
		return response.getBody();
	}

}
