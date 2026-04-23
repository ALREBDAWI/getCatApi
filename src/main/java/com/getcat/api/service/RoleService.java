package com.getcat.api.service;

import com.getcat.api.model.Role;
import com.getcat.api.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }

    public Role getRoleById(Integer id){
        return roleRepo.findById(id).orElseThrow(()->new RuntimeException("Role not found with id: "+id));
    }

}
