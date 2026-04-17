package com.getcat.api.service;

import com.getcat.api.model.Role;
import com.getcat.api.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }

    public Role getRoleById(Integer id){
        return roleRepo.findById(id).orElseThrow(()->new RuntimeException("Role not found"));
    }

}
