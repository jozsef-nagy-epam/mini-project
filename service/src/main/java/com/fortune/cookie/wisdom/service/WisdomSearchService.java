package com.fortune.cookie.wisdom.service;

import java.util.List;

import com.fortune.cookie.wisdom.service.domain.Wisdom;

// Are you sure lists are really lists and not Sets?
// Does the order of its elements matter?
public interface WisdomSearchService {
	List<String> getCategories();

	List<Wisdom> getWisdomsByCategory(String category);

	Wisdom getWisdomByCategoryAndId(String category, Long wisdomId);
}
