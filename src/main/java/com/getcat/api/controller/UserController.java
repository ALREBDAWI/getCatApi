package com.getcat.api.controller;

import com.getcat.api.model.User;
import com.getcat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id)); //ResponseEntity.ok means if status is 200
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
