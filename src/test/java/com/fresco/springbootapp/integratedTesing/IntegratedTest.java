package com.fresco.springbootapp.integratedTesing;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegratedTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepo repo;

    @Test
    public void testApi() throws JSONException {

        String result = testRestTemplate.getForObject("/users/1", String.class);
        JSONAssert.assertEquals("{userId : 1, userName :  'jack'}", result, false);

        String savedData = testRestTemplate.postForObject("/users", new User(5, "Rakshith", "pass_word", null), String.class);
        JSONAssert.assertEquals("{userId:5, userName : 'Rakshith', password : 'pass_word'}", savedData, false);

        assertEquals(5, repo.findAll().size());
    }


}
