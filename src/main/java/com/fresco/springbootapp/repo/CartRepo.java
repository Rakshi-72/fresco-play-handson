package com.fresco.springbootapp.repo;

import com.fresco.springbootapp.models.Cart;
import com.fresco.springbootapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findByUser(User user);

}
