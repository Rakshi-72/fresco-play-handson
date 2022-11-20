package com.fresco.springbootapp.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fresco.springbootapp.controllers.Utils;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.ProductRepo;
import com.fresco.springbootapp.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private ProductRepo repo;

    @Autowired
    private Utils utils;

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {

        User user = utils.getCurrentLoggedInUser();
        List<Product> products = repo.findBySeller(user)
                .orElseThrow(() -> new RuntimeException("no products available with the current seller"));
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        User user = utils.getCurrentLoggedInUser();
        List<Product> bySeller = repo.findBySeller(user)
                .orElseThrow(() -> new RuntimeException("no products available with the current seller"));
        Product product = bySeller.stream().filter(p -> p.getProductId() == id).findFirst().get();
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        product.setSeller(utils.getCurrentLoggedInUser());
        return ResponseEntity.ok(repo.save(product));

    }

    @Override
    public void updateProduct(Product product) {

        Product fromDataBase = repo.findById(product.getProductId())
                .orElseThrow(() -> new RuntimeException("user with the given id not present to update"));
        fromDataBase.setSeller(product.getSeller());
        fromDataBase.setCategory(product.getCategory());
        fromDataBase.setPrice(product.getPrice());
        fromDataBase.setProductName(product.getProductName());

        repo.save(fromDataBase);

    }

    @Override
    public Boolean deleteProduct(Integer id) {
        boolean isPresent = repo.findBySeller(utils.getCurrentLoggedInUser())
                .orElseThrow(() -> new RuntimeException("no products available with the current seller"))
                .stream()
                .map(Product::getProductId)
                .anyMatch(e -> e == id);
        if (isPresent) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}