package com.fortune.cookie.wisdom.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fortune.cookie.wisdom.service.domain.Category;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.domain.exception.CategoryDoesNotExistException;
import com.fortune.cookie.wisdom.service.domain.exception.WisdomDoesNotExistException;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {
	private final List<Wisdom> fakeWisdoms = Arrays.asList(new Wisdom(1L, "first wisdom", Category.GENERAL),
			new Wisdom(2L, "second wisdom", Category.PUN), new Wisdom(3L, "third wisdom", Category.FUTURE),
			new Wisdom(4L, "fourth wisdom", Category.GENERAL));

	@Override
	public List<Category> getCategories() {
		return Arrays.asList(Category.values());
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(String category) {
		Category categoryEnum = validateCategory(category);
		return fakeWisdoms.stream().filter(wisdom -> wisdom.getCategory().equals(categoryEnum))
				.collect(Collectors.toList());
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		Category categoryEnum = validateCategory(category);
		Optional<Wisdom> wisdom = fakeWisdoms.stream()
				.filter(w -> w.getCategory().equals(categoryEnum) && w.getId().equals(wisdomId)).findAny();
		if (!wisdom.isPresent()) {
			throw new WisdomDoesNotExistException("The wisdom with id(" + wisdomId + ") does not exist!");
		}
		return wisdom.get();
	}

	private Category validateCategory(String category) {
		Category categoryEnum = Category.getByName(category);
		if (categoryEnum == null) {
			throw new CategoryDoesNotExistException("the category(" + category + ") does not exist!");
		}
		return categoryEnum;
	}

}
