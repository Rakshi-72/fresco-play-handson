package com.fresco.springbootapp.service.imple;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import com.fresco.springbootapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return repo.findById(id).get();
    }
}
