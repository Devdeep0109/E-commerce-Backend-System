package com.example.E_commerce.controller;

import com.example.E_commerce.dto.categoryDto.CategoryRequestDto;
import com.example.E_commerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/createcategory")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequest){

        return service.createCategory(categoryRequest);
    }

}
