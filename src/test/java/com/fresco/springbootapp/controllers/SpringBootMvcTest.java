package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class SpringBootMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;


    @Test
    public void testUserById() throws Exception {

        User user = new User(1, "rakshith", "123", null);
        when(service.findById(anyInt())).thenReturn(user);

        RequestBuilder builder = MockMvcRequestBuilders.get("/users/1")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(builder)
                .andExpect(content().json("{userName : rakshith, password : '123' }"))
                .andExpect(status().isOk())
                .andReturn();
    }

//    @Test
//    public void testSaveUser() throws Exception {
//        when(service.saveUser(any(User.class))).thenReturn(new User(1, "Rakshith", "123", null));
//
//        var post = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON).content("{userName : Rakshith, password : '1234' }")
//                .contentType(MediaType.APPLICATION_JSON);
//        mvc.perform(post).andExpect(status().isCreated())
//                .andExpect(content().json("{id: 5, userName : Rakshith, password : 12s34 }"))
//                .andReturn();
//    }
}
