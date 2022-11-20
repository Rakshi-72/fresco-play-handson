package com.fresco.springbootapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fresco.springbootapp.models.Cart;
import com.fresco.springbootapp.models.CartProduct;
import com.fresco.springbootapp.models.Product;

public interface ConsumerService {
    ResponseEntity<List<Product>> getAllProducts();

    ResponseEntity<Cart> getCart();

    Boolean addProductToCart(Product product);

    void updateCart(CartProduct cartProduct);

}