package com.pooja.smartinventorysystem.service;

import com.pooja.smartinventorysystem.entity.Category;
import com.pooja.smartinventorysystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Add category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Delete category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
