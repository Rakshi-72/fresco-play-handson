package com.fresco.springbootapp.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fresco.springbootapp.controllers.Utils;
import com.fresco.springbootapp.models.Cart;
import com.fresco.springbootapp.models.CartProduct;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.CartProductRepo;
import com.fresco.springbootapp.repo.CartRepo;
import com.fresco.springbootapp.repo.ProductRepo;
import com.fresco.springbootapp.service.ConsumerService;
import com.fresco.springbootapp.service.ProductService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private Utils utils;

    @Autowired
    private ProductService service;
    @Autowired
    private CartProductRepo cartProductRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> getCart() {
        User user = utils.getCurrentLoggedInUser();
        return new ResponseEntity<>(cartRepo.findByUser(user), HttpStatus.OK);
    }

    @Override
    public Boolean addProductToCart(Product product) {
        User user = utils.getCurrentLoggedInUser();
        Cart cart = cartRepo.findByUser(user);
        boolean flag = cart.getCartProduct().stream()
                .noneMatch(prod -> prod.getProduct().getProductId() == product.getProductId());
        if (flag) {
            CartProduct cp = new CartProduct();
            cp.setCart(cart);
            cp.setProduct(productRepo.save(product));
            cp.setQuantity(1);
            cartProductRepo.save(cp);
            return true;
        }
        return false;
    }

    @Override
    public void updateCart(CartProduct cartProduct) {
        User user = utils.getCurrentLoggedInUser();
        Cart cart = cartRepo.findByUser(user);
        Optional<CartProduct> cpOptional = cart.getCartProduct().stream()
                .filter(cp -> cp.getProduct().getProductId() == cartProduct.getProduct().getProductId())
                .findAny();

        if (cpOptional.isPresent()) {
            if (cartProduct.getQuantity() == 0) {
                cartProductRepo.deleteById(cpOptional
                        .orElseThrow(() -> new RuntimeException("cart product with the given id not found")).getCpId());
            } else {
                CartProduct cp = cpOptional
                        .orElseThrow(() -> new RuntimeException("cart product with the given id not found"));
                cp.setQuantity(cartProduct.getQuantity());
                cartProductRepo.save(cp);
            }
        } else {
            if (cartProduct.getQuantity() != 0) {
                Product product = cartProduct.getProduct();
                productRepo.save(product);
                cartProduct.setCart(cart);
                cartProductRepo.save(cartProduct);
            }
        }
    }

}