package com.fresco.springbootapp.repo;

import com.fresco.springbootapp.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {

}
