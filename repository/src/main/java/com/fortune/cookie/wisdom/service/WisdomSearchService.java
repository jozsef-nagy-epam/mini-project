package com.fortune.cookie.wisdom.service;

import java.util.List;

import com.fortune.cookie.wisdom.service.domain.Category;
import com.fortune.cookie.wisdom.service.domain.Wisdom;

public interface WisdomSearchService {

	List<Category> getCategories();

	List<Wisdom> getWisdomsByCategory(Category category);

	Wisdom getWisdomById(Category category, Long wisdomId);
}
