package com.example.E_commerce.model.order;

import com.example.E_commerce.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private BigDecimal priceAtTime;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne

    @JoinColumn(name = "product_id")
    private Product product;


}
