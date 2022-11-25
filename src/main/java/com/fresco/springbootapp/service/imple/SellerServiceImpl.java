package com.fresco.springbootapp.service.imple;

import com.fresco.springbootapp.controllers.Utils;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.ProductRepo;
import com.fresco.springbootapp.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {
    private ProductRepo repo;

    private Utils utils;

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
                .orElse(new Product());

        System.out.println(product);
        fromDataBase.setProductId(product.getProductId());
        fromDataBase.setSeller(product.getSeller());
        fromDataBase.setCategory(product.getCategory());
        fromDataBase.setPrice(product.getPrice());
        fromDataBase.setProductName(product.getProductName());
        fromDataBase.getCategory().setCategoryId(product.getCategory().getCategoryId());
        fromDataBase.setSeller(utils.getCurrentLoggedInUser());
        System.out.println(fromDataBase);

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