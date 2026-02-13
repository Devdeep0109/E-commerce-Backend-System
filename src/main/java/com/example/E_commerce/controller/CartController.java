package com.example.E_commerce.controller;

import com.example.E_commerce.dto.cartDto.AddToCartRequestDto;
import com.example.E_commerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("/addtocart")
    public ResponseEntity<?> addProductToCart(@Valid @RequestBody AddToCartRequestDto cartRequest){
        return service.addProductToCart(cartRequest);
    }

    @GetMapping("/viewcart/{userid}")
    public ResponseEntity<?> viewCart(@PathVariable Long userid){
        return service.viewCart(userid);
    }

    @DeleteMapping("/removecartitem/{userId}")
    public  ResponseEntity<?> removeCartItemFromCart(@PathVariable Long userId){
        return service.removeCartItemById(userId);
    }
}
