package com.getcat.api.controller;

import com.getcat.api.model.User;
import com.getcat.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Integer id,
            @RequestBody User user
    ){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void softDeleteUser(@PathVariable Integer id){
        userService.softDeleteUser(id);
    }

    @DeleteMapping("/{id}/hard")
    public void hardDeleteUser(@PathVariable Integer id){
        userService.hardDeleteUser(id);
    }
}
