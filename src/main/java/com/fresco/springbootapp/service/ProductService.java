package com.fresco.springbootapp.service;

import com.fresco.springbootapp.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> searchProducts(String key);
}
