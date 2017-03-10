package com.fortune.cookie.wisdom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.config.RepositoryData;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;
import com.fortune.cookie.wisdom.service.transformer.ResponseToPojoTransformer;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {

	private RepositoryData repoData;

	private RestTemplate restTemplate;

	private ResponseToPojoTransformer responseTransformer;

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
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throwException(response);
		}
		return responseTransformer.convertResponseToWisdoms(response.getBody(), String.class);
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(String category) {
		ResponseEntity<String> response = restTemplate.getForEntity(repoData.getWisdomsByCategoryURI(category),
				String.class);
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throwException(response);
		}
		return responseTransformer.convertResponseToWisdoms(response.getBody(), Wisdom.class);
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		ResponseEntity<Wisdom> response = restTemplate
				.getForEntity(repoData.getWisdomByCategoryAndIdURI(category, wisdomId), Wisdom.class);
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throwException(response);
		}
		return response.getBody();
	}

	private void throwException(ResponseEntity response) {
		throw new RepositoryException(response.getBody().toString(), response.getStatusCode());
	}

}
