package com.getcat.api.controller;

import com.getcat.api.dto.UserRequestDTO;
import com.getcat.api.dto.UserResponseDTO;
import com.getcat.api.model.User;
import com.getcat.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    //ResponseEntity gives better http response control
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id)); //ResponseEntity.ok means if status is 200
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request){
        // http status if created with success = 201
        return new ResponseEntity<>(userService.createUser(request) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequestDTO request
    ){
        return ResponseEntity.ok(userService.updateUser(id, request)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Integer id){
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build(); // status 204
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDeleteUser(@PathVariable Integer id){
        userService.hardDeleteUser(id);
        return ResponseEntity.noContent().build(); // status 204
    }
}
