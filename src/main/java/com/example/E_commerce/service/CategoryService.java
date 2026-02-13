package com.example.E_commerce.service;

import com.example.E_commerce.dto.categoryDto.CategoryRequestDto;
import com.example.E_commerce.dto.categoryDto.CategoryResponseDto;
import com.example.E_commerce.model.Category;
import com.example.E_commerce.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public ResponseEntity<?> createCategory(CategoryRequestDto categoryRequest) {

        if (repo.existsByName(categoryRequest.getName())) {
            return new ResponseEntity<>("Category with name '" +
                    categoryRequest.getName() + "' already exists!", HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category saved = repo.save(category);

        CategoryResponseDto response = new CategoryResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
