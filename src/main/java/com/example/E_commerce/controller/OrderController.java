package com.example.E_commerce.controller;

import com.example.E_commerce.dto.orderDto.CheckoutRequestDto;
import com.example.E_commerce.dto.orderDto.CheckoutResponseDto;
import com.example.E_commerce.model.User;
import com.example.E_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutOrder(@RequestBody CheckoutRequestDto checkoutRequest){
        try {
            CheckoutResponseDto response = orderService.checkoutOrder(
                    checkoutRequest.getUserId(),
                    checkoutRequest.getCurrency()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Let global exception handler handle it if you have @ControllerAdvice
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("getOrders/{userId}")
    public ResponseEntity<?> getAllOrders(@PathVariable Long userId){
        return orderService.getAllOrder(userId);
    }
}
