package com.fresco.springbootapp.repository;

import com.fresco.springbootapp.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RepoTest {

    @Autowired
    private UserRepo repo;

    @Test
    public void testFindAll() {

        assertEquals(4, repo.findAll().size());
        
    }

}
