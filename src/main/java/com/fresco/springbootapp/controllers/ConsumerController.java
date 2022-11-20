package com.fresco.springbootapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.springbootapp.models.Cart;
import com.fresco.springbootapp.models.CartProduct;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.service.ConsumerService;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

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
