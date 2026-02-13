package com.example.E_commerce.service;


import com.example.E_commerce.dto.orderDto.CheckoutResponseDto;
import com.example.E_commerce.dto.orderDto.OrderResponseDto;
import com.example.E_commerce.model.User;
import com.example.E_commerce.model.cart.Cart;
import com.example.E_commerce.model.order.Order;
import com.example.E_commerce.model.order.OrderItem;
import com.example.E_commerce.model.order.OrderStatus;
import com.example.E_commerce.repo.CartRepo;
import com.example.E_commerce.repo.OrderRepo;
import com.example.E_commerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private CurrencyService currencyService;

    public CheckoutResponseDto checkoutOrder(Long userId,  String currency) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (!cart.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Cart does not belong to the user");
        }

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        //Calculate total INR amount
        BigDecimal totalAmount = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal rate = currencyService.getExchangeRate(currency);
        BigDecimal convertedAmount = totalAmount.multiply(rate);


        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setConvertedAmount(convertedAmount);
        order.setCurrency(currency);
        order.setStatus(OrderStatus.PLACED);


        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtTime(cartItem.getProduct().getPrice());
            return orderItem;
        }).toList();

        order.setOrderItemList(orderItems);


        Order savedOrder = orderRepository.save(order);


        cart.getCartItems().clear();
        cartRepository.save(cart);


        CheckoutResponseDto response = new CheckoutResponseDto();
        response.setOrderId(savedOrder.getId());
        response.setTotalAmount(totalAmount);
        response.setConvertedAmount(convertedAmount);
        response.setCurrency(currency);
        response.setStatus(savedOrder.getStatus().name());


        List<CheckoutResponseDto.OrderItemResponse> items = savedOrder.getOrderItemList().stream().map(item -> {
            CheckoutResponseDto.OrderItemResponse i = new CheckoutResponseDto.OrderItemResponse();
            i.setProductId(item.getProduct().getId());
            i.setProductName(item.getProduct().getName());
            i.setQuantity(item.getQuantity());
            i.setPriceAtTime(item.getPriceAtTime());
            return i;
        }).toList();

        response.setItems(items);

        return response;
    }

    public ResponseEntity<?> getAllOrder(Long userId) {

        List<Order> orderList = orderRepository.findByUserId(userId);

        if (orderList.isEmpty()) {
            throw new RuntimeException("No orders found for userId: " + userId);
        }

        List<OrderResponseDto> dtoList = orderList.stream()
                .map(this::mapToDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
    // Mapper method from Order to OrderResponseDto
    private OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setConvertedAmount(order.getConvertedAmount());
        dto.setCurrency(order.getCurrency());
        dto.setStatus(order.getStatus().name());

        List<OrderResponseDto.OrderItemResponse> items = order.getOrderItemList().stream()
                .map(item -> {
                    OrderResponseDto.OrderItemResponse i = new OrderResponseDto.OrderItemResponse();
                    i.setProductId(item.getProduct().getId());
                    i.setProductName(item.getProduct().getName());
                    i.setQuantity(item.getQuantity());
                    i.setPriceAtTime(item.getPriceAtTime());
                    return i;
                }).toList();

        dto.setItems(items);
        return dto;
    }
}


