package com.fresco.springbootapp.service;

import com.fresco.springbootapp.models.User;

import java.util.List;

// This is the service layer. It is the layer that is responsible for the business logic.
public interface UserService {

    /**
     * Save a user to the database.
     *
     * @param user The user object to be saved.
     * @return The user object that was saved.
     */
    User saveUser(User user);

    /**
     * It returns a User object with the given Id
     *
     * @param Id The id of the user you want to find.
     * @return A User object.
     **/
    List<User> getAllUsers();

    /**
     * This function returns a list of all users.
     *
     * @return A list of all users in the database.
     */
    User findById(Integer id);
}
