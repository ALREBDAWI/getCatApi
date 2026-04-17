package com.getcat.api.service;

import com.getcat.api.model.User;
import com.getcat.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Integer id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    }

    public User saveUser(User user){
        // password hash logic to be added here
        return userRepo.save(user);
    }

    public void deleteUser(Integer id){
        userRepo.deleteById(id);
    }
}
