package com.example.E_commerce.model.cart;

import com.example.E_commerce.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private BigDecimal priceAtTime;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
