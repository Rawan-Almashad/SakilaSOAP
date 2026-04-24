package org.iti.soap.service;

import org.iti.soap.dao.CategoryDao;
import org.iti.soap.dto.CategoryResponse;
import org.iti.soap.dto.CreateCategoryRequest;
import org.iti.soap.dto.UpdateCategoryRequest;
import org.iti.soap.entity.Category;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryResponse findById(Short id) {
        Category category = categoryDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return convertToResponse(category);
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryDao.findAll();
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setLastUpdate(Instant.now());

        Category savedCategory = categoryDao.save(category);
        return convertToResponse(savedCategory);
    }

    public CategoryResponse update(UpdateCategoryRequest request) {
        // First check if category exists
        Category existingCategory = categoryDao.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        // Update fields
        existingCategory.setName(request.getName());
        existingCategory.setLastUpdate(Instant.now());

        Category updatedCategory = categoryDao.update(existingCategory);
        return convertToResponse(updatedCategory);
    }

    public boolean deleteById(Short id) {
        // Check if category exists
        categoryDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryDao.deleteById(id);
    }

    // Helper method to convert Entity to Response DTO
    private CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setName(category.getName());

        // Convert Instant to String
        if (category.getLastUpdate() != null) {
            response.setLastUpdate(category.getLastUpdate().toString());
        }

        return response;
    }
}