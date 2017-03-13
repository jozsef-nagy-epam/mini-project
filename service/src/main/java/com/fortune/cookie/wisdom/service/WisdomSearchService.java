package com.fortune.cookie.wisdom.service;

import java.util.Set;

import com.fortune.cookie.wisdom.service.domain.Wisdom;

public interface WisdomSearchService {
	Set<String> getCategories();

	Set<Wisdom> getWisdomsByCategory(String category);

	Wisdom getWisdomByCategoryAndId(String category, Long wisdomId);
}
