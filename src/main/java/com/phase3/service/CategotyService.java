package com.phase3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phase3.model.Category;
import com.phase3.repository.CategoryRepository;

@Service
public class CategotyService {

	@Autowired
	private CategoryRepository categoryrepo;

	public List<Category> getAllCategory() {
		return categoryrepo.findAll();
	}

	public void addCategory(Category category) {
		categoryrepo.save(category);
	}

	public void removeCategoryById(int id) {
		categoryrepo.deleteById(id);
	}

	public Optional<Category> getCategoryById(int id) {
		return categoryrepo.findById(id);
	}
}
