package com.getcat.api.controller;

import com.getcat.api.model.Role;
import com.getcat.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
}
