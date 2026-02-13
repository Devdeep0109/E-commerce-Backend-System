package com.example.E_commerce.repo;

import com.example.E_commerce.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}
