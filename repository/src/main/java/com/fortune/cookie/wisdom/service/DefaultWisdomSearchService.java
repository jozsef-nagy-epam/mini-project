package com.fortune.cookie.wisdom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fortune.cookie.wisdom.service.domain.Category;
import com.fortune.cookie.wisdom.service.domain.Wisdom;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wisdom getWisdomById(Category category, Long wisdomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
