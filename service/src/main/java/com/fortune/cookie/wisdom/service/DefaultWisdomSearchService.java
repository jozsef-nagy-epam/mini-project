package com.fortune.cookie.wisdom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.service.config.RepositoryData;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.transformer.ResponseToPojoTransformer;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {

	private final RepositoryData repoData;

	private final RestTemplate restTemplate;

	private final ResponseToPojoTransformer responseTransformer;

	@Autowired
	public DefaultWisdomSearchService(RepositoryData repoData, RestTemplate restTemplate,
			ResponseToPojoTransformer responseTransformer) {
		this.repoData = repoData;
		this.restTemplate = restTemplate;
		this.responseTransformer = responseTransformer;
	}

	@Override
	public List<String> getCategories() {
		ResponseEntity<String> response = restTemplate.getForEntity(repoData.getCategoriesURI(), String.class);
		return responseTransformer.convertResponseToWisdoms(response.getBody(), String.class);
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(String category) {
		ResponseEntity<String> resp = restTemplate.getForEntity(repoData.getWisdomsByCategoryURI(category),
				String.class);
		return responseTransformer.convertResponseToWisdoms(resp.getBody(), Wisdom.class);
	}

	@Override
	public Wisdom getWisdomById(String category, Long wisdomId) {
		ResponseEntity<Wisdom> wisdomResponse = restTemplate
				.getForEntity(repoData.getWisdomsByCategoryAndIdURI(category, wisdomId), Wisdom.class);
		return wisdomResponse.getBody();
	}

}
