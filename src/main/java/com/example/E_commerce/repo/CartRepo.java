package com.example.E_commerce.repo;

import com.example.E_commerce.model.User;
import com.example.E_commerce.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);

}
