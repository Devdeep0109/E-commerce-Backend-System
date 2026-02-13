package com.example.E_commerce.service;

import com.example.E_commerce.dto.cartDto.AddToCartRequestDto;
import com.example.E_commerce.dto.cartDto.CartItemResponseDto;
import com.example.E_commerce.dto.cartDto.CartResponseDto;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.model.Product;
import com.example.E_commerce.model.User;
import com.example.E_commerce.model.cart.Cart;
import com.example.E_commerce.model.cart.CartItem;
import com.example.E_commerce.repo.CartItemRepo;
import com.example.E_commerce.repo.CartRepo;
import com.example.E_commerce.repo.ProductRepo;
import com.example.E_commerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    // add product to cart..
    public ResponseEntity<?> addProductToCart(AddToCartRequestDto cartRequest) {

        User user = userRepo.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + cartRequest.getUserId()));

        Product product = productRepo.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + cartRequest.getProductId()));

        Cart cart = cartRepo.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepo.save(newCart);
                });
        CartItem cartItem = cartItemRepo.findByCartAndProduct(cart, product).orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + cartRequest.getQuantity());
        }
        else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartRequest.getQuantity());
            cartItem.setPriceAtTime(product.getPrice());
        }

        cartItemRepo.save(cartItem);

        return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);

    }
//  .............................................................................
    // for viewing cart..
    public ResponseEntity<?> viewCart(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id " + userId));

        List<CartItemResponseDto> items = cart.getCartItems()
                .stream()
                .map(this::mapToCartItemResponse)
                .toList();

        BigDecimal totalAmount = items.stream()
                .map(CartItemResponseDto::getItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Long totalItems = items.stream()
                .mapToLong(CartItemResponseDto::getQuantity)
                .sum();

        CartResponseDto response = new CartResponseDto();
        response.setCartId(cart.getId());
        response.setUserId(user.getId());
        response.setCreatedAt(cart.getCreatedAt());
        response.setUpdatedAt(cart.getUpdatedAt());
        response.setItems(items);
        response.setTotalAmount(totalAmount);
        response.setTotalItems(totalItems);

        return ResponseEntity.ok(response);
    }

    //  MAPPING.....
    private CartItemResponseDto mapToCartItemResponse(CartItem item) {

        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setCartItemId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPriceAtTime(item.getPriceAtTime());
        dto.setQuantity(item.getQuantity());
        dto.setItemTotal(
                item.getPriceAtTime().multiply(BigDecimal.valueOf(item.getQuantity()))
        );
        return dto;

    }
    //...................................................................................
    // removing cart item from cart....
    public ResponseEntity<?> removeCartItemById(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id " + userId));

        CartItem item = cartItemRepo.findById(cart.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id " + cart.getId()));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new IllegalArgumentException("Cart item does not belong to this user");
        }

        cart.getCartItems().remove(item);
        cartRepo.save(cart);
        return ResponseEntity.ok("Cart item removed successfully");
    }
}
