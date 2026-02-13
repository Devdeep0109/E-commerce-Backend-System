package com.example.E_commerce.repo;

import com.example.E_commerce.model.Product;
import com.example.E_commerce.model.cart.Cart;
import com.example.E_commerce.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
