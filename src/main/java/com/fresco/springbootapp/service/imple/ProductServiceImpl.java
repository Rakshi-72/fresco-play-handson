package com.fresco.springbootapp.service.imple;

import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.repo.ProductRepo;
import com.fresco.springbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public List<Product> searchProducts(String key) {
        return repo.findByProductNameIgnoreCaseContaining(key);
    }
}
