package com.getcat.api.service;

import com.getcat.api.model.Service;
import com.getcat.api.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;

    public List<Service> getAllServices(){
        return serviceRepo.findAll();
    }

}
