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
// Naming: to avoid the default prefix, you could for example call it remoteWisdomService
// in the conract of the interface there is nothing that would indicate that it is supposed to fetch
// wisdoms from a remote location. This is a specific of this concrete implementation, so this is something
// that you might want to indicate in its name
public class DefaultWisdomSearchService implements WisdomSearchService {
    // formatting: there is no need to separate the fields with empty lines
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
		// RestTemplate should be able to give you the proper class for the response without explicitly having to convert it
		ResponseEntity<String> response = restTemplate.getForEntity(repoData.getCategoriesURI(), String.class);
		return responseTransformer.convertResponseToWisdoms(response.getBody(), String.class);
	}

	@Override
    // IDE complains about unhandled exception
	public List<Wisdom> getWisdomsByCategory(String category) {
		ResponseEntity<String> resp = restTemplate.getForEntity(repoData.getWisdomsByCategoryURI(category),
				String.class);
		return responseTransformer.convertResponseToWisdoms(resp.getBody(), Wisdom.class);
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		ResponseEntity<Wisdom> wisdomResponse = restTemplate
				.getForEntity(repoData.getWisdomByCategoryAndIdURI(category, wisdomId), Wisdom.class);
		return wisdomResponse.getBody();
	}

}
