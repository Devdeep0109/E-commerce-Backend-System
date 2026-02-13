package com.example.E_commerce.controller;

import com.example.E_commerce.dto.productDto.ProductRequestDto;
import com.example.E_commerce.dto.productDto.UpdateStockRequestDto;
import com.example.E_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDto productRequest){
        return service.createProduct(productRequest);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<?> updateStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateStockRequestDto updateStockRequest
    ) {
        return service.updateStock(productId, updateStockRequest);
    }
}
