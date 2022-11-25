package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.Cart;
import com.fresco.springbootapp.models.CartProduct;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import com.fresco.springbootapp.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/consumer")
public class ConsumerController {

    private ConsumerService service;

    private UserRepo repo;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products() {
        return service.getAllProducts();
    }

    /**
     * It returns the cart of the current logged in user
     *
     * @return A cart object
     */
    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart() {
        return service.getCart();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(repo.findByUsername(user.getUsername()).get());
    }

    /**
     * It adds a product to the cart of the current logged in user
     *
     * @param product The product object that is to be added to the cart.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/cart")
    public void addProductToCart(@RequestBody Product product, HttpServletResponse response) {
        Boolean flag = service.addProductToCart(product);
        response.setStatus(flag ? HttpStatus.OK.value() : HttpStatus.CONFLICT.value());
    }

    /**
     * If the product is already in the cart, update the quantity, otherwise add the
     * product to the cart
     *
     * @param cartProduct This is the object that is sent from the frontend. It
     *                    contains the product and the quantity.
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cart")
    public void updateCart(@RequestBody CartProduct cartProduct) {

        service.updateCart(cartProduct);
    }

}
