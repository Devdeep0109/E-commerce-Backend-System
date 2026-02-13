package com.example.E_commerce.service;

import com.example.E_commerce.dto.productDto.ProductRequestDto;
import com.example.E_commerce.dto.productDto.ProductResponseDto;
import com.example.E_commerce.dto.productDto.UpdateStockRequestDto;
import com.example.E_commerce.model.Category;
import com.example.E_commerce.model.Product;
import com.example.E_commerce.repo.CategoryRepo;
import com.example.E_commerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<?> createProduct(ProductRequestDto productRequest) {

        Category category = categoryRepo.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id " + productRequest.getCategoryId()));


        if (productRepo.existsByName(productRequest.getName())) {
            throw new RuntimeException("Product with name '" + productRequest.getName() + "' already exists!");
        }

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setCategory(category);
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());

        productRepo.save(product);

        ProductResponseDto res= new ProductResponseDto();
        res.setId(product.getId());
        res.setName(product.getName());
        res.setDescription(product.getDescription());
        res.setPrice(product.getPrice());
        res.setStockQuantity(product.getStockQuantity());
        res.setCreatedAt(product.getCreatedAt());
        res.setCategoryId(category.getId());
        res.setCategoryName(category.getName());

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    public ResponseEntity<?> updateStock(Long productId, UpdateStockRequestDto updateStockRequest) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id " + productId)
                );

        product.setStockQuantity(updateStockRequest.getStockQuantity());
        productRepo.save(product);

        return ResponseEntity.ok("Stock updated successfully");
    }

}
