package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/seller")
public class SellerController {

    private SellerService service;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {

        return service.getAllProducts();

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product) {

        service.updateProduct(product);

    }

    /**
     * If the product exists, delete it
     *
     * @param id The id of the product to be deleted.
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Integer id, HttpServletResponse response) {
        Boolean deleteProduct = service.deleteProduct(id);
        response.setStatus(deleteProduct ? 200 : 404);
    }
}
