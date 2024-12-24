package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Category;
import com.rkumarkravi.shopkro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Method to fetch all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Map<String, String>> getAllCategoryNames() {
        return categoryRepository.findAll().stream().map(x -> {
            Map<String, String> m = new HashMap<>();
            m.put("id", String.valueOf(x.getCategoryId()));
            m.put("name", x.getName());
            m.put("logo", x.getCategoryId() +".png");
            return m;
        }).collect(Collectors.toList());
    }

    // Method to fetch category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Method to create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Method to update an existing category
    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setCategoryId(id);
            return categoryRepository.save(category);
        } else {
            return null;  // or throw exception if category not found
        }
    }

    // Method to delete category by ID
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;  // or throw exception if category not found
        }
    }
}

