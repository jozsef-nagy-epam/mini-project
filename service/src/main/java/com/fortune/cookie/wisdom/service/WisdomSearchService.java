package com.fortune.cookie.wisdom.service;

import java.util.List;

import com.fortune.cookie.wisdom.service.domain.Wisdom;

public interface WisdomSearchService {
	List<String> getCategories();

	List<Wisdom> getWisdomsByCategory(String category);

	Wisdom getWisdomByCategoryAndId(String category, Long wisdomId);
}
